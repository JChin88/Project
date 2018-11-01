package edu.gatech.cs2340.cs2340project.presentation.view.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import edu.gatech.cs2340.cs2340project.R;
import edu.gatech.cs2340.cs2340project.mvc.controller.LocationList;

public class HomeFragment extends Fragment {

    private Button moveToLocationListBtn;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        moveToLocationListBtn = view.findViewById(R.id.move_to_location_btn);
        moveToLocationListBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), LocationList.class);
                startActivity(intent);
            }
        });
    }
}
