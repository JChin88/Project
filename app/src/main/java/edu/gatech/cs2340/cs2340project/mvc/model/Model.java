package edu.gatech.cs2340.cs2340project.mvc.model;

import java.util.HashMap;
import java.util.List;

import edu.gatech.cs2340.cs2340project.data.UserData;
import edu.gatech.cs2340.cs2340project.domain.model.Location;
import edu.gatech.cs2340.cs2340project.domain.model.User;

public class Model {

    private static final Model model = new Model();
    public static Model getInstanceOf() {
        return model;
    }

    //
    private HashMap<String, Integer> loginList = UserData.getLoginData();

    //
    private HashMap<String, User> userData = UserData.getUserList();

    //
    private List<Location> locationList;



    public Model() {

    }




}
