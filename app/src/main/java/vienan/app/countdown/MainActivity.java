package vienan.app.countdown;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import net.danlew.android.joda.JodaTimeAndroid;

import org.joda.time.DateTime;
import org.joda.time.Interval;
import org.joda.time.Period;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {
    TextView tvCountDown;
    Subscription subscriptionCountDown;
//    DateTime currentDateTime,targetDateTime;

//    String currentDate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        JodaTimeAndroid.init(this);

        initData();

        setUpView();
    }

    private void initData() {
//        currentDate="2016-5-3T12:00";
//        currentDate=new DateTime().plusHours(3).toString("yyyy-MM-dd HH:mm");
//        targetDateTime=new DateTime(2015,12,25,11,2,0);
//        targetDateTime=new DateTime().plusHours(3);
    }

    private void setUpView() {
        tvCountDown= (TextView) findViewById(R.id.tv_countdown);
        subscriptionCountDown=  Observable
                .interval(1, TimeUnit.SECONDS, Schedulers.computation())
                .map(new Func1<Long, DateTime>() {
                    @Override
                    public DateTime call(Long s) {
                        Log.d("s", ""+s);
                        return new DateTime(2016,2,25,11,2,0);
                    }
                })
                .map(new Func1<DateTime, Period>() {
                    @Override
                    public Period call(DateTime dateTime) {
                        return new Interval(DateTime.now(),dateTime).toPeriod();
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Period>() {
                    @Override
                    public void onCompleted() {
                        Log.d("onCompleted","task is completed");
                        tvCountDown.setText("倒计时结束");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("onError","something went wrong in TimingDemoFragment example:"+e.getMessage());
                    }

                    @Override
                    public void onNext(Period period) {
                        Log.d("onNext","show countdown here");
                        String countDownRText="剩 "+period.getDays()+"天"+period.getHours()+"时"+period.getMinutes()+ "分"
                                +period.getSeconds()+"秒";
                        tvCountDown.setText(countDownRText);
                    }
                });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (!subscriptionCountDown.isUnsubscribed())
            subscriptionCountDown.unsubscribe();
    }
}
