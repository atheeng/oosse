package com.dao;

import com.model.Product;
import java.util.List;

public interface ProductDao {

    String saveUpdate(Product product);

    List<Product> getAllProduct();
    
    List<String> getProductItem();

    List<Product> getAllProductSearch(String name, String type);

    boolean deleteProduct(int productId);

    Product getById(int productId);

    boolean existingProduct(String name, String type);
    

}
