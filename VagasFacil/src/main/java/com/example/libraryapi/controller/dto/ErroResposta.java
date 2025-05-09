package com.example.libraryapi.controller.dto;

import org.springframework.http.HttpStatus;
import java.util.List;
import com.example.libraryapi.execeptions.RegistrosDuplicadosExcepion;
import com.example.libraryapi.execeptions.OperacoesNaoPermitidasExceptions;

public record ErroResposta(String status, String mensagem, List<ErroCampo> erros) {

    public static ErroResposta respostaPadrao(String mensagem) {
        return new ErroResposta(HttpStatus.BAD_REQUEST.value(), mensagem, List.of());
    }

    public static ErroResposta conflito(String mensagem) {
        return new ErroResposta(HttpStatus.CONFLICT.value(), mensagem, List.of());
    }
}
