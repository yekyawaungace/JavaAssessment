package com.java.assessment.dto;

import com.java.assessment.entity.Book;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookPageResponse {
    private int currentPage;
    private int totalPages;
    private long totalElements;
    private int numberOfElements;
    private boolean hasNext;
    private boolean hasPrevious;
    private List<Book> items;
}