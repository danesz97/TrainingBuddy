package xyz.danielblack.trainingbuddy.ui.signup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;


import java.util.HashMap;
import java.util.Map;

import xyz.danielblack.trainingbuddy.R;
import xyz.danielblack.trainingbuddy.data.constants.Constants;
import xyz.danielblack.trainingbuddy.data.model.User;
import xyz.danielblack.trainingbuddy.ui.login.LoginActivity;
import xyz.danielblack.trainingbuddy.ui.main.MainActivity;

public class SignupActivity extends AppCompatActivity {
    private static final String LOG_TAG = SignupActivity.class.getName();
    private static final String PREF_KEY = LoginActivity.class.getCanonicalName();
    private SharedPreferences mPreferences;

    private EditText mEmailET;
    private EditText mPasswordET;
    private EditText mPasswordConfirmET;

    private FirebaseAuth mAuth;
    private FirebaseFirestore mDb;

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
        mDb = FirebaseFirestore.getInstance();
    }

    private void updateDisplayname(String email) {
        FirebaseUser user = mAuth.getCurrentUser();
        int at = email.indexOf('@');
        String nickname = email.substring(0, at);
        UserProfileChangeRequest profileUpdate = new UserProfileChangeRequest.Builder()
                .setDisplayName(nickname)
                .build();
        if (user != null) {
            user.updateProfile(profileUpdate)
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            Log.d(LOG_TAG, "User profile updated.");
                            startTraining();
                        }
                    });
        }
    }

    private void addToDataBase(Map<String, Object> user) {
        mDb.collection("users")
                .add(user)
                .addOnSuccessListener(documentReference -> Log.d(LOG_TAG, "DocumentSnapshot added with ID: " + documentReference.getId()))
                .addOnFailureListener(e -> Log.w(LOG_TAG, "Error adding document", e));
    }

    public void signup(View view) {
        String email = mEmailET.getText().toString();
        String pass = mPasswordET.getText().toString();
        String passConfirm = mPasswordConfirmET.getText().toString();
        if (pass.equals(passConfirm)) {
            mAuth.createUserWithEmailAndPassword(email, pass)
                    .addOnCompleteListener(this, task -> {
                        if (task.isSuccessful()) {
                            Log.d(LOG_TAG, "createUserWithEmail:success");
                            updateDisplayname(email);
                            Map<String, Object> user = new HashMap<>();
                            user.put("email", email);
                            user.put("numberOfWorkouts", 0);
                            addToDataBase(user);
                        } else {
                            Log.d(LOG_TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(SignupActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    });
        }
    }

    public void startTraining() {
        Intent switchToMainActivity = new Intent(this, MainActivity.class);
        switchToMainActivity.putExtra("SECRET_KEY", 1347866486);
        startActivity(switchToMainActivity);
    }


}