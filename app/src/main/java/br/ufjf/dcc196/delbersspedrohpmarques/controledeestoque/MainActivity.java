package br.ufjf.dcc196.delbersspedrohpmarques.controledeestoque;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerProdutos;
    private ActivityResultLauncher<Intent> launcher;
    private ProdutoAdapter produtoAdapter;
    private ProdutoRepository repo;
    private TextView textViewTotalPrecoProdutos;
    private TextView textViewEstoqueItens;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        repo = new ProdutoRepository(getApplicationContext());

        recyclerProdutos = findViewById(R.id.recyclerProdutos);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerProdutos.setLayoutManager(layoutManager);

        textViewTotalPrecoProdutos = findViewById(R.id.textViewTotalPrecoProdutos);
        textViewEstoqueItens = findViewById(R.id.textViewEstoqueItens);

        textViewTotalPrecoProdutos.setText("R$ " + repo.getValorTotalEstoque().toString());
        textViewEstoqueItens.setText(repo.getItensEstoque().toString());


        ProdutoAdapter.OnProdutoClickListener listener = new ProdutoAdapter.OnProdutoClickListener() {
            @Override
            public void onProdutoClick(View view, int position) {
                Produto produto = repo.getProduto(position);
                produto.setQuantidade(produto.getQuantidade()+1);
                textViewTotalPrecoProdutos.setText("R$ " + repo.getValorTotalEstoque().toString());
                textViewEstoqueItens.setText(repo.getItensEstoque().toString());
                produto.setPreco(produto.getPreco());
                produtoAdapter.notifyItemChanged(position);
            }
        };
        produtoAdapter = new ProdutoAdapter(repo.getProdutos(),listener);
        recyclerProdutos.setAdapter(produtoAdapter);


        launcher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        Bundle extras;
                        extras = result.getData().getExtras();
                        String nome = extras.getString("nome");
                        Float preco = extras.getFloat("preco");
                        Integer quantidade = extras.getInt("quantidade");
                        textViewTotalPrecoProdutos.setText("R$ " + repo.getValorTotalEstoque().toString());
                        textViewEstoqueItens.setText(repo.getItensEstoque().toString());

                        Produto novoProduto = new Produto(nome,preco,quantidade);
                        repo.addProduto(novoProduto);

                        produtoAdapter = new ProdutoAdapter(repo.getProdutos(),listener);
                        recyclerProdutos.setAdapter(produtoAdapter);

                        textViewTotalPrecoProdutos.setText("R$ " + repo.getValorTotalEstoque().toString());
                        textViewEstoqueItens.setText(repo.getItensEstoque().toString());
                    }
                });
    }

    public void adicionarProduto(View view){
        Intent intent = new Intent(MainActivity.this, AdicionaProduto.class);
        launcher.launch(intent);
    }

}