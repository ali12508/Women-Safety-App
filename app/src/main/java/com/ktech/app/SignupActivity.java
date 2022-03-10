package com.ktech.app;


import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;

import androidx.navigation.ui.NavigationUI;
import androidx.appcompat.widget.Toolbar;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;

public class SignupActivity extends AppCompatActivity {
    private AppBarConfiguration mAppBarConfiguration;
    TextInputEditText username, password, repassword;
    TextView signup, signin;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        Tools.setSystemBarLight(this);
        Tools.setSystemBarColor(this,R.color.white);
//        getSupportActionBar().hide();
        username = ( TextInputEditText) findViewById(R.id.username);
        password = ( TextInputEditText)  findViewById(R.id.password);
        repassword = ( TextInputEditText)  findViewById(R.id.repassword);
        signup =  findViewById(R.id.btnsignup);
        signin =  findViewById(R.id.btnsignin);
        DB = new DBHelper(this);





        TextView textView= (TextView)findViewById(R.id.text09);
        String text="Developed By: Ahsan Nawaz";
        SpannableString ss=new SpannableString(text);
        ForegroundColorSpan fcsred=new ForegroundColorSpan(Color.RED);
        ss.setSpan(fcsred,13,25 , Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        textView.setText(ss);


        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = username.getText().toString();
                String pass = password.getText().toString();
                String repass = repassword.getText().toString();

                if (user.isEmpty()){
                    username.setError("Please Input User name");
                }
                else if (pass.isEmpty()){
                   password.setError("Please Input Password");
                }
              else  if (repass.isEmpty()){
                    repassword.setError("Please Reconfirm Password");
                }
//
//                        || pass.equals("") || repass.equals(""))
//                    Toast.makeText(SignupActivity.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
                else  {
                    if (pass.equals(repass)) {
                        Boolean checkuser = DB.checkusername(user);
                        if (checkuser == false) {
                            Boolean insert = DB.insertData(user, pass);
                            if (insert == true) {
                                Toast.makeText(SignupActivity.this, "Registered successfully", Toast.LENGTH_LONG).show();
                                startActivity(new Intent(SignupActivity.this,LoginActivity.class));

                            } else {
                                Toast.makeText(SignupActivity.this, "Registration failed", Toast.LENGTH_LONG).show();
                            }
                        } else {
                            Toast.makeText(SignupActivity.this, "User already exists! please sign in", Toast.LENGTH_LONG).show();
                        }
                    } else {
//                        Toast.makeText(SignupActivity.this, "Passwords not matching", Toast.LENGTH_SHORT).show();
                       password.setError(" Passwords are not matching");
                        repassword.setError(" Passwords are not matching");
                    }
                }

            }
        });


        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            }
        });



    }

}


