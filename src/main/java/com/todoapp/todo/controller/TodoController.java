package com.todoapp.todo.controller;

import com.todoapp.todo.entity.ToDo;
import com.todoapp.todo.service.TodoService;
import javassist.NotFoundException;
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
        ToDo todo =  todoService.deleteById(todoId);
        if(todo == null) return ResponseEntity.notFound().build();
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{userName}/{id}/update")
    public ToDo updateByid(@PathVariable String userName, @PathVariable Long id, @RequestBody ToDo todo){
        return todoService.updateById(userName, id, todo);
    }

    @GetMapping("/{userName}")
    public List<ToDo> findTodoByUser(@PathVariable String userName) throws NotFoundException {
        return todoService.findAllTodos(userName);
    }

    @GetMapping("/{userName}/{id}/todo")
    public ToDo findTodoById(@PathVariable String userName, @PathVariable Long id){

        return todoService.findTodoById(userName,id);

    }

    @PutMapping("/users/{userName}/todos/{id}")
    public ResponseEntity<ToDo> updateToDo(@PathVariable String userName, @PathVariable Long id, @RequestBody ToDo todo){
        ToDo to = todoService.save(todo);
        return new ResponseEntity<ToDo>(to, HttpStatus.OK);
    }

    @PostMapping("/users/{userName}")
    public ResponseEntity<ToDo> addTodo(@PathVariable String userName, @RequestBody ToDo todo){
        ToDo to = todoService.save(todo);
        return new ResponseEntity<>(to,HttpStatus.CREATED);
    }

}
