package com.example.agenda.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.agenda.DAO.AlunoDAO;
import com.example.agenda.R;
import com.example.agenda.model.Aluno;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ListaAlunosActivity extends AppCompatActivity {

    final String TITULO_APP_BAR = "Lista de Alunos";
    private final AlunoDAO dao = new AlunoDAO();
    private ArrayAdapter<Aluno> adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_alunos);

        setTitle(TITULO_APP_BAR);

        configuraBotao();
        configuraLista();

        for (int i = 0; i < 20; i++) {
            dao.salva(new Aluno("Rafa", "", ""));
        }
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo)
    {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater()
                .inflate(R.menu.activity_lista_alunos_context_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item)
    {
        int itemId = item.getItemId();
        if(itemId == R.id.activity_lista_alunos_context_menu_remover){
            AdapterView.AdapterContextMenuInfo menuInfo =
                    (AdapterView.AdapterContextMenuInfo)item.getMenuInfo();

            Aluno alunoEscolhido = null;
            if (menuInfo != null)
                alunoEscolhido = adapter.getItem(menuInfo.position);

            removeAluno(alunoEscolhido);
        }

        return super.onContextItemSelected(item);
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        atualizaLista();
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
        adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1
        );
        listaDeAlunos.setAdapter(adapter);
    }

    private void atualizaLista()
    {
        adapter.clear();
        adapter.addAll(dao.todos());
    }

    private void clickNaLista(ListView listaDeAlunos)
    {
        listaDeAlunos.setOnItemClickListener((adapterView, view, pos, id) -> {
            Intent toFormularioActivity = new Intent(ListaAlunosActivity.this, FormularioAlunoActivity.class);
            toFormularioActivity.putExtra("posAluno", pos);

            startActivity(toFormularioActivity);
        });
    }

    private void removeAluno(Aluno aluno)
    {
        dao.remove(aluno);
        adapter.remove(aluno);
        Toast.makeText(this, "Aluno Removido com Sucesso!", Toast.LENGTH_SHORT).show();
    }
}