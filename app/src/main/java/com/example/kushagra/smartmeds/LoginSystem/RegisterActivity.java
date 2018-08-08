package com.example.kushagra.smartmeds.LoginSystem;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.kushagra.smartmeds.AlarmSystem.MainActivity;
import com.example.kushagra.smartmeds.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class RegisterActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private DatabaseReference mDatabaseUser;

    private EditText mRegUsername, mRegEmail, mRegPassword;
    private Button mBtnReg;

    //progressDialgoue
    private ProgressDialog mRegProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //progress dialogue
        mRegProgress = new ProgressDialog(this);

        //fields
        mRegUsername = (EditText)findViewById(R.id.reg_username);
        mRegEmail = (EditText)findViewById(R.id.reg_email);
        mRegPassword =(EditText)findViewById(R.id.reg_password);
        mBtnReg = (Button)findViewById(R.id.reg_button);

        //Firebase
        mAuth = FirebaseAuth.getInstance();

        mBtnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //textfields

                String email = mRegEmail.getText().toString();
                String password = mRegPassword.getText().toString();
                String username = mRegUsername.getText().toString();

//                blankspaces check
                if (!TextUtils.isEmpty(username)||!TextUtils.isEmpty(email)||!TextUtils.isEmpty(password)){

                    //if not empty then start registration
                    mRegProgress.setTitle("Logging In...");
                    mRegProgress.setMessage("Please wait...");
                    mRegProgress.setCanceledOnTouchOutside(false);
                    mRegProgress.show();

                    register_user(username, email,password);
                }
                else {

                    Toast.makeText(RegisterActivity.this, "Field Empty", Toast.LENGTH_SHORT).show();
                }

                register_user(username,email,password);



            }
        });
    }

    private void register_user(final String username, final String email, final String password) {

        mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful()){

                    //check current user uid
                    FirebaseUser current_user = FirebaseAuth.getInstance().getCurrentUser();
                    String uid = current_user.getUid();

                    Log.d("TAG1",uid);

                    //push to database
                    mDatabaseUser = FirebaseDatabase.getInstance().getReference().child("Users").child(uid);

                    //taking auth values to database of users
                    HashMap<String,String> userMap =new HashMap<>();
                    userMap.put("username",username);
                    userMap.put("email",email);
                    userMap.put("password",password);

                    //setting usermap to database
                    mDatabaseUser.setValue(userMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()){

                                mRegProgress.dismiss();

                                sendToMain();
                            }


                        }
                    });


                }


            }
        });
    }

    private void sendToMain() {
        Intent toMain = new Intent(RegisterActivity.this, MainActivity.class);
        //flag set to prevent going back to startpage after being logged in
        toMain.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(toMain);
        finish();
    }
}