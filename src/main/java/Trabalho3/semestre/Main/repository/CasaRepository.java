package Trabalho3.semestre.Main.repository;

import Trabalho3.semestre.Main.entidades.Casa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CasaRepository extends JpaRepository<Casa, Long> {
    Optional<Casa> findById(Long id);
}
