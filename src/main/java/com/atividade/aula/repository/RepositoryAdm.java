package com.atividade.aula.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.atividade.aula.models.Adm;

public interface RepositoryAdm extends JpaRepository<Adm, Long>  {

    // 🔐 BUSCAR POR EMAIL (LOGIN)
    Optional<Adm> findByEmail(String email);

}