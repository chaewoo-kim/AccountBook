package com.chaewookim.AccoutBook;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Statistics {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @ManyToOne
    @JoinColumn(name="userId", referencedColumnName = "userId")
    public Userinfo userId;

    public Integer monthly_expense_recommended;
    public Integer day_expense_recommended;
}
