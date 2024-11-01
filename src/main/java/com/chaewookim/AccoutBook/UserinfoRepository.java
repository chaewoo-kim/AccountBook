package com.chaewookim.AccoutBook;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserinfoRepository extends JpaRepository<Userinfo, Long> {
    List<Userinfo> findAllByUserId(String user_id);
    Optional<Userinfo> findByUserId(String user_id);
}
