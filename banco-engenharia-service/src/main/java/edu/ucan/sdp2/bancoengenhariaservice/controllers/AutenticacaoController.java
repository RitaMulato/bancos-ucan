package edu.ucan.sdp2.bancoengenhariaservice.controllers;

import edu.ucan.sdp2.bancoengenhariaservice.dto.Resposta;
import edu.ucan.sdp2.bancoengenhariaservice.dto.requisicoes.ResetSenhaRequisicaoDto;
import edu.ucan.sdp2.bancoengenhariaservice.dto.requisicoes.UtilizadorAutoRegistoDto;
import edu.ucan.sdp2.bancoengenhariaservice.dto.requisicoes.UtilizadorLoginRequisicaoDto;
import edu.ucan.sdp2.bancoengenhariaservice.models.TelefoneVerificacao;
import edu.ucan.sdp2.bancoengenhariaservice.services.AutenticacaoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@RestController
@RequestMapping("/autenticacao")
@RequiredArgsConstructor
public class AutenticacaoController {

    private final AutenticacaoService service;
    @Operation(summary = "verificar Telemovel", description = "Rota utilizada para enviar um código de verificação no telemovel")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful"),
            @ApiResponse(responseCode = "404", description = "número de telemovel não encontrado")
    })
    @PostMapping(value = "/verificar_telemovel")
    public ResponseEntity<Resposta> verificarTelemovel(@RequestParam String phone){
        return  service.enviarCodigoVerificacao(phone);
    }

    @Operation(summary = "Confirmar Codigo", description = "Rota utilizada para confirmar o código de verificação")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful"),
            @ApiResponse(responseCode = "404", description = "Código não encontrado")
    })
    @Secured({"ROLE_USER"})
    @PostMapping(value = "/confirmar-codigo")
    public ResponseEntity<Resposta> confirmarCodigo(@RequestBody TelefoneVerificacao dto){
        return  service.verificacaoCodigoTelefone(dto.getTelefone(), dto.getCodigo());
    }
    @Operation(summary = "Registrar Cliente", description = "Registrar um novo cliente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful"),
            @ApiResponse(responseCode = "404", description = "Cliente não encontrado")
    })
    @PostMapping(value= "/registar", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    public ResponseEntity<Resposta> register(
             UtilizadorAutoRegistoDto dto,
             @RequestPart MultipartFile ficheiro
    ) {
        return service.autoRegistoCliente(dto.withFicheiro(ficheiro));
    }

    @Operation(summary = "Login", description = "Rota utilizada para efectuar o login")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful"),
            @ApiResponse(responseCode = "404", description = "Dados incorrectos")
    })
    @PostMapping("/login")
    public ResponseEntity<Resposta> login(
            @RequestBody UtilizadorLoginRequisicaoDto dto
    ) {
        return service.login(dto);
    }

    @Operation(summary = "Alterar a palavra passe", description = "Rota utilizada para alterar a palavra passe")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful"),
            @ApiResponse(responseCode = "404", description = "Dados incorrectos")
    })
    @PostMapping(value = "/resetar-pwd")
    public ResponseEntity<Resposta> alterarPassword(@RequestBody ResetSenhaRequisicaoDto request){
        return  service.passwordReset(request);
    }

}


