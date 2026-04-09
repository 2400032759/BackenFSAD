package com.klu.project.repository;

import com.klu.project.entity.Transport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TransportRepository extends JpaRepository<Transport, Long> {

    Optional<Transport> findByStudentId(Long studentId);

    boolean existsByStudentId(Long studentId);
}
