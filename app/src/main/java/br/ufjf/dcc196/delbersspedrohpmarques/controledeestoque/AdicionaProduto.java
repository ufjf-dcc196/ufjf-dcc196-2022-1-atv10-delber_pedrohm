package br.ufjf.dcc196.delbersspedrohpmarques.controledeestoque;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AdicionaProduto extends AppCompatActivity {

    private String nome;
    private String preco;

    private EditText nomeAvistamento;
    private EditText precoProduto;

    private Button adicionarRegistro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adiciona_produto);
        adicionarRegistro = findViewById(R.id.botaoAdicionarProduto);
        nomeAvistamento = findViewById(R.id.nomeProduto);
        precoProduto = findViewById(R.id.preco);

    }
    public void botaoCriarProduto(View view){
        nome = nomeAvistamento.getText().toString();
        preco = precoProduto.getText().toString();

        Intent novoProduto = new Intent();
        novoProduto.putExtra("nome", nome);
        novoProduto.putExtra("preco", preco);

        setResult(1, novoProduto);
        finish();
    }
}