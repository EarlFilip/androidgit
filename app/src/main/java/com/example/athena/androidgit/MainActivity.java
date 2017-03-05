package com.example.athena.androidgit;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {
    EditText editLogin;
    EditText editPassword;
    AccountDAO accountDAO;
    Account accountVerification;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editLogin = (EditText) findViewById(R.id.editLogin);
        editPassword = (EditText) findViewById(R.id.editAccountPassword);
        accountDAO = new AccountDAO(getApplicationContext());
    }

    public void SignUp(View v) {
        Intent signUp = new Intent(getApplicationContext(), SignUpActivity.class);
        startActivity(signUp);
    }

    public void login(View v) {
        Intent menu = new Intent(getApplicationContext(), MenuActivity.class);
        accountVerification = new Account();

        accountVerification = accountDAO.searchByEmail(editLogin.getText().toString());
        if (accountVerification.getEmail().toString() != null) {
            if (accountVerification.getPassword().toString() == editPassword.getText().toString()) {
                finish();
                startActivity(menu);
            } else
                Toast.makeText(this, "Account and Password don't match ", Toast.LENGTH_SHORT).show();

        } else {
            Toast.makeText(this, "Account don't match or account doesn't has created ", Toast.LENGTH_SHORT).show();
        }
    }
}
