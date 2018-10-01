package com.gkdtkd562.firebase_auth;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import com.gkdtkd562.firebase_auth.fragment.JoinFragment;
import com.gkdtkd562.firebase_auth.fragment.LoginFragment;
import com.gkdtkd562.firebase_auth.fragment.StartFragment;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    ViewPager startViewPager ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startViewPager = findViewById(R.id.start_view_pager);
        startViewPager.setAdapter(new FragmentStatePagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int i) {
                return new StartFragment();
            }

            @Override
            public int getCount() {
                return 1;
            }
        });

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    // MainActivity가 종료될때 호출되는 method
    @Override
    protected void onDestroy() {
        super.onDestroy();

        // 앱이 종료될 때 자동으로 로그인 정보를 해제
        FirebaseAuth.getInstance().signOut();

    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.start_navigation_home:
                    return true;
                case R.id.start_navigation_join:
                    startViewPager.setAdapter(new FragmentStatePagerAdapter(getSupportFragmentManager()) {
                        @Override
                        public Fragment getItem(int position) {
                            return new JoinFragment();
                        }

                        @Override
                        public int getCount() {
                            return 1;
                        }
                    });
                    return true;
                case R.id.start_navigation_login:
                    startViewPager.setAdapter(new FragmentStatePagerAdapter(getSupportFragmentManager()) {
                        @Override
                        public Fragment getItem(int position) {
                            return new LoginFragment();
                        }

                        @Override
                        public int getCount() {
                            return 1;
                        }
                    });
                    return true;
            }
            return false;
        }
    };

}
