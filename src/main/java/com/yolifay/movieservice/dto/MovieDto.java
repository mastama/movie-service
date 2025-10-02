package com.yolifay.movieservice.dto;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MovieDto implements Serializable {
    private Long id;
    private String title;
    private String description;
    private Integer releaseYear;
}
