package com.example.pro_q;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pro_q.model.ShopServices;
import com.example.pro_q.model.ShopUtilities;

import java.util.ArrayList;
import java.util.List;

public class ShopServicesFragment extends Fragment
{
    public ShopServicesFragment()
    {
        // Required empty public constructor
    }

    private RecyclerView shopServicesRecyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.shop_detail_services_layout, container, false);
        shopServicesRecyclerView = view.findViewById(R.id.services_list);

        LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(view.getContext());
        linearLayoutManager1.setOrientation(LinearLayoutManager.VERTICAL);

        shopServicesRecyclerView.setLayoutManager(linearLayoutManager1);

        List<ShopServices> shopServicesList = new ArrayList<>();
        shopServicesList.add(new ShopServices("HairCut","500"));
        shopServicesList.add(new ShopServices("Shaving","100"));
        shopServicesList.add(new ShopServices("HairCut","500"));
        shopServicesList.add(new ShopServices("Shaving","100"));
        shopServicesList.add(new ShopServices("HairCut","500"));
        shopServicesList.add(new ShopServices("Shaving","100"));
        shopServicesList.add(new ShopServices("HairCut","500"));
        shopServicesList.add(new ShopServices("Shaving","100"));

        ShopServicesAdapter shopServicesAdapter = new ShopServicesAdapter(shopServicesList);
        shopServicesRecyclerView.setAdapter(shopServicesAdapter);
        shopServicesAdapter.notifyDataSetChanged();


        return view;
    }

}
