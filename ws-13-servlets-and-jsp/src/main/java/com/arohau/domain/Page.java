package com.arohau.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Page<T> {
    private int currentPageNum;
    private int totalItems;
    private int amountPerPage;
    private int totalPages;
    private List<T> items;
}
