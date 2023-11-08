package com.example.agenda.ui;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.agenda.DAO.AlunoDAO;
import com.example.agenda.R;
import com.example.agenda.model.Aluno;
import com.example.agenda.ui.adapter.AlunoAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
public class ListaAlunosActivity extends AppCompatActivity {

    final String TITULO_APP_BAR = "Lista de Alunos";
    private final AlunoDAO dao = new AlunoDAO();
    private AlunoAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_alunos);

        setTitle(TITULO_APP_BAR);

        configuraBotao();
        configuraLista();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo)
    {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater()
                .inflate(R.menu.activity_lista_alunos_context_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull final MenuItem item)
    {
        int itemId = item.getItemId();
        if(itemId == R.id.activity_lista_alunos_context_menu_remover){
            AdapterView.AdapterContextMenuInfo menuInfo =
                    (AdapterView.AdapterContextMenuInfo)item.getMenuInfo();

            Aluno alunoEscolhido = null;
            if (menuInfo != null)
                alunoEscolhido = adapter.getItem(menuInfo.position);

            createDialog(alunoEscolhido);
        }

        return super.onContextItemSelected(item);
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        adapter.atualiza(dao.todos());
    }

    private void configuraBotao()
    {
        FloatingActionButton botaoNovoAluno = findViewById(R.id.activity_lista_alunos_fab_novo_aluno);
        botaoNovoAluno.setOnClickListener(view -> startActivity(new Intent(ListaAlunosActivity.this,
                FormularioAlunoActivity.class)));
    }

    private void configuraLista() {
        ListView listaDeAlunos = findViewById(R.id.activity_lista_alunos_alunos);
        configuraAdapter(listaDeAlunos);
        registerForContextMenu(listaDeAlunos);
        clickNaLista(listaDeAlunos);
    }

    private void configuraAdapter(ListView listaDeAlunos) {
        adapter = new AlunoAdapter(this);
        listaDeAlunos.setAdapter(adapter);
    }

    private void clickNaLista(ListView listaDeAlunos)
    {
        listaDeAlunos.setOnItemClickListener((adapterView, view, pos, id) -> {
            Intent toFormularioActivity = new Intent(ListaAlunosActivity.this, FormularioAlunoActivity.class);
            toFormularioActivity.putExtra("posAluno", pos);

            startActivity(toFormularioActivity);
        });
    }

    private void createDialog(Aluno aluno)
    {
        new AlertDialog.Builder(this)
                .setTitle("Removendo Aluno")
                .setMessage("Tem certeza que deseja remover esse aluno?")
                .setPositiveButton("Sim", (dialog, which) -> removeAluno(aluno))
                .setNegativeButton("Não", null)
                .show();
    }

    private void removeAluno(Aluno aluno)
    {
        dao.remove(aluno);
        adapter.remove(aluno);
        Toast.makeText(this, "Aluno Removido com Sucesso!", Toast.LENGTH_SHORT).show();
    }
}