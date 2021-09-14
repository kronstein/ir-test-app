package com.consultantplus.test.ir

import android.util.Log
import androidx.annotation.Keep
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import androidx.lifecycle.ProcessLifecycleOwner
import com.consultantplus.test.ir.producer.Producer
import io.reactivex.disposables.CompositeDisposable

fun helloWorld() {
    Log.v("DKDK", "Hello world!")
}

@Keep
val subscriptions = CompositeDisposable().apply {
    ProcessLifecycleOwner.get().lifecycle.addObserver(object: LifecycleObserver {
        @OnLifecycleEvent(Lifecycle.Event.ON_START)
        fun onStart() {
            add(Producer.getInstance().subscribe { value ->
                Log.v("DKDK", "$value")
            })
        }

        @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
        fun onStop() {
            clear()
        }
    })
}
