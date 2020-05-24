package com.todoapp.todo.controller;

import com.todoapp.todo.entity.ToDo;
import com.todoapp.todo.repository.TodoRepository;
import com.todoapp.todo.repository.UserRepository;
import com.todoapp.todo.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/todos/jpa")
public class TodoJpaController {

    @Autowired
    private TodoService todoService;

    @Autowired
    private TodoRepository todoRepository;

    @Autowired
    private UserRepository userRepository;

    //All todos for a user
    @GetMapping("/{userName}")
    ResponseEntity<?> findAllTodos(@PathVariable String userName){
        try {
            List<ToDo> toDos = todoService.findAllTodos(userName);
            return ResponseEntity.ok().body(toDos);
        }catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }

//    Get to do by username and id for the update page
    @GetMapping("{userName}/{id}/update")
    ResponseEntity<?> findTodoById(@PathVariable String userName, @PathVariable Long id){
        ToDo todo = todoService.findById(id);
        if(todo == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok().body(todo);
    }

//    //Update existing to do
    @PostMapping("{userName}/{id}/update")
    ResponseEntity<?> updateTodoById(@PathVariable String userName, @PathVariable Long id, @RequestBody ToDo toDo){
        ToDo todo = todoService.updateById(userName,id, toDo);
        if(todo == null) return ResponseEntity.badRequest().build();
        return ResponseEntity.ok().body(toDo);
    }

    @DeleteMapping("{userName}/{id}/delete")
    ResponseEntity<?> deleteById(@PathVariable String userName, @PathVariable Long id){
        ToDo todo = todoService.deleteById(id);
        if(todo == null) return ResponseEntity.badRequest().build();
        return ResponseEntity.noContent().build();
    }
//
//    @PostMapping("{userName}/add")
//    ResponseEntity<?> addTodo(@PathVariable String userName, ToDoDTO todo){
//        todoService.addToDo(userName,todo);
//        return ResponseEntity.ok().build();
//    }

}
