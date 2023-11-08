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
    final String TITULO_APP_BAR = "Novo Aluno";
    Boolean create;

    private EditText campoNome;
    private EditText campoTelefone;
    private EditText campoEmail;
    private Button botaoSalvar;

    private final Intent dados = getIntent();

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
            if(create)criarAluno();
            else modificarAluno();
            finish();
        });
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_aluno);
        setTitle(TITULO_APP_BAR);

        definirCampos();

        create = true;
        if (dados.hasExtra("aluno")) {
            Aluno aluno = dados.getParcelableExtra("aluno");
            if (aluno != null) {
                create = false;

                campoNome.setText(aluno.getNome());
                campoTelefone.setText(aluno.getTelefone());
                campoEmail.setText(aluno.getEmail());
            }
        }
    }

    private void modificarAluno()
    {
        
    }

    private void criarAluno()
    {
        String nome = campoNome.getText().toString();
        String telefone = campoTelefone.getText().toString();
        String email = campoEmail.getText().toString();

        alunoDAO.salva(new Aluno(nome, telefone, email));

        Toast.makeText(FormularioAlunoActivity.this, "Aluno Salvo com sucesso", Toast.LENGTH_SHORT).show();
    }
}