package com.nhnacademy.board.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "Users")
public class User {
    @Id
    @Column(name ="user_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userid;
    @Column(name ="name")
    private String name;
    @Column(name = "pwd")
    private String pwd;



    @Override
    public String toString() {
        return name;
    }
}
