package edu.gatech.cs2340.cs2340project.data.datasource;

import io.reactivex.Observable;

public class CloudUserDataStore implements UserDataStore {
    @Override
    public Observable<String> userStringID(String email, String password) {
        return null;
    }
}
