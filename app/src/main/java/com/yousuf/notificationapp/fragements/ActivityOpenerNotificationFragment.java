package com.yousuf.notificationapp.fragements;


import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.core.app.NotificationCompat;
import androidx.fragment.app.Fragment;

import com.yousuf.notificationapp.R;
import com.yousuf.notificationapp.activities.SecondActivity;
import com.yousuf.notificationapp.models.MyConstants;

/**
 * A simple {@link Fragment} subclass.
 */
public class ActivityOpenerNotificationFragment extends Fragment {


    private static final String KEY_CHANNEL_ID = "chat-channel";
    private Context mContext;

    public ActivityOpenerNotificationFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View parentView = inflater.inflate(R.layout.fragment_single_button, container, false);

        Button btnShowNotification = parentView.findViewById(R.id.btn_show);

        btnShowNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showNotification();
            }
        });

        return parentView;
    }

    private void showNotification() {
        String msg = "تم أيداع مبلغ وقدرة 10,000 في حسابك\n";
        createNotificationChannel();
        NotificationCompat.Builder builder = new NotificationCompat.Builder(mContext, KEY_CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_notifications)
                .setContentTitle("Bank")
                .setContentText(msg)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        Intent intent = new Intent(mContext, SecondActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra(MyConstants.KEY_MASSAGE, msg);
        PendingIntent pendingIntent = PendingIntent.getActivity(mContext, 0, intent, PendingIntent.FLAG_ONE_SHOT);
        builder.setContentIntent(pendingIntent);
        NotificationManager notificationManager;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            notificationManager = mContext.getSystemService(NotificationManager.class);
        } else {
            notificationManager = (NotificationManager) mContext.getSystemService(Context.NOTIFICATION_SERVICE);
        }
        notificationManager.notify(0, builder.build());

    }

    private void createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "General";
            String description = "General Notification channel";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(KEY_CHANNEL_ID, name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = mContext.getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }


}
