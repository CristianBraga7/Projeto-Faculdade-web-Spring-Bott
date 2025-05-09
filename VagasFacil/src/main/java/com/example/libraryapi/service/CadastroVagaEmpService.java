package com.example.libraryapi.service;

import com.example.libraryapi.model.CadastroVagaEmp;
import com.example.libraryapi.repository.CadastroVagaEmpRepository;
import com.example.libraryapi.execeptions.RegistrosDuplicadosExcepion;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import com.example.libraryapi.validador.CadastroVagaEmpValidador;
import com.example.libraryapi.execeptions.OperacoesNaoPermitidasExceptions;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CadastroVagaEmpService {

    private final CadastroVagaEmpRepository repository;
    private final CadastroVagaEmpValidador validador;
    private final CadastroVagaEmpRepository cadastroVagaEmpRepository;

    public CadastroVagaEmp salvar(CadastroVagaEmp cadastroVagaEmp) {
        validador.validar(cadastroVagaEmp);
        return repository.save(cadastroVagaEmp);
    }

    public CadastroVagaEmp atualizar(CadastroVagaEmp cadastroVagaEmp) {
        if (cadastroVagaEmp.getCodVaga() == null) {
            throw new RuntimeException("Para atulizar é necessário que o cadastroVagaEmp esteja salvo");
        }
        validador.validar(cadastroVagaEmp);
        return repository.save(cadastroVagaEmp);
    }

    public Optional<CadastroVagaEmp> obterPorId(UUID id) {
        return repository.findById(id);
    }

    public void deletar(CadastroVagaEmp cadastroVagaEmp) {
        if (possuiVaga(cadastroVagaEmp)) {
            throw new OperacoesNaoPermitidasExceptions("Empresa possui vagas cadastradas");
        }
        repository.delete(cadastroVagaEmp);
    }

    public List<CadastroVagaEmp> pesquisar(String codVaga, String dadosTituloVaga) {
        if (codVaga != null && dadosTituloVaga != null) {
            return repository.findByCodVagaAndDadosTituloVaga(UUID.fromString(codVaga), dadosTituloVaga);
        }
        if (codVaga != null) {
            return repository.findByCodVaga(UUID.fromString(codVaga));
        }
        if (dadosTituloVaga != null) {
            return repository.findByDadosTituloVaga(dadosTituloVaga);
        }
        return repository.findAll();
    }

    public List<CadastroVagaEmp> pesquisarByExample(String codVaga, String dadosTituloVaga) {
        var cadastroVagaEmp = new CadastroVagaEmp();
        cadastroVagaEmp.setCodVaga(UUID.fromString(codVaga));
        cadastroVagaEmp.setDadosTituloVaga(dadosTituloVaga);

        ExampleMatcher matcher = ExampleMatcher
                .matching()
                .withIgnorePaths("codVaga", "dadosTituloVaga")
                .withIgnoreNullValues()
                .withIgnoreCase()
                .withStringMatcher(StringMatcher.CONTAINING);

        Example<CadastroVagaEmp> cadastroVagaEmpExample = Example.of(cadastroVagaEmp, matcher);
            return repository.findAll(cadastroVagaEmpExample);
    }

    public boolean possuiVaga(CadastroVagaEmp cadastroVagaEmp) {
        return cadastroVagaEmpRepository.existsByCodVaga(cadastroVagaEmp.getCodVaga());
    }
}
    
}
