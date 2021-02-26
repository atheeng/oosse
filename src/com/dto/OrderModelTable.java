/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dto;

import com.model.Product;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
public class OrderModelTable extends AbstractTableModel{
    List<OrderListDto> orderList = new ArrayList<>();

    private final String[] columnNames = new String[]{
        "Order By", "Order No.","ItmeNo", "Total Amt", "Date","ACTION"
    };
    private final Class[] columnClass = new Class[]{
        String.class, String.class , String.class, String.class,String.class,String.class
    };

    public OrderModelTable(List<OrderListDto> orderList) {
        this.orderList = orderList;
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
        return orderList.size();
    }
    @Override
    public int getColumnCount() {
         return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        OrderListDto row = orderList.get(rowIndex);
        if(0 == columnIndex) {
            return row.getOrderBy();
        }
        else if(1 == columnIndex) {
            return row.getOrderNo();
        }
        else if(2 == columnIndex) {
             return row.getItemNo();
        }
        else if(3 == columnIndex) {
            return row.getTotalAmt();
        }
        else if(4 == columnIndex) {
            return row.getOrderDate();
        }
        else if(5 == columnIndex) {
            return row.getAction();
        }
        return null;
    }
}
