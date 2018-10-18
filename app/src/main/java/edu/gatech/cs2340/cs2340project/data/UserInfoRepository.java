package edu.gatech.cs2340.cs2340project.data;

import java.util.HashMap;

import edu.gatech.cs2340.cs2340project.domain.model.User;
import edu.gatech.cs2340.cs2340project.domain.repository.UserRepository;
import edu.gatech.cs2340.cs2340project.domain.model.UserData;

public class UserInfoRepository implements UserRepository {

    @Override
    public User getUser(String id) {
        HashMap<String, Integer> loginData = UserData.getLoginData();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if (loginData.containsKey(id)) {
            return UserData.getUser(id);
        }
        return null;
    }
}
