package com.atividade.aula.Controler;

public class LoginResponseDTO {

    private Long crp;
    private String nome;
    private String email;
    private String telefone;
    private String cep;
    private String cidade;
    private String redeSocialUrl;

    public LoginResponseDTO() {
    }

    public LoginResponseDTO(
        Long crp,
        String nome,
        String email,
        String telefone,
        String cep,
        String cidade,
        String redeSocialUrl
    ) {
        this.crp = crp;
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.cep = cep;
        this.cidade = cidade;
        this.redeSocialUrl = redeSocialUrl;
    }

    public Long getCrp() { return crp; }
    public String getNome() { return nome; }
    public String getEmail() { return email; }
    public String getTelefone() { return telefone; }
    public String getCep() { return cep; }
    public String getCidade() { return cidade; }
    public String getRedeSocialUrl() { return redeSocialUrl; }
}