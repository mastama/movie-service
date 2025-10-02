package com.yolifay.movieservice.dto;

import lombok.*;
import org.springframework.data.domain.Page;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class PageResponse<T> implements Serializable {
    @Serial
    private static final long serialVersionUID = 4202309107085368873L;

    private transient List<T> content;
    private int page;            // current page (1-based)
    private int size;            // page size
    private long totalElements;  // total rows
    private int totalPages;      // total pages (1-based)
    private boolean first;
    private boolean last;

    public static <U> PageResponse<U> from(Page<U> page) {
        return PageResponse.<U>builder()
                .content(page.getContent())
                .page(page.getNumber() + 1) // Convert to 1-based page number
                .size(page.getSize())
                .totalElements(page.getTotalElements())
                .totalPages(page.getTotalPages())
                .first(page.isFirst())
                .last(page.isLast())
                .build();
    }
}
