package com.abrarkotwal.movies.utils.rx;

import rx.Scheduler;

public interface RxSchedulers {


    Scheduler runOnBackground();

    Scheduler io();

    Scheduler compute();

    Scheduler androidThread();

    Scheduler internet();



}
