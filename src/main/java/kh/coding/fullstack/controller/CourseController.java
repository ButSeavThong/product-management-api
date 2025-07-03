package kh.coding.fullstack.controller;


import kh.coding.fullstack.dto.CourseResponse;
import kh.coding.fullstack.service.CourseService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/course")
public class CourseController {
    private final CourseService courseService;

    /**
     * bellow method handle two types of request
     * 1. localhost:8080/course get all course
     * 2. we can query or search by status and title of course
     * @param status
     * @param title
     * @return  List<CourseResponse>
     */
    @GetMapping
    public List<CourseResponse> searchCourses(
            @RequestParam(required = false, defaultValue = "true")Boolean status,
            @RequestParam (required = false, defaultValue = "") String title) {
        return courseService.getCoursesQuery(status, title);

    }

    /**ThongFazon
     * @param code
     * @return CourseResponse
     */
    @GetMapping("/code/{code}")
    public CourseResponse getCourseByCode(@PathVariable String code) {
        return courseService.getCourseByCode(code);
    }

    @GetMapping("/id/{id}")
    public CourseResponse getCourseById(@PathVariable Integer id) {
        return courseService.getCourseById(id);
    }

}
