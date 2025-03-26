package com.miapp.files.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class UsuarioModel {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;

    private String name;
    private long cellphone;

    @Column(nullable = false, length = 10, unique = true)
    private long credential;
    
    //getters and setters
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }    

    public long getCellphone() {
        return cellphone;
    }
    public void setCellphone(long cellphone) {
        this.cellphone = cellphone;
    }

    public long getCredential() {
        return credential;
    }
    public void setCredential(long credential) {
        this.credential = credential;
    }
}
