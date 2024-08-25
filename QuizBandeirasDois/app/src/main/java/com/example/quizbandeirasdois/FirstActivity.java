package com.example.quizbandeirasdois;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Random;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FirstActivity extends AppCompatActivity {
    Button btn1;
    Button btn2;
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_first);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        btn1 = findViewById(R.id.enterBtn);
        btn2 = findViewById(R.id.exitBtn);
        editText = findViewById(R.id.editText);
    }

    public void entrar(View v){
        String nome = editText.getText().toString().trim();
        if(nome.isEmpty()){
            Toast.makeText(this,"Digite seu nome",Toast.LENGTH_SHORT).show();
        }else{
            TabelaDeAcertos.nome = nome;
            Intent novaAct = new Intent(this,MainActivity.class);
            startActivity(novaAct);
        }
    }

    public void exit(View v){
        finish();
    }

}