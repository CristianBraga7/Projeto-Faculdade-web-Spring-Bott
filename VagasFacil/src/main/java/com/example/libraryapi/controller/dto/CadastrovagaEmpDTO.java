package com.example.libraryapi.controller.dto;

import java.time.LocalDate;
import java.util.UUID;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import com.example.libraryapi.model.CadastroVagaEmp;

public record CadastrovagaEmpDTO(UUID id,
        @NotBlank(message = "Campo obrigatório") @Size(min = 2, max = 100, message = "Campo fora do padrão") String dadosTituloVaga,
        @NotNull(message = "Campo obrigatório") @Past(message = "Data de nascimento inválida") String dadosAreaAtuacao,
        @NotBlank(message = "Campo obrigatório") @Size(min = 2, max = 50, message = "Campo fora do padrão") String dadosTipoContrato,
        @NotBlank(message = "Campo obrigatório") @Size(min = 2, max = 50, message = "Campo fora do padrão") String dadosNivelExperiencia,
        @NotBlank(message = "Campo obrigatório") @Size(min = 2, max = 50, message = "Campo fora do padrão") String dadosQuantVagas,
        @NotBlank(message = "Campo obrigatório") @Size(min = 2, max = 50, message = "Campo fora do padrão") String dadosSalario,
        @NotBlank(message = "Campo obrigatório") @Size(min = 2, max = 50, message = "Campo fora do padrão") String dadosDescricaoVaga,
        @NotBlank(message = "Campo obrigatório") @Size(min = 2, max = 50, message = "Campo fora do padrão") String requisitosEscolariadade,
        @NotBlank(message = "Campo obrigatório") @Size(min = 2, max = 50, message = "Campo fora do padrão") String requisitosExperiencia,
        @NotBlank(message = "Campo obrigatório") @Size(min = 2, max = 50, message = "Campo fora do padrão") String requisitosHabilidades,
        @NotBlank(message = "Campo obrigatório") @Size(min = 2, max = 50, message = "Campo fora do padrão") String beneficiosVale_Refeicao,
        @NotBlank(message = "Campo obrigatório") @Size(min = 2, max = 50, message = "Campo fora do padrão") String beneficiosAssistencia_Medica,
        @NotBlank(message = "Campo obrigatório") @Size(min = 2, max = 50, message = "Campo fora do padrão") String beneficiosSeguro_Vida,
        @NotBlank(message = "Campo obrigatório") @Size(min = 2, max = 50, message = "Campo fora do padrão") String beneficiosBonificacao,
        @NotBlank(message = "Campo obrigatório") @Size(min = 2, max = 50, message = "Campo fora do padrão") String beneficiosVale_Transporte,
        @NotBlank(message = "Campo obrigatório") @Size(min = 2, max = 50, message = "Campo fora do padrão") String localTrabPossRemoto,
        @NotBlank(message = "Campo obrigatório") @Size(min = 2, max = 50, message = "Campo fora do padrão") LocalDate infoAdDataPublVaga,
        @NotBlank(message = "Campo obrigatório") @Size(min = 2, max = 50, message = "Campo fora do padrão") LocalDate infoAdDataLimite,
        @NotBlank(message = "Campo obrigatório") @Size(min = 2, max = 50, message = "Campo fora do padrão") String infoAdProcessoSeletivo,
        @NotBlank(message = "Campo obrigatório") @Size(min = 2, max = 50, message = "Campo fora do padrão") String dadosContatoEmailCurriculo,
        @NotBlank(message = "Campo obrigatório") @Size(min = 2, max = 50, message = "Campo fora do padrão") String dadosContatoTelefone,
        @NotBlank(message = "Campo obrigatório") @Size(min = 2, max = 50, message = "Campo fora do padrão") String observacoesVaga,
        @NotBlank(message = "Campo obrigatório") @Size(min = 2, max = 50, message = "Campo fora do padrão") String codFichaEmp

) {

    public CadastrovagaEmp mapearParaCadastrovagaEmp() {
        CadastrovagaEmp cadastroVaga = new CadastrovagaEmp();
        cadastroVaga.setDadosTituloVaga(this.dadosTituloVaga);
        cadastroVaga.setDadosAreaAtuacao(this.dadosAreaAtuacao);
        cadastroVaga.setDadosTipoContrato(this.dadosTipoContrato);
        cadastroVaga.setDadosNivelExperiencia(this.dadosNivelExperiencia);
        cadastroVaga.setDadosQuantVagas(this.dadosQuantVagas);
        cadastroVaga.setDadosSalario(this.dadosSalario);
        cadastroVaga.setDadosDescricaoVaga(this.dadosDescricaoVaga);
        cadastroVaga.setRequisitosEscolariadade(this.requisitosEscolariadade);
        cadastroVaga.setRequisitosExperiencia(this.requisitosExperiencia);
        cadastroVaga.setRequisitosHabilidades(this.requisitosHabilidades);
        cadastroVaga.setBeneficiosVale_Refeicao(this.beneficiosVale_Refeicao);
        cadastroVaga.setBeneficiosAssistencia_Medica(this.beneficiosAssistencia_Medica);
        cadastroVaga.setBeneficiosSeguro_Vida(this.beneficiosSeguro_Vida);
        cadastroVaga.setBeneficiosBonificacao(this.beneficiosBonificacao);
        cadastroVaga.setBeneficiosVale_Transporte(this.beneficiosVale_Transporte);
        cadastroVaga.setLocalTrabPossRemoto(this.localTrabPossRemoto);
        cadastroVaga.setInfoAdDataPublVaga(this.infoAdDataPublVaga);
        cadastroVaga.setInfoAdDataLimite(this.infoAdDataLimite);
        cadastroVaga.setInfoAdProcessoSeletivo(this.infoAdProcessoSeletivo);
        cadastroVaga.setDadosContatoEmailCurriculo(this.dadosContatoEmailCurriculo);
        cadastroVaga.setDadosContatoTelefone(this.dadosContatoTelefone);
        cadastroVaga.setObservacoesVaga(this.observacoesVaga);
        cadastroVaga.setCodFichaEmp(this.codFichaEmp);

        return cadastroVaga;
    }

}
