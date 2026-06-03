package com.atividade.aula.Controler;

public class PsicologoDTO {

    private String nome;
    private String email;
    private String senha;
    private Long crp;
    private String telefone;

    private String cep;
    private String cidade;

    // NOVOS CAMPOS
    private String logradouro;
    private String bairro;
    private String numero;

    private Double latitude;
    private Double longitude;

    private String redeSocialUrl;

    private boolean humanista;
    private boolean psicanalise;
    private boolean tcc;
    private boolean gestalt;
    private boolean fenomenologia;
    private boolean sistemica;

    // ==========================
    // GETTERS E SETTERS
    // ==========================

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

    public Long getCrp() {
        return crp;
    }

    public void setCrp(Long crp) {
        this.crp = crp;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public String getRedeSocialUrl() {
        return redeSocialUrl;
    }

    public void setRedeSocialUrl(String redeSocialUrl) {
        this.redeSocialUrl = redeSocialUrl;
    }

    public boolean isHumanista() {
        return humanista;
    }

    public void setHumanista(boolean humanista) {
        this.humanista = humanista;
    }

    public boolean isPsicanalise() {
        return psicanalise;
    }

    public void setPsicanalise(boolean psicanalise) {
        this.psicanalise = psicanalise;
    }

    public boolean isTcc() {
        return tcc;
    }

    public void setTcc(boolean tcc) {
        this.tcc = tcc;
    }

    public boolean isGestalt() {
        return gestalt;
    }

    public void setGestalt(boolean gestalt) {
        this.gestalt = gestalt;
    }

    public boolean isFenomenologia() {
        return fenomenologia;
    }

    public void setFenomenologia(boolean fenomenologia) {
        this.fenomenologia = fenomenologia;
    }

    public boolean isSistemica() {
        return sistemica;
    }

    public void setSistemica(boolean sistemica) {
        this.sistemica = sistemica;
    }
}