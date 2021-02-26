/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao;

import com.model.User;
import java.util.List;

/**
 *
 * @author salinthapa
 */
public interface UserDao {
    String saveUpdate(User user);
    List<User> getAllUsers();
    boolean existingUser(String username);
    boolean deleteUser(int id);
    User getById(int id);
    void getAllUserList();
    List<User> getAlluserByname(String search);
    User getByUserPassword(String username,String password);
}
