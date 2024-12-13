package com.chaewookim.AccoutBook;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {

    List<Expense> findAllByUserId_UserIdAndDate(String user_id, LocalDate date);
    List<Expense> findAllByUserIdAndDateBetween(Userinfo user_id, LocalDate startDate, LocalDate endDate);
    List<Expense> findAllByUserId_UserId(String user_id);

    List<Expense> findAllByUserIdAndDateAndNameAndAmountGreaterThanEqual(Userinfo user_id, LocalDate date, String name, Integer Amount);
    List<Expense> findAllByUserIdAndNameAndAmountGreaterThanEqual(Userinfo user_id, String name, Integer Amount);
    List<Expense> findAllByUserIdAndDateAndAmountGreaterThanEqual(Userinfo user_id, LocalDate date, Integer Amount);
    List<Expense> findAllByUserIdAndDateAndName(Userinfo user_id, LocalDate date, String name);
    List<Expense> findAllByUserIdAndAmountGreaterThanEqual(Userinfo user_id, Integer Amount);
    List<Expense> findAllByUserIdAndDate(Userinfo user_id, LocalDate date);
    List<Expense> findAllByUserIdAndName(Userinfo user_id, String name);


    List<Expense> findAllByUserIdAndDateAndNameAndAmountLessThanEqual(Userinfo user_id, LocalDate date, String name, Integer Amount);
    List<Expense> findAllByUserIdAndNameAndAmountLessThanEqual(Userinfo user_id, String name, Integer Amount);
    List<Expense> findAllByUserIdAndDateAndAmountLessThanEqual(Userinfo user_id, LocalDate date, Integer Amount);
    List<Expense> findAllByUserIdAndAmountLessThanEqual(Userinfo user_id, Integer Amount);

    List<Expense> findAllByUserIdAndDateAndNameAndAmount(Userinfo user_id, LocalDate date, String name, Integer Amount);
    List<Expense> findAllByUserIdAndAmount(Userinfo user_id, Integer Amount);
    List<Expense> findAllByUserIdAndNameAndAmount(Userinfo user_id, String name, Integer Amount);
    List<Expense> findAllByUserIdAndDateAndAmount(Userinfo user_id, LocalDate date, Integer Amount);

}
