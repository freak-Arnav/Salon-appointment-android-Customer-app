package com.example.pro_q;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class ShopDetailsAdapter extends FragmentPagerAdapter
{

    private int totalTabs;

    public ShopDetailsAdapter(@NonNull FragmentManager fm, int totalTabs)
    {
        super(fm);
        this.totalTabs = totalTabs;
    }

    @NonNull
    @Override
    public Fragment getItem(int i) {
        switch (i)
        {
            case 0:
                ShopDescriptionFragment shopDescriptionFragment = new ShopDescriptionFragment();
                return shopDescriptionFragment;

            case 1:
                ShopUtilitiesFragment shopUtilitiesFragment = new ShopUtilitiesFragment();
                return shopUtilitiesFragment;

            default:
                return null;

        }
    }

    @Override
    public int getCount() {
        return totalTabs;
    }
}