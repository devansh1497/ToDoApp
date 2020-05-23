package com.todoapp.todo.service;

import com.todoapp.todo.dto.ToDoDTO;
import com.todoapp.todo.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class TodoService {

    @Autowired
    private TodoRepository todoRepository;

    static List<ToDoDTO> todoList = new ArrayList<>();
    static Long id = 0L;

    static{
        todoList.add(new ToDoDTO(++id,"devansh1497","Get milk",false,new Date()));
        todoList.add(new ToDoDTO(++id,"devansh1497","Encash cheque",true,new Date()));
        todoList.add(new ToDoDTO(++id,"devansh1497","Find high paying job according to skill set",false,new Date()));
        todoList.add(new ToDoDTO(++id,"devansh1497","Get milk",false,new Date()));
        todoList.add(new ToDoDTO(++id,"devansh1497","Encash cheque",true,new Date()));
        todoList.add(new ToDoDTO(++id,"devansh1497","Find high paying job according to skill set",false,new Date()));
        todoList.add(new ToDoDTO(++id,"devansh1497","Get milk",false,new Date()));
        todoList.add(new ToDoDTO(++id,"devansh1497","Encash cheque",true,new Date()));
        todoList.add(new ToDoDTO(++id,"devansh1497","Find high paying job according to skill set",false,new Date()));
    }

    public List<ToDoDTO> findAllTodos(){
        return todoList;
    }

    public List<ToDoDTO> findAllTodos(String userName){
        return todoRepository.findByUserName(userName);
    }

    public ToDoDTO findByIdAndUserName(Long id, String userName){
        return todoRepository.findByIdAndUserName(id,userName);
    }

    public ToDoDTO deleteById(Long id){
        Optional<ToDoDTO> todo = todoRepository.findById(id);
        if(!todo.isPresent()){
            return null;
        }
        todoRepository.deleteById(id);
        return todo.get();
    }

    public ToDoDTO addNewTodo(String userName, Long id, ToDoDTO toDoDTO){
        toDoDTO.setUserName(userName);
        todoRepository.save(toDoDTO);
        return toDoDTO;
    }

    public ToDoDTO updateById(String userName, Long id, ToDoDTO toDoDTO) {

        if(id.equals(-1L)){
            return addNewTodo(userName,id,toDoDTO);
        }
        ToDoDTO todo = findByIdAndUserName(id,userName);
        if(todo == null) return null;
        toDoDTO.setId(todo.getId());
        toDoDTO.setUserName(userName);
        todoRepository.save(toDoDTO);
        return toDoDTO;
    }

    public ToDoDTO findTodoById(String userName, Long id) {
        Optional<ToDoDTO> todo = todoRepository.findById(id);
        return todo.orElse(null);
    }

    public ToDoDTO save(ToDoDTO todo){
        return todoRepository.save(todo);
    }

    public void addToDo(String userName, ToDoDTO todo) {
        todo.setUserName(userName);
        todoRepository.save(todo);
    }
}
