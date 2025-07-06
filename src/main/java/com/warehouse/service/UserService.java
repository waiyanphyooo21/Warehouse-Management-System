package com.warehouse.service;

import com.warehouse.beans.User;
import com.warehouse.dao.UserDao;

public class UserService {

    private UserDao userDao;

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public User getUserByUsername(String username) {
        return userDao.getUserByUsername(username);
    }

    public boolean authenticate(String username, String rawPassword) {
        User user = userDao.getUserByUsername(username);
        if (user == null) {
            System.out.println("❌ No user found with username: " + username);
            return false;
        }

        System.out.println("🔍 Raw password: " + rawPassword);
        System.out.println("🔐 Stored (hashed) password: " + user.getPassword());

        boolean match = userDao.verifyPassword(rawPassword, user.getPassword());
        System.out.println("✅ Password match: " + match);
        return match;
    }
}
