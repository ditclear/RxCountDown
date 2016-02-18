package vienan.app.countdown

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.TextView
import android.widget.Toast

import net.danlew.android.joda.JodaTimeAndroid

import org.joda.time.DateTime
import org.joda.time.Interval
import org.joda.time.Period

import java.util.concurrent.TimeUnit

import rx.Observable
import rx.Observer
import rx.Subscription
import rx.android.schedulers.AndroidSchedulers
import rx.functions.Func1
import rx.schedulers.Schedulers

class MainActivity : AppCompatActivity() {
    internal var subscriptionCountDown: Subscription ?=null
    //    DateTime currentDateTime,targetDateTime;

    //    String currentDate;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        JodaTimeAndroid.init(this)

        initData()

        setUpView()
    }

    private fun initData() {
        //        currentDate="2016-5-3T12:00";
        //        currentDate=new DateTime().plusHours(3).toString("yyyy-MM-dd HH:mm");
        //        targetDateTime=new DateTime(2015,12,25,11,2,0);
        //        targetDateTime=new DateTime().plusHours(3);
    }

    private fun setUpView() {
        var tvCountDown: TextView =findViewById(R.id.tv_countdown) as TextView
        subscriptionCountDown = Observable.interval(1, TimeUnit.SECONDS, Schedulers.computation()).map { s ->
            Log.d("s", "" + s!!)
            DateTime(2016, 2, 25, 11, 2, 0)
        }.map { dateTime -> Interval(DateTime.now(), dateTime).toPeriod() }.observeOn(AndroidSchedulers.mainThread()).subscribe(object : Observer<Period> {
            override fun onCompleted() {
                Log.d("onCompleted", "task is completed")

            }

            override fun onError(e: Throwable) {
                Log.d("onError", "something went wrong in TimingDemoFragment example:" + e.message)
                tvCountDown?.text = "倒计时结束"
                toast("倒计时结束")
            }

            override fun onNext(period: Period) {
                Log.d("onNext", "show countdown here")
                val countDownRText = "剩 " + period.days + "天" + period.hours + "时" + period.minutes + "分"+period.seconds + "秒"
                tvCountDown?.text = countDownRText
            }
        })

    }

    override fun onDestroy() {
        super.onDestroy()
//        subscriptionCountDown?.unsubscribe()
    }

    fun MainActivity.toast(message:CharSequence,length:Int= Toast.LENGTH_SHORT){
        Toast.makeText(this,message,length).show();
    }
}
