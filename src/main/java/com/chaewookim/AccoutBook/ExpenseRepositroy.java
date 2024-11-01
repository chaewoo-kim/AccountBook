package com.chaewookim.AccoutBook;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface ExpenseRepositroy extends JpaRepository<Expense, Long> {

    List<Expense> findAllByUserId_UserIdAndDate(String user_id, LocalDate date);
    List<Expense> findAllByUserIdAndDateBetween(Userinfo user_id, LocalDate startDate, LocalDate endDate);

}
