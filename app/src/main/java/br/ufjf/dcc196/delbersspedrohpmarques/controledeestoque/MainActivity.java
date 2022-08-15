package br.ufjf.dcc196.delbersspedrohpmarques.controledeestoque;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerProdutos;
    List<Produto> produtos;
    private ProdutoAdapter produtoAdapter;
    private AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = AppDatabase.getInstance(getApplicationContext());

        Produto p1 = db.produtoDao().buscaPorId(1L);
        System.out.println(p1.getNome());

        db.produtoDao().excluir(p1);

        produtos = db.produtoDao().listarTodos();


        /*
        produtos = new ArrayList<Produto>(){{
            add(new Produto( "Halter", 50.00f,10));
            add(new Produto( "Anilha",45.00f,10));
            add(new Produto( "Barra", 100f,5));
        }};
        */

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