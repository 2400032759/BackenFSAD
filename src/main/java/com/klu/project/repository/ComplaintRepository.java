package com.klu.project.repository;

import com.klu.project.entity.Complaint;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ComplaintRepository extends JpaRepository<Complaint, Long> {

    List<Complaint> findByStudentId(Long studentId);

    Page<Complaint> findByStudentId(Long studentId, Pageable pageable);

    Page<Complaint> findAll(Pageable pageable);
}
