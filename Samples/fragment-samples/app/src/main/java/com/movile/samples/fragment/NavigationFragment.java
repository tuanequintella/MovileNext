package com.movile.samples.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.movile.samples.R;

public class NavigationFragment extends Fragment {

    private OnNavigationOptionSelected mListener;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnNavigationOptionSelected) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException("Interface not implemented");
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.navigation_fragment, container, true);

        view.findViewById(R.id.navigation_first_content_action).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.onFirstOptionSelected();
            }
        });
        view.findViewById(R.id.navigation_second_content_action).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.onSecondOptionSelected();
            }
        });
        view.findViewById(R.id.navigation_clear_action).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.onClearOptionSelected();
            }
        });

        return view;
    }

    public interface OnNavigationOptionSelected {
        void onFirstOptionSelected();

        void onSecondOptionSelected();

        void onClearOptionSelected();
    }


}
