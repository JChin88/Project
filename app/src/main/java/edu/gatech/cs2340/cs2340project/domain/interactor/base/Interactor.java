package edu.gatech.cs2340.cs2340project.domain.interactor.base;

/**
 * This is the main interface of an interactor. Each interactor serves a specific use case.
 */
public interface Interactor<T> {

    void notifyError(final String errorMessage);

    void goBackMainThread(final T params);

    /**
     * This is the main method that starts an interactor. It will make sure that the interactor operation is done on a
     * background thread.
     */
    void execute();
}
