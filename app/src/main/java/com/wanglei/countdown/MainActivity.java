package com.wanglei.countdown;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @InjectView(R.id.inputtime)
    EditText inputtime;
    @InjectView(R.id.gettime)
    Button gettime;
    @InjectView(R.id.countdowm)
    TextView countdowm;
    @InjectView(R.id.start)
    Button start;
    @InjectView(R.id.off)
    Button off;
    private int time;
    private Timer timer;
    private TimerTask timerTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
    }

    @OnClick({R.id.gettime, R.id.start, R.id.off})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.gettime:
                countdowm.setText(inputtime.getText().toString());
                time = Integer.parseInt(inputtime.getText().toString());
                break;
            case R.id.start:
                start();
                break;
            case R.id.off:
                end();
                break;
        }
    }

    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
               countdowm.setText(msg.arg1+"");
            start();
        }
    };

    private void start(){
        timer = new Timer();
        timerTask = new TimerTask() {
            @Override
            public void run() {
                time--;
                Message msg = Message.obtain();
                msg.arg1=time;
                mHandler.sendMessage(msg);
            }
        };

        timer.schedule(timerTask,1000);
    }

    private void end (){

        timer.cancel();

    }
}
