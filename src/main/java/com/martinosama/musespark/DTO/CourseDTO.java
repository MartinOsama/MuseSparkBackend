package com.martinosama.musespark.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CourseDTO {
    private String title;
    private String description;
    private String sourceType;
    private String sourceUrl;

    public CourseDTO(String title, String description, String sourceType, String sourceUrl) {
        this.title = title;
        this.description = description;
        this.sourceType = sourceType;
        this.sourceUrl = sourceUrl;
    }
}