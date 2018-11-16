package edu.gatech.cs2340.cs2340project.domain.interactor.base;

/**
 * This is the main interface of an interactor. Each interactor serves a specific use case.
 */
public interface Interactor {

    /**
     * show error meessage
     * @param errorMessage error message wnat to show
     */
    void onError(final String errorMessage);

    /**
     * Next action
     * @param params value want to pass next
     * @param <T> the type of value
     */
    <T> void onNext(final T params);

    /**
     * This is the main method that starts an interactor. It will make sure that the interactor
     * operation is done on a background thread.
     */
    void execute();
}
