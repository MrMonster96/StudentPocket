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
public class SecondSlide extends Fragment {
    TextView next,back;
    ViewPager viewPager;

    public SecondSlide() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_second_slide, container, false);
    next=view.findViewById(R.id.next_button);
    back=view.findViewById(R.id.back_button);

    viewPager =getActivity().findViewById(R.id.viewpager);

    next.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            viewPager.setCurrentItem(2);
        }
    });
    back.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            viewPager.setCurrentItem(0);
        }
    });







    return view;
    }

}
