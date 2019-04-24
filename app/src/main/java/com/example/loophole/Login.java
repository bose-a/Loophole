package com.example.loophole;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
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
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity {

    FirebaseAuth mAuth;
    TextView title;
    EditText enteremail;
    EditText enterpassword;
    Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        title = findViewById(R.id.id_title_login);
        enteremail = findViewById(R.id.id_enteremail_login);
        enterpassword = findViewById(R.id.id_enterpassword_login);
        login = findViewById(R.id.id_login_login);

        mAuth = FirebaseAuth.getInstance();

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Sign in user
                String email = enteremail.getText().toString();
                String password = enterpassword.getText().toString();
                login(email, password);
            }
        });
    }

    public void login(String email, String password){
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d("FIRE", "Login - signInWithEmail:success");
                    FirebaseUser user = mAuth.getCurrentUser();
                    startActivity(new Intent(Login.this, Home.class));
                }
                else {
                    // If sign in fails, display a message to the user.
                    Log.w("FIRE", "Login - signInWithEmail:failure", task.getException());
                    Toast.makeText(Login.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}
