package com.geo.mvpframe_maters.network;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

import static com.geo.mvpframe_maters.constant.Constants.ACCOUNT_KEY;
import static com.geo.mvpframe_maters.constant.Constants.PASSWORD_KEY;

/**
 * @package: com.example.mymvpnetwork_master.baseFile
 * 创建人： created by zlj
 * 时间：2022/03/27 16
 */
public class BaseNetwork extends RetrofitUtils {

    /**by zlj
     * 插入观察者
     * @param observable
     * @param observer
     * @param <T>
     */
    public  <T> void setSubscribe(Observable<T> observable, Observer<T> observer) {
        observable
                .subscribeOn(Schedulers.io())//子线程访问网络
                .observeOn(AndroidSchedulers.mainThread())//回调到主线程
                .subscribe(observer);
    }

    /**
     * 网络访问头文件
     * @param timestamp
     * @param nonce
     * @param sign
     * @return
     */
    public Map<String,String> getHeader(String timestamp, String nonce, String sign) {
        Map<String,String> map =new HashMap<>();
        map.put("accessKey",ACCOUNT_KEY);
        map.put("secretAccessKey",PASSWORD_KEY);
        map.put("timestamp", timestamp);
        map.put("nonce",nonce );
        map.put("sign",sign);
        return map;
    }
    public int getRand(){

        int N = 99;

        Random rand = new Random();

        int randNum = rand.nextInt(N);
        return randNum;
    }
}
