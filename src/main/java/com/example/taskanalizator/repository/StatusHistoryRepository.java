package com.example.taskanalizator.repository;

import com.example.taskanalizator.domain.StatusHistoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface StatusHistoryRepository extends JpaRepository<StatusHistoryEntity, UUID> {
}
