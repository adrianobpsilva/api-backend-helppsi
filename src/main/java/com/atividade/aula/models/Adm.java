package com.atividade.aula.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table (name = "Adm")
public class Adm {
    
    // Construtor Vazio (Obrigatório para o JPA)
    public Adm() {
    }

    // CAMPOS (Alterados para private)
    @Column (
        name = "nome",
        columnDefinition= "VARCHAR(60)",
        nullable= false
    )
    private String nome; // Alterado para private

    @Column ( name = "email", columnDefinition= "VARCHAR(60)", nullable= false)
    private String email; // Alterado para private

    @Column (name = "senha", columnDefinition= "VARCHAR(60)", nullable= false)
    private String senha; // Alterado para private
    
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column( name = "inscricao")
    private Long inscricao; // Alterado para private
    
    
    // -------------------------------------------------------------------
    // MÉTODOS GETTERS E SETTERS
    // -------------------------------------------------------------------
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Long getInscricao() {
        return inscricao;
    }

    public void setInscricao(Long inscricao) {
        this.inscricao = inscricao;
    }
}