package com.chaewookim.AccoutBook;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StatisticsRepository extends JpaRepository<Statistics, Long> {

    Optional<Statistics> findByUserId_UserId(String user_id);

}
