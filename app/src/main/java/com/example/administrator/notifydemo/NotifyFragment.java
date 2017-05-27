package com.example.administrator.notifydemo;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.media.MediaBrowserCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class NotifyFragment extends Fragment {

    private View view;

    private Button btnNotify;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle
            savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_content,container,false);

        btnNotify = (Button) view.findViewById(R.id.btn_notify);


        btnNotify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {

                    Intent intent = new Intent(getContext(),MainActivity.class);
                    //判断当前activity是否创建
//                    Intent intent = new Intent(Intent.ACTION_MAIN);
                    intent.putExtra("toValue","href");


//                    intent.addCategory(Intent.CATEGORY_LAUNCHER);
//
//                    intent.setComponent(new ComponentName(getContext(), MainActivity.class));
//
//                    intent.setFlags( Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);

                    PendingIntent pend = PendingIntent.getActivity(getContext(),201,intent,PendingIntent.FLAG_UPDATE_CURRENT);

                    Notification notify = new Notification.Builder(getContext())
                            .setWhen(System.currentTimeMillis())
                            .setSmallIcon(R.mipmap.ic_launcher)
                            .setContentTitle("这是标题")
                            .setContentText("这是内容")
                            .setContentIntent(pend)
                            .build();

                    notify.flags = Notification.FLAG_AUTO_CANCEL;

                    NotificationManager manager = (NotificationManager) getContext().getSystemService(Context.NOTIFICATION_SERVICE);

                    manager.notify(1,notify);

                }

            }
        });

        return view;
    }
}
