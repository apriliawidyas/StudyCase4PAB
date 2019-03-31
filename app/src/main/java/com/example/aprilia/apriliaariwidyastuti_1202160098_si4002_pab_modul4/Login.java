package com.example.aprilia.apriliaariwidyastuti_1202160098_si4002_pab_modul4;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {

    EditText mEmail, mPassword;
    Button btnMasuk;
    TextView mDaftar;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mEmail = findViewById(R.id.email);
        mPassword = findViewById(R.id.password);

        mDaftar = findViewById(R.id.txtDaftar);
        mDaftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Login.this, Registrasi.class));
                finish();
            }
        });
//
        //Authentication Firebase
        mAuth = FirebaseAuth.getInstance();
    }

    public boolean cek() {
        if (mEmail.getText().toString().equals("")) {
            mEmail.setError("Masukkan Email Anda");
            mEmail.requestFocus();
            return false;
        }
        if (mPassword.getText().toString().equals("")) {
            mPassword.setError("Masukkan Password Anda");
            mPassword.requestFocus();
            return false;
        }
        return true;
    }

    public void Login(View view) {
        if (cek()) {
            new AsyncTask<Void, Void, Boolean>() {
                @Override
                protected Boolean doInBackground(Void... voids) {
                    mAuth.signInWithEmailAndPassword(mEmail.getText().toString(), mPassword.getText().toString())
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        startActivity(new Intent(Login.this, MainActivity.class));
                                        finish();
                                    } else {
                                        Toast.makeText(Login.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                    return null;
                }

                @Override
                protected void onPreExecute() {
                    Toast.makeText(Login.this, "Sign In...", Toast.LENGTH_SHORT).show();
                }

                @Override
                protected void onPostExecute(Boolean aBoolean) {
                    super.onPostExecute(aBoolean);
                }
            }.execute();
        }

    }
}
