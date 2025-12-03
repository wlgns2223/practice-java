package org.example.commerce.repository;

import jakarta.persistence.LockModeType;
import org.example.commerce.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ItemRepository extends JpaRepository<Item,Long> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query(value = "SELECT i FROM Item i WHERE i.id = :itemId")
    Optional<Item> findByIdWithLock(Long itemId);
}
