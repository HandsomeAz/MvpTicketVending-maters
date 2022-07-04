package com.geo.mvpframe_maters.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;

import com.jeremyliao.liveeventbus.LiveEventBus;

import java.io.InputStream;
import java.text.DecimalFormat;

import cn.mineki.CardReaders.FPRCardInfo;
import cn.mineki.CardReaders.GATCardInfo;
import cn.mineki.CardReaders.IDCardInfo;
import cn.mineki.CardReaders.IUsbReaderCallback;
import cn.mineki.CardReaders.UsbReader;
import cn.mineki.Utils.CertImageUtils;
import cn.mineki.Utils.Logs;

import static java.lang.Thread.sleep;

public class ReadIDUtil {
    private final String TAG = "ReadIDUtil_log";
    public boolean initReaderSuccess = false;//是否初始化成功
    private int nSlot = 1;
    private byte[] byLic = null;//授权信息
    private String LicPath = "mineki";//授权文件存放路径
    private String LicFilename = "license.bin";//授权文件名称
    private boolean isPrevModule = false;//模块版本标志
    private boolean isReading = false;//读卡判断标志

    private UsbReader idReader = null;
    private IDCardInfo idCardInfo;
    private ReadIDUtil(){
    }

    public static ReadIDUtil getInstance(){
        return ReadIDUtilHolder.instance;
    }

    private static class  ReadIDUtilHolder{
        private static final  ReadIDUtil instance = new ReadIDUtil();
    }

    private void initVar(Context context)
    {
        //关闭调试信息
        Logs.setsIsLogEnabled(false);

        //获取license.bin文件内容
        try {
            //从assets读license.bin文件
            InputStream inputStream = context.getResources().getAssets().open(LicFilename);
            byLic = new byte[inputStream.available()];
            inputStream.read(byLic);
        }catch (Exception e){
            e.printStackTrace();
            //从/sdcard/mineki读license.bin文件
//            FileUnits units = new FileUnits();
//            byLic = units.readSDFile(LicPath, LicFilename);
        }
    }

    private int errorType =0;
    public void initCardReader(Context context){
        if (initReaderSuccess){
            return;
        }
        if (idReader == null) {
            initVar(context);
            idReader = UsbReader.getInstance(context);
        }

        idReader.InitReader(byLic, new IUsbReaderCallback() {
            @Override
            public void ReaderInitSucc() {
                idReader.setLicense(byLic);
                initReaderSuccess = idReader.GetAct();
                ToastUtils.toast(context,"读卡器初始化成功");

            }

            @Override
            public void ReaderInitError() {
                initReaderSuccess = false;
                errorType =0;
                ToastUtils.toast(context,"读卡器初始化失败");

            }

            @Override
            public void UsbAttach() {
                ToastUtils.toast(context,"读卡器插入");
            }

            @Override
            public void UsbDeAttach() {
                ToastUtils.toast(context,"读卡器拔出了");
                initCardReader(context);
            }
        });
    }

    //读卡
    public void readIDCard(Context context) {
        if(!initReaderSuccess) {
            if (errorType==0){
                ToastUtils.toast(context,"读卡器初始化失败，请联系工作人员");
            }
            return;
        }

        if(isReading)   return;
        isReading = true;

        String[] sRet = new String[1];

        String sSamID = idReader.ReadSAMID(sRet);
        if(sSamID.indexOf("05.01") > 0|| sSamID.indexOf("05.02") > 0)
            isPrevModule = true;

        idCardInfo = null;

        new Thread(new Runnable() {
            @Override
            public void run() {
                int time = 20*1000;
                long start = System.currentTimeMillis();

                try {
                    while (idCardInfo == null && initReaderSuccess) {
                        long startTime = System.currentTimeMillis();
                        //idReader.ReadAllCardInfo(new String[1]);
                        idCardInfo = idReader.ReadBaseCardInfo(new String[1]);
                        long consumingTime = System.currentTimeMillis() - startTime;
                        //consumingTime -= 700;
                        if (idCardInfo != null) {

                            float distanceValue = consumingTime/1000f;
                            DecimalFormat decimalFormat =new DecimalFormat("##0.00");//构造方法的字符格式这里如果小数不足2位,会以0补足.
                            String distanceString = decimalFormat.format(distanceValue) + "s";//format 返回的是字符串
                            ToastUtils.toast(context,"身份证号读取成功");
                            LiveEventBus.get("idRead_success").post(idCardInfo);

                        } else if (System.currentTimeMillis() - start >= time) {//超时

                            ToastUtils.toast(context,"读取身份证超时");
                            break;
                        } else {
                            try {
                                sleep(100);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                } catch (Exception e) {

                    Log.e("", e.getMessage());
                } finally {
                    isReading = false;
                }
            }
        }).start();
    }

    public void releaseCardReader() {
        if (idReader != null) {
            idReader.ReleaseReader();
            idReader = null;
            initReaderSuccess = false;
        }
    }
}
