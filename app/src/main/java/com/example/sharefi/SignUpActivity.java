package com.example.sharefi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;

public class SignUpActivity extends AppCompatActivity {

    EditText editTextUserName;
    EditText editTextPassword;
    EditText editTextPhoneNo;
    EditText editTextEmail;

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

    }
}