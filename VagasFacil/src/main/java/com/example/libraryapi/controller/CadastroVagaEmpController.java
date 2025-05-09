package com.example.libraryapi.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import com.example.libraryapi.execeptions.RegistrosDuplicadosExcepion;
import com.example.libraryapi.execeptions.OperacoesNaoPermitidasExceptions;
import com.example.libraryapi.controller.dto.ErroResposta;


import jakarta.validation.Valid;
import java.net.URI;

import com.example.libraryapi.controller.dto.CadastrovagaEmpDTO;
import com.example.libraryapi.service.CadastroVagaEmpService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import com.example.libraryapi.model.CadastroVagaEmp;

public class CadastroVagaEmpController {

    private final CadastroVagaEmpService service;

    @PostMapping
    public ResponseEntity<Object> salvar(@RequestBody @Valid CadastrovagaEmpDTO cadastroVagaEmp) {
        try {
            CadastrovagaEmpDTO cadastroVagaEmpEntidade = cadastroVagaEmp.mapearParaCadastrovagaEmp();
            service.salvar(cadastroVagaEmpEntidade);

            URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("/{codVaga}")
                    .buildAndExpand(cadastroVagaEmpEntidade.getCodVaga())
                    .toUri();

            return ResponseEntity.created(location).build();
        } catch (RegistrosDuplicadosExcepion e) {
            var erroDTO = ErroResposta.conflito(e.getMessage());
            return ResponseEntity.status(erroDTO.status()).body(erroDTO);
        }

    }

    @GetMapping("{id}")
    public ResponseEntity<CadastrovagaEmpDTO> obterDetalhes(@PathVariable("id") String id) {
        var idCadastroVaga = UUID.fromString(id);
        Optional<CadastroVagaEmp> cadastroVagaOptional = service.obterPorId(idCadastroVaga);
        if (cadastroVagaOptional.isPresent()) {
            CadastrovagaEmpDTO cadastroVaga = cadastroVagaOptional.get();
            CadastrovagaEmpDTO dto = new CadastrovagaEmpDTO(
                    cadastroVaga.getId(),
                    cadastroVaga.getNome(),
                    cadastroVaga.getDataNascimento(),
                    cadastroVaga.getNacionalidade());
            return ResponseEntity.ok(dto);
        }

        return ResponseEntity.notFound().build();
    }

    // se nao tiver autor, retorna ok
    @DeleteMapping("{codVaga}")
    public ResponseEntity<Object> deletar(@PathVariable("codVaga") String codVaga) {
        try {
            var idCadastroVaga = UUID.fromString(codVaga);
            Optional<CadastroVagaEmp> cadastroVagaOptional = service.obterPorId(idCadastroVaga);

            // se nao tiver autor, retorna erro 404
            if (cadastroVagaOptional.isEmpty()) {
                return ResponseEntity.notFound().build();
            }

            service.deletar(cadastroVagaOptional.get());

            return ResponseEntity.noContent().build();

        } catch (OperacoesNaoPermitidasExceptions e) {
            var erroResposta = ErroResposta.respostaPadrao(e.getMessage());
            return ResponseEntity.status(erroResposta.status()).body(erroResposta);
        }
    }

