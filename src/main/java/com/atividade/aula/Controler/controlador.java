package com.atividade.aula.Controler;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;

import com.atividade.aula.models.Adm;
import com.atividade.aula.models.Psicologo;
import com.atividade.aula.repository.RepositoryAdm;
import com.atividade.aula.repository.RepositoryPsicologo;
import com.atividade.aula.service.OpenCageService;

@RestController
@CrossOrigin(origins = "http://localhost:3000, https://helpsi.netlify.app/login")
public class controlador {

    @Autowired
    RepositoryAdm repositoryAdm;

    @Autowired
    RepositoryPsicologo repositoryPsicologo;

    @Autowired
    OpenCageService openCageService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    // ================= LOGIN PSICÓLOGO =================

    @PostMapping("/login-psicologo")
    public ResponseEntity<?> loginPsicologo(
            @RequestBody Map<String, String> dados) {

        try {

            String email = dados.get("email");
            String senha = dados.get("senha");

            Optional<Psicologo> usuario =
                    repositoryPsicologo.findByEmail(email);

            if (usuario.isEmpty()) {

                return ResponseEntity
                        .status(HttpStatus.UNAUTHORIZED)
                        .body("Usuário não encontrado");
            }

            Psicologo p = usuario.get();

            if (!passwordEncoder.matches(
                    senha,
                    p.getSenha())) {

                return ResponseEntity
                        .status(HttpStatus.UNAUTHORIZED)
                        .body("Senha inválida");
            }

            Map<String, Object> resposta = Map.of(
                    "crp", p.getCrp(),
                    "nome", p.getNome(),
                    "email", p.getEmail(),
                    "telefone", p.getTelefone(),
                    "cep", p.getCep(),
                    "cidade", p.getCidade(),
                    "redeSocialUrl", p.getRedeSocialUrl()
            );

            return ResponseEntity.ok(resposta);

        } catch (Exception e) {

            e.printStackTrace();

            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro no login: " + e.getMessage());
        }
    }

    // ================= CADASTRO =================

    @PostMapping("/cadastrar-psicologo")
    public ResponseEntity<?> cadastrarPsicologo(
            @RequestPart("dados") PsicologoDTO dados) {

        try {

            Psicologo p = new Psicologo();

            p.setNome(dados.getNome());
            p.setEmail(dados.getEmail());

            // SENHA CRIPTOGRAFADA
            p.setSenha(
                    passwordEncoder.encode(
                            dados.getSenha()
                    )
            );

            p.setCrp(dados.getCrp());
            p.setTelefone(dados.getTelefone());

            p.setCep(dados.getCep());
            p.setCidade(dados.getCidade());

            p.setLogradouro(
                    dados.getLogradouro()
            );

            p.setBairro(
                    dados.getBairro()
            );

            p.setNumero(
                    dados.getNumero()
            );

            String enderecoBusca =
                    dados.getLogradouro()
                            + ", "
                            + dados.getNumero()
                            + ", "
                            + dados.getBairro()
                            + ", "
                            + dados.getCidade()
                            + ", PE, Brasil";

            System.out.println(
                    "Endereço enviado ao OpenCage:"
            );

            System.out.println(
                    enderecoBusca
            );

            var coordenadas =
                    openCageService.buscarCoordenadas(
                            enderecoBusca
                    );

            System.out.println(
                    "Coordenadas encontradas:"
            );

            System.out.println(
                    coordenadas
            );

            if (coordenadas != null) {

                p.setLatitude(
                        coordenadas.get("latitude")
                );

                p.setLongitude(
                        coordenadas.get("longitude")
                );
            }

            p.setRedeSocialUrl(
                    dados.getRedeSocialUrl()
            );

            p.setHumanista(
                    dados.isHumanista()
            );

            p.setPsicanalise(
                    dados.isPsicanalise()
            );

            p.setTcc(
                    dados.isTcc()
            );

            p.setGestalt(
                    dados.isGestalt()
            );

            p.setFenomenologia(
                    dados.isFenomenologia()
            );

            p.setSistemica(
                    dados.isSistemica()
            );

            repositoryPsicologo.save(p);

            return ResponseEntity.ok(p);

        } catch (Exception e) {

            e.printStackTrace();

            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(
                            "Erro ao salvar psicólogo: "
                                    + e.getMessage()
                    );
        }
    }

    // ================= LISTAGEM =================

    @GetMapping("/psicologos")
    public ResponseEntity<List<Psicologo>> listarPsicologos() {

        return ResponseEntity.ok(
                repositoryPsicologo.findAll()
        );
    }

    @GetMapping("/psicologo/{crp}")
    public ResponseEntity<?> buscarPsicologoPorCrp(
            @PathVariable Long crp) {

        Optional<Psicologo> psicologo =
                repositoryPsicologo.findById(crp);

        if (psicologo.isEmpty()) {

            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("Psicólogo não encontrado");
        }

        return ResponseEntity.ok(
                psicologo.get()
        );
    }

    @GetMapping("/psicologos/cidade")
    public ResponseEntity<List<Psicologo>>
    buscarPsicologosPorCidade(String cidade) {

        return ResponseEntity.ok(
                repositoryPsicologo.findByCidade(cidade)
        );
    }

    // ================= ATUALIZAR =================

    @PutMapping("/psicologo/{crp}")
    public ResponseEntity<?> atualizarPsicologo(
            @PathVariable Long crp,
            @RequestBody Psicologo dadosAtualizados) {

        Optional<Psicologo> psicologoExistente =
                repositoryPsicologo.findById(crp);

        if (psicologoExistente.isEmpty()) {

            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("Psicólogo não encontrado");
        }

        Psicologo p = psicologoExistente.get();

        p.setNome(dadosAtualizados.getNome());
        p.setEmail(dadosAtualizados.getEmail());
        p.setTelefone(dadosAtualizados.getTelefone());
        p.setCep(dadosAtualizados.getCep());
        p.setCidade(dadosAtualizados.getCidade());
        p.setRedeSocialUrl(
                dadosAtualizados.getRedeSocialUrl()
        );

        repositoryPsicologo.save(p);

        return ResponseEntity.ok(p);
    }

    // ================= DELETAR =================

    @DeleteMapping("/DeletarPsicologo/{crp}")
    public ResponseEntity<Void> deletarPsicologo(
            @PathVariable Long crp) {

        if (repositoryPsicologo.existsById(crp)) {

            repositoryPsicologo.deleteById(crp);

            return ResponseEntity
                    .noContent()
                    .build();
        }

        return ResponseEntity
                .notFound()
                .build();
    }

    // ================= ADM =================

    @PostMapping("/cadastrar-adm")
    public ResponseEntity<Adm> cadastrarAdm(
            @RequestBody Adm adm) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(
                        repositoryAdm.save(adm)
                );
    }

    @GetMapping("/adms")
    public ResponseEntity<List<Adm>> listarAdms() {

        return ResponseEntity.ok(
                repositoryAdm.findAll()
        );
    }
}