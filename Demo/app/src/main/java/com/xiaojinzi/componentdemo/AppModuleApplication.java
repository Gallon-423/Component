package com.xiaojinzi.componentdemo;

import android.app.Application;
import androidx.annotation.NonNull;
import android.util.Log;

import com.xiaojinzi.component.anno.ModuleAppAnno;
import com.xiaojinzi.component.application.IApplicationLifecycle;
import com.xiaojinzi.component.application.IModuleNotifyChanged;
import com.xiaojinzi.component.impl.Router;
import com.xiaojinzi.component.impl.RouterErrorResult;
import com.xiaojinzi.component.impl.RouterListener;
import com.xiaojinzi.component.impl.RouterRequest;
import com.xiaojinzi.component.impl.RouterResult;
import com.xiaojinzi.component.support.LogUtil;
import com.xiaojinzi.component.support.Utils;

@ModuleAppAnno()
public class AppModuleApplication implements IApplicationLifecycle, IModuleNotifyChanged {

    private final String TAG = "自定义实现的路由监听";

    private RouterListener listener = new RouterListener() {

        @Override
        public void onSuccess(@NonNull RouterResult successResult) throws Exception {
            RouterRequest originalRequest = successResult.getOriginalRequest();
            Log.e(TAG, "路由成功：" + originalRequest.getUri().toString() + ",requestCode is " + (originalRequest.getRequestCode() == null ? "null" : originalRequest.getRequestCode()));
        }

        @Override
        public void onError(RouterErrorResult errorResult) throws Exception {
            RouterRequest originalRequest = errorResult.getOriginalRequest();
            if (originalRequest == null) {
                Log.e(TAG, "路由失败：errorMsg = " + Utils.getRealMessage(errorResult.getError()));
            } else {
                Log.e(TAG, "路由失败：" + originalRequest.getUri().toString()
                        + " ,errorMsg = " + Utils.getRealMessage(errorResult.getError())
                        + "\nrequestCode is " + (originalRequest.getRequestCode() == null ? "null" : originalRequest.getRequestCode())
                );
            }
        }

        @Override
        public void onCancel(@NonNull RouterRequest originalRequest) throws Exception {
            Log.e(TAG, "路由被取消：" + originalRequest.getUri().toString() + ",requestCode is " + (originalRequest.getRequestCode() == null ? "null" : originalRequest.getRequestCode()));
        }
    };

    @Override
    public void onCreate(@NonNull Application app) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            // ignore
        }
        Router.addRouterListener(listener);
    }

    @Override
    public void onDestroy() {
        Router.removeRouterListener(listener);
    }

    @Override
    public void onModuleChanged(@NonNull Application app) {
        LogUtil.loge("'模块发生变化啦");
    }

}
