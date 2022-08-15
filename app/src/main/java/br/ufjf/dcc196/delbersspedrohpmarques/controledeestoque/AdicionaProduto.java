package br.ufjf.dcc196.delbersspedrohpmarques.controledeestoque;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AdicionaProduto extends AppCompatActivity {

    private String nome;
    private Float preco;
    private Integer quantidade;

    private EditText nomeAvistamento;
    private EditText precoProduto;

    private Button adicionarRegistro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adiciona_produto);
        adicionarRegistro = findViewById(R.id.botaoAdicionarProduto);

        nomeAvistamento = findViewById(R.id.nomeProduto);
        precoProduto = findViewById(R.id.editPreco);

    }
    public void botaoCriarProduto(View view){
        nome = nomeAvistamento.getText().toString();
        preco = Float.parseFloat(precoProduto.getText().toString());
        quantidade = 1;

        Intent novoProduto = new Intent();
        novoProduto.putExtra("nome", nome);
        novoProduto.putExtra("preco", preco);
        novoProduto.putExtra("quantidade", quantidade);

        setResult(1, novoProduto);
        finish();
    }
}