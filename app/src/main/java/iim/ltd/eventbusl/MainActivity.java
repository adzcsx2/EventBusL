package iim.ltd.eventbusl;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import hoyn.eventbusl.EventBus;
import hoyn.eventbusl.Subscribe;
import hoyn.eventbusl.ThreadMode;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EventBus.getDefault().register(this);
        findViewById(R.id.hello).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(new TestJavaBean());
            }
        });
        findViewById(R.id.next).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,MainActivity2.class);
                startActivity(intent);
            }
        });
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void revMessage(TestJavaBean testJavaBean){

    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e("aa","aaa");
        EventBus.getDefault().unregister(this);
    }
}
