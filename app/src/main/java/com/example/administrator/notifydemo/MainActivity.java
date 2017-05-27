package com.example.administrator.notifydemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_index,new NotifyFragment()).commit();

    }

    @Override
    protected void onResume() {
        getNotify(getIntent());
        super.onResume();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        getNotify(intent);
        setIntent(intent);
    }

    private void getNotify(Intent intent){
        String value = intent.getStringExtra("toValue");

        Log.i("TAG", "onNewIntent: " + value);

        if(!TextUtils.isEmpty(value)) {
            switch (value) {
                case "href":
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_index, new HrefFragment()).commitAllowingStateLoss();
                    //这里不是用的commit提交，用的commitAllowingStateLoss方式。commit不允许后台执行，不然会报Deferring update until onResume 错误
                    break;
            }
        }
        super.onNewIntent(intent);
    }
}
