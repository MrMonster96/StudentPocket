package com.example.studentpocket;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class IntroAdapter extends FragmentPagerAdapter {
    public IntroAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {

       switch (position){
           case 0:
               return new FirstSlide();
           case 1:
               return new SecondSlide();
           case 2:
               return new ThirdSlide();
           default:
               return null;
       }




    }

    @Override
    public int getCount() {
        return 3;
    }
}
