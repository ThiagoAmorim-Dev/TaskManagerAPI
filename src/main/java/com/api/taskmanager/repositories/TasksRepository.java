package com.api.taskmanager.repositories;

import com.api.taskmanager.models.TaskModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TasksRepository extends JpaRepository<TaskModel, UUID> {
}
