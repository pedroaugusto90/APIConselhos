package com.example.webapi;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class Todos extends AppCompatActivity {

    public ListView listView;
    ArrayAdapter<String> adpater;
    ArrayList<String> arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todos);

        Button btnVoltar = (Button) findViewById(R.id.btnVoltar);
        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it1 = new Intent(Todos.this, MainActivity.class);
                startActivity(it1);
            }
        });



        listView = (ListView) findViewById(R.id.listConselhos);

        arrayList = new ArrayList<String>();
        adpater = new ArrayAdapter<String>(Todos.this, android.R.layout.simple_list_item_1, arrayList);
        listView = (ListView) findViewById(R.id.listConselhos);
        listView.setAdapter(adpater);
        ListaTodosConselhos();



    }

    public void ListaTodosConselhos() {
        BancoDeDados db = new BancoDeDados(this);
        List<Conselho> TodosConselhos = db.ListaTodosConselhos();

        //loop para mostrar tudo
        for (Conselho c : TodosConselhos) {
            //corpo do item list
            arrayList.add(c.getConselho());

            adpater.notifyDataSetChanged();
        }
    }
}