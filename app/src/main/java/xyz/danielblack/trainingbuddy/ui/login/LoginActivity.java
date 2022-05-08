package xyz.danielblack.trainingbuddy.ui.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import xyz.danielblack.trainingbuddy.R;
import xyz.danielblack.trainingbuddy.ui.main.MainActivity;
import xyz.danielblack.trainingbuddy.ui.signup.SignupActivity;

public class LoginActivity extends AppCompatActivity {
    private static final String LOG_TAG = LoginActivity.class.getName();
    private static final String PREF_KEY = LoginActivity.class.getCanonicalName();

    private EditText mEmailET;
    private EditText mPasswordET;
    private SharedPreferences mPreferences;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mEmailET = findViewById(R.id.editTextEmailAddress);
        mPasswordET = findViewById(R.id.editTextPassword);
        mPreferences = getSharedPreferences(PREF_KEY, MODE_PRIVATE);

        mAuth = FirebaseAuth.getInstance();

    }

    public void login(View view) {
        String email = mEmailET.getText().toString();
        String password = mPasswordET.getText().toString();
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, task -> {
                    if(task.isSuccessful()) {
                        Log.d(LOG_TAG, "signInWithEmail:success");
                        FirebaseUser user = mAuth.getCurrentUser();
                        startTraining();
                    } else {
                        Log.e(LOG_TAG, "signInWithEmail:failure", task.getException());
                        Toast.makeText(LoginActivity.this, "Authentication failed.",
                                Toast.LENGTH_SHORT).show();
                    }
                });
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

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            startTraining();
        }
    }

    public void startTraining() {
        Intent switchToMainActivity = new Intent(this, MainActivity.class);
        switchToMainActivity.putExtra("SECRET_KEY", 1347866486);
        startActivity(switchToMainActivity);
    }
}