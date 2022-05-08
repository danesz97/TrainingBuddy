package xyz.danielblack.trainingbuddy.ui.signup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import xyz.danielblack.trainingbuddy.R;
import xyz.danielblack.trainingbuddy.data.constants.Constants;
import xyz.danielblack.trainingbuddy.ui.login.LoginActivity;

public class SignupActivity extends AppCompatActivity {
    private static final String LOG_TAG = SignupActivity.class.getName();
    private static final String PREF_KEY = LoginActivity.class.getCanonicalName();
    private SharedPreferences mPreferences;

    private EditText mEmailET;
    private EditText mPasswordET;
    private EditText mPasswordConfirmET;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        mEmailET = findViewById(R.id.editTextEmailAddress);
        mPasswordET = findViewById(R.id.editTextPassword);
        mPasswordConfirmET = findViewById(R.id.editTextPasswordConfirmation);

        int secretKey = getIntent().getIntExtra("SECRET_KEY", 0);
        mPreferences = getSharedPreferences(PREF_KEY, MODE_PRIVATE);
        String emailPref = mPreferences.getString("email", "");
        String passwordPref = mPreferences.getString("password", "");

        mEmailET.setText(emailPref);
        mPasswordET.setText(passwordPref);


        if (secretKey != Constants.SECRET_KEY) {
            finish();
        }
        mAuth = FirebaseAuth.getInstance();
    }

    public void signup(View view) {
        String email = mEmailET.getText().toString();
        String pass = mPasswordET.getText().toString();
        String passConfirm = mPasswordConfirmET.getText().toString();
        if (pass.equals(passConfirm)) {
            mAuth.createUserWithEmailAndPassword(email, pass)
                    .addOnCompleteListener(this, task -> {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(LOG_TAG, "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            startTraining(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(LOG_TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(SignupActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
//                                updateUI(null);
                        }
                    });
        }
    }

    public void startTraining(FirebaseUser user) {

    }


}