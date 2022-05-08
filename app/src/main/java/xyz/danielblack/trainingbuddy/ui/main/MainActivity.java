package xyz.danielblack.trainingbuddy.ui.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.widget.ViewPager2;
//<a href="https://www.flaticon.com/free-icons/barbell" title="barbell icons">Barbell icons created by DinosoftLabs - Flaticon</a>
//<a href="https://www.flaticon.com/free-icons/ruler" title="ruler icons">Ruler icons created by Kiranshastry - Flaticon</a>
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;

import xyz.danielblack.trainingbuddy.R;

public class MainActivity extends AppCompatActivity {

    private ViewPager2 viewPager;
    private TabLayout tabLayout;
    private ViewPagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager = findViewById(R.id.viewPager);
        tabLayout = findViewById(R.id.tabLayout);

        FragmentManager fragmentManager = getSupportFragmentManager();
        adapter = new ViewPagerAdapter(fragmentManager, getLifecycle());
        viewPager.setAdapter(adapter);
        tabLayout.addTab(tabLayout.newTab().setText("Profile").setIcon(R.drawable.ic_profile));
        tabLayout.addTab(tabLayout.newTab().setText("History").setIcon(R.drawable.ic_history));
        tabLayout.addTab(tabLayout.newTab().setText("Workout").setIcon(R.drawable.ic_workout));
        tabLayout.addTab(tabLayout.newTab().setText("Exercise").setIcon(R.drawable.ic_exercises));
        tabLayout.addTab(tabLayout.newTab().setText("Measure").setIcon(R.drawable.ic_measure));

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                tabLayout.selectTab(tabLayout.getTabAt(position));
            }
        });
    }
}