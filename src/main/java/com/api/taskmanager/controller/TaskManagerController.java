package com.api.taskmanager.controller;

import com.api.taskmanager.dtos.TaskDTO;
import com.api.taskmanager.models.TaskModel;
import com.api.taskmanager.repositories.TasksRepository;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
public class TaskManagerController {
    private final TasksRepository tasksRepository;

    @Autowired
    public TaskManagerController(TasksRepository tasksRepository){
        this.tasksRepository = tasksRepository;
    }

    @PostMapping("/tasks")
    public ResponseEntity<Object> AddTask(@RequestBody TaskDTO taskDTO){
        TaskModel taskModel = new TaskModel();
        BeanUtils.copyProperties(taskDTO, taskModel);
        tasksRepository.save(taskModel);
        return ResponseEntity.status(HttpStatus.OK).body("Task salva com sucesso:\n" + taskModel);
    }

    @GetMapping("/tasks")
    public List<TaskModel> getAllTasks(){
        return tasksRepository.findAll();
    }

    @GetMapping("/tasks/{id}")
    public ResponseEntity<Object> getTask(@PathVariable UUID id){
        Optional<TaskModel> taskOptional = tasksRepository.findById(id);
        if(taskOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.OK).body(taskOptional.get());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não foi possível encontrar essa task");
    }

    @DeleteMapping("/tasks/{id}")
    public ResponseEntity<Object> deleteTask(@PathVariable UUID id){
        Optional<TaskModel> taskOptional = tasksRepository.findById(id);
        if(taskOptional.isPresent()){
            tasksRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body("Task excluída com sucesso.");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não foi possível encontrar a Task para realizar a exclusão.");
    }

    @PutMapping("/tasks/{id}")
    public ResponseEntity<Object> editTask(@PathVariable UUID id, @RequestBody TaskDTO taskDTO){
        Optional<TaskModel> taskOptional = tasksRepository.findById(id);
        if(taskOptional.isPresent()){
            var taskModel = taskOptional.get();
            BeanUtils.copyProperties(taskDTO, taskModel);
            return ResponseEntity.status(HttpStatus.OK).body(tasksRepository.save(taskModel));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não foi possível encontrar a Task para realizar a alteração.");
    }



}
