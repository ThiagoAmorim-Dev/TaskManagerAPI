package com.api.taskmanager.models;

import com.api.taskmanager.State;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;



@Entity
@Table(name = "TB_TASKS")
@Getter
@Setter
public class TaskModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String title;
    private String description;
    private LocalDate creation_date;
    private LocalDate deadline;
    @Enumerated(EnumType.STRING)
    private State state;

}

