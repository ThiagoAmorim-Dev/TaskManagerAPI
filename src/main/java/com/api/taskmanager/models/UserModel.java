package com.api.taskmanager.models;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "TB_USERS")
public class UserModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String first_name;
    private String last_name;
    private String mail;

}
