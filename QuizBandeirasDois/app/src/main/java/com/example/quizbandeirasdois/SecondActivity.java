package com.example.quizbandeirasdois;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_second);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        int resultado = TabelaDeAcertos.acertos - TabelaDeAcertos.erros;

        if(resultado > 0){
            TextView txt1 = findViewById(R.id.txt1);
            TextView txt2 = findViewById(R.id.txt2);
            TextView txt3 = findViewById(R.id.txt3);
            TextView txt4 = findViewById(R.id.txt4);
            txt3.setText("Parabéns, " + TabelaDeAcertos.nome + ".");
            txt4.setText( "Seu saldo foi positivo");
            txt1.setText("Acertos: " + TabelaDeAcertos.acertos);
            txt2.setText("Erros: " + TabelaDeAcertos.erros);
        }else {
            TextView txt1 = findViewById(R.id.txt1);
            TextView txt2 = findViewById(R.id.txt2);
            TextView txt3 = findViewById(R.id.txt3);
            TextView txt4 = findViewById(R.id.txt4);
            txt3.setText("Que pena, " + TabelaDeAcertos.nome + ".");
            txt4.setText( "Seu saldo foi negativo");
            txt1.setText("Acertos: " + TabelaDeAcertos.acertos);
            txt2.setText("Erros: " + TabelaDeAcertos.erros);
        }
    }

    public void mainScreen(View v) {
        TabelaDeAcertos.acertos = 0;
        TabelaDeAcertos.erros = 0;
        TabelaDeAcertos.nome = "";
        Intent i = new Intent(this,FirstActivity.class);
        startActivity(i);
    }


    public void quizScreen(View v) {
        TabelaDeAcertos.acertos = 0;
        TabelaDeAcertos.erros = 0;
        Intent i = new Intent(this,MainActivity.class);
        startActivity(i);
    }

}
