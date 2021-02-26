package com.dao.daoImpl;

import com.dao.ProductDao;
import com.db.DataBaseConnection;
import com.model.Product;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ProductDaoImpl implements ProductDao {

    @Override
    public String saveUpdate(Product product) {
        Connection conn = null;
        int id = product.getId();
        long millis = System.currentTimeMillis();
        java.sql.Date date = new java.sql.Date(millis);

        if (id == 0) {
            boolean existingUser = existingProduct(product.getProductName(), product.getProductType());
            if (existingUser == true) {
                return product.getProductName() + " and " + product.getProductType() + " product is already exist please make unique. ";
            } else {
                try {
                    conn = DataBaseConnection.getInstance().getConnection();
                    String query = "INSERT INTO product(product_name,product_type,price,total_quantity,description,created_date) VALUES (?,?,?,?,?,?)";
                    PreparedStatement preparedStmt = conn.prepareStatement(query);
                    preparedStmt.setString(1, product.getProductName().toUpperCase());
                    preparedStmt.setString(2, product.getProductType());
                    preparedStmt.setDouble(3, product.getPrice());
                    preparedStmt.setInt(4, product.getTotalQty());
                    preparedStmt.setString(5, product.getDescription());
                    preparedStmt.setDate(6, date);
                    preparedStmt.executeUpdate();
                    preparedStmt.close();
                } catch (Exception e) {
                    System.out.println("error:" + e.getMessage());
                }
                return product.getProductName() + " Product Successfully Saved";
            }

        } else {
            try {
                conn = DataBaseConnection.getInstance().getConnection();
                String query = "update product set product_name=?,product_type=?,price=?,total_quantity=?,description=?,updated_date=? where id=?";
                PreparedStatement preparedStmt = conn.prepareStatement(query);
                preparedStmt.setString(1, product.getProductName());
                preparedStmt.setString(2, product.getProductType());
                preparedStmt.setDouble(3, product.getPrice());
                preparedStmt.setInt(4, product.getTotalQty());
                preparedStmt.setString(5, product.getDescription());
                preparedStmt.setDate(6, date);
                preparedStmt.setInt(7, product.getId());
                preparedStmt.executeUpdate();
                preparedStmt.close();
                System.out.println("Update");
            } catch (Exception e) {
                System.out.println("error:" + e.getMessage());
            }
            return product.getProductName() + " Product Successfully Updated";
        }
    }

    @Override
    public List<Product> getAllProduct() {
        List<Product> productList = new ArrayList<>();
        Connection conn = null;
        try {
            conn = DataBaseConnection.getInstance().getConnection();
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery("select id,product_name,product_type,price,total_quantity,description FROM product");
            while (rs.next()) {
                Product product = new Product();
                product.setId(rs.getInt(1));
                product.setProductName(rs.getString(2));
                product.setProductType(rs.getString(3));
                product.setPrice(rs.getDouble(4));
                product.setTotalQty(rs.getInt(5));
                product.setDescription(rs.getString(6));
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
    public boolean deleteProduct(int productId) {
        Connection conn = null;
        try {
            conn = DataBaseConnection.getInstance().getConnection();
            String query = "delete from product where id=?";
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setInt(1, productId);
            preparedStmt.executeUpdate();
            preparedStmt.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return true;
    }

    @Override
    public Product getById(int productId) {
        Connection conn = null;
        Product product = new Product();
        try {
            conn = DataBaseConnection.getInstance().getConnection();
            String query = "select id,product_name,product_type,price,total_quantity,description FROM product where id=? ";
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setInt(1, productId);
            ResultSet rs = preparedStmt.executeQuery();
            while (rs.next()) {
                product.setId(rs.getInt(1));
                product.setProductName(rs.getString(2));
                product.setProductType(rs.getString(3));
                product.setPrice(rs.getDouble(4));
                product.setTotalQty(rs.getInt(5));
                product.setDescription(rs.getString(6));
            }
            rs.close();
            preparedStmt.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return product;
    }

    @Override
    public boolean existingProduct(String name, String type) {
        Connection conn = null;
        Integer id = null;
        try {
            conn = DataBaseConnection.getInstance().getConnection();
            String query = "SELECT id FROM PRODUCT WHERE PRODUCT_NAME =? AND PRODUCT_TYPE=? ";
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setString(1, name);
            preparedStmt.setString(2, type);
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
    public List<Product> getAllProductSearch(String name, String type) {
        List<Product> productList = new ArrayList<>();
        Connection conn = null;
        try {
            conn = DataBaseConnection.getInstance().getConnection();
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery("select id,product_name,product_type,price,total_quantity,description FROM product where product_name like '%"+name+"%' and product_type like '%"+type+"%' ");
            while (rs.next()) {
                Product product = new Product();
                product.setId(rs.getInt(1));
                product.setProductName(rs.getString(2));
                product.setProductType(rs.getString(3));
                product.setPrice(rs.getDouble(4));
                product.setTotalQty(rs.getInt(5));
                product.setDescription(rs.getString(6));
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
    public List<String> getProductItem() {
        List<String> productList = new ArrayList<>();
        Connection conn = null;
        try {
            conn = DataBaseConnection.getInstance().getConnection();
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery("select id,product_name,product_type,price,total_quantity,description FROM product where total_quantity >0");
            while (rs.next()) {
                String finalItem=String.valueOf(rs.getInt(1))+"-"+rs.getString(2)+"("+rs.getString(3)+")";
                productList.add(finalItem);
            }
            rs.close();
            statement.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return productList;
    }

}
