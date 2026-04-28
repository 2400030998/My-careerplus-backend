package com.mycareer.repository;

import com.mycareer.model.Class;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ClassRepository extends JpaRepository<Class, Long> {
    List<Class> findBySubject(String subject);
}