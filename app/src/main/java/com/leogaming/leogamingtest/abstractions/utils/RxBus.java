package com.leogaming.leogamingtest.abstractions.utils;


import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;

@SuppressWarnings("unused")
public class RxBus<T> {

    @SuppressWarnings("unused")
    private final PublishSubject<T> bus = PublishSubject.create();

    @SuppressWarnings("unused")
    public void send(T o) {
        bus.onNext(o);
    }

    @SuppressWarnings("unused")
    public Observable<T> toObservable() {
        return bus;
    }
}
