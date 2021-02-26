/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.util;

import com.db.DataBaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class ProductReserve {

    public String addProduct(int total, int reserve, int productId) {
         int totalQty=total-reserve;
        try {
            Connection conn = null;
            conn = DataBaseConnection.getInstance().getConnection();
            String query = "update product set total_quantity=? where id=?";
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setInt(1, totalQty);
            preparedStmt.setInt(2, productId);
            preparedStmt.executeUpdate();
            preparedStmt.close();

        } catch (Exception e) {
            System.out.println("error:" + e.getMessage());
        }
        return "UPDATE";
    }

    public String minusProduct(int total, int reserve, int productId) {
        int totalQty = total - reserve;
        try {
            Connection conn = null;
            conn = DataBaseConnection.getInstance().getConnection();
            String query = "update product set total_quantity=? where id=?";
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setInt(1, totalQty);
            preparedStmt.setInt(2, productId);
            preparedStmt.executeUpdate();
            preparedStmt.close();

        } catch (Exception e) {
            System.out.println("error:" + e.getMessage());
        }
        return "UPDATE";
    }

}
