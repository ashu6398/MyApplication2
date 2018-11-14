package com.example.ashu.myapplication;

import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethod;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.ashu.myapplication.Home_page;
import com.example.ashu.myapplication.R;
import com.example.ashu.myapplication.register_page;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class log_in_page extends AppCompatActivity  implements View.OnClickListener{

    private EditText editEmail;
    private EditText editPassword;
    private Button buttonLogin;
    private Button resetPassword;
    private FirebaseAuth firebaseAuth;
    private ProgressBar progressBar;
    private ConstraintLayout mainlayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in_page);
        firebaseAuth = FirebaseAuth.getInstance();
        //       if (firebaseAuth.getCurrentUser()!=null)
        //       {
        //        finish();
        //       startActivity(new Intent(getApplicationContext(),Home_page.class));
        //       }
        editEmail = (EditText)findViewById(R.id.userId);
        editPassword = (EditText)findViewById(R.id.password);
        buttonLogin = (Button) findViewById(R.id.log_in);
        resetPassword=(Button)findViewById(R.id.reset_button);
        progressBar = (ProgressBar)findViewById(R.id.progressBar);
        mainlayout = (ConstraintLayout)findViewById(R.id.mainlayout);

        mainlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closekeyboard();
                editEmail.setError(null);
                editPassword.setError(null);
            }
        });

        buttonLogin.setOnClickListener(this);

        resetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closekeyboard();
                progressBar.setVisibility(View.VISIBLE);
                buttonLogin.setText(null);
                final String email = editEmail.getText().toString().trim();
                if(TextUtils.isEmpty(email))
                {
                    editEmail.setError("Please Enter Your E-mail");
                    editPassword.setError(null);
                    progressBar.setVisibility(View.INVISIBLE);
                    buttonLogin.setText("Log In");
                    closekeyboard();

                }
                else
                {
                    firebaseAuth.sendPasswordResetEmail(email)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful())
                                    {
                                        Toast.makeText(log_in_page.this,"Check Your E-mail",Toast.LENGTH_LONG).show();
                                        progressBar.setVisibility(View.INVISIBLE);
                                        buttonLogin.setText("Log In");

                                    }
                                    else
                                    {
                                        Toast.makeText(log_in_page.this,"Error In sending E-mail",Toast.LENGTH_LONG).show();
                                        progressBar.setVisibility(View.INVISIBLE);
                                        buttonLogin.setText("Log In");
                                    }
                                }

                            });
                }

            }
        });



    }
    private void userLogIn()
    {
        String email = editEmail.getText().toString().trim();
        String password = editPassword.getText().toString().trim();

        if(TextUtils.isEmpty(email))
        {
            editEmail.setError("Please Enter Your E-mail");
            buttonLogin.setText("Log In");
            return;
        }
        if (TextUtils.isEmpty(password))
        {
            editPassword.setError("Please Enter Password");
            progressBar.setVisibility(View.INVISIBLE);
            buttonLogin.setText("Log In");
            return;
        }

        firebaseAuth.signInWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful())
                        {
                            finish();
                            startActivity(new Intent(getApplicationContext(),Home_page.class));
                        }
                        else
                        {
                            Toast.makeText(log_in_page.this,"Invalid User Name Or Password",Toast.LENGTH_LONG).show();
                            progressBar.setVisibility(View.INVISIBLE);
                            buttonLogin.setText("Log In");
                            buttonLogin.setWidth(200);

                        }


                    }
                });
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

    }





    @Override
    public void onClick(View view) {
        if (view==buttonLogin)
        {
            buttonLogin.setText("Logging In...");
            userLogIn();
            closekeyboard();
        }
    }

    public void LaunchRegister(View v){
        Intent i = new Intent(getApplicationContext(),register_page.class);
        startActivity(i);
        }
    public void closekeyboard()
    {
        View view =this.getCurrentFocus();
        if (view !=null)
        {
            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
    private static long back_pressed;

    @Override
    public void onBackPressed()
    {
        if (back_pressed + 2000 > System.currentTimeMillis())super.finishAffinity();
        else Toast.makeText(getBaseContext(), "Press once again to exit!", Toast.LENGTH_SHORT).show();
        back_pressed = System.currentTimeMillis();
    }

   }

