package com.todoapp.todo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@Entity
@Table(name="Todo")
public class ToDoDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;
    @Column(name="user_name")
    private String userName;
    @Column(name="description")
    private String description;
    @Column(name="status")
    private boolean status;
    @Column(name="target_date")
    private Date targetDate;



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ToDoDTO toDoDTO = (ToDoDTO) o;
        return id.equals(toDoDTO.id) &&
                userName.equals(toDoDTO.userName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userName);
    }
}
