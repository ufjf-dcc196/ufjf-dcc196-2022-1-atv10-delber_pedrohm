package br.ufjf.dcc196.delbersspedrohpmarques.controledeestoque;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerProdutos;
    private ActivityResultLauncher<Intent> launcher;
    private ActivityResultLauncher<Intent> Editlauncher;
    private ProdutoAdapter produtoAdapter;
    private ProdutoRepository repo;
    private TextView textViewTotalPrecoProdutos;
    private TextView textViewEstoqueItens;
    private SharedPreferences pref;
    private List<Produto> listSaveProduto;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        repo = new ProdutoRepository(getApplicationContext());

        pref = getSharedPreferences("produtos", Context.MODE_PRIVATE);
        Gson gson = new Gson();
        if(pref.getString("repo",null) != null) {
            Type listType = new TypeToken<ArrayList<Produto>>(){}.getType();
            listSaveProduto = gson.fromJson(pref.getString("repo",null), listType);

            for(int i = 0; i < listSaveProduto.size(); i++){
                repo.addProduto(listSaveProduto.get(i));
            }
        }

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
                editarProduto(view, produto, position);
//                produto.setQuantidade(produto.getQuantidade() + 1);
//                textViewTotalPrecoProdutos.setText("R$ " + repo.getValorTotalEstoque().toString());
//                textViewEstoqueItens.setText(repo.getItensEstoque().toString());
//                produto.setPreco(produto.getPreco());
//                produtoAdapter.notifyItemChanged(position);
            }
        };
        produtoAdapter = new ProdutoAdapter(repo.getProdutos(), listener);
        recyclerProdutos.setAdapter(produtoAdapter);

        Editlauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == Activity.RESULT_OK) {
                            Bundle extras;
                            extras = result.getData().getExtras();

                            Integer delete = extras.getInt("delete");
                            Float preco_new = extras.getFloat("preco_new");
                            Integer quantidade_new = extras.getInt("quantidade_new");
                            Integer position_new = extras.getInt("position");

                            repo.editProduto(position_new, preco_new, quantidade_new);

                            if(delete == 1) {
                                repo.deleteProduto(position_new);
                            }

                            pref = getSharedPreferences("produtos", Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor = pref.edit();
                            editor.putString("repo",new Gson().toJson(repo.getProdutos()));
                            editor.commit();

                            produtoAdapter = new ProdutoAdapter(repo.getProdutos(), listener);
                            recyclerProdutos.setAdapter(produtoAdapter);
                            textViewTotalPrecoProdutos.setText("R$ " + repo.getValorTotalEstoque().toString());
                            textViewEstoqueItens.setText(repo.getItensEstoque().toString());
                        }
                    }
                });

            launcher = registerForActivityResult(
                    new ActivityResultContracts.StartActivityForResult(),
                    new ActivityResultCallback<ActivityResult>() {
                        @Override
                        public void onActivityResult(ActivityResult result) {
                            if (result.getResultCode() == Activity.RESULT_OK) {
                            Bundle extras;
                            extras = result.getData().getExtras();
                                String nome = extras.getString("nome");
                                Float preco = extras.getFloat("preco");
                                Integer quantidade = extras.getInt("quantidade");

                                textViewTotalPrecoProdutos.setText("R$ " + repo.getValorTotalEstoque().toString());
                                textViewEstoqueItens.setText(repo.getItensEstoque().toString());

                                Produto novoProduto = new Produto(nome, preco, quantidade);
                                repo.addProduto(novoProduto);

                                pref = getSharedPreferences("produtos", Context.MODE_PRIVATE);
                                SharedPreferences.Editor editor = pref.edit();
                                editor.putString("repo",new Gson().toJson(repo.getProdutos()));
                                editor.commit();

                                produtoAdapter = new ProdutoAdapter(repo.getProdutos(), listener);
                                recyclerProdutos.setAdapter(produtoAdapter);

                                textViewTotalPrecoProdutos.setText("R$ " + repo.getValorTotalEstoque().toString());
                                textViewEstoqueItens.setText(repo.getItensEstoque().toString());
                            }
                        }
                    });

    }

    public void adicionarProduto(View view){
        Intent intent = new Intent(MainActivity.this, AdicionaProduto.class);
        launcher.launch(intent);
    }

    public void editarProduto(View view, Produto produto, Integer position){
        Intent intent = new Intent(MainActivity.this, EditorProduto.class);
        intent.putExtra("dados_produto", new Gson().toJson(produto));
        intent.putExtra("pos", position.toString());
        Editlauncher.launch(intent);
    }


}
