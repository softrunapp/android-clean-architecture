package com.test.domain.intractor;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;

public abstract class UseCase<T> {
    private final CompositeDisposable compositeDisposable;
    private final Scheduler executorThread;
    private final Scheduler uiThread;

    UseCase(Scheduler executorThread, Scheduler uiThread) {
        this.executorThread = executorThread;
        this.uiThread = uiThread;
        this.compositeDisposable = new CompositeDisposable();
    }

    public void execute(DisposableObserver<T> disposableObserver) {
        compositeDisposable.add(
                this.createUseCaseObservable()
                        .subscribeOn(executorThread)
                        .observeOn(uiThread)
                        .subscribeWith(disposableObserver)
        );
    }

    public void dispose() {
        if (!compositeDisposable.isDisposed()) {
            compositeDisposable.dispose();
        }
    }

    protected abstract Observable<T> createUseCaseObservable();
}
