package com.giov.todosimple.services;

import com.giov.todosimple.models.Task;
import com.giov.todosimple.models.User;
import com.giov.todosimple.repositories.TaskRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {
    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private UserService userService;

    public Task findById(Long id){
        Optional<Task> task = this.taskRepository.findById(id);
        return task.orElseThrow(() -> new RuntimeException("Task not found ! \nId: " + id + "\nType:" + Task.class.getSimpleName()));
    }

    public List<Task> findAllByUserId(Long userId){
        List<Task> tasks = this.taskRepository.findByUser_Id(userId);
        return tasks;
    }

    @Transactional
    public Task create(Task obj){
        User user = this.userService.findById(obj.getUser().getId());
        obj.setId(null);
        obj = this.taskRepository.save(obj);
        return obj;
    }

    @Transactional
    public Task update(Task obj){
        Task newObj = this.findById(obj.getId());
        newObj.setDescription(obj.getDescription());
        return taskRepository.save(newObj);
    }

    public Task delete(Long id){
        findById(id);
        try {
            this.taskRepository.deleteById(id);
        }catch (Exception e){
            throw new RuntimeException("Error deleting task because already relational entity's! \nId: " + id);
        }
        return this.taskRepository.findById(id).get();
    }
}
