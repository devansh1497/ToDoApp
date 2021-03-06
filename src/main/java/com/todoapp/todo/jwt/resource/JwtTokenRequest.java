package com.todoapp.todo.jwt.resource;

import java.io.Serializable;

public class JwtTokenRequest implements Serializable {

  private static final long serialVersionUID = -5616176897013108345L;

  private String userName;
    private String password;

    public JwtTokenRequest() {
        super();
    }

    public JwtTokenRequest(String username, String password) {
        this.setUsername(username);
        this.setPassword(password);
    }

    public String getUsername() {
        return this.userName;
    }

    public void setUsername(String username) {
        this.userName = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
