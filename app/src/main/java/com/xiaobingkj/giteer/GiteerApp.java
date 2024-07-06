package com.xiaobingkj.giteer;

import android.app.Application;
import android.util.Log;

import com.tencent.mmkv.MMKV;
import com.tencent.smtt.export.external.TbsCoreSettings;
import com.tencent.smtt.sdk.QbSdk;
import com.tencent.smtt.sdk.TbsListener;
import com.xiaobingkj.giteer.network.RxHttpManager;

import java.util.HashMap;

public class GiteerApp extends Application {
    public static final String TAG = "GiteerApp";
    @Override
    public void onCreate() {
        super.onCreate();

        //初始化RxHttp
        RxHttpManager.init(this);

        //初始化MMKV
        String rootDir = MMKV.initialize(this);
        System.out.println("MMKV根目录" + rootDir);

        // 在调用TBS初始化、创建WebView之前进行如下配置
        HashMap map = new HashMap();
        map.put(TbsCoreSettings.TBS_SETTINGS_USE_SPEEDY_CLASSLOADER, true);
        map.put(TbsCoreSettings.TBS_SETTINGS_USE_DEXLOADER_SERVICE, true);
        QbSdk.initTbsSettings(map);
        QbSdk.setDownloadWithoutWifi(true);
        /* SDK内核初始化周期回调，包括 下载、安装、加载 */
        QbSdk.setTbsListener(new TbsListener() {

            /**
             * @param stateCode 110: 表示当前服务器认为该环境下不需要下载
             */
            @Override
            public void onDownloadFinish(int stateCode) {
                Log.d(TAG, "onDownloadFinished: " + stateCode);
            }

            /**
             * @param stateCode 200、232安装成功
             */
            @Override
            public void onInstallFinish(int stateCode) {
                Log.d(TAG, "onInstallFinished: " + stateCode);
            }

            /**
             * 首次安装应用，会触发内核下载，此时会有内核下载的进度回调。
             * @param progress 0 - 100
             */
            @Override
            public void onDownloadProgress(int progress) {
                Log.d(TAG, "Core Downloading: " + progress);
            }
        });
        QbSdk.initX5Environment(this, new QbSdk.PreInitCallback() {
            @Override
            public void onCoreInitFinished() {
                // 内核初始化完成，可能为系统内核，也可能为系统内核
                Log.d(TAG, "onCoreInitFinished");
            }

            /**
             * 预初始化结束
             * 由于X5内核体积较大，需要依赖网络动态下发，所以当内核不存在的时候，默认会回调false，此时将会使用系统内核代替
             * @param isX5 是否使用X5内核
             */
            @Override
            public void onViewInitFinished(boolean isX5) {
                Log.d(TAG, "onViewInitFinished: " + isX5);
            }
        });
    }
}
