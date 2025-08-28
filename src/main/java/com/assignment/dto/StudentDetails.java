package com.assignment.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class StudentDetails {
    @Schema(description = "studentId", example = "STU123")
    private String studentId;
    @Schema(description = "name", example = "Ravi")
    private String name;
    @Schema(description = "grade", example = "10")
    private String grade;
}

