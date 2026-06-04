package ru.itis.cars.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.data.domain.Page;

import java.util.List;

public class PageDto<T> {
    @Schema(description = "Список элементов")
    private List<T> elements;
    @Schema(description = "Количество страниц")
    private int totalPage;
    @Schema(description = "Количество элементов")
    private long totalElements;

    public PageDto() {}

    private PageDto(List<T> elements, int totalPage, long totalElements) {
        this.elements = elements;
        this.totalPage = totalPage;
        this.totalElements = totalElements;
    }

    public static <T> PageDto<T> from(List<T> elements, int totalPage, long totalElements) {
        return new PageDto<>(elements, totalPage, totalElements);
    }

    public List<T> getElements() {
        return elements;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public long getTotalElements() {
        return totalElements;
    }
}
