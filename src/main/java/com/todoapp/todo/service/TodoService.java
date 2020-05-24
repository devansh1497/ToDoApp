package com.todoapp.todo.service;

import com.todoapp.todo.entity.ToDo;
import com.todoapp.todo.entity.User;
import com.todoapp.todo.repository.TodoRepository;
import com.todoapp.todo.repository.UserRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TodoService {

    @Autowired
    private TodoRepository todoRepository;

    @Autowired
    private UserRepository userRepository;


    public List<ToDo> findAllTodos(String userName) throws NotFoundException {
        Optional<User> user = userRepository.findByUserName(userName);
        if(!user.isPresent()){
            throw new NotFoundException("Invalid user");
        }
        return user.get().getTodos();
    }

    public ToDo findById(Long id){
        Optional<ToDo> opt = todoRepository.findById(id);
        if(!opt.isPresent()) return null;
        return opt.get();
    }

    public ToDo deleteById(Long id){
        Optional<ToDo> todo = todoRepository.findById(id);
        if(!todo.isPresent()){
            return null;
        }
        todoRepository.deleteById(id);
        return todo.get();
    }

    public ToDo addNewTodo(String userName, Long id, ToDo toDo){
        Optional<User> opt = userRepository.findByUserName(userName);
        if(!opt.isPresent()) return null;
        toDo.setUser(opt.get());
        todoRepository.save(toDo);
        return toDo;
    }

    public ToDo updateById(String userName, Long id, ToDo toDo) {

        if(id.equals(-1L)){
            return addNewTodo(userName,id, toDo);
        }
        Optional<ToDo> toDoOptional = todoRepository.findById(id);
        if(!toDoOptional.isPresent()) return null;
        ToDo todo = toDoOptional.get();
        toDo.setId(toDo.getId());
        todoRepository.save(toDo);
        return toDo;
    }

    public ToDo findTodoById(String userName, Long id) {
        Optional<ToDo> todo = todoRepository.findById(id);
        return todo.orElse(null);
    }

    public ToDo save(ToDo todo){
        return todoRepository.save(todo);
    }

}
