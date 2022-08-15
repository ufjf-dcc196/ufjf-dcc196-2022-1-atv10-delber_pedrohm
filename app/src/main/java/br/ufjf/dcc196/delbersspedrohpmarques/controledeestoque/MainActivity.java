package br.ufjf.dcc196.delbersspedrohpmarques.controledeestoque;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerProdutos;
    List<Produto> produtos;
    private ProdutoAdapter produtoAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        recyclerProdutos = findViewById(R.id.recyclerProdutos);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerProdutos.setLayoutManager(layoutManager);

        ProdutoAdapter.OnProdutoClickListener listener = new ProdutoAdapter.OnProdutoClickListener() {
            @Override
            public void onProdutoClick(View view, int position) {
                Produto avistamento = produtos.get(position);
                avistamento.setQuantidade(avistamento.getQuantidade()+1);
                produtoAdapter.notifyItemChanged(position);
            }
        };
        produtoAdapter = new ProdutoAdapter(produtos, listener);
        recyclerProdutos.setAdapter(produtoAdapter);
    }
}
