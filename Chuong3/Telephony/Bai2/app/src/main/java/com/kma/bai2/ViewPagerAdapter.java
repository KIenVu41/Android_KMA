package com.kma.bai2;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.kma.bai2.fragment.CallFragment;
import com.kma.bai2.fragment.ContactFragment;
import com.kma.bai2.fragment.MissCallFragment;
import com.kma.bai2.model.CallLog;
import com.kma.bai2.model.Contact;

import java.util.List;

public class ViewPagerAdapter extends FragmentStateAdapter {

    List<Contact> contactList;
    List<CallLog> callLogs;

    public ViewPagerAdapter(@NonNull FragmentActivity fragmentActivity, List<Contact> contactList, List<CallLog> callLogs) {
        super(fragmentActivity);
        this.contactList = contactList;
        this.callLogs = callLogs;
    }

    // return fragments at every position
    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new ContactFragment(contactList);
            case 1:
                return new CallFragment(callLogs);
            case 2:
                return new MissCallFragment();
        }
        return new ContactFragment(contactList);
    }


    @Override
    public int getItemCount() {
        return 3;
    }
}
