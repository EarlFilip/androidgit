package com.example.athena.androidgit;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends Activity {
    EditText editLogin;
    EditText editPassword;
    AccountDAO accountDAO;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editLogin = (EditText) findViewById(R.id.editLogin);
        editPassword = (EditText) findViewById(R.id.editPassword);
    }

    public void SignUp(View v){
        Intent signUp = new Intent(getApplicationContext(), SignUpActivity.class);
        startActivity(signUp);
    }

    public void Login(View v){
        if(accountDAO.searchByEmail(editLogin.getText().toString())){
            Intent menu = new Intent(getApplicationContext(), MenuActivity.class);
            startActivity(menu);
        }
    }
}
