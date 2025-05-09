package com.example.libraryapi.validador;

import com.example.libraryapi.repository.CadastroVagaEmpRepository;
import com.example.libraryapi.execeptions.RegistrosDuplicadosExcepion;
import org.springframework.stereotype.Component;
import java.util.Optional;
import com.example.libraryapi.model.CadastroVagaEmp;
import java.util.UUID;

@Component
public class CadastroVagaEmpValidador {

    private CadastroVagaEmpRepository repository;

    public CadastroVagaEmpValidador(CadastroVagaEmpRepository repository) {
        this.repository = repository;
    }

    public void validar(CadastroVagaEmp cadastroVagaEmp) {
        if (existeCadastroVagaEmpComMesmoCodVaga(cadastroVagaEmp)) {
            throw new RegistrosDuplicadosExcepion("Empresa já cadastrada");
        }
    }

    private boolean existeCadastroVagaEmpComMesmoCodVaga(CadastroVagaEmp cadastroVagaEmp) {
        Optional<CadastroVagaEmp> cadastroVagaEmpEncontrado = repository.findByCodVaga(
                cadastroVagaEmp.getCodVaga());

        if (cadastroVagaEmp.getCodVaga() == null) {
            return cadastroVagaEmpEncontrado.isPresent();
        }
        return cadastroVagaEmp.getCodVaga().equals(cadastroVagaEmpEncontrado.get().getCodVaga())
                && cadastroVagaEmpEncontrado.isPresent();

    }

}
