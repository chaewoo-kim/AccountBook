package com.chaewookim.AccoutBook;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Userinfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Column(unique = true)
    public String userId;
    public String userName;
    public String userPw;
    public String userEmail;
}
