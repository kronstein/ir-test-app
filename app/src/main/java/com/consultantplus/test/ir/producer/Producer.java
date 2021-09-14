package com.consultantplus.test.ir.producer;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.processors.PublishProcessor;

public class Producer {
    private PublishProcessor<Integer> publishProcessor = PublishProcessor.create();

    private Producer() {

    }

    public Disposable subscribe(Consumer<Integer> action) {
        return publishProcessor
                .onBackpressureLatest()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(action);
    }

    public void post(int value) {
        if (publishProcessor.hasSubscribers()) {
            publishProcessor.onNext(value);
        }
    }

    private static final Producer instance = new Producer();

    public static Producer getInstance() {
        return instance;
    }
}