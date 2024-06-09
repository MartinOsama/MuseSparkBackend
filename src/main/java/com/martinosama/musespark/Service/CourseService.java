package com.martinosama.musespark.Service;

import com.martinosama.musespark.DTO.CourseDTO;
import com.martinosama.musespark.Entity.Course;
import com.martinosama.musespark.Repository.CourseRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class CourseService {
    private final CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public Course createCourse(CourseDTO courseDTO, MultipartFile file) throws IOException {
        Course course = new Course();
        course.setTitle(courseDTO.getTitle());
        course.setDescription(courseDTO.getDescription());
        course.setSourceType(courseDTO.getSourceType());
        if (file != null && !file.isEmpty()) {
            String fileUrl = uploadFile(file);
            course.setSourceUrl(fileUrl);
        } else {
            course.setSourceUrl(courseDTO.getSourceUrl());
        }
        return courseRepository.save(course);
    }

    private String uploadFile(MultipartFile file) throws IOException {
        return "https://example.com/uploads/" + file.getOriginalFilename();
    }

    public Course getCourseById(Long courseId) {
        return courseRepository.findById(courseId).orElse(null);
    }

    public Course updateCourse(Long courseId, CourseDTO courseDTO) {
        Course course = courseRepository.findById(courseId).orElse(null);
        if (course != null) {
            course.setTitle(courseDTO.getTitle());
            course.setDescription(courseDTO.getDescription());
            course.setSourceType(courseDTO.getSourceType());
            course.setSourceUrl(courseDTO.getSourceUrl());
            return courseRepository.save(course);
        }
        return null;
    }

    public void deleteCourse(Long courseId) {
        courseRepository.deleteById(courseId);
    }
}