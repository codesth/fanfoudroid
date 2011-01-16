package com.ch_linghu.fanfoudroid.helper;

import android.app.Activity;
import android.content.Intent;
import android.widget.Toast;

import com.ch_linghu.fanfoudroid.LoginActivity;
import com.ch_linghu.fanfoudroid.R;
import com.ch_linghu.fanfoudroid.weibo.HttpAuthException;
import com.ch_linghu.fanfoudroid.weibo.HttpRefusedException;
import com.ch_linghu.fanfoudroid.weibo.HttpServerException;
import com.ch_linghu.fanfoudroid.weibo.RefuseError;
import com.ch_linghu.fanfoudroid.weibo.WeiboException;

public class ExceptionHandler {
    
    private Activity mActivity;
    
    public ExceptionHandler(Activity activity) {
        mActivity = activity;
    }
    
    public void handle(WeiboException e) {
        
        Throwable cause = e.getCause();
        if (null == cause) return;
        
        // Handle Different Exception 
        
        if (cause instanceof HttpAuthException) {
            // 用户名/密码错误
            Toast.makeText(mActivity, R.string.error_not_authorized, Toast.LENGTH_LONG).show();
            Intent intent = new Intent(mActivity, LoginActivity.class);
            mActivity.startActivity(intent); // redirect to the login activity
        } else if (cause instanceof HttpServerException) {
            // 服务器暂时无法响应
            Toast.makeText(mActivity, R.string.error_not_authorized, Toast.LENGTH_LONG).show();
        } else if (cause instanceof HttpRefusedException) {
            // 服务器拒绝请求，如没有权限查看某用户信息
            RefuseError error = ((HttpRefusedException) cause).getError();
            switch (error.getErrorCode()) {
            // TODO: finish it
            case -1:
            default:
                Toast.makeText(mActivity, error.getMessage(), Toast.LENGTH_LONG).show();
                break;
            }
        }
        
    }
    
    private void handleCause() {
        
        
    }

}
