package com.codecool.shop.model;

public class User {
    private int id;
    private String name;
    private String email;
    private String phone;
    private String password;

    public User(String name, String email, String phone, String password) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return String.format("id: %1$d," +
                        "name: %2$s," +
                        "email: %3$s" +
                        "phone: %4$s",
                this.id,
                this.name,
                this.email,
                this.phone);
    }
}
