package com.klu.project.repository;

import com.klu.project.entity.CourseMaterial;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseMaterialRepository extends JpaRepository<CourseMaterial, Long> {

    List<CourseMaterial> findByCourseId(Long courseId);

    Page<CourseMaterial> findByCourseId(Long courseId, Pageable pageable);

    List<CourseMaterial> findByUploadedById(Long uploadedById);
}
