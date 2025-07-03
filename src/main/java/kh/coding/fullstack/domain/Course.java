package kh.coding.fullstack.domain;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@Builder
public class Course {
    private Integer id;
    private String code;
    private String title;
    private Double price;
    private Boolean status;
}
