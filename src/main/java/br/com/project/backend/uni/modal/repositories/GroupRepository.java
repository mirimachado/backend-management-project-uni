package br.com.project.backend.uni.modal.repositories;

import br.com.project.backend.uni.modal.entities.Group;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GroupRepository extends JpaRepository<Group, Long> {
    Optional<Group> findById(Long Long);
}