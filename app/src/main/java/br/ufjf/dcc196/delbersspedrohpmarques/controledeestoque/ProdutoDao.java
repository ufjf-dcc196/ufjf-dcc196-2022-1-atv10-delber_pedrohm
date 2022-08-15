package br.ufjf.dcc196.delbersspedrohpmarques.controledeestoque;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ProdutoDao {

    @Insert
    void criar(Produto novoProduto);

    @Query("SELECT * FROM Produto")
    List<Produto> listarTodos();

    @Query("SELECT * FROM Produto WHERE id=:id LIMIT 1")
    Produto buscaPorId(Long id);

    @Update
    void salvar(Produto produto);

    @Delete
    void excluir(Produto p1);
}
