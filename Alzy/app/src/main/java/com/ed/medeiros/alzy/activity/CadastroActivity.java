package com.ed.medeiros.alzy.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.ed.medeiros.alzy.R;
import com.ed.medeiros.alzy.pacoteauxiliar.Base64ID;

import com.ed.medeiros.alzy.pacoteauxiliar.Usuario;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class CadastroActivity extends AppCompatActivity {
    private ProgressBar progressBarCircle;
    private EditText campoUsuario, campoEmail, campoSenha;
    private FirebaseAuth autenticacao;
    private int contadorProgress = 0;
    private Usuario  usuario;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        campoUsuario        = findViewById(R.id.editNomeCadastro);
        campoEmail          = findViewById(R.id.editEmailCadastro);
        campoSenha          = findViewById(R.id.editSenhaCadastro);
        progressBarCircle   = findViewById(R.id.progressBarCircularCadastro);

        progressBarCircle.setVisibility(View.GONE);
        //OBS
    }
    //--
    public void validarECadastrarUsuario(View view){

        if (isOnline()){
            String nome = campoUsuario.getText().toString();
            String email = campoEmail.getText().toString();
            String senha = campoSenha.getText().toString();
            if (nome.equals("") || email.equals("") || senha.equals("")){
                Toast.makeText(this, "Preencha todos os campos!!", Toast.LENGTH_LONG).show();

            }else {
                progressBarCircle.setVisibility(View.VISIBLE);
                usuario = new Usuario();
                usuario.setNome(campoUsuario.getText().toString());
                usuario.setEmail(campoEmail.getText().toString());
                usuario.setSenha(campoSenha.getText().toString());

                autenticacao = FirebaseAuth.getInstance();
                autenticacao.createUserWithEmailAndPassword(
                        usuario.getEmail(),
                        usuario.getSenha()
                ).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(getApplicationContext(), "Sucesso ao Cadastrar", Toast.LENGTH_SHORT).show();

                            String idUsuario = Base64ID.codificarBase64(campoEmail.getText().toString());
                            usuario.setIdUsuario(idUsuario);
                            usuario.salvar();

                            irTelaSplash();
                        }else {
                            Toast.makeText(getApplicationContext(), "Erro ao Cadastrar", Toast.LENGTH_SHORT).show();
                            progressBarCircle.setVisibility(View.GONE);
                        }
                    }
                });
            }
        }
    }
    //--
    public void irTelaLogin(View view){
        startActivity(new Intent(getApplicationContext(), LoginActivity.class));
        finish();
    }
    public void irTelaSplash(){
        startActivity(new Intent(this, SplashActivity.class));
        finish();
    }
    //--
    public boolean isOnline() {
        ConnectivityManager manager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        return manager.getActiveNetworkInfo() != null && manager.getActiveNetworkInfo().isConnectedOrConnecting();
    }
    public void irTelaPrincipal(){
        startActivity(new Intent(this, PrincipalActivity.class));
        finish();
    }
    public void carregarProgressBar(){
        this.contadorProgress = this.contadorProgress +1;
        progressBarCircle.setVisibility(View.VISIBLE);
        if (this.contadorProgress == 7){
            progressBarCircle.setVisibility(View.GONE);
        }

    }

}