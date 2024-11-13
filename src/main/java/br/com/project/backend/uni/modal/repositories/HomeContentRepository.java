package br.com.project.backend.uni.modal.repositories;

import br.com.project.backend.uni.modal.entities.HomeContent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface HomeContentRepository extends JpaRepository<HomeContent, Long> {
    Optional<HomeContent> findById(Long Long);
}