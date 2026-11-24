package jku.at.dto;

import jku.at.entity.Course;

// Daten, die das Backend zu einem Kurs zurückgibt
public record CourseResponse(Long id, String name) {

    public static CourseResponse from(Course course) {
        return new CourseResponse(course.getId(), course.getName());
    }
}
