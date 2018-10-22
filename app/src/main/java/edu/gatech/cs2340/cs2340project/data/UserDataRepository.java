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
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;
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

import edu.gatech.cs2340.cs2340project.domain.model.User;
import edu.gatech.cs2340.cs2340project.domain.repository.UserRepository;

public class UserDataRepository implements UserRepository {

    public static final String LOGIN_SUCCESS = "Login Success!";
    public static final String LOGIN_INVALID_UIDPS = "Incorrect email or password!";

    private FirebaseAuth mAuth;
    private FirebaseFirestore db;

    User user;
    List<User> users;
    String email;
    String password;

    private String LOGIN_MESSAGE;

    public UserDataRepository() {
        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();
    }

    @Override
    public User getUser(String uid) {
//        FirebaseUser firebaseUser = mAuth.getCurrentUser();
//        firebaseUser.updateProfile()
////        User user = new User(firebaseUser.getUid(),
////                                firebaseUser.getDisplayName(),
////                                firebaseUser.getEmail(),
////                                firebaseUser.getPhoneNumber(),
////                                firebaseUser.getProviderData());

        if (user == null) {
            DocumentReference userRef =  db.collection("users").document(uid);
            userRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                @Override
                public void onSuccess(DocumentSnapshot documentSnapshot) {
                    if (documentSnapshot.exists()) {
                        user =  documentSnapshot.toObject(User.class);
                    }
                }
            });
//            user = new GetUserAsyncTask(db).execute(uid);
        }
        return user;
//        HashMap<String, Integer> loginData = UserData.getLoginData();
//        try {
//            Thread.sleep(2000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//
//        if (loginData.containsKey(id)) {
//            return UserData.getUser(id);
//        }
//        return null;
    }

    @Override
    public String getCurrentUID() {
        return mAuth.getCurrentUser().getUid();
    }

    @Override
    public String getMessage() {
        return LOGIN_MESSAGE;
    }

    @Override
    public void login(final String email, final String password) {
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            LOGIN_MESSAGE = LOGIN_SUCCESS;
                        } else {
                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException
                                    || task.getException() instanceof FirebaseAuthInvalidUserException) {
                                LOGIN_MESSAGE = LOGIN_INVALID_UIDPS;
                            } else {
                                LOGIN_MESSAGE = task.getException().getMessage();
                            }
                        }
                        // mian
                    }
                });
        //new loginTask().execute(email,password);
//        return LOGIN_MESSAGE;
    }



    @Override
    public List<User> getUsers() {
        if (users == null) {
            users = new ArrayList<>();
            CollectionReference usersRef = db.collection("users");
            usersRef.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                @Override
                public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                    for (QueryDocumentSnapshot documentSnapshots : queryDocumentSnapshots) {
                        User user = documentSnapshots.toObject(User.class);
                        users.add(user);
                    }
                }
            });
        }
        return users;
    }

//    public class loginTask extends AsyncTask<String, Void, String> {
//
//        private String tempLoginMessage;
////        private String userID;
////        private String userPassword;
////
////        public loginTask(String userID, String userPassword) {
////            this.userID = userID;
////            this.userPassword = userPassword;
////        }
////
//
//        @Override
//        protected String doInBackground(String... strings) {
//            String userID = strings[0];
//            String userPassword = strings[1];
//            mAuth.signInWithEmailAndPassword(userID, userPassword)
//                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
//                        @Override
//                        public void onComplete(@NonNull Task<AuthResult> task) {
//                            if (task.isSuccessful()) {
//                                tempLoginMessage = LOGIN_SUCCESS;
//                            } else {
//                                if (task.getException() instanceof FirebaseAuthInvalidCredentialsException
//                                        || task.getException() instanceof FirebaseAuthInvalidUserException) {
//                                    tempLoginMessage = LOGIN_INVALID_UIDPS;
//                                } else {
//                                    tempLoginMessage = task.getException().getMessage();
//                                }
//                            }
//                        }
//                    });
//            return tempLoginMessage;
//        }
//
//        @Override
//        protected void onPostExecute(String s) {
//            super.onPostExecute(s);
//            LOGIN_MESSAGE = s;
//        }
//    }

}
