
package com.Enum;

public enum StatusEnum {
    PENDING("ADMIN"),
    DELIVERED("MANAGER");

    public static String valueOf(StatusEnum role) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private final String name;
    private StatusEnum(final String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
  
    
}
