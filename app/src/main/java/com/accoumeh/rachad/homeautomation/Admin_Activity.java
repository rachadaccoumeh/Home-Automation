package com.accoumeh.rachad.homeautomation;

import androidx.annotation.NonNull;

import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Admin_Activity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    Button signup;
    EditText user, pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        signup = findViewById(R.id.signup);
        user = findViewById(R.id.usernameup);
        pass = findViewById(R.id.passwordup);
        mAuth = FirebaseAuth.getInstance();
        signup.setOnClickListener(v -> {
    createAccount(user.getText().toString(),pass.getText().toString());
        });
    }

    public void createAccount(String email, String password) {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            //   Log.d(TAG, "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            //    updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            //  Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(Admin_Activity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            //    updateUI(null);
                        }

                        // ...
                    }
                });
    }
}