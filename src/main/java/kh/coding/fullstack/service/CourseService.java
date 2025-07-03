package kh.coding.fullstack.service;

import kh.coding.fullstack.dto.CourseResponse;

import java.util.List;

public interface CourseService {
    /**
     * How to write document comment
     * get alll user from database
     * @return List<courseResponse>
     * Author ( Seavthong )
     */
    List<CourseResponse> getCourses();

    List<CourseResponse> getCoursesQuery(Boolean status, String title);

    CourseResponse getCourseByCode(String code);

    CourseResponse getCourseById(Integer id);
}
