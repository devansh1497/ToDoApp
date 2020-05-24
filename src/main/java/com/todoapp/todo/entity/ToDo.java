package com.todoapp.todo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@Entity
@Table(name="Todo")
public class ToDo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;
    @Column(name="description")
    private String description;
    @Column(name="status")
    private boolean status;
    @Column(name="target_date")
    private Date targetDate;
    @JsonIgnore
    @ManyToOne()
    @JoinColumn(name = "todo_user_id",referencedColumnName = "id",nullable = false)
    private User user;



}
