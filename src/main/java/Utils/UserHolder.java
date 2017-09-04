package Utils;

import Enums.Role;

import java.util.HashMap;
import java.util.Map;

public class UserHolder {
    private static Map<Integer, Role> users = new HashMap<>();
    private static UserHolder instance;

    private UserHolder() {
    }

    public static UserHolder getInstance() {
        if (instance == null) {
            instance = new UserHolder();
            users = DatabaseUtil.reloadUserRolesFromDatabase();

        }
        return instance;
    }

    public Role getUserRole(Integer userId) {
        return users.get(userId);
    }


}
