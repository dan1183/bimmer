package com.example.bimmer.data.models;

public class Users {
    private String email, password, name, surname, phone;

    public Users(){}

    public Users(String email, String password, String name, String surname, String phone) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
