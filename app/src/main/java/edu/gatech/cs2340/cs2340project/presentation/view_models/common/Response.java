package edu.gatech.cs2340.cs2340project.presentation.view_models.common;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import static edu.gatech.cs2340.cs2340project.presentation.view_models.common.Status.ERROR;
import static edu.gatech.cs2340.cs2340project.presentation.view_models.common.Status.LOADING;
import static edu.gatech.cs2340.cs2340project.presentation.view_models.common.Status.SUCCESS;

/**
 * Response holder provided to the UI
 */
public class Response<T> {

    public final Status status;

    @Nullable
    public final T data;

    @Nullable
    public final Throwable error;

    private Response(Status status, @Nullable T data, @Nullable Throwable error) {
        this.status = status;
        this.data = data;
        this.error = error;
    }

    public static Response loading() {
        return new Response(LOADING, null, null);
    }

    public static <T> Response success(@NonNull final T data) {
        return new Response(SUCCESS, data, null);
    }

    public static Response error(@NonNull Throwable error) {
        return new Response(ERROR, null, error);
    }
}
