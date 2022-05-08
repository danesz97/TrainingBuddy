package xyz.danielblack.trainingbuddy.ui.signup;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import xyz.danielblack.trainingbuddy.R;
import xyz.danielblack.trainingbuddy.data.constants.Constants;

public class SignupActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        int secretKey = getIntent().getIntExtra("SECRET_KEY", 0);

        if (secretKey != Constants.SECRET_KEY) {
            finish();
        }
    }

    public void signup(View view) {

    }
}