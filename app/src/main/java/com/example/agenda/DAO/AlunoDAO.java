package com.example.agenda.DAO;

import com.example.agenda.model.Aluno;

import java.util.List;
import java.util.ArrayList;


public class AlunoDAO {

    private final static List<Aluno> alunos = new ArrayList<>();

    public void salva(Aluno aluno)
    { if(aluno != null)alunos.add(aluno); }

    public List<Aluno> todos()
    { return new ArrayList<>(alunos); }

    public void editAluno(int pos, Aluno aluno)
    {
        if(pos >= 0 && pos < alunos.size() && aluno != null)
            alunos.set(pos, aluno);
    }

    public void remove(int pos)
    {
        if(pos >= 0 && pos <= alunos.size())
            alunos.remove(pos);
    }
}
