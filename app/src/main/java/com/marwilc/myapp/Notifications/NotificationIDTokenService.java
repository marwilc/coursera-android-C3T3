package com.marwilc.myapp.Notifications;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

/**
 * Created by marwilc on 27/06/17.
 * Package name  com.marwilc.myapp.Notifications
 * Time 12:02
 * Project  MyApp
 */
public class NotificationIDTokenService extends FirebaseInstanceIdService {

    private static final String TAG = "FIREBASE_TOKEN";
    @Override
    public void onTokenRefresh() {
            //super.onTokenRefresh();
        String token = FirebaseInstanceId.getInstance().getToken();
        sendTokenRegister(token);
    }

    private void sendTokenRegister(String token){
        Log.d(TAG,token);
    }
}

