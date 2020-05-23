package com.todoapp.todo.controller;

import com.todoapp.todo.dto.ToDoDTO;
import com.todoapp.todo.repository.TodoRepository;
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

    //All todos for a user
    @GetMapping("/{userName}")
    ResponseEntity<?> findAllTodos(@PathVariable String userName){
        List<ToDoDTO> toDoDTOs = todoService.findAllTodos(userName);
        return ResponseEntity.ok().body(toDoDTOs);
    }

//    Get to do by username and id for the update page
    @GetMapping("{userName}/{id}/update")
    ResponseEntity<?> findTodoById(@PathVariable String userName, @PathVariable Long id){
        ToDoDTO todo = todoService.findByIdAndUserName(id, userName);
        if(todo == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok().body(todo);
    }

//    //Update existing to do
    @PostMapping("{userName}/{id}/update")
    ResponseEntity<?> updateTodoById(@PathVariable String userName, @PathVariable Long id, @RequestBody ToDoDTO toDoDTO){
        ToDoDTO todo = todoService.updateById(userName,id,toDoDTO);
        if(todo == null) return ResponseEntity.badRequest().build();
        return ResponseEntity.ok().body(toDoDTO);
    }

    @DeleteMapping("{userName}/{id}/delete")
    ResponseEntity<?> deleteById(@PathVariable String userName, @PathVariable Long id){
        ToDoDTO todo = todoService.deleteById(id);
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
