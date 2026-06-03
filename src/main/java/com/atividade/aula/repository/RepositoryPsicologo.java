package com.atividade.aula.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.atividade.aula.models.Psicologo;

public interface RepositoryPsicologo extends JpaRepository<Psicologo, Long> {

    // Buscar psicólogos por cidade
    List<Psicologo> findByCidade(String cidade);

    // Buscar por email (mantemos porque pode ser usado em outros lugares)
    Optional<Psicologo> findByEmail(String email);

    // Buscar usuário para login
    @Query("""
        SELECT p
        FROM Psicologo p
        WHERE p.email = :email
    """)
    Optional<Psicologo> buscarParaLogin(
            @Param("email") String email);
}