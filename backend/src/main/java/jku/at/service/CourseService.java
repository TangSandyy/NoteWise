package jku.at.service;

import jku.at.dto.CourseRequest;
import jku.at.dto.CourseResponse;
import jku.at.dto.CourseUpdateRequest;
import jku.at.entity.Course;
import jku.at.entity.User;
import jku.at.repository.CourseRepository;
import jku.at.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@Transactional
public class CourseService {

    private final CourseRepository courseRepository;
    private final UserRepository userRepository;

    public CourseService(CourseRepository courseRepository, UserRepository userRepository) {
        this.courseRepository = courseRepository;
        this.userRepository = userRepository;
    }

    @Transactional(readOnly = true)
    public List<CourseResponse> getCoursesOfUser(Long userId) {
        return courseRepository.findByUserId(userId).stream()
                .map(CourseResponse::from)
                .toList();
    }

    @Transactional(readOnly = true)
    public CourseResponse getCourse(Long id) {
        return CourseResponse.from(findCourse(id));
    }

    public CourseResponse createCourse(CourseRequest request) {
        User user = userRepository.findById(request.userId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "User mit ID " + request.userId() + " nicht gefunden"));

        Course course = new Course();
        course.setName(request.name());
        course.setUser(user);

        return CourseResponse.from(courseRepository.save(course));
    }

    public CourseResponse updateCourse(Long id, CourseUpdateRequest request) {
        Course course = findCourse(id);
        course.setName(request.name());
        return CourseResponse.from(course);
    }

    public void deleteCourse(Long id) {
        courseRepository.delete(findCourse(id));
    }

    private Course findCourse(Long id) {
        return courseRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Kurs mit ID " + id + " nicht gefunden"));
    }
}
