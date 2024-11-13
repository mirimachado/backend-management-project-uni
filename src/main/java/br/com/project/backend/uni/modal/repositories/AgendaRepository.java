package br.com.project.backend.uni.modal.repositories;

import br.com.project.backend.uni.modal.entities.Agenda;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AgendaRepository extends JpaRepository<Agenda, Long> {
    Optional<Agenda> findById(Long Long);
}