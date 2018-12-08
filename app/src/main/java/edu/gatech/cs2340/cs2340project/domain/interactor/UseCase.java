package edu.gatech.cs2340.cs2340project.domain.interactor;

import edu.gatech.cs2340.cs2340project.domain.executor.ThreadExecutor;
import edu.gatech.cs2340.cs2340project.domain.executor.MainThread;
import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public abstract class UseCase<T, Params>  {
    
    private final ThreadExecutor threadExecutor;
    private final MainThread mainThread;
    private final CompositeDisposable disposables;

    UseCase(ThreadExecutor threadExecutor, MainThread mainThread) {
        this.threadExecutor = threadExecutor;
        this.mainThread = mainThread;
        this.disposables = new CompositeDisposable();
    }

    /**
     * Builds an {@link Observable} which will be used when executing the current {@link UseCase}.
     */
    abstract Observable<T> buildUseCaseObservable(Params params);

    /**
     * Executes the current use case.
     *
     * @param observer {@link DisposableObserver} which will be listening to the observable build
     * by {@link #buildUseCaseObservable(Params)} ()} method.
     * @param params Parameters (Optional) used to build/execute this use case.
     */
    public void execute(DisposableObserver<T> observer, Params params) {
//        Preconditions.checkNotNull(observer);
        final Observable<T> observable = this.buildUseCaseObservable(params)
                .subscribeOn(Schedulers.from(threadExecutor))
                .observeOn(mainThread.getScheduler());
        addDisposable(observable.subscribeWith(observer));
    }

    /**
     * Dispose from current {@link CompositeDisposable}.
     */
    public void dispose() {
        if (!disposables.isDisposed()) {
            disposables.dispose();
        }
    }

    /**
     * Dispose from current {@link CompositeDisposable}.
     */
    private void addDisposable(Disposable disposable) {
//        Preconditions.checkNotNull(disposable);
//        Preconditions.checkNotNull(disposables);
        disposables.add(disposable);
    }
}