    @GetMapping
    public ResponseEntity<List<CadastrovagaEmpDTO>> buscar(@RequestParam(value = "codVaga", required = false) String codVaga,
            @RequestParam(value = "dadosTituloVaga", required = false) String dadosTituloVaga) {
        List<CadastrovagaEmpDTO> resultado = service.pesquisarByExample(codVaga, dadosTituloVaga);
        List<CadastrovagaEmpDTO> Lista = resultado
                .stream().map(cadastroVaga -> new CadastrovagaEmpDTO(
                        cadastroVaga.getCodVaga(),
                        cadastroVaga.getDadosTituloVaga(),
                        cadastroVaga.getDadosAreaAtuacao(),
                        cadastroVaga.getDadosTipoContrato(),
                        cadastroVaga.getDadosNivelExperiencia(),
                        cadastroVaga.getDadosQuantVagas(),
                        cadastroVaga.getDadosSalario(),
                        cadastroVaga.getDadosDescricaoVaga(),
                        cadastroVaga.getRequisitosEscolariadade(),
                        cadastroVaga.getRequisitosExperiencia(),
                        cadastroVaga.getRequisitosHabilidades(),
                        cadastroVaga.getBeneficiosVale_Refeicao(),
                        cadastroVaga.getBeneficiosAssistencia_Medica(),
                        cadastroVaga.getBeneficiosSeguro_Vida(),
                        cadastroVaga.getBeneficiosBonificacao(),
                        cadastroVaga.getBeneficiosVale_Transporte(),
                        cadastroVaga.getLocalTrabPossRemoto(),
                        cadastroVaga.getInfoAdDataPublVaga(),
                        cadastroVaga.getInfoAdDataLimite(),
                        cadastroVaga.getInfoAdProcessoSeletivo(),
                        cadastroVaga.getDadosContatoEmailCurriculo(),
                        cadastroVaga.getDadosContatoTelefone(),
                        cadastroVaga.getObservacoesVaga(),
                        cadastroVaga.getCodFichaEmp()

                ))  

                .collect(Collectors.toList())   ;
        return ResponseEntity.ok(Lista);

    }

    @PutMapping("{id}")
    public ResponseEntity<Object> atualizar(@PathVariable("id") String id,
            @RequestBody CadastrovagaEmpDTO dto) {
        try {
            var idCadastroVaga = UUID.fromString(id);
            Optional<CadastrovagaEmpDTO> cadastroVagaOptional = service.obterPorId(idCadastroVaga);

            if (cadastroVagaOptional.isEmpty()) {
                return ResponseEntity.notFound().build();
            }

            var cadastroVaga = cadastroVagaOptional.get();
            cadastroVaga.setDadosTituloVaga(dto.dadosTituloVaga());
            cadastroVaga.setDadosAreaAtuacao(dto.dadosAreaAtuacao());
            cadastroVaga.setDadosTipoContrato(dto.dadosTipoContrato());
            cadastroVaga.setDadosNivelExperiencia(dto.dadosNivelExperiencia());
            cadastroVaga.setDadosQuantVagas(dto.dadosQuantVagas());
            cadastroVaga.setDadosSalario(dto.dadosSalario());
            cadastroVaga.setDadosDescricaoVaga(dto.dadosDescricaoVaga());
            cadastroVaga.setRequisitosEscolariadade(dto.requisitosEscolariadade());
            cadastroVaga.setRequisitosExperiencia(dto.requisitosExperiencia());
            cadastroVaga.setRequisitosHabilidades(dto.requisitosHabilidades());
            cadastroVaga.setBeneficiosVale_Refeicao(dto.beneficiosVale_Refeicao());
            cadastroVaga.setBeneficiosAssistencia_Medica(dto.beneficiosAssistencia_Medica());
            cadastroVaga.setBeneficiosSeguro_Vida(dto.beneficiosSeguro_Vida());
            cadastroVaga.setBeneficiosBonificacao(dto.beneficiosBonificacao());
            cadastroVaga.setBeneficiosVale_Transporte(dto.beneficiosVale_Transporte());
            cadastroVaga.setLocalTrabPossRemoto(dto.localTrabPossRemoto());
            cadastroVaga.setInfoAdDataPublVaga(dto.infoAdDataPublVaga());
            cadastroVaga.setInfoAdDataLimite(dto.infoAdDataLimite());
            cadastroVaga.setInfoAdProcessoSeletivo(dto.infoAdProcessoSeletivo());
            cadastroVaga.setDadosContatoEmailCurriculo(dto.dadosContatoEmailCurriculo());
            cadastroVaga.setDadosContatoTelefone(dto.dadosContatoTelefone());
            cadastroVaga.setObservacoesVaga(dto.observacoesVaga());
            cadastroVaga.setCodFichaEmp(dto.codFichaEmp());
            service.atualizar(cadastroVaga);

            return ResponseEntity.noContent().build();
        } catch (

        RegistrosDuplicadosExcepion e) {
            var erroDTO = ErroResposta.conflito(e.getMessage());
            return ResponseEntity.status(erroDTO.status()).body(erroDTO);
        }
    }
}
