package iim.ltd.eventbusl;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import hoyn.eventbusl.EventBus;
import hoyn.eventbusl.Subscribe;
import hoyn.eventbusl.ThreadMode;

public class MainActivity3 extends Activity {

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
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void revWTF(TestJavaBean testJavaBean){

    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
