package com.chaewookim.AccoutBook;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Income {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @ManyToOne
    @JoinColumn(name="userId", referencedColumnName = "userId")
    public Userinfo userId;

    public String name;
    public String bank;
    public Long account_number;
    public Integer amount;
    public Integer monthly_spending_upper_limit;

    @Column(columnDefinition = "boolean default false")
    public Boolean is_fixed;

}
