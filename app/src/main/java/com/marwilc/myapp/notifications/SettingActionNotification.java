package com.marwilc.myapp.notifications;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.marwilc.myapp.view.MainActivity;

/**
 * Created by marwilc on 2/07/17.
 * Package name  com.marwilc.myapp.notifications
 * Time 15:39
 * Project  MyApp
 */

public class SettingActionNotification extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();


        switch (action) {
            case ActionKeys.ACTION_VIEW_MY_PROFILE:
               //initProfileUser(context,intent); // inicia el perfil del usuario
                break;

            case ActionKeys.ACTION_TAP_FOLLOW_UNFOLLOW:
                //initTapFollowUnFollow();
                break;

            case ActionKeys.ACTION_VIEW_USER:  //
                //initViewUser();
                break;
            default:
                break;
        }
    }

    private void initProfileUser(Context context, Intent intent) {
        intent = new Intent(context, MainActivity.class);
        intent.putExtra(MainActivity.PAGE_PROFILE, "dos");
        context.startActivity(intent);
    }
}
