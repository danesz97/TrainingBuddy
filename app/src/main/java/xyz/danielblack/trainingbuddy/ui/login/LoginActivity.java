package xyz.danielblack.trainingbuddy.ui.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import xyz.danielblack.trainingbuddy.R;
import xyz.danielblack.trainingbuddy.ui.signup.SignupActivity;

public class LoginActivity extends AppCompatActivity {
    private static final String LOG_TAG = LoginActivity.class.getName();

    EditText emailET;
    EditText passwordET;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        emailET = findViewById(R.id.editTextEmailAddress);
        passwordET = findViewById(R.id.editTextPassword);
    }

    public void login(View view) {
        Log.i(LOG_TAG, emailET.getText().toString() + " => " + passwordET.getText().toString());
    }

    public void signup(View view) {
        Intent switchToSignupActivity = new Intent(this, SignupActivity.class);
        switchToSignupActivity.putExtra("SECRET_KEY", 1347866486);
        startActivity(switchToSignupActivity);
    }
}