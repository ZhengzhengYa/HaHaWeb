package com.zz.model;

import java.io.Serializable;

public class User implements Serializable {
    private Integer id;

    private String name;

    private String password;

    private String realName;

    private Integer securityCard;

    private String respond;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName == null ? null : realName.trim();
    }

    public Integer getSecurityCard() {
        return securityCard;
    }

    public void setSecurityCard(Integer securityCard) {
        this.securityCard = securityCard;
    }

    public String getRespond() {
        return respond;
    }

    public void setRespond(String respond) {
        this.respond = respond == null ? null : respond.trim();
    }
}