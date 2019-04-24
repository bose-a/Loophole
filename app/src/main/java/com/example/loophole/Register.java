package com.example.loophole;

import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Register extends AppCompatActivity {

    FirebaseAuth mAuth;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference mRef = database.getReference("message");

    TextView header_text;
    EditText enter_email;
    EditText enter_password;
    Button signup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        header_text = findViewById(R.id.id_headertext);
        enter_email = findViewById(R.id.id_enteremail);
        enter_password = findViewById(R.id.id_enterpassword);
        signup = findViewById(R.id.id_signup);

        // Initialize Auth
        mAuth = FirebaseAuth.getInstance();
        //mRef.setValue("Hello, World!");

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = enter_email.getText().toString();
                String password = enter_password.getText().toString();
                createUser(email, password);
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        //Update UI
    }
    public void createUser(String email, String password){
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    // Sign in success
                    Log.d("FIRE", "createUserWithEmail:success");
                    FirebaseUser user = mAuth.getCurrentUser();
                    header_text.setText("Signed in!");
                    startActivity(new Intent(Register.this, Home.class));
                }
                else {
                    // Sign in failed
                    Log.w("FIRE", "createUserWithEmail:failure", task.getException());
                    Toast.makeText(Register.this, "Authentication failed.", Toast.LENGTH_LONG).show();
                    //updateUI(null);
                }
            }
        });
    }

}
