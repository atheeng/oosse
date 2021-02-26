/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dto;

import com.model.Cart;
import com.model.User;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Arun Tamang
 */
public class TempOrderModelTable extends AbstractTableModel{
    List<Cart> tempOrders = new ArrayList<>();

    private final String[] columnNames = new String[]{
        "order Id", "product Id","Item", "Qty","Unit Rate", "Total Price",
    };
    private final Class[] columnClass = new Class[]{
        Integer.class, String.class, String.class , String.class, String.class,String.class, String.class
    };

    public TempOrderModelTable(List<Cart> tempOrders) {
        this.tempOrders = tempOrders;
    }

    @Override
     public String getColumnName(int column)
    {
        return columnNames[column];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return columnClass[columnIndex];
    }

    @Override
    public int getRowCount()
    {
        return tempOrders.size();
    }
    @Override
    public int getColumnCount() {
         return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Cart row = tempOrders.get(rowIndex);
        if(0 == columnIndex) {
            return row.getId();
        }
        else if(1 == columnIndex) {
            return row.getProductId();
        }
        else if(2 == columnIndex) {
             return row.getItem();
        }
        else if(3 == columnIndex) {
            return row.getQty();
        }
        else if(4 == columnIndex) {
            return row.getUnitPrice();
        }
         else if(5 == columnIndex) {
            return row.getQty()*row.getUnitPrice();
        }
        return null;
    }
}
