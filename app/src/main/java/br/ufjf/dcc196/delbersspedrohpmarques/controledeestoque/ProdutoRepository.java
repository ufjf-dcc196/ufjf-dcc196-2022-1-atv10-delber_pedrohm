package br.ufjf.dcc196.delbersspedrohpmarques.controledeestoque;

import android.content.Context;
import java.util.List;
import android.content.SharedPreferences;

import java.util.ArrayList;

public class ProdutoRepository  {

    private Context context;
    private SharedPreferences preferences;
    private List<Produto> produtos;
    private final String PREFERENCES_NAME = "preferences_produtos";

    public ProdutoRepository(Context context){
        this.context = context;
        preferences = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE);
        produtos = new ArrayList<Produto>();
    }

    public void addProduto(Produto produto){
        produtos.add(produto);
    }

    public Produto getProduto(int position){
        return produtos.get(position);
    }

    public List<Produto> getProdutos(){
        return produtos;
    }

}