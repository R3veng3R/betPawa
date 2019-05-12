package com.aj.pawatask.models;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@Table(name="users")
public class User extends AbstractModel {

    @Column(name = "name")
    private String name;

    @Column(name = "last_name")
    private String lastName;
}
