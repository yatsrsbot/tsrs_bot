package Utils;

import Enums.Role;

import java.util.HashMap;
import java.util.Map;

public class UserHolder {
    private static Map<Integer, UserData> users = new HashMap<>();
    private static UserHolder instance;

    private UserHolder() {
    }

    static class UserData {
        Role role;
        String userName;

        UserData(Role role, String userName) {
            this.role = role;
            this.userName = userName;
        }
    }

    public static UserHolder getInstance() {
        if (instance == null) {
            instance = new UserHolder();
        }
        return instance;
    }

    public Role getUserRole(Integer userId) {
        Role role = Role.NONE;
        for (Map.Entry<Integer, UserData> user : users.entrySet()) {
            if (user.getKey().equals(userId)) {
                role = user.getValue().role;
            }

        }
        return role;
    }

    String getUserName(Integer userId) {
        String userName = "";
        for (Map.Entry<Integer, UserData> user : users.entrySet()) {
            if (user.getKey().equals(userId)) {
                userName = user.getValue().userName;
            }

        }
        return userName;
    }


    public void reloadUsersHolder() {
        users = DatabaseUtil.reloadUserRolesFromDatabase();
    }

    public boolean containsUserName(String userName) {
        Boolean contains = false;
        for (Map.Entry<Integer, UserData> user : users.entrySet()) {
            if (user.getValue().userName.equals(userName)) {
                contains = true;
            }
        }
        return contains;
    }

    public Integer getUserIdbyName(String userName) {
        Integer userId = null;
        for (Map.Entry<Integer, UserData> user : users.entrySet()) {
            if (user.getValue().userName.equals(userName)) {
                userId = user.getKey();
            }
        }
        return userId;
    }


}
