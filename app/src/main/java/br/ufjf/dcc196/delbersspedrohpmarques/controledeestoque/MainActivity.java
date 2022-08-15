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

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    /*
    List<Produto> produtos;
    private AppDatabase db;
     */
    private RecyclerView recyclerProdutos;
    private ActivityResultLauncher<Intent> launcher;
    private ProdutoAdapter produtoAdapter;
    private ProdutoRepository repo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*
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

        repo = new ProdutoRepository(getApplicationContext());

        recyclerProdutos = findViewById(R.id.recyclerProdutos);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerProdutos.setLayoutManager(layoutManager);

        ProdutoAdapter.OnProdutoClickListener listener = new ProdutoAdapter.OnProdutoClickListener() {
            @Override
            public void onProdutoClick(View view, int position) {
                Produto produto = repo.getProduto(position);
                produto.setQuantidade(produto.getQuantidade()+1);
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

                        Produto novoProduto = new Produto(nome,preco);
                        repo.addProduto(novoProduto);

                        produtoAdapter = new ProdutoAdapter(repo.getProdutos(),listener);
                        recyclerProdutos.setAdapter(produtoAdapter);
                    }
                });
    }

    public void adicionarProduto(View view){
        Intent intent = new Intent(MainActivity.this, AdicionaProduto.class);
        launcher.launch(intent);
    }
}
