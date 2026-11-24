package jku.at.controller;

import jakarta.validation.Valid;
import jku.at.dto.CourseRequest;
import jku.at.dto.CourseResponse;
import jku.at.dto.CourseUpdateRequest;
import jku.at.service.CourseService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/courses")
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    // GET /api/courses?userId=1
    @GetMapping
    public List<CourseResponse> getCourses(@RequestParam Long userId) {
        return courseService.getCoursesOfUser(userId);
    }

    // GET /api/courses/5
    @GetMapping("/{id}")
    public CourseResponse getCourse(@PathVariable Long id) {
        return courseService.getCourse(id);
    }

    // POST /api/courses  { "name": "Data Warehousing", "userId": 1 }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CourseResponse createCourse(@Valid @RequestBody CourseRequest request) {
        return courseService.createCourse(request);
    }

    // PUT /api/courses/5  { "name": "Neuer Name" }
    @PutMapping("/{id}")
    public CourseResponse updateCourse(@PathVariable Long id, @Valid @RequestBody CourseUpdateRequest request) {
        return courseService.updateCourse(id, request);
    }

    // DELETE /api/courses/5
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCourse(@PathVariable Long id) {
        courseService.deleteCourse(id);
    }
}
