package com.ktech.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    EditText username, password;
    TextView btnlogin;
    DBHelper DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = (EditText) findViewById(R.id.username1);
        password = (EditText) findViewById(R.id.password1);
        btnlogin =  findViewById(R.id.btnsignin1);
        DB = new DBHelper(this);
//        getSupportActionBar().hide();


        TextView textView= (TextView)findViewById(R.id.text3);
        String text="Developed By: Hammad Nawaz";
        SpannableString ss=new SpannableString(text);
        ForegroundColorSpan fcsred=new ForegroundColorSpan(Color.RED);
        ss.setSpan(fcsred,13,26 , Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        textView.setText(ss);
        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String user = username.getText().toString();
                String pass = password.getText().toString();
              if (user.isEmpty()){
                    username.setError("Please Input username");
                }
                else if (pass.isEmpty()){
                   password.setError("Please Input Password");
                }
//                if(user.equals("")||pass.equals(""))
//                    Toast.makeText(LoginActivity.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
                else{
                    Boolean checkuserpass = DB.checkusernamepassword(user, pass);
                    if(checkuserpass==true){
                        Toast.makeText(LoginActivity.this, "Sign in successfull", Toast.LENGTH_SHORT).show();
                        Intent intent  = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                    }else{
                        Toast.makeText(LoginActivity.this, "Username/Password is Invalid", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }
}


