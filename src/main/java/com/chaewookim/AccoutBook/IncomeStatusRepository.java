package com.chaewookim.AccoutBook;


import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface IncomeStatusRepository extends JpaRepository<IncomeStatus, Long> {
    List<IncomeStatus> findAllByUserId_UserId(String user_id);

    List<IncomeStatus> findAllByUserIdAndDateAndNameAndAmountGreaterThanEqual(Userinfo user_id, LocalDate date, String name, Integer Amount);
    List<IncomeStatus> findAllByUserIdAndNameAndAmountGreaterThanEqual(Userinfo user_id, String name, Integer Amount);
    List<IncomeStatus> findAllByUserIdAndDateAndAmountGreaterThanEqual(Userinfo user_id, LocalDate date, Integer Amount);
    List<IncomeStatus> findAllByUserIdAndDateAndName(Userinfo user_id, LocalDate date, String name);
    List<IncomeStatus> findAllByUserIdAndAmountGreaterThanEqual(Userinfo user_id, Integer Amount);
    List<IncomeStatus> findAllByUserIdAndDate(Userinfo user_id, LocalDate date);
    List<IncomeStatus> findAllByUserIdAndName(Userinfo user_id, String name);


    List<IncomeStatus> findAllByUserIdAndDateAndNameAndAmountLessThanEqual(Userinfo user_id, LocalDate date, String name, Integer Amount);
    List<IncomeStatus> findAllByUserIdAndNameAndAmountLessThanEqual(Userinfo user_id, String name, Integer Amount);
    List<IncomeStatus> findAllByUserIdAndDateAndAmountLessThanEqual(Userinfo user_id, LocalDate date, Integer Amount);
    List<IncomeStatus> findAllByUserIdAndAmountLessThanEqual(Userinfo user_id, Integer Amount);

    List<IncomeStatus> findAllByUserIdAndDateAndNameAndAmount(Userinfo user_id, LocalDate date, String name, Integer Amount);
    List<IncomeStatus> findAllByUserIdAndAmount(Userinfo user_id, Integer Amount);
    List<IncomeStatus> findAllByUserIdAndNameAndAmount(Userinfo user_id, String name, Integer Amount);
    List<IncomeStatus> findAllByUserIdAndDateAndAmount(Userinfo user_id, LocalDate date, Integer Amount);
}
