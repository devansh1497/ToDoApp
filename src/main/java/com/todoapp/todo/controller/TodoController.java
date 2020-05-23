package com.todoapp.todo.controller;

import com.todoapp.todo.dto.ToDoDTO;
import com.todoapp.todo.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/todos")
 public class TodoController {

    @Autowired
    private TodoService todoService;


    @DeleteMapping("/{userName}/{todoId}/delete")
    public ResponseEntity<String> deleteById(@PathVariable String userName, @PathVariable Long todoId){
        ToDoDTO todo =  todoService.deleteById(todoId);
        if(todo == null) return ResponseEntity.notFound().build();
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{userName}/{id}/update")
    public ToDoDTO updateByid(@PathVariable String userName, @PathVariable Long id, @RequestBody ToDoDTO todoDTO){
        return todoService.updateById(userName, id, todoDTO);
    }

    @GetMapping("/{userName}")
    public List<ToDoDTO> findTodoByUser(@PathVariable String userName){
        return todoService.findAllTodos(userName);
    }

    @GetMapping("/{userName}/{id}/todo")
    public ToDoDTO findTodoById(@PathVariable String userName, @PathVariable Long id){

        return todoService.findTodoById(userName,id);

    }

    @PutMapping("/users/{userName}/todos/{id}")
    public ResponseEntity<ToDoDTO> updateToDo(@PathVariable String userName, @PathVariable Long id, @RequestBody ToDoDTO todo){
        ToDoDTO to = todoService.save(todo);
        return new ResponseEntity<ToDoDTO>(to, HttpStatus.OK);
    }

    @PostMapping("/users/{userName}")
    public ResponseEntity<ToDoDTO> addTodo(@PathVariable String userName, @RequestBody ToDoDTO todo){
        ToDoDTO to = todoService.save(todo);
        return new ResponseEntity<>(to,HttpStatus.CREATED);
    }

}
