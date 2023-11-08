package com.example.agenda.ui;

import android.content.Intent;
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
    private Aluno aluno;
    final String TITULO_APP_BAR = "Novo Aluno";
    Boolean create;
    private Intent dados;
    private EditText campoNome;
    private EditText campoTelefone;
    private EditText campoEmail;
    private Button botaoSalvar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_aluno);
        setTitle(TITULO_APP_BAR);

        definirCampos();

        dados = getIntent();

        create = true;
        if (dados.hasExtra("posAluno")) {
            aluno = alunoDAO.todos().get(dados.getIntExtra("posAluno", -1));
            if (aluno != null) {
                create = false;

                campoNome.setText(aluno.getNome());
                campoTelefone.setText(aluno.getTelefone());
                campoEmail.setText(aluno.getEmail());
            }
        }
    }

    /**
     * @brief define os elementos interativos dessa activity
     * @return void
     */
    private void definirCampos() {
        campoNome = findViewById(R.id.activity_formulario_aluno_nome);
        campoTelefone = findViewById(R.id.activity_formulario_aluno_telefone);
        campoEmail = findViewById(R.id.activity_formulario_aluno_email);
        botaoSalvar = findViewById(R.id.activity_formulario_aluno_botao_salvar);

        botaoSalvar.setOnClickListener(view -> {
            if(create)
                criarAluno();
            else
                modificarAluno();
            finish();
        });
    }

    private void defineAluno()
    {
        String nome = campoNome.getText().toString();
        String telefone = campoTelefone.getText().toString();
        String email = campoEmail.getText().toString();

        aluno = new Aluno(nome, telefone, email);
    }

    private void modificarAluno()
    {
        defineAluno();
        alunoDAO.editAluno(dados.getIntExtra("posAluno", -1), aluno);
        Toast.makeText(FormularioAlunoActivity.this, "Aluno Editado com sucesso", Toast.LENGTH_SHORT).show();
    }

    private void criarAluno()
    {
        defineAluno();
        alunoDAO.salva(aluno);

        Toast.makeText(FormularioAlunoActivity.this, "Aluno Criado com sucesso", Toast.LENGTH_SHORT).show();
    }
}