package com.klu.project.service.impl;

import com.klu.project.dto.response.CourseMaterialResponseDTO;
import com.klu.project.entity.Course;
import com.klu.project.entity.CourseMaterial;
import com.klu.project.entity.Role;
import com.klu.project.entity.User;
import com.klu.project.exception.BadRequestException;
import com.klu.project.exception.ResourceNotFoundException;
import com.klu.project.mapper.CourseMaterialMapper;
import com.klu.project.repository.CourseMaterialRepository;
import com.klu.project.repository.CourseRepository;
import com.klu.project.repository.UserRepository;
import com.klu.project.service.CourseMaterialService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.UUID;

@Service
public class CourseMaterialServiceImpl implements CourseMaterialService {

    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(CourseMaterialServiceImpl.class);

    private final CourseMaterialRepository materialRepository;
    private final CourseRepository courseRepository;
    private final UserRepository userRepository;
    private final CourseMaterialMapper materialMapper;

    @Value("${app.upload.dir}")
    private String uploadDir;

    public CourseMaterialServiceImpl(CourseMaterialRepository materialRepository, CourseRepository courseRepository,
                                     UserRepository userRepository, CourseMaterialMapper materialMapper) {
        this.materialRepository = materialRepository;
        this.courseRepository = courseRepository;
        this.userRepository = userRepository;
        this.materialMapper = materialMapper;
    }

    @Override
    @Transactional
    public CourseMaterialResponseDTO uploadMaterial(Long courseId, MultipartFile file, String username) {
        User faculty = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User", "username", username));

        if (faculty.getRole() != Role.FACULTY) {
            throw new BadRequestException("Only faculty can upload materials");
        }

        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new ResourceNotFoundException("Course", "id", courseId));

        if (file.isEmpty()) {
            throw new BadRequestException("File cannot be empty");
        }

        try {
            Path uploadPath = Paths.get(uploadDir).toAbsolutePath().normalize();
            Files.createDirectories(uploadPath);

            String originalFileName = file.getOriginalFilename();
            String storedFileName = UUID.randomUUID() + "_" + originalFileName;
            Path targetLocation = uploadPath.resolve(storedFileName);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

            CourseMaterial material = new CourseMaterial(
                    course,
                    originalFileName,
                    storedFileName,
                    faculty
            );

            material = materialRepository.save(material);
            log.info("Material uploaded: {} for course {} by {}", originalFileName, course.getCode(), username);
            return materialMapper.toDTO(material);
        } catch (IOException ex) {
            throw new BadRequestException("Failed to store file: " + ex.getMessage());
        }
    }

    @Override
    public Resource downloadMaterial(Long materialId) {
        CourseMaterial material = materialRepository.findById(materialId)
                .orElseThrow(() -> new ResourceNotFoundException("Material", "id", materialId));

        try {
            Path filePath = Paths.get(uploadDir).toAbsolutePath().normalize().resolve(material.getFileUrl());
            Resource resource = new UrlResource(filePath.toUri());

            if (resource.exists() && resource.isReadable()) {
                return resource;
            } else {
                throw new ResourceNotFoundException("File not found: " + material.getFileName());
            }
        } catch (MalformedURLException ex) {
            throw new BadRequestException("File not found: " + ex.getMessage());
        }
    }

    @Override
    @Transactional
    public void deleteMaterial(Long materialId, String username) {
        CourseMaterial material = materialRepository.findById(materialId)
                .orElseThrow(() -> new ResourceNotFoundException("Material", "id", materialId));

        User faculty = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User", "username", username));

        if (!material.getUploadedBy().getId().equals(faculty.getId())) {
            throw new BadRequestException("You can only delete materials you uploaded");
        }

        try {
            Path filePath = Paths.get(uploadDir).toAbsolutePath().normalize().resolve(material.getFileUrl());
            Files.deleteIfExists(filePath);
        } catch (IOException ex) {
            log.error("Failed to delete file: {}", ex.getMessage());
        }

        materialRepository.delete(material);
        log.info("Material deleted: {} by {}", material.getFileName(), username);
    }

    @Override
    public List<CourseMaterialResponseDTO> getMaterialsByCourse(Long courseId) {
        List<CourseMaterial> materials = materialRepository.findByCourseId(courseId);
        return materialMapper.toDTOList(materials);
    }
}
