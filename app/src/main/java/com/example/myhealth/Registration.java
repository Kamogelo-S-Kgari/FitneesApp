package com.example.myhealth;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Registration extends AppCompatActivity {

    // Variables used for this class:
    private Button btnRegister, btnExistingAccount;
    private EditText textName, textSurname, textGender, textContact, textEmail, textPassword;

    UserInformation info;

    ProgressDialog progressDialog;

    private FirebaseAuth firebaseAuth;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        info = new UserInformation();

        firebaseAuth = FirebaseAuth.getInstance();

        progressDialog = new ProgressDialog(this);

        btnRegister = findViewById(R.id.btn_register);

        textName = findViewById(R.id.first_name);
        textSurname = findViewById(R.id.last_name);
        textGender = findViewById(R.id.gender);
        textContact = findViewById(R.id.contact_number);
        textEmail = findViewById(R.id.email_address);
        textPassword = findViewById(R.id.enter_password);

        btnExistingAccount = findViewById(R.id.existing_account);

        reference = FirebaseDatabase.getInstance().getReference().child("Users");

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ValidateDetails()){

                    // Validates user email and password and saves it to Firebase:
                    String username = textEmail.getText().toString().trim();
                    String password = textPassword.getText().toString().trim();

                    firebaseAuth.createUserWithEmailAndPassword(username, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if(task.isSuccessful()) {

                                // Gets all info that is entered by the user:
                                info.setName(textName.getText().toString().trim());
                                info.setSurname(textSurname.getText().toString().trim());
                                info.setGender(textGender.getText().toString().trim());
                                info.setContact(textContact.getText().toString().trim());
                                info.setEmail(textEmail.getText().toString().trim());
                                info.setPassword(textPassword.getText().toString().trim());

                                // Saves info into Firebase:
                                reference.push().setValue(info);

                                // A ProgressBar will display:
                                progressDialog.setMessage("Registering user...");
                                progressDialog.show();

                                startActivity(new Intent(Registration.this, HomePage.class));
                                Toast.makeText(Registration.this, "Registered successfully. Details successfully saved.", Toast.LENGTH_SHORT).show();
                            }

                            else{
                                Toast.makeText(Registration.this, "Registration failed. Please try again.", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });

        btnExistingAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent exist = new Intent(Registration.this, MainActivity.class);
                startActivity(exist);
            }
        });
    }

    private boolean ValidateDetails() {
        // Method checks that all fields are entered:
        Boolean result = false;

        String name = textName.getText().toString().trim();
        String surname = textSurname.getText().toString().trim();
        String gender = textGender.getText().toString().trim();
        String contact = textContact.getText().toString().trim();
        String email = textEmail.getText().toString().trim();
        String password = textPassword.getText().toString().trim();

        if(name.isEmpty() || surname.isEmpty() || gender.isEmpty() || contact.isEmpty() || email.isEmpty() || password.isEmpty()){
            Toast.makeText(Registration.this, "Registration failed. Please enter all details.", Toast.LENGTH_SHORT).show();
        }

        else {
            result = true;
        }
        return result;
    }
}
