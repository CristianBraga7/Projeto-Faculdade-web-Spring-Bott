package com.example.libraryapi.execeptions;

public class RegistrosDuplicadosExcepion extends RuntimeException {
    public RegistrosDuplicadosExcepion(String message) {
        super(message);
    }
}
