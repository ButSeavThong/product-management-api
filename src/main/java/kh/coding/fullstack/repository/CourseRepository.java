package kh.coding.fullstack.repository;

import kh.coding.fullstack.domain.Course;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
@Setter
@Getter
@NoArgsConstructor
@Repository
public class CourseRepository {
    List<Course> courses = new ArrayList<>(
            List.of(
                    new Course(1 ,"A01", "Java", 1000.0, true),
                    new Course(2, "A02", "JavaScript", 1000.0, true),
                    new Course(2, "A03", "Python", 950.0, true),
                    new Course(4, "A04", "C#", 900.0, true),
                    new Course(5, "A05", "Kotlin", 920.0, true),
                    new Course(6, "A06", "Go", 980.0, true),
                    new Course(7, "A07", "Rust", 970.0, true),
                    new Course(8, "A08", "Java", 990.0, true),
                    new Course(9, "A09", "PHP", 850.0, true),
                    new Course(10, "A10", "TypeScript", 1000.0, true)
            )
    );

    /**
     * get all product from database !
     * @return List<Course>
     */
    public List<Course> getAllCoursesFromDatabase() {
        return courses;
    }


}
