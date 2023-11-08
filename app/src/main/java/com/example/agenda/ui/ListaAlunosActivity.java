package com.example.agenda.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.agenda.DAO.AlunoDAO;
import com.example.agenda.R;
import com.example.agenda.model.Aluno;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class ListaAlunosActivity extends AppCompatActivity {

    final String TITULO_APP_BAR = "Lista de Alunos";
    private final AlunoDAO dao = new AlunoDAO();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_alunos);

        setTitle(TITULO_APP_BAR);

        FloatingActionButton botaoNovoAluno = findViewById(R.id.activity_lista_alunos_fab_novo_aluno);
        botaoNovoAluno.setOnClickListener(view -> startActivity(new Intent(ListaAlunosActivity.this,
                FormularioAlunoActivity.class)));
    }

    @Override
    protected void onResume()
    {
        super.onResume();

        ListView listaDeAlunos = findViewById(R.id.activity_lista_alunos_alunos);
        final List<Aluno> alunos = dao.todos();

        listaDeAlunos.setAdapter(new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                alunos));

        clickNaLista(listaDeAlunos);
        clickLongNaLista(listaDeAlunos);
    }

    private void clickNaLista(ListView listaDeAlunos)
    {
        listaDeAlunos.setOnItemClickListener((adapterView, view, pos, id) -> {
            Intent toFormularioActivity = new Intent(ListaAlunosActivity.this, FormularioAlunoActivity.class);
            toFormularioActivity.putExtra("posAluno", pos);

            startActivity(toFormularioActivity);
        });
    }

    private void clickLongNaLista(ListView listaDeAlunos)
    {
        listaDeAlunos.setOnItemLongClickListener((adapterView, view, pos, id) -> {
            dao.remove(pos);
            return true;
        });
    }
}