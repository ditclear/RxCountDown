# CountDown
A CountDown using RxAndroid and Jodatime

----------
[Apk Here](..\build\outputs\apk\countdown.apk)

###screenshot

![screenshot](..\screenshot\screenshot.jpg)

##Main Code
```java
				
				Observable
                .interval(0,1, TimeUnit.SECONDS, Schedulers.computation())
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
	
				
```

###If it helped you,welcome to star and  fork.