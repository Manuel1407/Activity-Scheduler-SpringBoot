package com.ikechukwu.week8.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "USERS")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String fullname;
    @Column(unique = true)
    private String username;
    private String password;

    @OneToMany(mappedBy = "user")
    private List<Task> task;

}
