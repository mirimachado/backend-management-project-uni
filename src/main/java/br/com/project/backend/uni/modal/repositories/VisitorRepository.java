package br.com.project.backend.uni.modal.repositories;

import br.com.project.backend.uni.modal.entities.Visitor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VisitorRepository extends JpaRepository<Visitor, Long> {
    Optional<Visitor> findById(Long Long);
}