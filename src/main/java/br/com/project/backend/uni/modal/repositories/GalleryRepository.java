package br.com.project.backend.uni.modal.repositories;

import br.com.project.backend.uni.modal.entities.Gallery;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GalleryRepository extends JpaRepository<Gallery, Long> {
    Optional<Gallery> findById(Long Long);
}