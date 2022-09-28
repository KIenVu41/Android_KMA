package com.kma.bai1;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.kma.bai1.fragments.ManageFragment;
import com.kma.bai1.fragments.SmsFragment;

import java.util.List;

public class ViewPagerAdapter extends FragmentStateAdapter {
    List<Message> messageList;
    public ViewPagerAdapter(@NonNull FragmentActivity fragmentActivity, List<Message> messageList) {
        super(fragmentActivity);
        this.messageList = messageList;
    }

    // return fragments at every position
    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new SmsFragment();
            case 1:
                return new ManageFragment(messageList);
        }
        return new SmsFragment();
    }


    @Override
    public int getItemCount() {
        return 2;
    }
}