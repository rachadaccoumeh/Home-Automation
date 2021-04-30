package com.accoumeh.rachad.homeautomation;


import androidx.annotation.NonNull;
import android.util.Log;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.accoumeh.rachad.homeautomation.BuildConfig;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity2 extends AppCompatActivity {
    Button signin;
    CheckBox show_password;
    EditText username, password1;
    TextView fpassword, showpass, sign_up;
    ImageButton imageButton;
    private FirebaseAuth mAuth;
    boolean signedin = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        signin = findViewById(R.id.signin);
        mAuth = FirebaseAuth.getInstance();
        show_password = findViewById(R.id.spassword);
        username = findViewById(R.id.username);
        password1 = findViewById(R.id.password);
        showpass = findViewById(R.id.showpass);
        fpassword = findViewById(R.id.forgot_password);
        imageButton = findViewById(R.id.imageButton);
        sign_up = findViewById(R.id.textView2);

        if (signedin) {

            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
        fpassword.setOnClickListener(v -> {
            Intent Getintent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://wa.me/message/V6ZRLWXBZNFWL1"));
            startActivity(Getintent);
        });
        imageButton.setOnClickListener(v -> {
            signup();
        });
        sign_up.setOnClickListener(v -> {
            signup();
        });
        signin.setOnClickListener(v -> {
            int i = 0;
            if (password1.getText().toString().isEmpty() || username.getText().toString().isEmpty()) {
                Toast.makeText(getApplicationContext(), "username or password can not be empty", Toast.LENGTH_LONG).show();
                i = 0;
            } else {
                i++;
            }
            if (password1.getText().toString().contains(" ") || username.getText().toString().contains(" ")) {
                Toast.makeText(getApplicationContext(), "space are not allowed in username password", Toast.LENGTH_LONG).show();
                i = 0;
            } else {
                i++;
            }
            if (i == 2) {
                String username2, password2;
                username2 = username.getText().toString();
                password2 = password1.getText().toString();
                signIn(username2,password2);
                if (username2.equals("rachad") && password2.equals("Admin")) {
                    Intent intent = new Intent(this, Admin_Activity.class);
                    startActivity(intent);
                } //else if another username password

            }
        });

        showpass.setOnClickListener(v -> {
            show_password.setChecked(!show_password.isChecked());
        });
        show_password.setOnCheckedChangeListener((compoundButton, isChecked) -> {
            if (isChecked) {
                // show password
                password1.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            } else {
                // hide password
                password1.setTransformationMethod(PasswordTransformationMethod.getInstance());
            }

        });


    }

    public void signup() {

        AlertDialog alertDialog = new AlertDialog.Builder(MainActivity2.this).create();
        alertDialog.setTitle("Sign up");
        alertDialog.setMessage("this app is private to has account please contact us \n   you must have an Arduino with wifi or ESP32/ESP8266 !!!");
        alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "cancel",
                (dialog, which) -> dialog.dismiss());
        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "contact us",
                (dialog, which) -> {
                    Intent Getintent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://wa.me/message/V6ZRLWXBZNFWL1"));
                    startActivity(Getintent);
                    dialog.dismiss();
                });
        alertDialog.show();
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            //reload();
        }
    }
    public void getinfo(){
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            // Name, email address, and profile photo Url
            String name = user.getDisplayName();
            String email = user.getEmail();
            Uri photoUrl = user.getPhotoUrl();

            // Check if user's email is verified
            boolean emailVerified = user.isEmailVerified();

            // The user's ID, unique to the Firebase project. Do NOT use this value to
            // authenticate with your backend server, if you have one. Use
            // FirebaseUser.getIdToken() instead.
            String uid = user.getUid();
        }
    }

    public void signIn(String email,String password) {
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                       //     Log.d(TAG, "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            getinfo();
                            guestguest();
                           // updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                         //   Log.w(TAG, "signInWithEmail:failure", task.getException());
                           // Toast.makeText(MainActivity2.this, "Authentication failed.",
                             //       Toast.LENGTH_SHORT).show();
                            Toast.makeText(getApplicationContext(), "username or password is incorrect", Toast.LENGTH_LONG).show();
                         //   updateUI(null);
                            // ...
                        }

                        // ...
                    }
                });
    }

    public void guestguest() {

/*String toggle1 = "Data/user_one/but_one", toggle2 = "Data/user_one/but_two", toggle3 = "Data/user_one/but_three", toggle4 = "Data/user_one/but_four";
      String push1 = "Data/user_one/but_one", push2 = "Data/user_one/but_two", push3 = "Data/user_one/but_three", push4 = "Data/user_one/but_four";
      String val1 = "Data/user_one/but_one", val2 = "Data/user_one/but_two", val3 = "Data/user_one/but_three", val4 = "Data/user_one/but_four";               */
        SharedPreferences pref = getApplicationContext().getSharedPreferences("Home Automation", 0); // 0 - for private mode
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("toggle1", "Data/user_one/but_one"); // Storing string
        editor.putString("toggle2", "Data/user_one/but_two"); // Storing string
        editor.putString("toggle3", "Data/user_one/but_three"); // Storing string
        editor.putString("toggle4", "Data/user_one/but_four"); // Storing string
        editor.putString("push1", "Data/user_one/but_one"); // Storing string
        editor.putString("push2", "Data/user_one/but_two"); // Storing string
        editor.putString("push3", "Data/user_one/but_three"); // Storing string
        editor.putString("push4", "Data/user_one/but_four"); // Storing string
        editor.putString("value1", "Data/user_one/but_one"); // Storing string
        editor.putString("value2", "Data/user_one/but_two"); // Storing string
        editor.putString("value3", "Data/user_one/but_three"); // Storing string
        editor.putString("value4", "Data/user_one/but_four"); // Storing string
        editor.putBoolean("signedin", true); // Storing boolean - true/false
        // editor.putInt("key_name", "int value"); // Storing integer
        //  editor.putFloat("key_name", "float value"); // Storing float
        //  editor.putLong("key_name", "long value"); // Storing long
        // editor.putBoolean("key_name", true); // Storing boolean - true/false
        editor.apply(); // commit changes

    }

    public void onBackPressed() {
        Intent a = new Intent(Intent.ACTION_MAIN);
        a.addCategory(Intent.CATEGORY_HOME);
        a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(a);
        MainActivity2.super.finish();

    }

}