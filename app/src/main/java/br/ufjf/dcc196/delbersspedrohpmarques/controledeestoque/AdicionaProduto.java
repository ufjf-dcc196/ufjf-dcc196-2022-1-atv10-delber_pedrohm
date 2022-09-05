package br.ufjf.dcc196.delbersspedrohpmarques.controledeestoque;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AdicionaProduto extends AppCompatActivity {

    private String nome;
    private Float preco;
    private Integer quantidade;

    private EditText nomeAvistamento;
    private EditText precoProduto;
    private EditText qtsProduto;

    private Button adicionarRegistro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adiciona_produto);

        adicionarRegistro = findViewById(R.id.botaoAdicionarProduto);

        nomeAvistamento = findViewById(R.id.nomeProduto);

        precoProduto = findViewById(R.id.editPreco);

        qtsProduto = findViewById(R.id.editQts);

        adicionarRegistro.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                nome = nomeAvistamento.getText().toString();

                if(nome.isEmpty() || precoProduto.getText().toString().isEmpty() || qtsProduto.getText().toString().isEmpty()){
                    Toast.makeText(view.getContext(), "Preencha todos os campos com valores validos !", Toast.LENGTH_SHORT).show();
                }else{
                    preco = Float.parseFloat(precoProduto.getText().toString());
                    quantidade = Integer.parseInt(qtsProduto.getText().toString());

                    Intent novoProduto = new Intent();
                    novoProduto.putExtra("nome", nome);
                    novoProduto.putExtra("preco", preco);
                    novoProduto.putExtra("quantidade", quantidade);

                    setResult(-1, novoProduto);
                    finish();
                }
            }
        });
    }
}

