package com.chaewookim.AccoutBook;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface IncomeRepository extends JpaRepository<Income, Long> {
    List<Income> findAllByUserId_UserId(String user_id);
    Optional<Income> findByUserId_UserId(String user_id);
}
