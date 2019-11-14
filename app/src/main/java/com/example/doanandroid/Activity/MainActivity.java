package com.example.doanandroid.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.example.doanandroid.Adapter.MainViewPagerAdapter;
import com.example.doanandroid.Fragment.fragment_timkiem;
import com.example.doanandroid.Fragment.fragment_trangchu;
import com.example.doanandroid.R;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {
    TabLayout tabLayout;
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        anhxa();
        init();
    }

    private void init() {
        MainViewPagerAdapter mainViewPagerAdapter=new MainViewPagerAdapter(getSupportFragmentManager());
        mainViewPagerAdapter.addFragment(new fragment_trangchu(),"Trang chủ");
        mainViewPagerAdapter.addFragment(new fragment_timkiem(),"Tìm kiếm");
        viewPager.setAdapter(mainViewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setIcon(R.drawable.icontrangchu );
        tabLayout.getTabAt(1).setIcon(R.drawable.iconsearch);



    }

    private void anhxa() {
        tabLayout=findViewById(R.id.myTabLayout);
        viewPager=findViewById(R.id.myViewPager);
        
    }
}
