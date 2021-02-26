package com.dashboard;

import com.dao.daoImpl.UserDaoImpl;
import com.Enum.RoleTypeEnum;
import com.dao.OrderDao;
import com.dao.ProductDao;
import com.dao.UserDao;
import com.dao.daoImpl.OrderDaoImpl;
import com.dao.daoImpl.ProductDaoImpl;
import com.dto.OrderListDto;
import com.dto.OrderModelTable;
import com.dto.ProductModelTable;
import com.dto.TempOrderModelTable;
import com.dto.UserModelTable;
import com.model.Product;
import com.model.Cart;
import com.model.User;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import view.LoginForm;
import view.OrderViewFrame;
import view.ProductFrame;

public class Dashboard extends javax.swing.JFrame {

    User user;
    int txt_user_id = 0;
    int txt_product_id = 0;
    int txt_temp_order_id = 0;

    public Dashboard() {
        initComponents();

    }

    public Dashboard(User user) {
        initComponents();
        this.user = user;
        label_user.setText("WELCOME " + user.getUsername().toUpperCase());
        txt_nos_items.setText("0");
        txt_grand_total.setText("0.00");
        txt_nos_items.setEditable(false);
        txt_grand_total.setEditable(false);
        menuTab.setSelectedIndex(0);

        int selectedMenu = menuTab.getSelectedIndex();
        String MenuName = menuTab.getTitleAt(selectedMenu);
        productItemList();

        if (user.getRole().toString() == "CUSTOMER") {
            menuTab.remove(1);
            menuTab.remove(0);
        }
        switch (MenuName.toUpperCase()) {
            case "CUSTOMER":
                loadTableCustomer();
                break;
            case "PRODUCT":
                loadTableProduct();
                break;
            case "ORDERLIST":
                loadOrderListTable();
                break;
        }
    }

    public void deleteAllTempOrderList() {
        OrderDao od = new OrderDaoImpl();
        od.deleteAll();
    }

    public void productItemList() {
        ProductDao pd = new ProductDaoImpl();
        List<String> list = pd.getProductItem();
        for (String l : list) {
            txt_order_items.addItem(l);
        }

        txt_order_price.setEnabled(false);
        txt_order_available_qty.setEditable(false);

    }

    public void loadTableCustomer() {
        UserDao ud = new UserDaoImpl();
        List<User> list = ud.getAllUsers();
        UserModelTable model = new UserModelTable(list);
        customer_table.setModel(model);
    }

    public void loadTableProduct() {
        ProductDao pd = new ProductDaoImpl();
        List<Product> list = pd.getAllProduct();
        System.out.println("list product:" + list.size());
        ProductModelTable model = new ProductModelTable(list);
        product_table.setModel(model);
    }

    public void loadTableTempOrder() {
        OrderDao od = new OrderDaoImpl();
        List<Cart> list = od.getAllTempList();
        TempOrderModelTable model = new TempOrderModelTable(list);
        temp_order_table.setModel(model);
        double grandTotal = 0.00;
        for (Cart t : list) {
            grandTotal += t.getTotalPrice();
        }
        int listSize = list.size();
        txt_nos_items.setText(String.valueOf(listSize));
        txt_grand_total.setText(String.valueOf(grandTotal));
        txt_nos_items.setEditable(false);
        txt_grand_total.setEditable(false);
    }

    public void resetCustomerForm() {
        txt_user_username.setText("");
        txt_user_first_name.setText("");
        txt_user_password.setText("");
        cb_user_role.setSelectedIndex(0);
        txt_user_first_name.setText("");
        txt_user_last_name.setText("");
        txt_user_address.setText("");
        txt_user_mobile_no.setText("");
        txt_user_id = 0;
    }

    public void resetProductForm() {
        txt_product_name.setText("");
        txt_product_name.setEditable(true);
        txt_product_price.setText(null);
        txt_product_quantity.setText(null);
        cb_product_type.setSelectedIndex(0);
        cb_product_type.setEnabled(true);
        txt_product_description.setText("");
        txt_product_id = 0;
    }

    public void resetOrderForm() {
        txt_order_items.setSelectedIndex(0);
        txt_order_price.setText(null);
        txt_order_available_qty.setText(null);
        txt_order_required_qty.setText(null);
    }

