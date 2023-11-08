package com.example.agenda.model;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class Aluno implements Serializable {
    private final String nome;
    private final String telefone;
    private final String email;

    public Aluno(String nome, String telefone, String email)
    {
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
    }

    @NonNull
    @Override
    public String toString()
    { return nome; }
}