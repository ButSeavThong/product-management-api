package kh.coding.fullstack.dto;

public record CourseResponse(
        String code,
        String title,
        Double price,
        Boolean status
) {
}
