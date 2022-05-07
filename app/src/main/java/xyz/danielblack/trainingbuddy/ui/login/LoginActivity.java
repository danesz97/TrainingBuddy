package xyz.danielblack.trainingbuddy.ui.login;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import xyz.danielblack.trainingbuddy.R;

public class LoginActivity extends AppCompatActivity {
    private static final String LOG_TAG = LoginActivity.class.getName();
    EditText emailET;
    EditText passwordET;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        emailET = findViewById(R.id.editTextTextEmailAddress);
        passwordET = findViewById(R.id.editTextTextPassword);
    }

    public void login(View view) {
        Log.i(LOG_TAG, emailET.getText().toString() + " => " + passwordET.getText().toString());
    }
}