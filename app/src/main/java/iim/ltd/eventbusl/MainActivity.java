package iim.ltd.eventbusl;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import hoyn.eventbusl.EventBus;
import hoyn.eventbusl.Subscribe;
import hoyn.eventbusl.ThreadMode;

public class MainActivity extends Activity {

    public static final String TAG = "MainActivity";
    public static Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.e(TAG,"onCreate");
        context = this;
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
                startActivityForResult(intent,0);
            }
        });
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void revMessage(TestJavaBean testJavaBean){

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.e("aaa","aaaa");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.e(TAG,"onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e(TAG,"onResume");
        Log.e("onCreate",hashCode()+"");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e(TAG,"onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.e(TAG,"onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e(TAG,"onDestroy");
        EventBus.getDefault().unregister(this);
    }
    public static void show(){
        Toast.makeText(MainActivity.context,"showSomeThing",Toast.LENGTH_SHORT).show();
    }
}
