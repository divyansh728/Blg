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

public class LgnActivity extends AppCompatActivity {

    private EditText psd;
    private  EditText eml;
    private Button btn;
    private Button btnn;
    private ProgressBar progress;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_lgn );

        mAuth = FirebaseAuth.getInstance();
        psd = (EditText) findViewById( R.id.psd );
        eml = (EditText) findViewById( R.id.eml );
        btn = (Button) findViewById( R.id.btn );
        btnn = (Button) findViewById( R.id.btnn );
        progress = (ProgressBar) findViewById( R.id.pgbb );
        progress.setVisibility(View.INVISIBLE);

        btnn.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent regintent = new Intent( LgnActivity.this, RegActivity.class );
                startActivity( regintent );
            }
        } );

        btn.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progress.setVisibility( View.VISIBLE );
                String leml = eml.getText().toString();
                String lpsd = psd.getText().toString();
                if (!TextUtils.isEmpty( leml ) && !TextUtils.isEmpty( lpsd )) {
                    progress.setVisibility( View.VISIBLE );
                    mAuth.signInWithEmailAndPassword( leml, lpsd ).addOnCompleteListener( new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                sendtomain();
                            } else {
                                String errormessage = task.getException().getMessage();
                                Toast.makeText( LgnActivity.this, "Error :" + errormessage, Toast.LENGTH_LONG ).show();
                            }

                            progress.setVisibility( View.INVISIBLE );
                        }

                    } );
                }
            }
        } );


    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentuser = mAuth.getCurrentUser();
        if (currentuser != null) {
           sendtomain();

        }


    }


    private void sendtomain() {
        Intent mainintent = new Intent( LgnActivity.this, MainActivity.class );
        startActivity( mainintent );
        finish();
    }
    }