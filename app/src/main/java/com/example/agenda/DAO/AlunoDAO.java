package com.example.agenda.DAO;

import com.example.agenda.model.Aluno;

import java.util.List;
import java.util.ArrayList;


public class AlunoDAO {

    private final static List<Aluno> alunos = new ArrayList<>();

    public void salva(Aluno aluno)
    { alunos.add(aluno); }

    public List<Aluno> todos()
    { return new ArrayList<>(alunos); }
}
