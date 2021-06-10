package com.example.k_app.fragmets;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.k_app.MainActivity;
import com.example.k_app.R;

public class ScanFragment extends Fragment implements View.OnClickListener {

    Button scanBtn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View contentView = inflater.inflate(R.layout.fragment_scan, container, false);
        scanBtn= contentView.findViewById(R.id.scanbtn);
        scanBtn.setOnClickListener(this);
        return contentView;
    }

    @Override
    public void onClick(View view) {
        ((MainActivity)getActivity()).scanCode();
    }
}