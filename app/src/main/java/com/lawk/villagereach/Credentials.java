package com.lawk.villagereach;

public class Credentials {
    String username;
    String password;
    String type;
    public Credentials(String username, String password) {
        this.username = username;
        this.password = password;
    }
    public Credentials(String username, String password, String type) {
        this.username = username;
        this.password = password;
        this.type = type;
    }
}
