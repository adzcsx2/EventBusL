package iim.ltd.eventbusl;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import hoyn.eventbusl.EventBus;
import hoyn.eventbusl.Subscribe;
import hoyn.eventbusl.ThreadMode;

public class MainActivity2 extends Activity {

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
                Intent intent = new Intent(MainActivity2.this,MainActivity3.class);
                startActivity(intent);
            }
        });
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void revTestBean(TestJavaBean testJavaBean){

    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
