package com.myapp.entity;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Table(name = "register")
public class Register {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "username", nullable = false)
    private String username;
    @Pattern(
            regexp = "^(?=.*[0-9])(?=.*[A-Z]).{6,}$",
            message = "Password must be at least 6 charcters long, contain at least one uppercase letter and one digit"
    )
    @Column(name = "password", nullable = false)
    private String password;

    public Register(){

    }

    public Register(String username, String password) {

        this.username = username;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
