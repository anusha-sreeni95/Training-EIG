package com.endurance.products.teamtraining.crudapi.auth;

import javax.persistence.*;

/**
 * Created by hemantv on 30/6/17.
 */
@Entity
@Table(name = "employee")
public class Employee {
    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE)
    @Column(name = "id")
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "password")
    private String password;
    @Column(name = "authToken")
    private String authToken;

    public Employee() {
    }

    public Employee(int id, String name, String password, String authToken) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.authToken = authToken;
    }

    private static final ThreadLocal<String> current = new ThreadLocal<String>();

    public static String getCurrentEmployeeAuth(){
        return current.get();
    }
    public static void setCurrentEmployeeAuth(String authToken){
        current.set(authToken);
    }
    public static void clearCurrentEmployeeAuth(){
        current.remove();
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
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getAuthToken() {
        return authToken;
    }
    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }
}
