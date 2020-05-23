package com.todoapp.todo.repository;

import com.todoapp.todo.dto.ToDoDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TodoRepository extends JpaRepository<ToDoDTO,Long> {
    List<ToDoDTO> findByUserName(String userName);
    ToDoDTO findByIdAndUserName(Long id, String userName);
}
