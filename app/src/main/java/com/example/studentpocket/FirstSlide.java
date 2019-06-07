package com.example.studentpocket;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class FirstSlide extends Fragment {
    TextView next;
    ViewPager viewPager;

    public FirstSlide() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

      View view= inflater.inflate(R.layout.fragment_first_slide, container, false);


   //Initialize Viewpager from main Intro slide Activity
    viewPager =getActivity().findViewById(R.id.viewpager);
    next=view.findViewById(R.id.next_button);

    next.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            viewPager.setCurrentItem(1);


        }
    });

    return view;
    }

}
