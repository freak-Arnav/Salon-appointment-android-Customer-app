package com.example.pro_q;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.pro_q.model.ShopUtilities;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class ShopUtilitiesFragment extends Fragment {


    public ShopUtilitiesFragment() {
        // Required empty public constructor
    }

    private RecyclerView shopUtilityRecyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_shop_utilities, container, false);
        shopUtilityRecyclerView = view.findViewById(R.id.shop_utilities_recyclerview);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(view.getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);


        shopUtilityRecyclerView.setLayoutManager(linearLayoutManager);

        List<ShopUtilities> shopUtilitiesList = new ArrayList<>();
        shopUtilitiesList.add(new ShopUtilities("Brands","LOreal, Lotus, Skin Co, O3+, GK"));
        shopUtilitiesList.add(new ShopUtilities("Services","Hair, Beauty, Hand&Feet, Makeup, Spa, Nail"));
        shopUtilitiesList.add(new ShopUtilities("Shop Timing","10:00AM-08:00PM"));
        shopUtilitiesList.add(new ShopUtilities("Wifi","Available"));
        shopUtilitiesList.add(new ShopUtilities("Working days","7 Days A Week"));
        shopUtilitiesList.add(new ShopUtilities("Shop Type","Mens"));
        shopUtilitiesList.add(new ShopUtilities("Shop Class","Elite"));


        shopUtilitiesList.add(new ShopUtilities("Shop Timing","10:00AM-08:00PM"));
        shopUtilitiesList.add(new ShopUtilities("Wifi","Available"));
        shopUtilitiesList.add(new ShopUtilities("Working days","7 Days A Week"));

        ShopUtilityAdapter shopUtilityAdapter = new ShopUtilityAdapter(shopUtilitiesList);
        shopUtilityRecyclerView.setAdapter(shopUtilityAdapter);
        shopUtilityAdapter.notifyDataSetChanged();


        return view;
    }

}
