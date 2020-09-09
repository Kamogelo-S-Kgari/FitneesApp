package com.example.myhealth;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
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

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText textEmail, textPassword;
    private Button btnLogin;

    private TextView viewRegister;
    private TextView viewForgot;

    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firebaseAuth = FirebaseAuth.getInstance();

        if(firebaseAuth.getCurrentUser() != null){

        }

        textEmail = findViewById(R.id.username);
        textPassword = findViewById(R.id.password);
        btnLogin = findViewById(R.id.btn_login);
        viewRegister = findViewById(R.id.sign_up);
        viewForgot = findViewById(R.id.forgot_password);

        progressDialog = new ProgressDialog(this);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userLogin();
            }
        });
        viewForgot.setOnClickListener(this);


        viewRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signUpIntent = new Intent(MainActivity.this,Registration.class);
                startActivity(signUpIntent);
            }
        });
    }

    private void userLogin(){
        String email = textEmail.getText().toString().trim();
        String password = textPassword.getText().toString().trim();

        String valid_email = "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +

                "\\@" +

                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +

                "(" +

                "\\." +

                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +

                ")+";

        Matcher matcher = Pattern.compile(valid_email).matcher(email);

        if (matcher.matches()){

            Toast.makeText(getApplicationContext(),"",Toast.LENGTH_LONG).show();
            textEmail.setText("");
            textPassword.setText("");

        }
        else {

            Toast.makeText(getApplicationContext(),"Enter valid email",Toast.LENGTH_LONG).show();

            if(textPassword.getText().toString().equals("")){
                textPassword.setError("Password should be a minimum of 8 characters and must have one uppercase letter, number, and symbol");
            }

        }

        if(TextUtils.isEmpty(email)){
            //Email address is empty:
            Toast.makeText(this, "Please enter email", Toast.LENGTH_SHORT).show();

            //Prevents the function from executing further:
            return;
        }

        if(TextUtils.isEmpty(password)){
            //Password is empty:
            Toast.makeText(this, "Please enter password", Toast.LENGTH_SHORT).show();

            //Prevents the function from executing further:
            return;
        }

        //Checking if validations are true:

        //A ProgressBar will be used:
        progressDialog.setMessage("Signing in...");
        progressDialog.show();

        firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                progressDialog.dismiss();

                if(task.isSuccessful()){
                    //The HomePage will display:
                    startActivity(new Intent(MainActivity.this, HomePage.class));
                    finish();
                }
                else {
                   Toast.makeText(MainActivity.this, "Login failed. Please try again later.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onClick(View v) {

        if(v == viewForgot){
            finish();
            startActivity(new Intent(this, RecoverPassword.class));
        }

    }
}
