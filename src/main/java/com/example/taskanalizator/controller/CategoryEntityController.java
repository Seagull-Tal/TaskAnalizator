package com.example.taskanalizator.controller;

import com.example.taskanalizator.domain.dtos.CategoryDTO;
import com.example.taskanalizator.domain.dtos.SetCategoryDTO;
import com.example.taskanalizator.service.CategoryEntityService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/category")
@RequiredArgsConstructor
@Slf4j
public class CategoryEntityController {
    private final CategoryEntityService categoryService;

    @PutMapping("/{ticketId}/{categoryId}")
    public ResponseEntity<CategoryDTO> setCategory(@PathVariable UUID ticketId,
                                                   @PathVariable UUID categoryId,
                                                   @RequestBody SetCategoryDTO setCategoryDTO) {
        log.info("Setting category with id {}", categoryId);
        return ResponseEntity.ok(categoryService.setCategory(setCategoryDTO, ticketId, categoryId));
    }
}
