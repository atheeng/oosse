
package com.Enum;

public enum RoleTypeEnum {
    ADMIN("ADMIN"),
    MANAGER("MANAGER"),
    CUSTOMER("CUSTOMER");

    public static String valueOf(RoleTypeEnum role) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private final String name;
    private RoleTypeEnum(final String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
  
    
}
