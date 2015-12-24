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
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {
    TextView tvCountDown;
    Subscription subscriptionCountDown;
    DateTime currentDateTime,targetDateTime;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        JodaTimeAndroid.init(this);

        initData();

        setUpView();
    }

    private void initData() {
        //targetDateTime=new DateTime(2015,12,25,11,2,0);
        targetDateTime=new DateTime().plusHours(3);
    }

    private void setUpView() {
        tvCountDown= (TextView) findViewById(R.id.tv_countdown);
        subscriptionCountDown=  Observable
                .interval(0,1, TimeUnit.SECONDS, Schedulers.computation())//
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Long>() {
                    @Override
                    public void onCompleted() {
                        Log.d("onCompleted","task is completed");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("onError","something went wrong in TimingDemoFragment example:"+e.getMessage());
                    }

                    @Override
                    public void onNext(Long text) {
                        Log.d("onNext","show countdown here");
                        currentDateTime=DateTime.now();
                        Interval interval=new Interval(currentDateTime,targetDateTime);
                        Period period=interval.toPeriod();
                        String countDownRText=period.getDays()+"天"+period.getHours()+"时"+period.getMinutes()+ "分"
                                +period.getSeconds()+"秒";
                        tvCountDown.setText(countDownRText);
                    }
                });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        subscriptionCountDown.unsubscribe();
    }
}
