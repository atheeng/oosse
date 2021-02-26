/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao.daoImpl;

import com.Enum.RoleTypeEnum;
import com.Enum.StatusEnum;
import com.dao.OrderDao;
import com.db.DataBaseConnection;
import com.dto.OrderListDto;
import com.model.Cart;
import com.model.User;
import com.util.ProductReserve;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Arun Tamang
 */
public class OrderDaoImpl implements OrderDao{

    Connection conn = null;

    long millis = System.currentTimeMillis();
    java.sql.Date date = new java.sql.Date(millis);

    @Override
    public String saveUpdate(Cart card) {
        long millis = System.currentTimeMillis();
        java.sql.Date date = new java.sql.Date(millis);
        int id = 0;
        int idrows = 0;
        if (card.getId() == 0) {
//            for id
            boolean existingUser = existingItem(card.getItem());
            if (existingUser == true) {
                return "EXIST";
            } else {
                try {
                    conn = DataBaseConnection.getInstance().getConnection();
                    Statement statement = conn.createStatement();
                    ResultSet rs = statement.executeQuery("SELECT COUNT(*) as id FROM cart");
                    while (rs.next()) {
                        idrows = rs.getInt(1);
                    }
                    rs.close();
                    statement.close();
                    if (idrows == 0) {
                        idrows = 1;
                    } else {
                        idrows = idrows + 1;
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
            try {
                conn = DataBaseConnection.getInstance().getConnection();
                String query = "INSERT INTO cart VALUES(?,?,?,?,?,?)";
                PreparedStatement preparedStmt = conn.prepareStatement(query);
                preparedStmt.setInt(1, idrows);
                preparedStmt.setInt(2, card.getProductId());
                preparedStmt.setString(3, card.getItem());
                preparedStmt.setDouble(4, card.getUnitPrice());
                preparedStmt.setInt(5, card.getQty());
                preparedStmt.setDouble(6, (card.getUnitPrice()) * (card.getQty()));
                preparedStmt.executeUpdate();
                preparedStmt.close();

            } catch (Exception e) {
                System.out.println("error:" + e.getMessage());
            }
            return card.getItem() + " with " + card.getQty() + " quantity is added";

        } else {
            try {
                conn = DataBaseConnection.getInstance().getConnection();
                String query = "UPDATE cart SET product_id=?,item=?,unit_price=?,quantity=?,totalAmount=? where id=?";
                PreparedStatement preparedStmt = conn.prepareStatement(query);
                preparedStmt.setInt(1, card.getProductId());
                preparedStmt.setString(2, card.getItem());
                preparedStmt.setDouble(3, card.getUnitPrice());
                preparedStmt.setInt(4, card.getQty());
                preparedStmt.setDouble(5, (card.getUnitPrice()) * (card.getQty()));
                preparedStmt.setInt(6, card.getId());
                preparedStmt.executeUpdate();
                preparedStmt.close();

            } catch (Exception e) {
                System.out.println("error:" + e.getMessage());
            }
            return card.getItem() + " with " + card.getQty() + " quantity is updated";
        }
    }

    @Override
    public boolean existingItem(String item) {
        Integer id = null;
        try {
            conn = DataBaseConnection.getInstance().getConnection();
            String query = "SELECT * FROM cart WHERE item=?";
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setString(1, item);
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
    public boolean deleteAll() {
        try {
            conn = DataBaseConnection.getInstance().getConnection();
            String query = "delete from cart";
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.executeUpdate();
            preparedStmt.close();

        } catch (Exception e) {
            System.out.println("error:" + e.getMessage());
        }
        return true;
    }

    @Override
    public boolean deleteById(int id) {
        try {
            conn = DataBaseConnection.getInstance().getConnection();
            String query = "delete from cart where id=?";
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setInt(1, id);
            preparedStmt.executeUpdate();
            preparedStmt.close();

        } catch (Exception e) {
            System.out.println("error:" + e.getMessage());
        }
        return true;
    }

    @Override
    public List<Cart> getAllTempList() {
        List<Cart> productList = new ArrayList<>();
        try {
            conn = DataBaseConnection.getInstance().getConnection();
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery("SELECT id,product_id,item,unit_price,quantity,totalAmount FROM cart");
            while (rs.next()) {
                Cart product = new Cart();
                product.setId(rs.getInt(1));
                product.setProductId(rs.getInt(2));
                product.setItem(rs.getString(3));
                product.setUnitPrice(rs.getDouble(4));
                product.setQty(rs.getInt(5));
                product.setTotalPrice(rs.getDouble(6));
                productList.add(product);
            }
            rs.close();
            statement.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return productList;
    }

    @Override
    public String purchase(List<Cart> list,User user) {
//        for orderno;
        String id = null;
        String date = null;
        int userId = user.getId();
        int itemNo = list.size();
        Double totalAmount = 0.00;
        for (Cart t : list) {
            totalAmount =totalAmount+t.getTotalPrice();
        }
        System.out.println("total Amount::"+totalAmount);
         try {
            Connection conn = null;
            conn = DataBaseConnection.getInstance().getConnection();
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery("SELECT IFNULL(MAX(id),'1') as id,DATE_FORMAT(NOW(),'%Y-%m-%d') as date FROM orders");
            while (rs.next()) {
                id = rs.getString("id");
                date = rs.getString("date");
            }
            rs.close();
            statement.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        String[] finalDate = date.split("-");
        String orderNo = finalDate[0]+finalDate[1]+finalDate[2]+id;
        // orders
        try {
            Connection conn = null;
            conn = DataBaseConnection.getInstance().getConnection();
            String query = "insert into orders(no_item,order_date,total_amount,status,order_no,user_id) values(?,?,?,?,?,?)";
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setInt(1, itemNo);
            preparedStmt.setDate(2, this.date);
            preparedStmt.setDouble(3, totalAmount);
            preparedStmt.setString(4, StatusEnum.PENDING.toString());
            preparedStmt.setString(5, orderNo);
            preparedStmt.setInt(6, userId);
            preparedStmt.executeUpdate();
            preparedStmt.close();
        } catch (Exception e) {
            System.out.println("error add orders :" + e.getMessage());
        }
        //orderItem
        for (Cart tem : list) {
             try {
                Connection conn = null;
                conn = DataBaseConnection.getInstance().getConnection();
                String query = "insert into order_item(order_no,product_id,qty) values(?,?,?)";
                PreparedStatement preparedStmt = conn.prepareStatement(query);
                preparedStmt.setString(1, orderNo);
                preparedStmt.setInt(2, tem.getProductId());
                preparedStmt.setDouble(3, tem.getQty());
                preparedStmt.executeUpdate();
                preparedStmt.close();
            } catch (Exception e) {
                System.out.println("error add order_item :" + e.getMessage());
            }
        }
        //update item quantity
        for (Cart tem : list) {
            int quantity = 0;
            try {
                conn = DataBaseConnection.getInstance().getConnection();
                String query = "SELECT total_quantity FROM product WHERE id=?";
                PreparedStatement preparedStmt = conn.prepareStatement(query);
                preparedStmt.setInt(1, tem.getProductId());
                ResultSet rs = preparedStmt.executeQuery();
                while (rs.next()) {
                    quantity = rs.getInt(1);
                }
                rs.close();
                preparedStmt.close();
            } catch (Exception e) {
                System.out.println("error get total qty :" + e.getMessage());
            }
            int currentQty = quantity - tem.getQty();
            try {
                conn = DataBaseConnection.getInstance().getConnection();
                String query = "UPDATE product SET total_quantity=? where id=?";
                PreparedStatement preparedStmt = conn.prepareStatement(query);
                preparedStmt.setInt(1, currentQty);
                preparedStmt.setInt(2, tem.getProductId());
                preparedStmt.executeUpdate();
                preparedStmt.close();

            } catch (Exception e) {
                System.out.println("error update qty:" + e.getMessage());
            }

        }

        return "TRUE";
    }

    @Override
    public List<OrderListDto> getOrderList(User user) {
        String role=user.getRole().toString();
        String params="";
        if(role==(RoleTypeEnum.ADMIN.toString()) || role==(RoleTypeEnum.MANAGER.toString())){
        }else{
           params= "WHERE o.user_id="+user.getId(); 
        }
        List<OrderListDto> orderList=new ArrayList<>();
        try {
            Connection conn = null;
            conn = DataBaseConnection.getInstance().getConnection();
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(
                    "SELECT CONCAT(u.first_name ,' ',u.last_name) AS NAME ,o.order_no,o.no_item,o.total_amount,o.order_date\n" +
                    "FROM orders o INNER JOIN USER u ON o.user_id=u.id "+params+" ");
            while (rs.next()) {
                OrderListDto order=new OrderListDto();
                order.setOrderBy(rs.getString(1));
                order.setOrderNo(rs.getString(2));
                order.setItemNo(rs.getInt(3));
                order.setTotalAmt(rs.getDouble(4));
                order.setOrderDate(rs.getString(5));
                order.setAction("VIEW DETAILS");
                orderList.add(order);
            }
            rs.close();
            statement.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return orderList;
    }

    @Override
    public List<Map<String, String>> getOrderItemList(String orderNo) {
        List<Map<String,String>> orderItemList = new ArrayList<>();
        try {
            conn = DataBaseConnection.getInstance().getConnection();
            String query = "SELECT oi.order_no,p.id,p.product_name,p.product_type,oi.qty,p.price,p.description FROM product p "
                    + "INNER JOIN order_item oi ON p.id=oi.product_id where oi.order_no=?";
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setString(1, orderNo);
            ResultSet rs = preparedStmt.executeQuery();
            while (rs.next()) {
                Map<String,String> orderItemMap=new HashMap<>();
                orderItemMap.put("orderNo", rs.getString(1));
                orderItemMap.put("productName",rs.getString(2)+"-"+rs.getString(3)+"("+rs.getString(4)+")");
                orderItemMap.put("qty", rs.getString(5));
                orderItemMap.put("unitPrice", rs.getString(6));
                orderItemMap.put("description", rs.getString(7));
                orderItemList.add(orderItemMap);
            }
            rs.close();
            preparedStmt.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return orderItemList;
    }

    @Override
    public List<OrderListDto> getOrderListSearch(User user,String userName, String orderNo) {
        String role=user.getRole().toString();
        String params="";
        if(role==(RoleTypeEnum.ADMIN.toString()) || role==(RoleTypeEnum.MANAGER.toString())){
        }else{
           params= "o.user_id="+user.getId()+" AND "; 
        }
        List<OrderListDto> orderList=new ArrayList<>();
        try {
            Connection conn = null;
            conn = DataBaseConnection.getInstance().getConnection();
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(
                    "SELECT CONCAT(u.first_name ,' ',u.last_name) AS NAME ,o.order_no,o.no_item,o.total_amount,o.order_date\n" +
                    "FROM orders o INNER JOIN USER u ON o.user_id=u.id where "+params+"  u.username like '%"+userName+"%' AND o.order_no like '%"+orderNo+"%' ");
            while (rs.next()) {
                OrderListDto order=new OrderListDto();
                order.setOrderBy(rs.getString(1));
                order.setOrderNo(rs.getString(2));
                order.setItemNo(rs.getInt(3));
                order.setTotalAmt(rs.getDouble(4));
                order.setOrderDate(rs.getString(5));
                order.setAction("VIEW DETAILS");
                orderList.add(order);
            }
            rs.close();
            statement.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return orderList;
    }

}
