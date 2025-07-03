package kh.coding.fullstack.service.Iml;

import kh.coding.fullstack.dto.CourseResponse;
import kh.coding.fullstack.repository.CourseRepository;
import kh.coding.fullstack.service.CourseService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
@Service
@AllArgsConstructor
public class CoureServiceIml implements CourseService  {
    private final CourseRepository courseRepository;
    @Override
    public List<CourseResponse> getCourses() {
        return courseRepository
                .getAllCoursesFromDatabase()
                .stream()
                .filter(course -> course.getStatus() == true)
                .map(course->new CourseResponse(
                                course.getCode(),
                                course.getTitle(),
                                course.getPrice(),
                        true
                        )
                ).toList();
    }

    /**
     * Mr.Thong_backend Developer
     * @param status
     * @param title
     * @return List<CourseResponse>
     */
    @Override
    public List<CourseResponse> getCoursesQuery(Boolean status,String title) {
        return courseRepository
                .getAllCoursesFromDatabase()
                .stream()
                .filter(course ->course.getStatus().equals(status)
                && course.getTitle().toLowerCase().contains(title.toLowerCase()))
                .map(course-> new CourseResponse(
                        course.getCode(),
                        course.getTitle(),
                        course.getPrice(),
                        course.getStatus()
                )).toList();
    }

    @Override
    public CourseResponse getCourseByCode(String code) {
        return courseRepository
                .getAllCoursesFromDatabase()
                .stream()
                .filter(course->
                        course.getCode().toLowerCase().contains(code.toLowerCase())
                )
                .map(couse-> new CourseResponse(
                        couse.getCode(),
                        couse.getTitle(),
                        couse.getPrice(),
                        couse.getStatus()
                ))
                .findFirst().orElseThrow(()->new RuntimeException("Course not found"));
    }

    @Override
    public CourseResponse getCourseById(Integer id) {
        return courseRepository
                .getAllCoursesFromDatabase()
                .stream()
                .filter(course->course.getId()
                        .equals(id)
                )
                .map(couse-> new CourseResponse(
                        couse.getCode(),
                        couse.getTitle(),
                        couse.getPrice(),
                        couse.getStatus()
                ))
                .findFirst().orElseThrow(()->new RuntimeException("Course not found"));
    }
}
