package com.example.aprilia.apriliaariwidyastuti_1202160098_si4002_pab_modul4;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class Registrasi extends AppCompatActivity {

    EditText mNama, mEmail, mPassword;
    Button btnDaftar;
    TextView mLogin;
    ProgressDialog pd;  //untuk semacam loading
    private FirebaseAuth mAuth;
    DatabaseReference ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regist);

        mNama = (EditText) findViewById(R.id.namaLengkap);
        mEmail = (EditText) findViewById(R.id.email);
        mPassword = (EditText) findViewById(R.id.password);


        mLogin = (TextView) findViewById(R.id.txtLogin);
        mLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Registrasi.this, Login.class));
                finish();
            }
        });

        btnDaftar = (Button) findViewById(R.id.btnDaftar);
        btnDaftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Registrasi.this, Login.class));
                regist(mEmail,mPassword,mNama);
                finish();
            }

            public void regist(EditText mEmail, EditText mPassword, View view) {
                if (cek()) {
                    mAuth = FirebaseAuth.getInstance();
                    mAuth.createUserWithEmailAndPassword(Registrasi.this.mEmail.getText().toString(), Registrasi.this.mPassword.getText().toString())
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        FirebaseUser User = mAuth.getCurrentUser();
                                        UserProfileChangeRequest userProfileChangeRequest = new UserProfileChangeRequest.Builder().setDisplayName(mNama.getText().toString()).build();

                                        startActivity(new Intent(Registrasi.this, Login.class));
                                        finish();
                                    }
                                }
                            });
                }

            }
            private boolean cek() {
                if (mNama.getText().toString().equals("")) {
                    mNama.setError("Masukkan Namamu");
                    mNama.requestFocus();
                    return false;
                }
                if (mEmail.getText().toString().equals("")) {
                    mEmail.setError("Masukkan Email");
                    mEmail.requestFocus();
                    return false;
                }
                if (mPassword.getText().toString().equals("")) {
                    mPassword.setError("Masukkan Password Anda");
                    mPassword.requestFocus();
                    return false;
                } else {
                    return true;
                }
            }


        });

    }
}
