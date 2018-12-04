package edu.gatech.cs2340.cs2340project.presentation.view;

/**
 * <p>
 * This interface represents a basic view. All views should implement these common methods.
 * </p>
 */
public interface BaseView {

    /**
     * Show a view with a progress bar indicating a loading process.
     */
    void showProgress();

    /**
     * Hide a loading view.
     */
    void hideProgress();

    /**
     * Show a retry view in case of an error when retrieving data.
     */
    void showViewRetry();

    /**
     * Hide a retry view shown if there was an error when retrieving data.
     */
    void hideViewRetry();

    /**
     * This method is used for showing error messages on the UI.
     *
     * @param errorMessage The error message to be displayed.
     */
    void showError(String errorMessage);
}
