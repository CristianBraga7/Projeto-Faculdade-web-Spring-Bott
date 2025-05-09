package com.example.libraryapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

import com.example.libraryapi.model.CadastroVagaEmp;

public interface CadastroVagaEmpRepository extends JpaRepository<CadastroVagaEmp, UUID> {

    
}
