package br.com.project.backend.uni.modal.repositories;

import br.com.project.backend.uni.modal.entities.VisitorInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VisitorInfoRepository extends JpaRepository<VisitorInfo, Long> {
    Optional<VisitorInfo> findById(Long Long);
}
