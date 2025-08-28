package org.example.repo;

import org.example.entity.TakenBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TakenBookRepository extends JpaRepository<TakenBook, Integer> {
}
