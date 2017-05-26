package com.hongqing.observerdemo;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.hongqing.observerdemo.observer.PersonalObserver;
import com.hongqing.observerdemo.observer.SchoolObserver;
import com.hongqing.observerdemo.subject.ISubject;
import com.hongqing.observerdemo.subject.Subject;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity implements OnClickListener {

    private Button notify, send,eventBus;
    private ISubject subject;
    private EventBus eventBus1=EventBus.getDefault();
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        notify = (Button) findViewById(R.id.notifyAll);
        send = (Button) findViewById(R.id.rxJava2);
        eventBus = (Button) findViewById(R.id.eventBus);
        textView = (TextView) findViewById(R.id.textView);
        //注册观察者
        subject = new Subject();
        subject.addObserver(new PersonalObserver());
        subject.addObserver(new SchoolObserver());


        notify.setOnClickListener(this);
        notify.setTag(1);

        send.setOnClickListener(this);
        send.setTag(2);

        eventBus.setOnClickListener(this);
        eventBus.setTag(3);
    }

    @Override
    protected void onResume() {
        super.onResume();
        eventBus1.register(this);
    }

    @Override
    public void onClick(View view) {
        int tag = (int) view.getTag();
        switch (tag) {
            case 1:
                normalObserver();
                break;
            case 2:
                sendCodeClick();

                break;
            case 3:
                doEventBus();
                break;
        }
    }

    private void normalObserver() {
        //随机获取温度   通知观察者
        Random random = new Random();
        int i = 0;
        while (++i < 10) {
            subject.setTemperature(random.nextInt(45));
        }
    }



    //发送验证码
    private void sendCodeClick() {
        final int count = 10;
        Observable.interval(0, 1, TimeUnit.SECONDS)
                .take( count+1)
                .map(new Function<Long, Long>() {
                    @Override
                    public Long apply(@NonNull Long aLong) throws Exception {

                        return count - aLong;
                    }
                })
                .subscribeOn(Schedulers.io())

                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(new Consumer<Disposable>() {

                    @Override
                    public void accept(@NonNull Disposable disposable) throws Exception {
                        send.setEnabled(false);
                        send.setTextColor(Color.GRAY);
                    }
                })

                .subscribe(new Observer<Long>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Long aLong) {
                        send.setText(aLong + "秒后重新发送");
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                        send.setEnabled(true);
                        send.setText("重新发送");
                        send.setTextColor(Color.RED);
                    }
                });
    }

    private void doEventBus() {

              eventBus1.post(new Event(Event.keyString) );

    }

    @Subscribe
    public void onEvent(Event event){
        if (event.getKey().equals(Event.keyString) ) {
            textView.setText("eventBus实现的观察者模式");
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        eventBus1.unregister(this);
    }

}
