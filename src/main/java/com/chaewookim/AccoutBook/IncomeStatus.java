package com.chaewookim.AccoutBook;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
public class IncomeStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @ManyToOne
    @JoinColumn(name="userId", referencedColumnName = "userId")
    public Userinfo userId;

    public String name;
    public Integer amount;
    public LocalDate date;

}
