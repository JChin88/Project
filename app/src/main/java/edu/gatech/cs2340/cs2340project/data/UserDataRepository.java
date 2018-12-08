package edu.gatech.cs2340.cs2340project.data;

import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import edu.gatech.cs2340.cs2340project.domain.model.User;
import edu.gatech.cs2340.cs2340project.domain.model.UserRights;
import edu.gatech.cs2340.cs2340project.domain.repository.UserRepository;
import io.reactivex.Observable;

@Singleton
public class UserDataRepository implements UserRepository {

    public static final String LOGIN_SUCCESS = "Login Success!";
    public static final String LOGIN_INVALID_UIDPS = "Incorrect email or password!";

    private FirebaseAuth mAuth;

    private FirebaseFirestore db;

    User user;
    List<User> users;
    @Inject
    public UserDataRepository(FirebaseFirestore firebaseFirestore, FirebaseAuth firebaseAuth) {
        db = firebaseFirestore;
        mAuth =  firebaseAuth;
    }

    @Override
    public  User getCurrentUserTest() {
        return user;
    }

    @Override
    public String getCurrentUserID() {
        return mAuth.getCurrentUser().getUid();
    }

    @Override
    public Observable<String> addUser(final String name, final String email,
                                      final String password, final UserRights userRights) {
        return Observable.create(emitter -> {
            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                String userID = mAuth.getCurrentUser().getUid();
                                User user = new User(userID, name, email, userRights);
                                db.collection("users").document(userID).set(user);
                                emitter.onNext("User Registered Successful");
                                emitter.onComplete();
                            } else {
                                emitter.onError(task.getException());
                            }
                        }
                    });
        });

    }

    public void getCurrentUser() {
//        final FirebaseUser firebaseUser = mAuth.getCurrentUser();
//        String currentUserID = firebaseUser.getProviderId();
////        firebaseUser.updateProfile()
//        DocumentReference userRef = db.collection("users").document(currentUserID);
//        userRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
//            @Override
//            public void onSuccess(DocumentSnapshot documentSnapshot) {
//                UserRights accountType = UserRights.valueOf(documentSnapshot.get("userRights").toString());
//                User user = new User(firebaseUser.getProviderId(), firebaseUser.getDisplayName(),
//                        firebaseUser.getEmail(), accountType);
//                interactor.onNext(user);
//            }
//        }).addOnFailureListener(new OnFailureListener() {
//            @Override
//            public void onFailure(@NonNull Exception e) {
//                interactor.onError("Error on retrieve current user info");
//            }
//        });
    }

    @Override
    public Observable<User> getUser(String uid) {
        return Observable.create(emitter -> {
            DocumentReference userRef =  db.collection("users").document(uid);
            userRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                @Override
                public void onSuccess(DocumentSnapshot documentSnapshot) {
                    if (documentSnapshot.exists()) {
                        user =  documentSnapshot.toObject(User.class);
                        emitter.onNext(user);
                        emitter.onComplete();
                    }
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    emitter.onError(e);
                }
            });
        });
    }

    @Override
    public Observable<String> login(final String email, final String password) {
        return Observable.create(emitter -> {
            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                user = new User("Dummy", null, null, null);
                                emitter.onNext(mAuth.getCurrentUser().getUid());
                                emitter.onComplete();
                            } else {
                                emitter.onError(task.getException());
                            }
                        }
                    });
        });

    }

    @Override
    public Observable<List<User>> getUsers() {
        return Observable.create(emitter -> {
            List<User> userList = new ArrayList<>();
            CollectionReference usersRef = db.collection("users");
            usersRef.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                @Override
                public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                    for (QueryDocumentSnapshot documentSnapshots : queryDocumentSnapshots) {
                        if (!queryDocumentSnapshots.isEmpty()) {
                            User user = documentSnapshots.toObject(User.class);
                            userList.add(user);
                        }
                    }
                    emitter.onNext(userList);
                    emitter.onComplete();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    emitter.onError(e);
                }
            });
        });
    }


}
