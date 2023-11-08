package com.example.agenda.ui;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.agenda.DAO.AlunoDAO;
import com.example.agenda.R;
import com.example.agenda.model.Aluno;

public class FormularioAlunoActivity extends AppCompatActivity {
    final AlunoDAO alunoDAO = new AlunoDAO();
    final String TITULO_APP_BAR = "Novo Aluno";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_aluno);
        setTitle(TITULO_APP_BAR);

        Button botaoSalvar = findViewById(R.id.activity_formulario_aluno_botao_salvar);
        botaoSalvar.setOnClickListener(view -> {
            this.criarAluno(alunoDAO);
            finish();
        });
    }

    private void criarAluno(AlunoDAO alunoDAO) {
        String nome = ((EditText) findViewById(R.id.activity_formulario_aluno_nome)).getText().toString();
        String telefone = ((EditText) findViewById(R.id.activity_formulario_aluno_telefone)).getText().toString();
        String email = ((EditText) findViewById(R.id.activity_formulario_aluno_email)).getText().toString();

        alunoDAO.salva(new Aluno(nome, telefone, email));

        Toast.makeText(FormularioAlunoActivity.this, "Aluno Salvo com sucesso", Toast.LENGTH_SHORT).show();
    }
}
