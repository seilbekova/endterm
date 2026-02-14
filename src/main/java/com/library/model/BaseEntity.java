// src/main/java/com/library/model/BaseEntity.java
package com.library.model;

public abstract class BaseEntity {
    protected int id;

    public BaseEntity() {}

    public BaseEntity(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public abstract String getDetails();
    public abstract void printBasicInfo();

    public void validate() throws IllegalArgumentException {
        if (id < 0) {
            throw new IllegalArgumentException("ID cannot be negative");
        }
    }
}







