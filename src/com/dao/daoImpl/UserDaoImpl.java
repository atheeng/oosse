/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao.daoImpl;

import com.Enum.RoleTypeEnum;
import com.dao.UserDao;
import com.db.DataBaseConnection;
import com.model.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author salinthapa
 */
public class UserDaoImpl implements UserDao {

    @Override
    public List<User> getAllUsers() {

        List<User> userList = new ArrayList<>();
        Connection conn = null;
        try {
            conn = DataBaseConnection.getInstance().getConnection();
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM USER WHERE STATUS='ACTIVE'");
            while (rs.next()) {
                User u = new User();
                u.setId(rs.getInt(1));
                u.setUsername(rs.getString(2));
                u.setRole(RoleTypeEnum.valueOf(rs.getString(4)));
                u.setFirstName(rs.getString(5));
                u.setLastName(rs.getString(6));
                u.setAddress(rs.getString(7));
                u.setMobileNo(rs.getString(8));
                userList.add(u);
            }
            rs.close();
            statement.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return userList;
    }

    @Override
    public boolean existingUser(String username) {

        Connection conn = null;
        Integer id = null;
        try {
            conn = DataBaseConnection.getInstance().getConnection();
            String query = "SELECT id FROM USER WHERE USERNAME =? ";
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setString(1, username);
            ResultSet rs = preparedStmt.executeQuery();
            while (rs.next()) {
                id = rs.getInt(1);
            }
            rs.close();
            preparedStmt.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        if (id != null) {
            return true;
        } else {
            return false;
        }

    }

    @Override
    public boolean deleteUser(int id) {
        Connection conn = null;
        try {
            conn = DataBaseConnection.getInstance().getConnection();
            String query = "update user set status='INACTIVE' where id=?";
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setInt(1, id);
            preparedStmt.executeUpdate();
            preparedStmt.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return true;
    }

    @Override
    public User getById(int id) {
        Connection conn = null;
        User user = new User();
        try {
            conn = DataBaseConnection.getInstance().getConnection();
            String query = "select id,username,password,role,first_name,last_name,address,mobile_no from user where id=? ";
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setInt(1, id);
            ResultSet rs = preparedStmt.executeQuery();
            while (rs.next()) {
                user.setId(rs.getInt(1));
                user.setUsername(rs.getString(2));
                user.setPassword(rs.getString(3));
                user.setRole(RoleTypeEnum.valueOf(rs.getString(4)));
                user.setFirstName(rs.getString(5));
                user.setLastName(rs.getString(6));
                user.setAddress(rs.getString(7));
                user.setMobileNo(rs.getString(8));
            }
            rs.close();
            preparedStmt.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return user;
    }

    @Override
    public void getAllUserList() {
        System.out.println("");

    }

    @Override
    public User getByUserPassword(String username, String password) {
        Connection conn = null;
        User user = new User();
        try {
            conn = DataBaseConnection.getInstance().getConnection();
            String query = "select id,username,role from user where username=? AND password=sha(?)";
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setString(1, username);
            preparedStmt.setString(2, password);
            ResultSet rs = preparedStmt.executeQuery();
            while (rs.next()) {
                user.setId(rs.getInt(1));
                user.setUsername(rs.getString(2));
                user.setRole(RoleTypeEnum.valueOf(rs.getString(3)));
            }
            rs.close();
            preparedStmt.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return user;
    }

    @Override
    public String saveUpdate(User user) {
        Connection conn = null;
        int id = user.getId();

        if (id == 0) {
            boolean existingUser = existingUser(user.getUsername());
            if (existingUser == true) {
                return "EXIST";
            } else {
                try {
                    conn = DataBaseConnection.getInstance().getConnection();
                    String query = "insert into user (username, password, role,first_name,last_name,address,mobile_no,status) values (?, sha(?), ?,?, ?, ?,?,?)";
                    PreparedStatement preparedStmt = conn.prepareStatement(query);
                    preparedStmt.setString(1, user.getUsername());
                    preparedStmt.setString(2, user.getPassword());
                    String role = user.getRole().toString();
                    preparedStmt.setString(3, role);
                    preparedStmt.setString(4, user.getFirstName());
                    preparedStmt.setString(5, user.getLastName());
                    preparedStmt.setString(6, user.getAddress());
                    preparedStmt.setString(7, user.getMobileNo());
                    preparedStmt.setString(8, "ACTIVE");
                    preparedStmt.executeUpdate();
                    preparedStmt.close();
                    System.out.println("saved");
                } catch (Exception e) {
                    System.out.println("error:" + e.getMessage());
                }
                return user.getUsername()+" Customer Saved ";
            }

        } else {
            try {
                conn = DataBaseConnection.getInstance().getConnection();
                String query = "update user set username=?, password=sha(?), role=?,first_name=?,last_name=?,address=?,mobile_no=? where id=?";
                PreparedStatement preparedStmt = conn.prepareStatement(query);
                preparedStmt.setString(1, user.getUsername());
                preparedStmt.setString(2, user.getPassword());
                String role = user.getRole().toString();
                preparedStmt.setString(3, role);
                preparedStmt.setString(4, user.getFirstName());
                preparedStmt.setString(5, user.getLastName());
                preparedStmt.setString(6, user.getAddress());
                preparedStmt.setString(7, user.getMobileNo());
                preparedStmt.setInt(8, user.getId());
                preparedStmt.executeUpdate();
                preparedStmt.close();
                
            } catch (Exception e) {
                System.out.println("error:" + e.getMessage());
            }
            return user.getUsername() + " Customer Updated";
        }

    }

    @Override
    public List<User> getAlluserByname(String search) {
        List<User> userList = new ArrayList<>();
        Connection conn = null;
        try {
            conn = DataBaseConnection.getInstance().getConnection();
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM USER WHERE USERNAME LIKE '" + search + "%' AND STATUS='ACTIVE'");
            while (rs.next()) {
                User u = new User();
                u.setId(rs.getInt(1));
                u.setUsername(rs.getString(2));
                u.setRole(RoleTypeEnum.valueOf(rs.getString(4)));
                u.setFirstName(rs.getString(5));
                u.setLastName(rs.getString(6));
                u.setAddress(rs.getString(7));
                u.setMobileNo(rs.getString(8));
                userList.add(u);
            }
            rs.close();
            statement.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return userList;
    }

}