    public void loadOrderListTable() {
        OrderDao od = new OrderDaoImpl();
        List<OrderListDto> list = od.getOrderList(user);
        OrderModelTable model = new OrderModelTable(list);
        order_table.setModel(model);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jComboBox1 = new javax.swing.JComboBox<>();
        jMenuBar2 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        menuTab = new javax.swing.JTabbedPane();
        customer_panel = new javax.swing.JPanel();
        customer_form = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();
        txt_user_first_name = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        txt_user_username = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        txt_user_last_name = new javax.swing.JTextField();
        user_save_update = new javax.swing.JButton();
        user_delete = new javax.swing.JButton();
        cb_user_role = new javax.swing.JComboBox<>();
        txt_user_address = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        txt_user_mobile_no = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txt_user_password = new javax.swing.JPasswordField();
        user_reset = new javax.swing.JButton();
        product_search_panel1 = new javax.swing.JPanel();
        customer_search = new javax.swing.JButton();
        txt_user_search_username = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        customer_table = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        reloadCustomerTable = new javax.swing.JButton();
        productPanel = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        txt_product_name = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        txt_product_quantity = new javax.swing.JTextField();
        cb_product_type = new javax.swing.JComboBox<>();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        txt_product_price = new javax.swing.JTextField();
        product_save_update = new javax.swing.JButton();
        product_delete = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        txt_product_description = new javax.swing.JTextArea();
        reset_product = new javax.swing.JButton();
        product_search_panel = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        product_table = new javax.swing.JTable();
        product_search = new javax.swing.JButton();
        txt_product_search_type = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txt_product_search_name = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        reloadProductTable = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        cart = new javax.swing.JPanel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        txt_order_available_qty = new javax.swing.JTextField();
        txt_order_price = new javax.swing.JTextField();
        order_add_update = new javax.swing.JButton();
        order_delete = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        reset_order = new javax.swing.JButton();
        txt_order_items = new javax.swing.JComboBox<>();
        txt_order_required_qty = new javax.swing.JTextField();
        product_search_panel2 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        temp_order_table = new javax.swing.JTable();
        txt_purchase = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txt_nos_items = new javax.swing.JTextField();
        txt_grand_total = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        product_search_panel3 = new javax.swing.JPanel();
        jScrollPane7 = new javax.swing.JScrollPane();
        order_table = new javax.swing.JTable();
        order_search = new javax.swing.JButton();
        txt_search_order_no = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txt_search_user = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        reloadorderList = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        label_user = new javax.swing.JLabel();
        logout = new javax.swing.JButton();

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jMenu1.setText("File");
        jMenuBar2.add(jMenu1);

        jMenu2.setText("Edit");
        jMenuBar2.add(jMenu2);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTextField1.setText("jTextField1");

        jTextField2.setText("jTextField2");

        jTextField3.setText("jTextField3");

        menuTab.setBackground(new java.awt.Color(153, 255, 255));
        menuTab.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menuTabMouseClicked(evt);
            }
        });

        customer_form.setBorder(javax.swing.BorderFactory.createTitledBorder("Customer Form"));

        jLabel20.setText("First Name:");

        txt_user_first_name.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_user_first_nameActionPerformed(evt);
            }
        });

        jLabel21.setText("Last Name:");

        jLabel22.setText("Username:");

        jLabel23.setText("Address:");

        jLabel24.setText("Role:");

        user_save_update.setText("SAVE/UPDATE");
        user_save_update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                user_save_updateActionPerformed(evt);
            }
        });

        user_delete.setText("DELETE");
        user_delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                user_deleteActionPerformed(evt);
            }
        });

        cb_user_role.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "SELECT ROLE", "ADMIN", "MANAGER", "CUSTOMER" }));
        cb_user_role.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb_user_roleActionPerformed(evt);
            }
        });

        txt_user_address.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_user_addressActionPerformed(evt);
            }
        });

        jLabel1.setText("MobileNo:");

        jLabel7.setText("Password:");

        txt_user_password.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_user_passwordActionPerformed(evt);
            }
        });

        user_reset.setText("RESET");
        user_reset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                user_resetActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout customer_formLayout = new javax.swing.GroupLayout(customer_form);
        customer_form.setLayout(customer_formLayout);
        customer_formLayout.setHorizontalGroup(
            customer_formLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(customer_formLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(customer_formLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(customer_formLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(user_reset, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(customer_formLayout.createSequentialGroup()
                        .addGroup(customer_formLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(customer_formLayout.createSequentialGroup()
                                .addComponent(user_save_update, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(user_delete, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(customer_formLayout.createSequentialGroup()
                                .addGroup(customer_formLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel21)
                                    .addComponent(jLabel24)
                                    .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel1))
                                .addGap(32, 32, 32)
                                .addGroup(customer_formLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txt_user_address, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(txt_user_first_name)
                                    .addComponent(txt_user_username)
                                    .addComponent(txt_user_last_name)
                                    .addComponent(cb_user_role, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txt_user_password)
                                    .addComponent(txt_user_mobile_no, javax.swing.GroupLayout.Alignment.TRAILING))))
                        .addGap(10, 10, 10))))
        );
        customer_formLayout.setVerticalGroup(
            customer_formLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(customer_formLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(customer_formLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20)
                    .addComponent(txt_user_first_name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(customer_formLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel21)
                    .addComponent(txt_user_last_name, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(customer_formLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel22)
                    .addComponent(txt_user_username, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(customer_formLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txt_user_password, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(customer_formLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cb_user_role, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel24))
                .addGap(18, 18, 18)
                .addGroup(customer_formLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel23)
                    .addComponent(txt_user_address, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(customer_formLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_user_mobile_no, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(13, 13, 13)
                .addComponent(user_reset)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(customer_formLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(user_delete)
                    .addComponent(user_save_update))
                .addContainerGap(52, Short.MAX_VALUE))
        );

        product_search_panel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Customer Details"));

        customer_search.setText("SEARCH");
        customer_search.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                customer_searchActionPerformed(evt);
            }
        });

        txt_user_search_username.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_user_search_usernameActionPerformed(evt);
            }
        });

        customer_table.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        customer_table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null}
            },
            new String [] {
                "id", "username", "full name", "role", "address", "contact no"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, true, true, true, true, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        customer_table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                customer_tableMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(customer_table);
        if (customer_table.getColumnModel().getColumnCount() > 0) {
            customer_table.getColumnModel().getColumn(0).setHeaderValue("id");
            customer_table.getColumnModel().getColumn(1).setHeaderValue("username");
            customer_table.getColumnModel().getColumn(2).setHeaderValue("full name");
            customer_table.getColumnModel().getColumn(3).setHeaderValue("role");
            customer_table.getColumnModel().getColumn(4).setHeaderValue("address");
            customer_table.getColumnModel().getColumn(5).setHeaderValue("contact no");
        }

        jLabel2.setText("username");

        reloadCustomerTable.setText("Table Reload");
        reloadCustomerTable.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reloadCustomerTableActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout product_search_panel1Layout = new javax.swing.GroupLayout(product_search_panel1);
        product_search_panel1.setLayout(product_search_panel1Layout);
        product_search_panel1Layout.setHorizontalGroup(
            product_search_panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(product_search_panel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(product_search_panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addGroup(product_search_panel1Layout.createSequentialGroup()
                        .addComponent(reloadCustomerTable, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 268, Short.MAX_VALUE)
                        .addComponent(jLabel2)
                        .addGap(26, 26, 26)
                        .addComponent(txt_user_search_username, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(35, 35, 35)
                        .addComponent(customer_search, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        product_search_panel1Layout.setVerticalGroup(
            product_search_panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, product_search_panel1Layout.createSequentialGroup()
                .addGroup(product_search_panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(product_search_panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(customer_search)
                        .addComponent(txt_user_search_username, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel2))
                    .addGroup(product_search_panel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(reloadCustomerTable)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout customer_panelLayout = new javax.swing.GroupLayout(customer_panel);
        customer_panel.setLayout(customer_panelLayout);
        customer_panelLayout.setHorizontalGroup(
            customer_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(customer_panelLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(customer_form, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(product_search_panel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        customer_panelLayout.setVerticalGroup(
            customer_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(customer_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(customer_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(customer_form, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(product_search_panel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 113, Short.MAX_VALUE))
        );

        menuTab.addTab("Customer", customer_panel);

        productPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder("Product Form"));

        jLabel15.setText("Name");

        txt_product_name.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_product_nameActionPerformed(evt);
            }
        });

        jLabel16.setText("Type");

        jLabel17.setText("Quantity");

        cb_product_type.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select Type", "Electronic_Media", "Foods", "Furniture_Appliances", "Clothing", "Books" }));

        jLabel18.setText("Description");

        jLabel19.setText("Unit Price");

        product_save_update.setText("SAVE/UPDATE");
        product_save_update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                product_save_updateActionPerformed(evt);
            }
        });

        product_delete.setText("DELETE");
        product_delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                product_deleteActionPerformed(evt);
            }
        });

        txt_product_description.setColumns(20);
        txt_product_description.setRows(5);
        jScrollPane4.setViewportView(txt_product_description);

        reset_product.setText("RESET");
        reset_product.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reset_productActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel16)
                            .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel17)
                            .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(32, 32, 32)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_product_name)
                            .addComponent(txt_product_quantity)
                            .addComponent(cb_product_type, javax.swing.GroupLayout.Alignment.TRAILING, 0, 146, Short.MAX_VALUE)
                            .addComponent(txt_product_price)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                        .addComponent(product_save_update, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(product_delete, javax.swing.GroupLayout.DEFAULT_SIZE, 91, Short.MAX_VALUE)
                            .addComponent(reset_product, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(txt_product_name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(cb_product_type, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(txt_product_quantity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(jLabel19))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(txt_product_price, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel18)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addComponent(reset_product)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(product_delete)
                    .addComponent(product_save_update))
                .addContainerGap(40, Short.MAX_VALUE))
        );

        product_search_panel.setBorder(javax.swing.BorderFactory.createTitledBorder("Product Details"));

        product_table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null}
            },
            new String [] {
                "Name", "Type", "Quantity", "Price", "Description"
            }
        ));
        product_table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                product_tableMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(product_table);

        product_search.setText("SEARCH");
        product_search.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                product_searchActionPerformed(evt);
            }
        });

        txt_product_search_type.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_product_search_typeActionPerformed(evt);
            }
        });

        jLabel3.setText("Product Type");

        txt_product_search_name.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_product_search_nameActionPerformed(evt);
            }
        });

        jLabel4.setText("Product Name");

        reloadProductTable.setText("reload table");
        reloadProductTable.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reloadProductTableActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout product_search_panelLayout = new javax.swing.GroupLayout(product_search_panel);
        product_search_panel.setLayout(product_search_panelLayout);
        product_search_panelLayout.setHorizontalGroup(
            product_search_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, product_search_panelLayout.createSequentialGroup()
                .addContainerGap(17, Short.MAX_VALUE)
                .addGroup(product_search_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 871, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(product_search_panelLayout.createSequentialGroup()
                        .addComponent(reloadProductTable, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(132, 132, 132)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txt_product_search_name, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txt_product_search_type, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(product_search, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        product_search_panelLayout.setVerticalGroup(
            product_search_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, product_search_panelLayout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(product_search_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_product_search_type, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(product_search)
                    .addComponent(jLabel3)
                    .addComponent(txt_product_search_name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(reloadProductTable))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout productPanelLayout = new javax.swing.GroupLayout(productPanel);
        productPanel.setLayout(productPanelLayout);
        productPanelLayout.setHorizontalGroup(
            productPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(productPanelLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(product_search_panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        productPanelLayout.setVerticalGroup(
            productPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(productPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(productPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(product_search_panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(128, Short.MAX_VALUE))
        );

        menuTab.addTab("Product", productPanel);

        cart.setBorder(javax.swing.BorderFactory.createTitledBorder("Cart"));

        jLabel25.setText("Item/product:");

        jLabel26.setText("Unit Price:");

        jLabel27.setText("Available Qty:");

        txt_order_available_qty.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        txt_order_available_qty.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_order_available_qtyActionPerformed(evt);
            }
        });

        txt_order_price.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N

        order_add_update.setText("Add/Update Cart");
        order_add_update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                order_add_updateActionPerformed(evt);
            }
        });

        order_delete.setText("DELETE");
        order_delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                order_deleteActionPerformed(evt);
            }
        });

        jLabel9.setText("Required Qty:");

        reset_order.setText("RESET");
        reset_order.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reset_orderActionPerformed(evt);
            }
        });

        txt_order_items.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select Item" }));
        txt_order_items.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                txt_order_itemsItemStateChanged(evt);
            }
        });
        txt_order_items.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txt_order_itemsMouseClicked(evt);
            }
        });
        txt_order_items.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_order_itemsActionPerformed(evt);
            }
        });

        txt_order_required_qty.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_order_required_qtyActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout cartLayout = new javax.swing.GroupLayout(cart);
        cart.setLayout(cartLayout);
        cartLayout.setHorizontalGroup(
            cartLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cartLayout.createSequentialGroup()
                .addGroup(cartLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(cartLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(cartLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(cartLayout.createSequentialGroup()
                                .addComponent(jLabel26)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txt_order_price, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(cartLayout.createSequentialGroup()
                                .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_order_items, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(cartLayout.createSequentialGroup()
                                .addGroup(cartLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(100, 100, 100)
                                .addGroup(cartLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txt_order_available_qty)
                                    .addComponent(txt_order_required_qty, javax.swing.GroupLayout.DEFAULT_SIZE, 109, Short.MAX_VALUE)))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, cartLayout.createSequentialGroup()
                        .addContainerGap(41, Short.MAX_VALUE)
                        .addComponent(order_add_update, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(order_delete, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, cartLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(reset_order, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        cartLayout.setVerticalGroup(
            cartLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cartLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(cartLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel25)
                    .addComponent(txt_order_items, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(cartLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel26)
                    .addComponent(txt_order_price, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(cartLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel27)
                    .addComponent(txt_order_available_qty, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(13, 13, 13)
                .addGroup(cartLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(txt_order_required_qty, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16)
                .addComponent(reset_order)
                .addGap(11, 11, 11)
                .addGroup(cartLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(order_delete)
                    .addComponent(order_add_update))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        product_search_panel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Cart Details"));

        temp_order_table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "order Id", "product Id", "item", "quantity", "unit rate", "total price"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        temp_order_table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                temp_order_tableMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(temp_order_table);

        txt_purchase.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        txt_purchase.setForeground(new java.awt.Color(0, 0, 255));
        txt_purchase.setText("Confirm Order");
        txt_purchase.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_purchaseActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel5.setText("number of items:");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel6.setText("grand total:");

        txt_nos_items.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        txt_grand_total.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        javax.swing.GroupLayout product_search_panel2Layout = new javax.swing.GroupLayout(product_search_panel2);
        product_search_panel2.setLayout(product_search_panel2Layout);
        product_search_panel2Layout.setHorizontalGroup(
            product_search_panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(product_search_panel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(product_search_panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txt_purchase, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(product_search_panel2Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(64, 64, 64)
                        .addComponent(txt_nos_items, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(product_search_panel2Layout.createSequentialGroup()
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(33, 33, 33)
                        .addComponent(txt_grand_total, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(17, 17, 17))
            .addGroup(product_search_panel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 809, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(18, Short.MAX_VALUE))
        );
        product_search_panel2Layout.setVerticalGroup(
            product_search_panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(product_search_panel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(product_search_panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_nos_items, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addGap(18, 18, 18)
                .addGroup(product_search_panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_grand_total, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addGap(18, 18, 18)
                .addComponent(txt_purchase)
                .addContainerGap(28, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cart, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(product_search_panel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cart, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(product_search_panel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(129, Short.MAX_VALUE))
        );

        menuTab.addTab("Order", jPanel3);

        product_search_panel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Order List"));

        order_table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null}
            },
            new String [] {
                "Order By", "Order No.", "Item No.", "Total Amt.", "Order Date", "Action"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, true, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        order_table.setColumnSelectionAllowed(true);
        order_table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                order_tableMouseClicked(evt);
            }
        });
        jScrollPane7.setViewportView(order_table);
        order_table.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        order_search.setText("SEARCH");
        order_search.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                order_searchMouseClicked(evt);
            }
        });
        order_search.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                order_searchActionPerformed(evt);
            }
        });
        order_search.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                order_searchKeyTyped(evt);
            }
        });

        txt_search_order_no.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_search_order_noActionPerformed(evt);
            }
        });

        jLabel8.setText("Order No.");

        txt_search_user.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
                txt_search_userInputMethodTextChanged(evt);
            }
        });
        txt_search_user.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_search_userActionPerformed(evt);
            }
        });

        jLabel10.setText("By User");

        reloadorderList.setText("reload table");
        reloadorderList.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reloadorderListActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout product_search_panel3Layout = new javax.swing.GroupLayout(product_search_panel3);
        product_search_panel3.setLayout(product_search_panel3Layout);
        product_search_panel3Layout.setHorizontalGroup(
            product_search_panel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, product_search_panel3Layout.createSequentialGroup()
                .addContainerGap(57, Short.MAX_VALUE)
                .addGroup(product_search_panel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(product_search_panel3Layout.createSequentialGroup()
                        .addComponent(reloadorderList, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(119, 119, 119)
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txt_search_user, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26)
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txt_search_order_no, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(order_search, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 891, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(53, 53, 53))
        );
        product_search_panel3Layout.setVerticalGroup(
            product_search_panel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, product_search_panel3Layout.createSequentialGroup()
                .addGroup(product_search_panel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(product_search_panel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txt_search_order_no, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(order_search)
                        .addComponent(jLabel8)
                        .addComponent(txt_search_user, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel10))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, product_search_panel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(reloadorderList)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 293, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(98, Short.MAX_VALUE)
                .addComponent(product_search_panel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(97, 97, 97))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(product_search_panel3, javax.swing.GroupLayout.PREFERRED_SIZE, 384, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(133, Short.MAX_VALUE))
        );

        menuTab.addTab("OrderList", jPanel2);

        jPanel1.setBackground(new java.awt.Color(153, 255, 255));

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel14.setText("ONLINE SHOPPING MANAGEMENT SYSTEM");

        label_user.setText("user");

        logout.setText("LOGOUT");
        logout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logoutActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(503, 503, 503)
                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(label_user, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(logout, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(12, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel14)
                        .addGap(10, 10, 10))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(logout)
                            .addComponent(label_user, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap())))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(45, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(menuTab, javax.swing.GroupLayout.PREFERRED_SIZE, 1210, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(menuTab, javax.swing.GroupLayout.PREFERRED_SIZE, 556, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txt_user_first_nameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_user_first_nameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_user_first_nameActionPerformed

    private void txt_user_search_usernameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_user_search_usernameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_user_search_usernameActionPerformed

    private void txt_user_addressActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_user_addressActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_user_addressActionPerformed

    private void logoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logoutActionPerformed
        LoginForm loginForm = new LoginForm();
        this.dispose();
        loginForm.setVisible(true);
        loginForm.setResizable(false);
    }//GEN-LAST:event_logoutActionPerformed

    private void customer_tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_customer_tableMouseClicked
        // TODO add your handling code here:
        txt_user_id = Integer.parseInt(customer_table.getValueAt(customer_table.getSelectedRow(), 0).toString());
        UserDao ud = new UserDaoImpl();
        User user = ud.getById(txt_user_id);
        txt_user_username.setText(user.getUsername());
        txt_user_username.setEditable(false);
        txt_user_first_name.setText(user.getFirstName());
        txt_user_last_name.setText(user.getLastName());
        txt_user_password.setText(user.getPassword());
        txt_user_address.setText(user.getAddress());
        txt_user_mobile_no.setText(user.getMobileNo());
        cb_user_role.setSelectedItem(user.getRole().toString());
    }//GEN-LAST:event_customer_tableMouseClicked

    private void txt_user_passwordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_user_passwordActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_user_passwordActionPerformed

    private void user_save_updateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_user_save_updateActionPerformed
        int userId = txt_user_id;
        String firstName = txt_user_first_name.getText().trim();
        String lastName = txt_user_last_name.getText().trim();
        String userName = txt_user_username.getText().trim();
        String password = txt_user_password.getText().trim();
        String roles = cb_user_role.getSelectedItem().toString();
        String address = txt_user_address.getText().trim();
        String mobileNo = txt_user_mobile_no.getText().trim();
        RoleTypeEnum roleText = null;
        if (firstName.length() == 0) {
            JOptionPane.showMessageDialog(null, "first name is required", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        } else if (lastName.length() == 0) {
            JOptionPane.showMessageDialog(null, "last name is required", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        } else if (userName.length() == 0) {
            JOptionPane.showMessageDialog(null, "username is required", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        } else if (password.length() < 4) {
            JOptionPane.showMessageDialog(null, "password is empty/less than 4 characters", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        } else if (roles == "SELECT ROLE") {
            JOptionPane.showMessageDialog(null, "role should be selected", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        } else if (address.length() == 0) {
            JOptionPane.showMessageDialog(null, "address is empty", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        } else if (mobileNo.length() == 0) {
            JOptionPane.showMessageDialog(null, "mobile no is required", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }
        try {
            Long number = Long.parseLong(txt_user_mobile_no.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "mobile should be numerice", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (mobileNo.length() != 10) {
            JOptionPane.showMessageDialog(null, "mobile no should be 10", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }
        roleText = RoleTypeEnum.valueOf(roles);
        UserDao userDao = new UserDaoImpl();
        User user;
        if (userId == 0) {
            user = new User(userName, password, roleText, firstName, lastName, mobileNo, address);

        } else {
            user = userDao.getById(userId);
            user.setId(userId);
            user.setFirstName(firstName);
            user.setLastName(lastName);
            user.setAddress(address);
            user.setRole(roleText);
            user.setUsername(userName);
            user.setPassword(password);
            user.setMobileNo(mobileNo);
        }
        
        try {
          String result = userDao.saveUpdate(user);
          if(result=="EXIST"){
             JOptionPane.showMessageDialog(null, "This username already exist", "Success", JOptionPane.WARNING_MESSAGE); 
             return;
          }
          JOptionPane.showMessageDialog(null, result, "Success", JOptionPane.WARNING_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "some thing error", "Success", JOptionPane.WARNING_MESSAGE);
        }
        txt_user_username.setEditable(true);
        resetCustomerForm();
        loadTableCustomer();
    }//GEN-LAST:event_user_save_updateActionPerformed

    private void user_resetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_user_resetActionPerformed
        txt_user_username.setEditable(true);
        resetCustomerForm();
    }//GEN-LAST:event_user_resetActionPerformed

    private void user_deleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_user_deleteActionPerformed
        int input = JOptionPane.showConfirmDialog(null, "Do you want to proceed?", "Confirm...",
                JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.ERROR_MESSAGE);
        if (input == 0) {
            int userId = txt_user_id;
            if (userId == 0) {
                JOptionPane.showMessageDialog(null, "Please Select User", "Success", JOptionPane.WARNING_MESSAGE);
            } else {
                UserDao userDao = new UserDaoImpl();
                boolean status = userDao.deleteUser(userId);
                JOptionPane.showMessageDialog(null, "Delete successfully", "Success", JOptionPane.WARNING_MESSAGE);
                loadTableCustomer();
                resetCustomerForm();
            }
        } else {
            return;
        }


    }//GEN-LAST:event_user_deleteActionPerformed

    private void customer_searchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_customer_searchActionPerformed
        String searchUsername = txt_user_search_username.getText().trim();
        if (searchUsername.length() == 0) {
            JOptionPane.showMessageDialog(null, "Please enter the username for search", "Success", JOptionPane.WARNING_MESSAGE);
        }
        UserDao userDao = new UserDaoImpl();
        List<User> list = userDao.getAlluserByname(searchUsername);
        UserModelTable model = new UserModelTable(list);
        customer_table.setModel(model);

    }//GEN-LAST:event_customer_searchActionPerformed

    private void reloadCustomerTableActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reloadCustomerTableActionPerformed
        txt_user_search_username.setText("");
        loadTableCustomer();
    }//GEN-LAST:event_reloadCustomerTableActionPerformed

    private void cb_user_roleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb_user_roleActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cb_user_roleActionPerformed

    private void txt_order_itemsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_order_itemsActionPerformed

    }//GEN-LAST:event_txt_order_itemsActionPerformed

    private void reset_orderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reset_orderActionPerformed
        resetOrderForm();
        txt_temp_order_id = 0;
    }//GEN-LAST:event_reset_orderActionPerformed

    private void order_add_updateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_order_add_updateActionPerformed
        String item = txt_order_items.getSelectedItem().toString();
        ProductDao pd = new ProductDaoImpl();
        String id[] = item.split("-");
        if (txt_order_available_qty.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please Select Item " + txt_order_available_qty.getText(), "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }
        int availableQty = Integer.parseInt(txt_order_available_qty.getText());
        if (availableQty == 0) {
            txt_order_required_qty.setEnabled(false);
            order_add_update.setEnabled(false);
        } else {
            txt_order_required_qty.setEnabled(true);
            order_add_update.setEnabled(true);
        }
        double unitPrice = Double.parseDouble(txt_order_price.getText());
        Integer qty = null;
        try {
            if (txt_order_required_qty.getText().trim().length() == 0) {
                JOptionPane.showMessageDialog(null, "Quantity is required", "Warning", JOptionPane.WARNING_MESSAGE);
                return;
            } else {
                qty = Integer.parseInt(txt_order_required_qty.getText());
            }

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Quantity should be numeric", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (availableQty < qty) {
            JOptionPane.showMessageDialog(null, "Quantity must be less or equal to " + availableQty, "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }

        Cart tempOrder = new Cart();
        tempOrder.setProductId(Integer.parseInt(id[0]));
        tempOrder.setItem(item);
        tempOrder.setId(txt_temp_order_id);
        tempOrder.setTotalQty(availableQty);
        tempOrder.setUnitPrice(unitPrice);
        tempOrder.setQty(qty);
        OrderDao od = new OrderDaoImpl();
        String status = od.saveUpdate(tempOrder);
        if (status == "EXIST") {
            JOptionPane.showMessageDialog(null, "this item already added", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        } else {
            JOptionPane.showMessageDialog(null, status, "Success", JOptionPane.WARNING_MESSAGE);
        }
        resetOrderForm();
        txt_temp_order_id = 0;
        loadTableTempOrder();
    }//GEN-LAST:event_order_add_updateActionPerformed

    private void txt_order_required_qtyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_order_required_qtyActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_order_required_qtyActionPerformed

    private void txt_order_itemsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txt_order_itemsMouseClicked

    }//GEN-LAST:event_txt_order_itemsMouseClicked

    private void txt_order_available_qtyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_order_available_qtyActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_order_available_qtyActionPerformed

    private void temp_order_tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_temp_order_tableMouseClicked
        txt_temp_order_id = Integer.parseInt(temp_order_table.getValueAt(temp_order_table.getSelectedRow(), 0).toString());
        txt_order_items.setSelectedItem(temp_order_table.getValueAt(temp_order_table.getSelectedRow(), 2).toString());
        txt_order_required_qty.setText(temp_order_table.getValueAt(temp_order_table.getSelectedRow(), 3).toString());
        txt_order_price.setText(temp_order_table.getValueAt(temp_order_table.getSelectedRow(), 4).toString());
    }//GEN-LAST:event_temp_order_tableMouseClicked

    private void order_deleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_order_deleteActionPerformed
        int input = JOptionPane.showConfirmDialog(null, "Do you want to proceed?", "Confirm...",
                JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.ERROR_MESSAGE);
        if (input == 0) {
            int temp_orderId = txt_temp_order_id;
            if (temp_orderId == 0) {
                JOptionPane.showMessageDialog(null, "Please select item", "Warning", JOptionPane.WARNING_MESSAGE);
                return;
            }
            OrderDao od = new OrderDaoImpl();
            boolean status = od.deleteById(temp_orderId);
            JOptionPane.showMessageDialog(null, "successfully deleted", "Success", JOptionPane.WARNING_MESSAGE);
            resetOrderForm();
            loadTableTempOrder();
            txt_temp_order_id = 0;
        } else {
            return;
        }

    }//GEN-LAST:event_order_deleteActionPerformed

    private void txt_purchaseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_purchaseActionPerformed
        int input = JOptionPane.showConfirmDialog(null, "Do you want to proceed?"
                + "After Order Can not Cancel Order???", "Confirm...",
                JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.ERROR_MESSAGE);
        if (input == 0) {
            OrderDao od = new OrderDaoImpl();
            List<Cart> list = od.getAllTempList();
            OrderDao ods = new OrderDaoImpl();
            String status = ods.purchase(list, user);
            deleteAllTempOrderList();
            loadTableTempOrder();
            loadTableProduct();
            JOptionPane.showMessageDialog(null, "Order Successful", "Success", JOptionPane.WARNING_MESSAGE);
        } else {
            return;
        }
    }//GEN-LAST:event_txt_purchaseActionPerformed

    private void txt_order_itemsItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_txt_order_itemsItemStateChanged
        if (txt_order_items.getSelectedItem().toString() == "Select Item") {
            return;
        }
        String item = txt_order_items.getSelectedItem().toString();
        ProductDao pd = new ProductDaoImpl();
        String id[] = item.split("-");
        Product product = pd.getById(Integer.parseInt(id[0]));
        txt_order_price.setText(String.valueOf(product.getPrice()));
        txt_order_available_qty.setText(String.valueOf(product.getTotalQty()));
        if (product.getTotalQty() == 0) {
            txt_order_required_qty.setEnabled(false);
            order_add_update.setEnabled(false);
        } else {
            txt_order_required_qty.setEnabled(true);
            order_add_update.setEnabled(true);
        }
    }//GEN-LAST:event_txt_order_itemsItemStateChanged

    private void reloadProductTableActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reloadProductTableActionPerformed
        txt_product_search_name.setText("");
        txt_product_search_type.setText("");
        loadTableProduct();
    }//GEN-LAST:event_reloadProductTableActionPerformed

    private void txt_product_search_nameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_product_search_nameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_product_search_nameActionPerformed

    private void txt_product_search_typeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_product_search_typeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_product_search_typeActionPerformed

    private void product_searchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_product_searchActionPerformed
        String name = txt_product_search_name.getText().trim();
        String type = txt_product_search_type.getText().trim();
        if ((name.length() == 0) && (type.length() == 0)) {
            JOptionPane.showMessageDialog(null, "Please enter the at least one field for search", "Success", JOptionPane.WARNING_MESSAGE);
        }
        ProductDao pd = new ProductDaoImpl();
        List<Product> list = pd.getAllProductSearch(name, type);
        ProductModelTable model = new ProductModelTable(list);
        product_table.setModel(model);
    }//GEN-LAST:event_product_searchActionPerformed

    private void product_tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_product_tableMouseClicked
        // TODO add your handling code here:
        txt_product_id = Integer.parseInt(product_table.getValueAt(product_table.getSelectedRow(), 0).toString());
        ProductDao pd = new ProductDaoImpl();
        Product product = pd.getById(txt_product_id);
        txt_product_name.setText(product.getProductName());
        txt_product_name.setEditable(false);
        cb_product_type.setSelectedItem(product.getProductType().toString());
        cb_product_type.setEnabled(false);
        String qty = String.valueOf(product.getTotalQty());
        txt_product_quantity.setText(qty);
        String price = String.valueOf(product.getPrice());
        txt_product_price.setText(price);
        txt_product_description.setText(product.getDescription());
    }//GEN-LAST:event_product_tableMouseClicked

    private void reset_productActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reset_productActionPerformed
        resetProductForm();
    }//GEN-LAST:event_reset_productActionPerformed

    private void product_deleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_product_deleteActionPerformed

        int input = JOptionPane.showConfirmDialog(null, "Do you want to proceed?", "Confirm...",
                JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.ERROR_MESSAGE);
        if (input == 0) {
            int productId = txt_product_id;
            System.out.println("productId::" + productId);
            ProductDao productDao = new ProductDaoImpl();
            boolean status = productDao.deleteProduct(productId);
            JOptionPane.showMessageDialog(null, "Deleted", "Deleted", JOptionPane.WARNING_MESSAGE);
            loadTableProduct();
            resetProductForm();
        } else {
            return;
        }

    }//GEN-LAST:event_product_deleteActionPerformed

    private void product_save_updateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_product_save_updateActionPerformed

        int productId = txt_product_id;
        String productName = txt_product_name.getText().trim();
        if (productName.length() == 0) {
            JOptionPane.showMessageDialog(null, "product name is required", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }
        String productType = cb_product_type.getSelectedItem().toString();
        if (productType == "Select Type") {
            JOptionPane.showMessageDialog(null, "product type is required", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }
        Integer qty = null;
        if ((txt_product_quantity.getText().trim()).length() == 0) {
            JOptionPane.showMessageDialog(null, "product quantity is required", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        } else {
            try {
                qty = Integer.parseInt(txt_product_quantity.getText());
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "product quantity should be numeric", "Warning", JOptionPane.WARNING_MESSAGE);
                return;
            }
        }
        Double price = null;
        if ((txt_product_price.getText().trim()).length() == 0) {
            JOptionPane.showMessageDialog(null, "product price is required", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        } else {
            try {
                price = Double.parseDouble(txt_product_price.getText());
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "product price should be numeric ", "Warning", JOptionPane.WARNING_MESSAGE);
                return;
            }
        }

        String description = txt_product_description.getText().trim();
        if (description.length() == 0) {
            JOptionPane.showMessageDialog(null, "description is required", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }
        Product product;
        ProductDao productDao = new ProductDaoImpl();
        if (productId == 0) {
            product = new Product(productName, productType, price, qty, description);
        } else {
            product = productDao.getById(productId);
            product.setId(productId);
            product.setProductName(productName);
            product.setProductType(productType);
            product.setPrice(price);
            product.setTotalQty(qty);
            product.setDescription(description);
        }
        
        String result = productDao.saveUpdate(product);
        JOptionPane.showMessageDialog(null, result, "Success", JOptionPane.WARNING_MESSAGE);
        resetProductForm();
        txt_product_name.setEditable(true);
        cb_product_type.setEnabled(true);
        loadTableProduct();
    }//GEN-LAST:event_product_save_updateActionPerformed

    private void txt_product_nameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_product_nameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_product_nameActionPerformed

    private void menuTabMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuTabMouseClicked
        int selectedMenu = menuTab.getSelectedIndex();
        String MenuName = menuTab.getTitleAt(selectedMenu);
        System.out.println("Tab Value:" + MenuName);
        switch (MenuName.toUpperCase()) {
            case "CUSTOMER":
                loadTableCustomer();
                break;
            case "PRODUCT":
                loadTableProduct();
                break;
            case "ORDERLIST":
                loadOrderListTable();
                break;
        }
    }//GEN-LAST:event_menuTabMouseClicked

    private void reloadorderListActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reloadorderListActionPerformed
        loadOrderListTable();
    }//GEN-LAST:event_reloadorderListActionPerformed

    private void txt_search_userActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_search_userActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_search_userActionPerformed

    private void txt_search_order_noActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_search_order_noActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_search_order_noActionPerformed

    private void order_searchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_order_searchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_order_searchActionPerformed

    private void order_tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_order_tableMouseClicked

        String orderNo = order_table.getValueAt(order_table.getSelectedRow(), 1).toString();
        OrderViewFrame orderViewFrame = new OrderViewFrame();
        orderViewFrame.loadItemDetails(orderNo);
        orderViewFrame.setVisible(true);
    }//GEN-LAST:event_order_tableMouseClicked

    private void order_searchKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_order_searchKeyTyped


    }//GEN-LAST:event_order_searchKeyTyped

    private void txt_search_userInputMethodTextChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_txt_search_userInputMethodTextChanged

    }//GEN-LAST:event_txt_search_userInputMethodTextChanged

    private void order_searchMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_order_searchMouseClicked
        String userName = txt_search_user.getText().trim();
        String orderNo = txt_search_order_no.getText().trim();
        OrderDao od = new OrderDaoImpl();
        List<OrderListDto> list = od.getOrderListSearch(user, userName, orderNo);
        OrderModelTable model = new OrderModelTable(list);
        order_table.setModel(model);
        txt_search_user.setText("");
        txt_search_order_no.setText("");
    }//GEN-LAST:event_order_searchMouseClicked
    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Dashboard().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel cart;
    private javax.swing.JComboBox<String> cb_product_type;
    private javax.swing.JComboBox<String> cb_user_role;
    private javax.swing.JPanel customer_form;
    private javax.swing.JPanel customer_panel;
    private javax.swing.JButton customer_search;
    private javax.swing.JTable customer_table;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JLabel label_user;
    private javax.swing.JButton logout;
    private javax.swing.JTabbedPane menuTab;
    private javax.swing.JButton order_add_update;
    private javax.swing.JButton order_delete;
    private javax.swing.JButton order_search;
    private javax.swing.JTable order_table;
    private javax.swing.JPanel productPanel;
    private javax.swing.JButton product_delete;
    private javax.swing.JButton product_save_update;
    private javax.swing.JButton product_search;
    private javax.swing.JPanel product_search_panel;
    private javax.swing.JPanel product_search_panel1;
    private javax.swing.JPanel product_search_panel2;
    private javax.swing.JPanel product_search_panel3;
    private javax.swing.JTable product_table;
    private javax.swing.JButton reloadCustomerTable;
    private javax.swing.JButton reloadProductTable;
    private javax.swing.JButton reloadorderList;
    private javax.swing.JButton reset_order;
    private javax.swing.JButton reset_product;
    private javax.swing.JTable temp_order_table;
    private javax.swing.JTextField txt_grand_total;
    private javax.swing.JTextField txt_nos_items;
    private javax.swing.JTextField txt_order_available_qty;
    private javax.swing.JComboBox<String> txt_order_items;
    private javax.swing.JTextField txt_order_price;
    private javax.swing.JTextField txt_order_required_qty;
    private javax.swing.JTextArea txt_product_description;
    private javax.swing.JTextField txt_product_name;
    private javax.swing.JTextField txt_product_price;
    private javax.swing.JTextField txt_product_quantity;
    private javax.swing.JTextField txt_product_search_name;
    private javax.swing.JTextField txt_product_search_type;
    private javax.swing.JButton txt_purchase;
    private javax.swing.JTextField txt_search_order_no;
    private javax.swing.JTextField txt_search_user;
    private javax.swing.JTextField txt_user_address;
    private javax.swing.JTextField txt_user_first_name;
    private javax.swing.JTextField txt_user_last_name;
    private javax.swing.JTextField txt_user_mobile_no;
    private javax.swing.JPasswordField txt_user_password;
    private javax.swing.JTextField txt_user_search_username;
    private javax.swing.JTextField txt_user_username;
    private javax.swing.JButton user_delete;
    private javax.swing.JButton user_reset;
    private javax.swing.JButton user_save_update;
    // End of variables declaration//GEN-END:variables
}
