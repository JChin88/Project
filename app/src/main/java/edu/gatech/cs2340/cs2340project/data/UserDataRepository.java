package edu.gatech.cs2340.cs2340project.data;

import android.app.Application;
import android.app.job.JobInfo;
import android.app.job.JobParameters;
import android.app.job.JobScheduler;
import android.app.job.JobService;
import android.content.ComponentName;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Parcel;
import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import edu.gatech.cs2340.cs2340project.domain.executor.MainThread;
import edu.gatech.cs2340.cs2340project.domain.interactor.LoginInteractor;
import edu.gatech.cs2340.cs2340project.domain.interactor.base.Interactor;
import edu.gatech.cs2340.cs2340project.domain.model.User;
import edu.gatech.cs2340.cs2340project.domain.repository.UserRepository;
import edu.gatech.cs2340.cs2340project.presentation.presenters.LoginPresenter;
import edu.gatech.cs2340.cs2340project.threading.MainThreadImpl;

public class UserDataRepository implements UserRepository {

    public static final String LOGIN_SUCCESS = "Login Success!";
    public static final String LOGIN_INVALID_UIDPS = "Incorrect email or password!";

    private FirebaseAuth mAuth;
    private FirebaseFirestore db;

    User user;
    List<User> users;

    private String LOGIN_MESSAGE;
    private Interactor interactor;

    public UserDataRepository() {
        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();
        //mMainThread = mainThread;
    }

    public void setInteractor(Interactor interactor) {
        this.interactor = interactor;
    }

    @Override
    public void addUser(final String name, final String email, final String password, final User.AccountType type) {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            String userID = mAuth.getCurrentUser().getUid();
                            user = new User(userID, name, email, type);
                            db.collection("users").document(userID).set(user);
                            interactor.onNext("User Registered Successful");
                        } else {
                            if (task.getException() instanceof FirebaseAuthUserCollisionException) {
                                interactor.onError("Email already registered!");
                            } else {
                                interactor.onError(task.getException().getMessage());
                            }
                        }
                    }
                });
    }

    @Override
    public void getCurrentUser() {
        final FirebaseUser firebaseUser = mAuth.getCurrentUser();
        String currentUserID = firebaseUser.getProviderId();
//        firebaseUser.updateProfile()
        DocumentReference userRef = db.collection("users").document(currentUserID);
        userRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                User.AccountType accountType = User.AccountType.valueOf(documentSnapshot.get("userType").toString());
                User user = new User(firebaseUser.getProviderId(), firebaseUser.getDisplayName(),
                        firebaseUser.getEmail(), accountType);
                interactor.onNext(user);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                interactor.onError("Error on retrieve current user info");
            }
        });
    }

    @Override
    public void getUser(String uid) {
//        FirebaseUser firebaseUser = mAuth.getCurrentUser();
//        firebaseUser.updateProfile()
////        User user = new User(firebaseUser.getUid(),
////                                firebaseUser.getDisplayName(),
////                                firebaseUser.getEmail(),
////                                firebaseUser.getPhoneNumber(),
////                                firebaseUser.getProviderData());

        DocumentReference userRef =  db.collection("users").document(uid);
        userRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                @Override
                public void onSuccess(DocumentSnapshot documentSnapshot) {
                    if (documentSnapshot.exists()) {
                        user =  documentSnapshot.toObject(User.class);
                        interactor.onNext(user);
                    } else {
                        interactor.onError("Get User Failed");
                    }
                }
            });

    }

    @Override
    public void login(final String email, final String password) {
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            LOGIN_MESSAGE = LOGIN_SUCCESS;
                            interactor.onNext(mAuth.getCurrentUser().getUid());
                        } else {
                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException
                                    || task.getException() instanceof FirebaseAuthInvalidUserException) {
                                LOGIN_MESSAGE = LOGIN_INVALID_UIDPS;
                            } else {
                                LOGIN_MESSAGE = task.getException().getMessage();
                            }
                            interactor.onError(LOGIN_MESSAGE);
                        }
                    }
                });
    }

    @Override
    public void getUsers() {
        if (users == null) {
            users = new ArrayList<>();
            CollectionReference usersRef = db.collection("users");
            usersRef.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                @Override
                public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                    for (QueryDocumentSnapshot documentSnapshots : queryDocumentSnapshots) {
                        if (!queryDocumentSnapshots.isEmpty()) {
                            User user = documentSnapshots.toObject(User.class);
                            users.add(user);
                        }
                    }
                    interactor.onNext(users);
                }
            });
        }
    }

}
