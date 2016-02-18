# CountDown
A CountDown using Kotlin、RxAndroid and Jodatime

----------
[Apk Here](https://github.com/vienan/CountDown/blob/master/countdown.apk)

>###screenshot

![](https://github.com/vienan/CountDown/raw/master/screenshot/screenshot.gif)

##Main Code
```java
				
      Observable.interval(1, TimeUnit.SECONDS, Schedulers.computation())
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
	
				
```
用`kotlin`之后
	
```java

      Observable.interval(1, TimeUnit.SECONDS, Schedulers.computation())
                        .map { s -> DateTime(2016, 2, 25, 11, 2, 0) }
                        .map { dateTime -> Interval(DateTime.now(), dateTime).toPeriod() }
                        .observeOn(AndroidSchedulers.mainThread()).subscribe(object : Observer<Period> {
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
                        val countDownRText = "剩 " + period.days + "天" + period.hours + "时" + period.minutes + "分" + period.seconds + "秒"
                        tvCountDown?.text = countDownRText
                    }
                })

```

welcome to `star` and  `fork`.
