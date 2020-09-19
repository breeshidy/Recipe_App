package com.example.recipeapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegistrationActivity extends AppCompatActivity {
    EditText email, password;
    Button btnSignUp;
    TextView loginMessage;
    FirebaseAuth mFirebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        mFirebaseAuth = FirebaseAuth.getInstance();
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        btnSignUp = findViewById(R.id.signUp_button);
        loginMessage = findViewById(R.id.login_msg);

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emailText = email.getText().toString();
                String passwordText =password.getText().toString();
                if (emailText.isEmpty()){
                    email.setError("Please enter an email");
                    email.requestFocus();
                }
                else if (passwordText.isEmpty()){
                    password.setError("Please enter a password");
                    password.requestFocus();
                }
                else if (emailText.isEmpty() && passwordText.isEmpty()){
                    Toast.makeText(RegistrationActivity.this, "Please Find the fields", Toast.LENGTH_SHORT).show();
                }
                else if (!(emailText.isEmpty() && passwordText.isEmpty())){
                   mFirebaseAuth.createUserWithEmailAndPassword(emailText,passwordText).addOnCompleteListener(RegistrationActivity.this, new OnCompleteListener<AuthResult>() {
                       @Override
                       public void onComplete(@NonNull Task<AuthResult> task) {
                           if (!task.isSuccessful()){
                               Toast.makeText(RegistrationActivity.this, "Registration is Unsuccessful ", Toast.LENGTH_SHORT).show();
                           }
                           else {
                               startActivity(new Intent(RegistrationActivity.this,RecipeActivity.class));
                           }
                       }
                   });
                }
                else {
                    Toast.makeText(RegistrationActivity.this, "Error Occurred!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        loginMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(RegistrationActivity.this,LoginActivity.class);
                startActivity(i);
            }
        });

    }
}