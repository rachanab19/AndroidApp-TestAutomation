package com.mytaxi.android_demo.model;

import java.util.HashMap;
/**
 * This is a model class holding the application user credentials
 */
public class User {
    private String userId;
    private String password;

    private static HashMap<String, User> users = null;
    private  User (String userId, String password){
        this.userId = userId;
        this.password = password;
    }

    //Fetch the valid, invalid and up-to-date data for the Application user to test the login
    private static void  loadUsers(){
        users = new HashMap<>();

        //FETCH UP TO DATE VALID CREDENTIALS
        DataHelper dataHelper = new DataHelper();
        String [] validUpToDateCredentials = dataHelper.getUpToDateCredentials();
        User user1 = new User(validUpToDateCredentials[0],validUpToDateCredentials[1]);

        //INVALID dataset
        User user2 = new User("mytaxi", "mytaxi");
        User user3 = new User ("crazydog%", "venture");

        users.put(Constants.VALID, user1);
        users.put(Constants.INVALID, user2);
        users.put(Constants.WITHSPECIALCHARS, user3);
    }

    public static User getUser (String userType) {
        if (users == null )
            loadUsers();
        User user = users.get(userType);
        return user;
    }

    public String getUserId() {
        return userId;
    }

    public String getPassword() {
        return password;
    }

}
