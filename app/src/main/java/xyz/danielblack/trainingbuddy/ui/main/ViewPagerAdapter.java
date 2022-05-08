package xyz.danielblack.trainingbuddy.ui.main;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class ViewPagerAdapter extends FragmentStateAdapter {
//    private final ArrayList<Fragment> fragmentArrayList = new ArrayList<>();
//    private final ArrayList<String> fragmentTitle = new ArrayList<>();

    public ViewPagerAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 1:
                return new HistoryFragment();
            case 2:
                return new WorkoutFragment();
            case 3:
                return new ExercisesFragment();
            case 4:
                return new MeasureFragment();
        }
        return new ProfileFragment();
    }

    @Override
    public int getItemCount() {
        return 5;
    }
    }
