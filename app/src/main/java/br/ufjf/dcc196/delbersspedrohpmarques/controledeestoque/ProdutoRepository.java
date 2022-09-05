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
    private Float valorTotalEstoque;
    private Integer itensEstoque;

    public ProdutoRepository(Context context){
        this.context = context;
        preferences = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE);
        produtos = new ArrayList<Produto>();
        valorTotalEstoque = 0f;
        itensEstoque = 0;
    }

    public void calcularTotalEstoque(){
        valorTotalEstoque = 0f;
        for(Produto produto: produtos){
            valorTotalEstoque+= produto.getPreco()*produto.getQuantidade();
        }
    }
    public void deleteProduto(int position){
        produtos.remove(position);
    }
    public Float getValorTotalEstoque() {
        this.calcularTotalEstoque();
        return valorTotalEstoque;
    }


    public void calcularItensEstoque(){
        itensEstoque = 0;
        for(Produto produto: produtos){
            itensEstoque+= produto.getQuantidade();
        }
    }

    public Integer getItensEstoque() {
        this.calcularItensEstoque();
        return itensEstoque;
    }


    public void addProduto(Produto produto){
        produtos.add(produto);
    }

    public void editProduto(int position, Float novoPreco, Integer novaQts){
        produtos.get(position).setPreco(novoPreco);
        produtos.get(position).setQuantidade(novaQts);
    }

    public void removeProduto(Produto produto){
        produtos.remove(produto);
    }

    public Produto getProduto(int position){
        return produtos.get(position);
    }

    public List<Produto> getProdutos(){
        return produtos;
    }

}
