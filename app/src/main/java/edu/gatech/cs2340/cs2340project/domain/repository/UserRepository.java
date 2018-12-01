package edu.gatech.cs2340.cs2340project.domain.repository;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;

import edu.gatech.cs2340.cs2340project.domain.interactor.base.Interactor;
import edu.gatech.cs2340.cs2340project.domain.model.User;
import edu.gatech.cs2340.cs2340project.domain.model.UserRights;
import io.reactivex.Observable;

public interface UserRepository {

    void addUser(String userName, String userEmail, String userPassword, UserRights userRights);

    String getCurrentUserID();

    void getUser(String id);

    //void addUser(User user);

    void getUsers();

    void setInteractor(Interactor interactor);

    /**
     * Login
     * Get an {@link Observable} which will emit a {@link User}.
     *
     * @param email The email used to sign in.
     * @param password The passwrod used to sign in.
     */
    Observable<String> login(final String email, final String password);
}
