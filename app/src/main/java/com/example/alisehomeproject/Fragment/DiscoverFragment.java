package com.example.alisehomeproject.Fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.alisehomeproject.Adapter.InterestedyouAdapter;
import com.example.alisehomeproject.Adapter.PremiumLIkeAdapter;
import com.example.alisehomeproject.R;


public class DiscoverFragment extends Fragment {


    RecyclerView recyclerView_interested;
    RecyclerView recyclerView_premium;
    View view = null;
    Context context;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view =inflater.inflate(R.layout.fragment_discover, container, false);
        context=getActivity();
        initializeViewElements(view);
        return view;
    }
    private void initializeViewElements(View view) {
        recyclerView_interested=(RecyclerView)view.findViewById(R.id.recyclerView_interested);
        recyclerView_premium=(RecyclerView)view.findViewById(R.id.recyclerView_premium);
        recyclerView_interested.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, true));
        recyclerView_interested.setHasFixedSize(true);
         InterestedyouAdapter interestedyouAdapter = new InterestedyouAdapter(context);
        recyclerView_interested.setAdapter(interestedyouAdapter);

        recyclerView_premium.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, true));
        recyclerView_premium.setHasFixedSize(true);
        PremiumLIkeAdapter premiumLIkeAdapter = new PremiumLIkeAdapter(context);
        recyclerView_premium.setAdapter(premiumLIkeAdapter);

    }
}