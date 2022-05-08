package xyz.danielblack.trainingbuddy.ui.main;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;


import java.util.Objects;

import xyz.danielblack.trainingbuddy.R;

public class ProfileFragment extends Fragment {
    FirebaseUser mUser;
    private FirebaseAuth mAuth;
    private FirebaseFirestore mDb;
    private TextView mProfileName;
    private TextView mTotalNumberOfWorkouts;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mAuth = FirebaseAuth.getInstance();
        mDb = FirebaseFirestore.getInstance();
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onResume() {
        super.onResume();
        mProfileName = requireView().findViewById(R.id.profileName);
        mTotalNumberOfWorkouts = requireView().findViewById(R.id.totalWorkouts);

        mUser = mAuth.getCurrentUser();
        mDb.collection("users")
                .whereEqualTo("email", mUser.getEmail())
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        for (QueryDocumentSnapshot document :
                                task.getResult()) {
                            mTotalNumberOfWorkouts.setText(Objects.requireNonNull(document.getData().get("numberOfWorkouts")) + " workouts");
                        }
                    }
                    else {
                        Log.d("PROFILE", "Error getting documents: ", task.getException());
                    }
                });
        assert mUser != null;
        mProfileName.setText(mUser.getDisplayName());
        requireActivity().setTitle("Profile");
    }
}