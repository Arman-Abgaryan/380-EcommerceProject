package com.example.comp380project;

import java.util.HashMap;

/**
 * Class that handles login procedures
 */
public class LoginSystem {
    private HashMap<String,String> loginCred;

    public LoginSystem(HashMap<String,String> loginCred){
        this.loginCred = loginCred;
    }

    /**
     * Checks if username doesn't already exist in the system.
     * @param username
     * @param password
     * @return true if username is valid, false if username already exists.
     */
    public boolean existingUser(String username, String password){
        if (loginCred.containsKey(username)){
            return false;
        }
        loginCred.put(username,password);
        return true;
    }

    /**
     * Method that verifies credentials
     * @param username
     * @param password
     * @return
     */
    public boolean authenticateUser(String username, String password){
        return loginCred.containsKey(username) && loginCred.get(username).equals(password);
    }
}
