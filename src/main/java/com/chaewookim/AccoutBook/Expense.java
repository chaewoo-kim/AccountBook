package com.chaewookim.AccoutBook;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Getter
@Setter
public class Expense {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @ManyToOne
    @JoinColumn(name="userId", referencedColumnName = "userId")
    public Userinfo userId;

    public Integer amount;
    public String memo;
    public String category;
    public LocalDate date;
    public String name;

}
