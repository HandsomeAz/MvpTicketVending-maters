package com.geo.mvpframe_maters.activity.MainActivity.fragment;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import com.geo.mvpframe_maters.bean.DataBean;
import com.geo.mvpframe_maters.bean.PictureInfo;
import com.geo.mvpframe_maters.mvp.BaseMvpPresenter;
import com.geo.mvpframe_maters.network.BaseObserver;
import com.geo.mvpframe_maters.network.RequestBean;
import com.geo.mvpframe_maters.utils.BitmapUtil;
import com.geo.mvpframe_maters.utils.MyFileUtils;
import com.geo.mvpframe_maters.utils.ThreadPoolUtils;
import com.jeremyliao.liveeventbus.LiveEventBus;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import io.reactivex.disposables.Disposable;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.ResponseBody;


/**
 * @package: com.geo.mvpframe_maters.activity.MainActivity.fragment
 * 创建人： created by zlj
 * 时间：2022/06/21 20
 */
public class ImagePresenter extends BaseMvpPresenter<ImageContract.IView, ImageModel> implements ImageContract.IPresenter {
    private int count =0;
    @Override
    public void getAboveImage(String accountKey) {
        getView().showLoading();
        getModel().getAboveImage(accountKey, new BaseObserver<RequestBean<List<PictureInfo>>>() {
            @Override
            public void onDisposable(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onSuccess(RequestBean<List<PictureInfo>> requestBean) {
                getView().getImageSuccess(requestBean);
            }

            @Override
            public void onFailure(Throwable e) {
                getView().hideLoading();
            }
        });
    }

    @Override
    public void download(List<PictureInfo> data,int width,int height) {
        count =data.size();
        downloadCount =0;
        for (PictureInfo info :
                data) {

//            String[] s = info.getPictureUrl().split("/");
//            String imageName  = s[s.length-1];
//            // 保存人脸图片
//            File faceDir = MyFileUtils.getPictureDirectory();
//            File file = new File(faceDir, imageName);
//            getPic(info.getPictureUrl(),file,width,height);
            DownloadRunnable downloadRunnable = new DownloadRunnable(info,width,height);
            ThreadPoolUtils.execute(downloadRunnable);
        }
    }

    @Override
    public void getVideos() {
        List<String> videoList = DataBean.getManager().getVideoList();
        getView().setVideoList(videoList);
    }


    class DownloadRunnable implements Runnable {
        private PictureInfo pictureInfo;
        private int width =0,height =0;
        public DownloadRunnable(PictureInfo info, int width, int height) {
            this.pictureInfo = info;
            this.width =width;
            this.height = height;

        }

        @Override
        public void run() {
            getModel().downloadImage(pictureInfo.getPictureUrl(), new BaseObserver<ResponseBody>() {
                @Override
                public void onDisposable(Disposable d) {
                    addDisposable(d);
                }

                @Override
                public void onSuccess(ResponseBody responseBody) {
                    String[] s = pictureInfo.getPictureUrl().split("/");
                    String imageName  = s[s.length-1];
                    // 保存人脸图片
                    File faceDir = MyFileUtils.getPictureDirectory();
                    File file = new File(faceDir, imageName);
                    onSaveBitmap(responseBody,file,width,height);

                }

                @Override
                public void onFailure(Throwable e) {

                }
            });
        }
    }

    private int downloadCount =0;
    private void writeFile(ResponseBody responseBody, File filePath)  {

//        InputStream is = responseBody.byteStream();
//        int len = 0;
//
//        FileOutputStream fos = new FileOutputStream(filePath);
//        byte[] buf = new byte[2048];
//        while((len = is.read(buf))!= -1){
//            fos.write(buf,0,len);
//
//        }
//        fos.flush();
//        fos.close();
//        is.close();


        BufferedInputStream in = null;
        BufferedOutputStream out = null;
        try {
            in = new BufferedInputStream(responseBody.byteStream());
            out = new BufferedOutputStream(new FileOutputStream(filePath));

            byte[] bytes = new byte[2048];
            int len = 0;
            while ((len = in.read(bytes)) !=-1) {
                out.write(bytes, 0, len);
            }
            out.flush();


        } catch (IOException e) {
            e.printStackTrace();

        }
        finally {

            try {
                if (in!=null){
                    in.close();
                }
                if (out!=null){
                    out.close();
                }
                downloadCount++;
                if (count == downloadCount){
                    getView().downloadFinish();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void getPic(String url, File file, int width, int height) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                getModel().downloadImage(url, new BaseObserver<ResponseBody>() {
                    @Override
                    public void onDisposable(Disposable d) {
                        addDisposable(d);
                    }

                    @Override
                    public void onSuccess(ResponseBody responseBody) {
                        Log.d("下载图片长度", String.valueOf(responseBody.contentLength()));
                        writeFile(responseBody,file);

                    }

                    @Override
                    public void onFailure(Throwable e) {
                        Log.d("下载图片长度出错",e.toString());
                    }
                });


                //获取okHttp对象get请求
//                try {
//                    OkHttpClient client = new OkHttpClient();
//                    //获取请求对象
//                    Request request = new Request.Builder().url(url).build();
//                    //获取响应体
//                    ResponseBody body = client.newCall(request).execute().body();
//                    Log.d("下载图片长度", String.valueOf(body.contentLength()));
////                    writeFile(body,file);
//                    onSaveBitmap(body,file,width,height);
//
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }

            }
        }).start();

    }

    private void onSaveBitmap(Bitmap bitmap, File file,int width, int height) {
        if (bitmap!=null){
            Bitmap mBitmap = BitmapUtil.scale(bitmap,width,height);

            try {
                FileOutputStream fos = new FileOutputStream(file);
                mBitmap.compress(Bitmap.CompressFormat.JPEG, 90, fos);
                fos.flush();
                fos.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        downloadCount++;
        if (count == downloadCount){
            getView().downloadFinish();
        }
    }
    private void onSaveBitmap(ResponseBody responseBody, File file, int width, int height) {
         BitmapFactory.Options options = new BitmapFactory.Options();
         options.inJustDecodeBounds =true;

        //获取流
        InputStream in = responseBody.byteStream();
        //转化为bitmap
        Bitmap bitmap = BitmapFactory.decodeStream(in);
        Bitmap mBitmap = BitmapUtil.scale(bitmap,width,height);
        try {
            FileOutputStream fos = new FileOutputStream(file);
            mBitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);
            fos.flush();
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            downloadCount++;
            if (count == downloadCount){
                getView().downloadFinish();
            }
        }



    }


    private void saveBitmap(ResponseBody responseBody, File file, int width, int height){

        Bitmap bitmap = null;
        byte[] bys = new byte[0];
        try {
            bys = responseBody.bytes(); //注意：把byte[]转换为bitmap时，也是耗时操作，也必须在子线程
            bitmap = BitmapFactory.decodeByteArray(bys, 0, bys.length);
            saveFile(bitmap,file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //保存图片到SD卡
    private void saveFile(Bitmap bm, File file) throws IOException {
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file));
        bm.compress(Bitmap.CompressFormat.JPEG, 80, bos);
        bos.flush();
        bos.close();

    }
}
