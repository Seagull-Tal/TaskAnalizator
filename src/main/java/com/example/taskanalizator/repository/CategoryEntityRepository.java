package com.example.taskanalizator.repository;

import com.example.taskanalizator.domain.CategoryEntity;
import com.example.taskanalizator.domain.TicketEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CategoryEntityRepository  extends JpaRepository<CategoryEntity, UUID> {
}
