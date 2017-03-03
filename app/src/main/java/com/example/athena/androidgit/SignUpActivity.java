package com.example.athena.androidgit;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class SignUpActivity extends Activity {
    EditText editEmail;
    EditText editAccountPassword;
    EditText editRePassword;
    AccountDAO accountDAO;
    Account verif;
    Account account;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        editEmail = (EditText) findViewById(R.id.editEmail);
        editAccountPassword = (EditText) findViewById(R.id.editAccountPassword);
        editRePassword = (EditText) findViewById(R.id.editRePassword);
        accountDAO = new AccountDAO(getApplicationContext());
    }

    public void create(View v) {
        account = new Account();
        verif = new Account();
        account.setEmail(editEmail.getText().toString());
        account.setPassword(editAccountPassword.getText().toString());

        verif = accountDAO.searchByEmail(editEmail.getText().toString());
        if (verif != null) {
            Toast.makeText(this, "That email is taken. Try another. " +
                            "if you forget your password try the button 'FORGOT YOUR PASSWORD?' ",
                    Toast.LENGTH_SHORT).show();
        } else {
            if (editAccountPassword.getText().toString() == editRePassword.getText().toString()) {
                finish();
                long newID = accountDAO.insert(account);

                if (newID != -1) {
                    String message = "ACCOUNT Successfully registered and your Id is " + newID;
                    Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "Registration not performed", Toast.LENGTH_SHORT).show();
                }
                finish();
            } else {
                Toast.makeText(this, "These passwords don't match ", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
