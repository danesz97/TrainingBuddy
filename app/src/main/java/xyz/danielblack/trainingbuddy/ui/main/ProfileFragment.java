package xyz.danielblack.trainingbuddy.ui.main;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Objects;

import xyz.danielblack.trainingbuddy.R;

public class ProfileFragment extends Fragment {
    FirebaseUser mUser;
    private FirebaseAuth mAuth;
    private TextView mProfileName;
    private TextView mTotalNumberOfWorkouts;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mAuth = FirebaseAuth.getInstance();
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onResume() {
        super.onResume();
        mProfileName = requireView().findViewById(R.id.profileName);
        mTotalNumberOfWorkouts = requireView().findViewById(R.id.totalWorkouts);

        mUser = mAuth.getCurrentUser();
        assert mUser != null;
        mProfileName.setText(mUser.getDisplayName());
        requireActivity().setTitle("Profile");
    }
}