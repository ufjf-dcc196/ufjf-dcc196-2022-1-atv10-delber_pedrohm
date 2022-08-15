package br.ufjf.dcc196.delbersspedrohpmarques.controledeestoque;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ProdutoAdapter extends RecyclerView.Adapter<ProdutoAdapter
        .ProdutoViewHolder> {
    private List<Produto> produtos;
    private OnProdutoClickListener listener;
    
    public ProdutoAdapter(List<Produto> produtos, OnProdutoClickListener listener) {
        this.produtos = produtos;
        this.listener = listener;
    }

    public ProdutoAdapter(List<Produto> produtos) {
        this.produtos = produtos;
    }

    @NonNull
    @Override
    public ProdutoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context contexto = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(contexto);
        View produtoView = inflater.inflate(R.layout.produto_layout, parent, false);
        ProdutoViewHolder viewHolder = new ProdutoViewHolder(produtoView);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ProdutoViewHolder holder, int position) {
        Produto produto = produtos.get(position);
        holder.textViewNome.setText(produto.getNome());
        holder.textViewProdutos.setText(produto.getPreco().toString());
        holder.textViewCategoria.setText(produto.getQuantidade().toString());

    }

    @Override
    public int getItemCount() {
        return produtos.size();
    }

    public class ProdutoViewHolder extends RecyclerView.ViewHolder{
        private TextView textViewNome;
        private TextView textViewProdutos;
        private TextView textViewCategoria;

        public ProdutoViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewNome = itemView.findViewById(R.id.textViewNome);
            textViewProdutos = itemView.findViewById(R.id.textViewProdutos);
            textViewCategoria = itemView.findViewById(R.id.textViewCategoria);
            
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onProdutoClick(v, getAdapterPosition());
                }
            });
        }
    }
     public interface OnProdutoClickListener{
        void onProdutoClick(View view, int position);
    }
}
