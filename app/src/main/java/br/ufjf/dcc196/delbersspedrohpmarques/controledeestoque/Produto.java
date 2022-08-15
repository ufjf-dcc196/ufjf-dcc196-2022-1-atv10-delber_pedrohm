package br.ufjf.dcc196.delbersspedrohpmarques.controledeestoque;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.List;

@Entity
public class Produto {
    @PrimaryKey(autoGenerate = true)
    private Long id;
    private String nome;
    private Float preco;
    private Integer quantidade;

    public Produto(Long id, String nome, Float preco, Integer quantidade) {
        this.id = id;
        this.nome = nome;
        this.preco = preco;
        this.quantidade = quantidade;
    }

    public Produto( String nome, Float preco, Integer quantidade) {
        this(null, nome,preco,quantidade);
    }

    public Produto(String nome,Float preco) {
        this(nome, preco, 0);
    }

    public Produto(){
        this( null,  null, 0);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Float getPreco() {
        return preco;
    }

    public void setPreco(Float preco) {
        this.preco = preco;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}