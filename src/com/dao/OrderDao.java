/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao;

import com.dto.OrderListDto;
import com.model.Cart;
import com.model.User;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Arun Tamang
 */
public interface OrderDao {
   String saveUpdate(Cart order);
   boolean existingItem(String item);
   boolean deleteAll();
   boolean deleteById(int id);
   List<Cart> getAllTempList();
   String purchase(List<Cart> list,User user);
   List<OrderListDto> getOrderList(User user);
   List<Map<String, String>> getOrderItemList(String orderNo);
   List<OrderListDto> getOrderListSearch(User user,String userName,String orderNo);
   
      
}
