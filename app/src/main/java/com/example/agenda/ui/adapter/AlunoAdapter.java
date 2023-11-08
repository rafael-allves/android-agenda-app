package com.example.agenda.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.agenda.R;
import com.example.agenda.model.Aluno;

import java.util.ArrayList;
import java.util.List;

public class AlunoAdapter extends BaseAdapter {
    private final List<Aluno> alunos = new ArrayList<>();
    private final Context context;

    public AlunoAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return alunos.size();
    }

    @Override
    public Aluno getItem(int position) {
        return alunos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = criaView(parent);
        Aluno aluno = alunos.get(position);

        vinculaCampos(view, aluno);

        return view;
    }

    private static void vinculaCampos(View view, Aluno aluno) {
        TextView nome = view.findViewById(R.id.item_aluno_nome);
        nome.setText(aluno.getNome());

        TextView telefone = view.findViewById(R.id.item_aluno_telefone);
        telefone.setText(aluno.getTelefone());
    }

    private View criaView(ViewGroup parent) {
        return LayoutInflater.from(context)
                .inflate(
                        R.layout.activity_lista_de_alunos_item_aluno,
                        parent,
                        false
                );
    }

    public void atualiza(List<Aluno> alunos)
    {
        this.alunos.clear();
        this.alunos.addAll(alunos);
        notifyDataSetChanged();
    }
    public void remove(Aluno aluno)
    {
        alunos.remove(aluno);
        notifyDataSetChanged();
    }
}