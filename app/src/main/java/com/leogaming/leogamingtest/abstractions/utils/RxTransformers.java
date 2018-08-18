package com.leogaming.leogamingtest.abstractions.utils;

import android.os.Handler;
import android.os.Looper;

import org.reactivestreams.Publisher;
import org.reactivestreams.Subscription;

import io.reactivex.Flowable;
import io.reactivex.FlowableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

@SuppressWarnings({"WeakerAccess", "unused", "Convert2Lambda"})
public class RxTransformers {

    @SuppressWarnings("unused")
    public static <T> FlowableTransformer<T, T> applySchedulers() {
        return new FlowableTransformer<T, T>() {

            @Override
            public Publisher<T> apply(Flowable<T> upstream) {
                return upstream.subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }

    public static <T> FlowableTransformer<T, T> applyProgress(final Runnable before, final Runnable after) {
        return new FlowableTransformer<T, T>() {
            @Override
            public Publisher<T> apply(Flowable<T> tObservable) {
                return tObservable.doOnSubscribe(new Consumer<Subscription>() {
                    @Override
                    public void accept(Subscription subscription) throws Exception {
                        new Handler(Looper.getMainLooper()).post(before);
                    }
                })
                        .doOnTerminate(new Action() {
                            @Override
                            public void run() throws Exception {
                                new Handler(Looper.getMainLooper()).post(after);
                            }
                        });
            }
        };
    }

    @SuppressWarnings("unused")
    public static <T> FlowableTransformer<T, T> applyErrorHandling(final BIErrorHandler errorHandler) {
        return new FlowableTransformer<T, T>() {

            @Override
            public Publisher<T> apply(Flowable<T> tObservable) {
                return tObservable
                        .onErrorResumeNext(new Function<Throwable, Flowable<T>>() {
                            @Override
                            public Flowable<T> apply(Throwable throwable) throws Exception {
                                throwable.printStackTrace();
                                errorHandler.processNetError(throwable);
                                return Flowable.empty();
                            }
                        });
            }
        };
    }

    @SuppressWarnings("unused")
    public static <T> FlowableTransformer<T, T> applyErrorHandling(final BIErrorHandler errorHandler, Runnable errorAction) {
        return new FlowableTransformer<T, T>() {

            @Override
            public Publisher<T> apply(Flowable<T> upstream) {
                return upstream
                        .onErrorResumeNext(new Function<Throwable, Publisher<T>>() {

                            @Override
                            public Publisher<T> apply(Throwable throwable) throws Exception {
                                throwable.printStackTrace();
                                errorHandler.processNetError(throwable);
                                new Handler(Looper.getMainLooper()).post(errorAction);
                                return Flowable.empty();
                            }
                        });
            }
        };
    }
}
