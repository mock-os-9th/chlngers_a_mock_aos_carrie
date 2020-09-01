package com.Carrie.challengersproject.src.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.Carrie.challengersproject.BaseActivity;
import com.Carrie.challengersproject.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class SearchFragment extends Fragment {
    ViewGroup viewGroup;
    FloatingActionButton fab;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewGroup = (ViewGroup) inflater.inflate(R.layout.search_fragment,container,false);
        return viewGroup;

    }
}
