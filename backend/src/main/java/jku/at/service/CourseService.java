package jku.at.service;

import jku.at.dto.CourseRequest;
import jku.at.dto.CourseResponse;
import jku.at.entity.Course;
import jku.at.entity.User;
import jku.at.repository.CourseRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@Transactional
public class CourseService {

    private final CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Transactional(readOnly = true)
    public List<CourseResponse> getCourses(User user) {
        return courseRepository.findByUserId(user.getId()).stream()
                .map(CourseResponse::from)
                .toList();
    }

    @Transactional(readOnly = true)
    public CourseResponse getCourse(Long id, User user) {
        return CourseResponse.from(findOwnCourse(id, user));
    }

    public CourseResponse createCourse(CourseRequest request, User user) {
        Course course = new Course();
        course.setName(request.name());
        course.setUser(user);

        return CourseResponse.from(courseRepository.save(course));
    }

    public CourseResponse updateCourse(Long id, CourseRequest request, User user) {
        Course course = findOwnCourse(id, user);
        course.setName(request.name());
        return CourseResponse.from(course);
    }

    public void deleteCourse(Long id, User user) {
        courseRepository.delete(findOwnCourse(id, user));
    }

    // Kurse von anderen Usern gibt es für einen selbst nicht, deshalb 404 statt 403
    private Course findOwnCourse(Long id, User user) {
        return courseRepository.findByIdAndUserId(id, user.getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Kurs mit ID " + id + " nicht gefunden"));
    }
}
