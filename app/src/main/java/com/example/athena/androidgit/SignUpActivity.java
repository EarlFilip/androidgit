package com.example.athena.androidgit;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class SignUpActivity extends Activity {
    EditText editEmail;
    EditText editPassword;
    EditText editRePassword;
    AccountDAO  accountDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        editEmail = (EditText) findViewById(R.id.editEmail);
        editPassword = (EditText) findViewById(R.id.editPassword);
        editRePassword = (EditText) findViewById(R.id.editRePassword);
        accountDAO= new AccountDAO(getApplicationContext());
    }

    public void create(View v)
    {
        Account account = new Account();
        account.setEmail(editEmail.getText().toString());
        account.setPassword(editPassword.getText().toString());

        if(accountDAO.searchByEmail(editEmail.getText().toString())){
            Toast.makeText(this, "That email is taken. Try another. " +
                    "if you forget your password try the button 'FORGOT YOUR PASSWORD?' ",
                    Toast.LENGTH_SHORT).show();
        }else{
            if(editPassword.getText() == editRePassword.getText()){
                insert(account);
            }else
                Toast.makeText(this, "These passwords don't match ", Toast.LENGTH_SHORT).show();

        }
    }

    public void insert(Account account){
        long newID = accountDAO.insert(account);

        if (newID != -1)
        {
            Toast.makeText(this, "Registered with ID ", Toast.LENGTH_SHORT).show();
            String mensagem = "ACCOUNT Successfully registered and your Id is " + newID;
            Toast.makeText(this, mensagem, Toast.LENGTH_SHORT).show();
        } else
        {
            Toast.makeText(this, "Registration not performed", Toast.LENGTH_SHORT).show();
        }
        finish();
    }

}
