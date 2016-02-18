# CountDown
A CountDown using RxAndroid and Jodatime

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
用lamda表达式简化后

```java

      Observable.interval(1, TimeUnit.SECONDS, Schedulers.computation())
                .map(s -> new DateTime(2016, 2, 25, 11, 2, 0))
                .map(dateTime -> new Interval(DateTime.now(), dateTime).toPeriod())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Period>() {
                    @Override
                    public void onCompleted() {
                        Log.d("onCompleted", "task is completed");
                        tvCountDown.setText("倒计时结束");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("onError", "something went wrong in TimingDemoFragment example:" + e.getMessage());
                    }

                    @Override
                    public void onNext(Period period) {
                        Log.d("onNext", "show countdown here");
                        String countDownRText = "剩 " + period.getDays() + "天" + period.getHours() + "时" + period.getMinutes() + "分" + period.getSeconds() + "秒";
                        tvCountDown.setText(countDownRText);
                    }
                });

```

###If it helped you,welcome to star and  fork.
