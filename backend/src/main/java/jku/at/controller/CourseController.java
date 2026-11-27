package jku.at.controller;

import jakarta.validation.Valid;
import jku.at.dto.CourseRequest;
import jku.at.dto.CourseResponse;
import jku.at.entity.User;
import jku.at.service.CourseService;
import jku.at.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// Alle Endpoints arbeiten nur mit den Kursen vom eingeloggten User
@RestController
@RequestMapping("/api/courses")
public class CourseController {

    private final CourseService courseService;
    private final UserService userService;

    public CourseController(CourseService courseService, UserService userService) {
        this.courseService = courseService;
        this.userService = userService;
    }

    // GET /api/courses
    @GetMapping
    public List<CourseResponse> getCourses(@AuthenticationPrincipal Jwt jwt) {
        return courseService.getCourses(currentUser(jwt));
    }

    // GET /api/courses/5
    @GetMapping("/{id}")
    public CourseResponse getCourse(@PathVariable Long id, @AuthenticationPrincipal Jwt jwt) {
        return courseService.getCourse(id, currentUser(jwt));
    }

    // POST /api/courses  { "name": "Data Warehousing" }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CourseResponse createCourse(@Valid @RequestBody CourseRequest request, @AuthenticationPrincipal Jwt jwt) {
        return courseService.createCourse(request, currentUser(jwt));
    }

    // PUT /api/courses/5  { "name": "Neuer Name" }
    @PutMapping("/{id}")
    public CourseResponse updateCourse(@PathVariable Long id, @Valid @RequestBody CourseRequest request,
                                       @AuthenticationPrincipal Jwt jwt) {
        return courseService.updateCourse(id, request, currentUser(jwt));
    }

    // DELETE /api/courses/5
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCourse(@PathVariable Long id, @AuthenticationPrincipal Jwt jwt) {
        courseService.deleteCourse(id, currentUser(jwt));
    }

    private User currentUser(Jwt jwt) {
        return userService.getCurrentUser(jwt);
    }
}
