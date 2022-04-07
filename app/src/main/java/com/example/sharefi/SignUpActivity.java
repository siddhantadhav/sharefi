package com.example.sharefi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class SignUpActivity extends AppCompatActivity {

    EditText editTextUserName;
    EditText editTextPassword;
    EditText editTextPhoneNo;
    EditText editTextEmail;
    ProgressBar progressBar;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        // Accessing User Input

        editTextUserName = (EditText)findViewById(R.id.editTextUserName);
        editTextPassword = (EditText)findViewById(R.id.editTextPassword);
        editTextPhoneNo = (EditText)findViewById(R.id.editTextMobileNo);
        editTextEmail = (EditText)findViewById(R.id.editTextEmail);
        progressBar = (ProgressBar)findViewById(R.id.progressBar);

        // Firebase Authentication initailize
        mAuth = FirebaseAuth.getInstance();
    }

    // Call back for signup button

    public void signupButtonClicked(View v){
        // store user input in variables

        String txtUserName = editTextUserName.getText().toString().trim();
        String txtPassword = editTextPassword.getText().toString().trim();
        String txtPhoneNo = editTextPhoneNo.getText().toString().trim();
        String txtEmail = editTextEmail.getText().toString().trim();

        // Check if edit text is empty

        if(txtUserName.isEmpty()){
            editTextUserName.setError("Please Enter User Name");
            editTextUserName.requestFocus();
        }

        if(txtPassword.isEmpty() || txtPassword.length() < 6){
            editTextPassword.setError("Please Enter Password Containing Atleast 6 Characters");
            editTextPassword.requestFocus();
        }

        if(txtPhoneNo.isEmpty()){
            editTextPhoneNo.setError("Please Enter Your Phone Number");
            editTextPhoneNo.requestFocus();
        }

        if(txtEmail.isEmpty()){
            editTextEmail.setError("Please Enter Your Email");
            editTextEmail.requestFocus();
        }

        progressBar.setVisibility(View.VISIBLE);

        mAuth.createUserWithEmailAndPassword(txtEmail, txtPassword)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            // Authentication

                            User user = new User(txtUserName, txtPassword, txtPhoneNo, txtEmail);

                            FirebaseDatabase.getInstance().getReference("Users")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful()){
                                        Toast.makeText(SignUpActivity.this, "User Registered Successfully", Toast.LENGTH_LONG).show();
                                    }
                                    else{
                                        Toast.makeText(SignUpActivity.this, "User Failed To Registered Successfully", Toast.LENGTH_LONG).show();
                                    }
                                    progressBar.setVisibility(View.GONE);
                                }
                            });
                        }
                        else{
                            Toast.makeText(SignUpActivity.this, "User Failed To Registered Successfully", Toast.LENGTH_LONG).show();
                            progressBar.setVisibility(View.GONE);
                        }
                    }
                });

    }
}