package com.marwilc.myapp.notifications;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.media.AudioAttributes;
import android.media.RingtoneManager;
import android.net.Uri;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.marwilc.myapp.R;
import com.marwilc.myapp.view.MainActivity;

import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v4.app.NotificationCompat.WearableExtender;
import android.view.Gravity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by marwilc on 26/06/17.
 * Package name  com.marwilc.notificationfirebase
 * Time 10:36
 * Project  NotificacionFirebase
 */

public class NotificationService extends FirebaseMessagingService {

    private static final String TAG = "MyFirebaseMsgService";
    private static final int NOTIFICATION_ID = 001;

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        //super.onMessageReceived(remoteMessage);

        // [START_EXCLUDE]
        // There are two types of messages data messages and notification messages. Data messages are handled
        // here in onMessageReceived whether the app is in the foreground or background. Data messages are the type
        // traditionally used with GCM. Notification messages are only received here in onMessageReceived when the app
        // is in the foreground. When the app is in the background an automatically generated notification is displayed.
        // When the user taps on the notification they are returned to the app. Messages containing both notification
        // and data payloads are treated as notification messages. The Firebase console always sends notification
        // messages. For more see: https://firebase.google.com/docs/cloud-messaging/concept-options
        // [END_EXCLUDE]

        // TODO(developer): Handle FCM messages here.
        // Not getting messages here? See why this may be: https://goo.gl/39bRNJ
        String from = remoteMessage.getFrom();
        String body = remoteMessage.getNotification().getBody();
        Log.d(TAG, "From: " + from);
        Log.d(TAG, "Message Notification Body: " + body);

        launchNotification(remoteMessage);

        // Also if you intend on generating your own notifications as a result of a received FCM
        // message, here is where that should be initiated. See sendNotification method below.
    }

    public void launchNotification(RemoteMessage remoteMessage) {


        ArrayList<NotificationCompat.Action> actions = new ArrayList<>();



        Intent intentMyProfile  = new Intent(this, MainActivity.class);
        Intent intentFollowUser = new Intent(ActionKeys.ACTION_TAP_FOLLOW_UNFOLLOW);
        Intent intentVewUser    = new Intent(ActionKeys.ACTION_VIEW_USER);

        intentMyProfile.putExtra(ExtraKeys.KEY_EVENT_SETUP_VIEWPAGER, ExtraKeys.EVENT_ID);// envio de la configuracion del viewPager
        //intentMyProfile.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        PendingIntent pendingIntentProfile      = PendingIntent.getActivity(this, 0, intentMyProfile, PendingIntent.FLAG_ONE_SHOT);
        PendingIntent pendingIntentFollow       = PendingIntent.getBroadcast(this, 1, intentFollowUser, PendingIntent.FLAG_UPDATE_CURRENT);
        PendingIntent pendingIntentViewUser     = PendingIntent.getBroadcast(this, 2, intentVewUser, PendingIntent.FLAG_UPDATE_CURRENT);

        //pendingIntent = PendingIntent.getActivity(this,0,intentMyProfile,PendingIntent.FLAG_UPDATE_CURRENT);
        Uri sound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        // configurar la accion del wear
        NotificationCompat.Action.Builder actionMyProfile =
                new NotificationCompat.Action.Builder(R.drawable.ic_full_account_circle_white,
                        getString(R.string.text_action_go_home), pendingIntentProfile)
                        ;

        // configurar la accion del wear
        NotificationCompat.Action.Builder actionTapFollow =
                new NotificationCompat.Action.Builder(R.drawable.ic_full_account_circle_white,
                        getString(R.string.text_action_tap_user), pendingIntentFollow)
                        ;

        // configurar la accion del wear
        NotificationCompat.Action.Builder actionViewUser =
                new NotificationCompat.Action.Builder(R.drawable.ic_full_account_circle_white,
                        getString(R.string.text_action_view_user), pendingIntentViewUser)
                        ;



        // agrega las acciones a la lista de acciones

        // soporte para wear creando la notificacion
        NotificationCompat.WearableExtender wearableExtender =
                new NotificationCompat.WearableExtender()
                        .setHintHideIcon(true)
                        .addActions(actions)
                        .setContentAction(0)
                        ;

        //Android Wear requires a hint to display the reply action inline.
        NotificationCompat.Action.WearableExtender actionExtender =
                new NotificationCompat.Action.WearableExtender()
                        .setHintLaunchesActivity(true)
                        .setHintDisplayActionInline(true);

        wearableExtender.addAction(actionViewUser.extend(actionExtender).build());
        wearableExtender.addAction(actionTapFollow.extend(actionExtender).build());
        wearableExtender.addAction(actionMyProfile.extend(actionExtender).build());

        NotificationCompat.Builder notificationCompat =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.drawable.ic_stat_pet_notification)
                        .setContentTitle("Notification")
                        .setContentText(remoteMessage.getNotification().getBody())
                        .setSound(sound)
                        .setContentIntent(pendingIntentProfile)
                        .extend(wearableExtender)
                        ;


        // soporte para androidWear
        NotificationManagerCompat notificationManager =
                NotificationManagerCompat.from(this);
        notificationManager.notify(NOTIFICATION_ID, notificationCompat.build());
        // fin del soporte
    }
}
