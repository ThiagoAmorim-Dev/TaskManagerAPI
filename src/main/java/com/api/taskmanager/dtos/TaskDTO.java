package com.api.taskmanager.dtos;

import com.api.taskmanager.State;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.annotation.Nonnull;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class TaskDTO {
    @NonNull
    private String title;
    private String description;
    @NonNull
    private LocalDate creation_date = LocalDate.now();
    private LocalDate deadline;
    @Enumerated(EnumType.STRING)
    private State state;

}
