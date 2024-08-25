package com.example.quizbandeirasdois;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    ImageView imgView;
    Button btn;
    RadioButton radBtn1,radBtn2,radBtn3,radBtn4;
    RadioGroup radGroup;


    int[] bandeiras = {R.drawable.alemanha, R.drawable.argentina, R.drawable.austria, R.drawable.brasil,
            R.drawable.china,R.drawable.letonia,R.drawable.russia,R.drawable.usa};
    String[] opcoes = {"Alemanha","Argentina","Austria","Brasil","China","Letonia","Russia","Estados Unidos"};
    List<Integer> usados = new ArrayList<>();

    //Criando número aleatório
    Random random = new Random();

    //rodada e indice
    int indice = random.nextInt(8);
    int rodada = 0;

    //respostas corretas e erradas
    int corretas = 0;
    int erradas = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        imgView = findViewById(R.id.imageView);
        btn = findViewById(R.id.btn);
        radGroup = findViewById(R.id.radioGroup);

        radBtn1 = findViewById(R.id.radioButton);
        radBtn2 = findViewById(R.id.radioButton2);
        radBtn3 = findViewById(R.id.radioButton3);
        radBtn4 = findViewById(R.id.radioButton4);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Obtém o ID do RadioButton selecionado
                int selectedId = radGroup.getCheckedRadioButtonId();
                //Pegando o id do RadioButton e passando pra variável selectedRadioButton
                RadioButton selectedRadioButton = findViewById(selectedId);

                //Se o botão tem o mesmo texto que a resposta correta
                //RADIO BOTÃO 1
                if(selectedRadioButton != null) {
                    if (selectedRadioButton.getText().toString().equals( opcoes[indice])) {
                        Toast.makeText(MainActivity.this, "Resposta correta", Toast.LENGTH_LONG).show();
                        rodada++;
                        corretas++;
                        carregarQuestao(rodada);
                    } else {
                        Toast.makeText(MainActivity.this, "Resposta incorreta", Toast.LENGTH_LONG).show();
                        rodada++;
                        erradas++;
                        carregarQuestao(rodada);
                    }
                }
            }
        });

        carregarQuestao(rodada);
    }

    public void carregarQuestao(int rodadas) {
        int indice;
        do {
            indice = random.nextInt(bandeiras.length);
        } while (usados.contains(indice));
        //adiciona o indice da bandeira usada na lista usados
        usados.add(indice);

        if (usados.size() >= bandeiras.length) {
            usados.clear(); //limpa a lista quando todas as bandeiras foram usadas
        }

        imgView.setImageResource(bandeiras[indice]);
        // Configura os botões com as opções, etc.
        //Setando a imagem da bandeira
        if(rodadas < opcoes.length) {
            imgView.setImageResource(bandeiras[indice]);

            // definindo qual botão irá exibir a resposta correta
            int respostaCorretaIndice = random.nextInt(4); //Índice do botão com a resposta correta

            //Lista temporária de opções (evita repetir a resposta correta)
            List<String> opcoesTemp = new ArrayList<>(Arrays.asList(opcoes));
            String respostaCorreta = opcoes[indice]; // A resposta correta
            opcoesTemp.remove(respostaCorreta);// Remove a resposta correta da lista temporária

            // Preenche os botões
            RadioButton[] radioButtons = {radBtn1,radBtn2,radBtn3,radBtn4};

            for (int i = 0; i < radioButtons.length; i++) {
                if (i == respostaCorretaIndice) {
                    // Coloca a resposta correta no botão escolhido
                    radioButtons[i].setText(respostaCorreta);
                } else {
                    // Preenche os outros botões com respostas incorretas
                    //Tamanho do lista (no caso é 7)
                    int opcaoErradaIndice = random.nextInt(opcoesTemp.size());
                    //pegando o elemento com o ìndice diferente do botão correto
                    radioButtons[i].setText(opcoesTemp.get(opcaoErradaIndice));
                    opcoesTemp.remove(opcaoErradaIndice); // Remove a opção usada
                }
            }
            radGroup.clearCheck(); //Limpar a seleção atual
        }else {
            TabelaDeAcertos.acertos = corretas;
            TabelaDeAcertos.erros = erradas;
            Intent tela = new Intent(this, SecondActivity.class);
            startActivity(tela);
        }
    }
}