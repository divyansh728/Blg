package com.example.collegeblog;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class RegActivity extends AppCompatActivity {
    private EditText emll;
    private EditText psdd;
    private EditText cpsd;
    private Button btm;
    private Button btmm;
    private ProgressBar pgb;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState);
        setContentView( R.layout.activity_reg );
        emll = (EditText) findViewById( R.id.emll);
        psdd = (EditText) findViewById( R.id.psdd );
        Button btm = (Button) findViewById( R.id.btm );
        btmm = (Button) findViewById( R.id.btmm );
        mAuth = FirebaseAuth.getInstance();
        pgb = (ProgressBar)findViewById( R.id.pgb );

        if (mAuth.getCurrentUser()  != null)  {
            startActivity( new Intent( getApplicationContext(), MainActivity.class ) );
            finish();
        }

        btm.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = emll.getText().toString().trim();
                String password = psdd.getText().toString().trim();

                if (TextUtils.isEmpty( email )) {
                    emll.setError( "Email is required" );
                    return;
                }
                if (TextUtils.isEmpty( password )) {
                    psdd.setError( "Password is required" );
                    return;
                }
                        mAuth.createUserWithEmailAndPassword( email, password ).addOnCompleteListener( new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                   Toast.makeText( RegActivity.this, "User created",Toast.LENGTH_LONG ).show();
                                   startActivity( new Intent( getApplicationContext(), MainActivity.class ) );
                                } else {
                                  String errormessage = task.getException().getMessage();
                                    Toast.makeText( RegActivity.this, "Error :" + errormessage, Toast.LENGTH_LONG ).show();
                                }

                            }
                        } );





            }
        } );
    }


}