package com.files.crudBackend.model;

import java.time.Instant;
import java.util.UUID;

public class ClientModel {
    private UUID id;
    private String name;
    private String email;
    private String cellphone;
    private Boolean active;
    private Instant deleteAt;
    private Instant createAt;
    private Instant updateAt;

    public ClientModel() {

    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
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

    public String getCellphone() {
        return cellphone;
    }

    public void setCellphone(String cellphone) {
        this.cellphone = cellphone;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Instant getDeleteAt() {
        return deleteAt;
    }

    public void setDeleteAt(Instant deleteAt) {
        this.deleteAt = deleteAt;
    }

    public Instant getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Instant createAt) {
        this.createAt = createAt;
    }

    public Instant getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(Instant updateAt) {
        this.updateAt = updateAt;
    }

}
