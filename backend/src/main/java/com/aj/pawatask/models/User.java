package com.aj.pawatask.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@Table(name = User.TABLE_NAME)
public class User extends AbstractModel {

    public static final String TABLE_NAME = "users";

    @Column(name = "name")
    private String name;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "is_active")
    private boolean isActive;

    @Column(name = "email", nullable = false)
    private String email;

    public void setActive(boolean status) {
        this.isActive = status;
    }

    public boolean getActive() {
        return this.isActive;
    }
}
