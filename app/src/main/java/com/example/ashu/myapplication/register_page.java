package com.example.ashu.myapplication;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;

public class register_page extends AppCompatActivity implements View.OnClickListener{

    private Button buttonsignup;
    private EditText editEmail;
    private EditText editPassword;
    private FirebaseAuth firebaseAuth;
    RadioButton radioButton;
    RadioGroup radioGroup;
    private ConstraintLayout mainlayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_page);

        firebaseAuth = FirebaseAuth.getInstance();
        buttonsignup = (Button) findViewById(R.id.sign_up);
        editEmail = (EditText) findViewById(R.id.userId);
        editPassword = (EditText) findViewById(R.id.password);
        radioGroup = (RadioGroup) findViewById(R.id.radio_group);
        mainlayout = (ConstraintLayout)findViewById(R.id.mainlayout);




        mainlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closekeyboard();
                editEmail.setError(null);
                editPassword.setError(null);
            }
        });

        buttonsignup.setOnClickListener(this);
    }

    private void registeruser()
    {
        String email = editEmail.getText().toString().trim();
        String password = editPassword.getText().toString().trim();
        checkuser();
        if(TextUtils.isEmpty(email))
        {
            buttonsignup.setText("Sign Up");
            editEmail.setError("Please enter Your E mail");
            return;
        }
        if (TextUtils.isEmpty(password))
        {
            buttonsignup.setText("Sign Up");
            editPassword.setError("Please enter Password");
            return;
        }
            firebaseAuth.createUserWithEmailAndPassword(email,password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()) {
                                Toast.makeText(register_page.this, "You are registered as " + radioButton.getText(), Toast.LENGTH_LONG).show();
                                if (radioButton.getText().equals("Faculty")) {
                                    startActivity(new Intent(getApplicationContext(), Faculty_signup.class));
                                    buttonsignup.setText("Next");

                                } else {
                                    startActivity(new Intent(getApplicationContext(), Student_signup.class));
                                    buttonsignup.setText("Next");
                                }
                            }
                            else
                                {
                                    if (task.getException() instanceof FirebaseAuthUserCollisionException)
                                    {
                                        buttonsignup.setText("Next");
                                        Toast.makeText(register_page.this,"User with this email already exist.",Toast.LENGTH_SHORT).show();
                                        Toast.makeText(register_page.this,"Select Forget Password from Log In Page",Toast.LENGTH_SHORT).show();
                                    }
                                    else
                                        {
                                        buttonsignup.setText("Next");
                                        Toast.makeText(register_page.this, "Failed To Register", Toast.LENGTH_SHORT).show();
                                    }
                            }
                        }
                    });
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        }

        @Override
        public void onClick(View view) {
        if (view == buttonsignup)
        {
         buttonsignup.setText("Just a while..");
         registeruser();
         closekeyboard();

        }

    }
    public void checkuser()
    {
        int radioID = radioGroup.getCheckedRadioButtonId();
        radioButton = findViewById(radioID);
    }

    public void LaunchLogin(View v)
    {
        Intent i = new Intent(getApplicationContext(),log_in_page.class);
        startActivity(i);
    }
    private static long back_pressed;
    @Override
    public void onBackPressed()
    {
        if (back_pressed + 2000 > System.currentTimeMillis())
        {
            super.finishAffinity();
        }
        else Toast.makeText(getBaseContext(), "Press once again to exit!", Toast.LENGTH_SHORT).show();
        back_pressed = System.currentTimeMillis();
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


}
