package com.bunjlabs.fingerprintpassstore;


import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Bundle;
import android.security.keystore.KeyProperties;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;

public class PasswordStoreActivity extends Activity {

    public PasswordStoreActivity() {

    }

    private SharedPreferences prefs;
    EditText edit;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        prefs = getSharedPreferences("password_storage", MODE_APPEND);
        setContentView(R.layout.pass_store);

        String password = prefs.getString("password", "");
        edit = (EditText) findViewById(R.id.editText);
        edit.setText(password);
        button = (Button) findViewById(R.id.button);
        final PasswordStoreActivity that = this;
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String password = edit.getText().toString();
                SharedPreferences.Editor editor = prefs.edit();
                editor.putString("password", password);
                editor.apply();
                editor.commit();
                Toast.makeText(that, "password saved", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
