package com.example.myhealth;

import android.app.ProgressDialog;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class RecoverPassword extends AppCompatActivity {

    private Button btnSubmit;
    private EditText textEmail;
    private ProgressDialog progressDialog;

    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recover_password);

        btnSubmit = findViewById(R.id.btnSubmit);
        textEmail = findViewById(R.id.username);
        progressDialog = new ProgressDialog(this);

        firebaseAuth = FirebaseAuth.getInstance();

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                firebaseAuth.sendPasswordResetEmail(textEmail.getText().toString()).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(RecoverPassword.this, "Password recovery sent to your email", Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(RecoverPassword.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }
}
