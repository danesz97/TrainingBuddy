package xyz.danielblack.trainingbuddy.ui.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import xyz.danielblack.trainingbuddy.R;
import xyz.danielblack.trainingbuddy.ui.signup.SignupActivity;

public class LoginActivity extends AppCompatActivity {
    private static final String LOG_TAG = LoginActivity.class.getName();
    private static final String PREF_KEY = LoginActivity.class.getCanonicalName();

    private EditText mEmailET;
    private EditText mPasswordET;
    private SharedPreferences mPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mEmailET = findViewById(R.id.editTextEmailAddress);
        mPasswordET = findViewById(R.id.editTextPassword);
        mPreferences = getSharedPreferences(PREF_KEY, MODE_PRIVATE);

    }

    public void login(View view) {
        Log.i(LOG_TAG, mEmailET.getText().toString() + " => " + mPasswordET.getText().toString());
    }

    public void signup(View view) {
        Intent switchToSignupActivity = new Intent(this, SignupActivity.class);
        switchToSignupActivity.putExtra("SECRET_KEY", 1347866486);
        startActivity(switchToSignupActivity);
    }

    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences.Editor editor = mPreferences.edit();
        editor.putString("email", mEmailET.getText().toString());
        editor.putString("password", mPasswordET.getText().toString());
        editor.apply();
    }
}