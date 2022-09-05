package br.ufjf.dcc196.delbersspedrohpmarques.controledeestoque;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

public class EditorProduto extends AppCompatActivity {

    private TextView preco_antigo;
    private TextView qts_antigo;
    private EditText preco;
    private EditText qts;
    private String pos;
    private String dados;

    private Button confAlteracoes;
    private Button deleteProduto;

    private Float preco_final;
    private Integer quantidade_final;
    private Integer pos_final;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor_produto);

        Intent intent = getIntent();

        dados = intent.getStringExtra("dados_produto");
        pos =  intent.getStringExtra("pos");
        Gson gson = new Gson();
        Produto produto = gson.fromJson(dados, Produto.class);

        qts_antigo = findViewById(R.id.textEditQtsAntigo);
        preco_antigo = findViewById(R.id.textEditPrecoAntigo);

        preco = findViewById(R.id.editPrecoNew);
        qts = findViewById(R.id.editQtsNew);

        confAlteracoes = findViewById(R.id.botaoEditar);
        deleteProduto =  findViewById(R.id.botaoDeletar);

        confAlteracoes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if( preco.getText().toString().isEmpty() || qts.getText().toString().isEmpty()){
                    Toast.makeText(view.getContext(), "Preencha todos os campos com valores validos !", Toast.LENGTH_SHORT).show();
                }else{
                    preco_final = Float.parseFloat(preco.getText().toString());
                    quantidade_final = Integer.parseInt(qts.getText().toString());
                    pos_final = Integer.parseInt(pos);

                    Intent novoProdutoValues = new Intent();
                    novoProdutoValues.putExtra("preco_new", preco_final);
                    novoProdutoValues.putExtra("quantidade_new", quantidade_final);
                    novoProdutoValues.putExtra("position", pos_final);

                    setResult(-1, novoProdutoValues);
                    finish();
                }
            }
        });

        deleteProduto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pos_final = Integer.parseInt(pos);
                Intent novoProdutoValues = new Intent();
                novoProdutoValues.putExtra("delete", 1);
                novoProdutoValues.putExtra("position", pos_final);
                setResult(-1, novoProdutoValues);
                finish();
            }
        });

        getSupportActionBar().setTitle("Editar produto " + produto.getNome());

        preco_antigo.setText("Preço Atual:  R$ " + produto.getPreco().toString());
        qts_antigo.setText("Qauntidade Atual:  " + produto.getQuantidade().toString());



    }
}