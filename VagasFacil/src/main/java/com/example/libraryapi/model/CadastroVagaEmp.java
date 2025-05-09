package com.example.libraryapi.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.UUID;
import java.time.LocalDate;

import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;

import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;

@Entity
@Table(name = "CadastroVaga", schema = "public")
@Data
@Getter
@Setter
@ToString(exclude = { "fichaEmpresa" })
@EntityListeners(AuditingEntityListener.class)
public class CadastroVagaEmp {

    @Id
    @Column(name = "codVaga")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID codVaga;

    @Column(name = "dadosTituloVaga", length = 60, nullable = false)
    private String dadosTituloVaga;

    @Column(name = "dadosAreaAtuacao", length = 60, nullable = false)
    private String dadosAreaAtuacao;

    @Column(name = "dadosTipoContrato", length = 60, nullable = false)
    private String dadosTipoContrato;

    @Column(name = "dadosNivelExperiencia", length = 50, nullable = false)
    private String dadosNivelExperiencia;

    @Column(name = "dadosQuantVagas", length = 45, nullable = false)
    private String dadosQuantVagas;

    @Column(name = "dadosSalario", precision = 18, scale = 2, nullable = false)
    private BigDecimal dadosSalario;

    @Column(name = "dadosDescricaoVaga", length = 500, nullable = false)
    private String dadosDescricaoVaga;

    @Column(name = "requisitosEscolariadade", length = 100, nullable = false)
    private String requisitosEscolariadade;

    @Column(name = "requisitosExperiencia", length = 500, nullable = false)
    private String requisitosExperiencia;

    @Column(name = "requisitosHabilidades", length = 500, nullable = false)
    private String requisitosHabilidades;

    @Column(name = "beneficiosVale_Refeicao", length = 45, nullable = false)
    private String beneficiosVale_Refeicao;

    @Column(name = "beneficiosAssistencia_Medica", length = 45, nullable = false)
    private String beneficiosAssistencia_Medica;

    @Column(name = "beneficiosSeguro_Vida", length = 45, nullable = false)
    private String beneficiosSeguro_Vida;

    @Column(name = "beneficiosBonificacao", length = 45, nullable = false)
    private String beneficiosBonificacao;

    @Column(name = "beneficiosVale_Transporte", length = 45, nullable = false)
    private String beneficiosVale_Transporte;

    @Column(name = "localTrabPossRemoto", length = 45, nullable = false)
    private String localTrabPossRemoto;

    @Column(name = "infoAdDataPublVaga", nullable = false)
    private LocalDate infoAdDataPublVaga;

    @Column(name = "infoAdDataLimite", nullable = false)
    private LocalDate infoAdDataLimite;

    @Column(name = "infoAdProcessoSeletivo", length = 45, nullable = false)
    private String infoAdProcessoSeletivo;

    @Column(name = "dadosContatoEmailCurriculo", length = 50, nullable = false)
    private String dadosContatoEmailCurriculo;

    @Column(name = "dadosContatoTelefone", length = 11, nullable = false)
    private String dadosContatoTelefone;

    @Column(name = "observacoesVaga", length = 500, nullable = false)
    private String observacoesVaga;

    @OneToMany
    @JoinColumn(name = "codFichaEmp")
    private FichaEmpresa fichaEmpresa;

}
