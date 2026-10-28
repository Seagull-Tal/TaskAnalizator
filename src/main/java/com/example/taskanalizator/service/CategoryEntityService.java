package com.example.taskanalizator.service;

import com.example.taskanalizator.domain.CategoryEntity;
import com.example.taskanalizator.domain.TicketEntity;
import com.example.taskanalizator.domain.dtos.CategoryDTO;
import com.example.taskanalizator.domain.dtos.SetCategoryDTO;
import com.example.taskanalizator.mapper.CategoryMapper;
import com.example.taskanalizator.repository.CategoryEntityRepository;
import com.example.taskanalizator.repository.TicketEntityRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class CategoryEntityService {
    private final CategoryEntityRepository categoryRepository;
    private final TicketEntityRepository ticketRepository;

    private final CategoryMapper categoryMapper;

    public CategoryDTO setCategory(SetCategoryDTO setCategoryDTO, UUID ticketId, UUID categoryId) {
        TicketEntity ticket = ticketRepository.findById(ticketId).orElseThrow(
                () -> new RuntimeException("Тикет не найден")
        );

        CategoryEntity category = categoryRepository.findById(categoryId).orElseThrow(
                () -> new RuntimeException("Категория не найдена")
        );

        log.info("CATEGORY" +  ticket.getCategory().toString());
        log.info(category.toString());

        if (!ticket.getCategory().equals(category)) {
            throw new RuntimeException("Категория не относится к тикету");
        }

        ticket.getCategory().setName(setCategoryDTO.name());
        ticket.getCategory().setDescription(setCategoryDTO.description());

        return categoryMapper.toDto(categoryRepository.save(category));
    }
}
