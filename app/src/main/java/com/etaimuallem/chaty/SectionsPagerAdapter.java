package com.etaimuallem.chaty;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by etaimuallem on 09/07/2017.
 */

public class
SectionsPagerAdapter extends FragmentPagerAdapter {
    public SectionsPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new WelcomeFragment();
            case 1:
                return ChatFragment.newInstance("General");
            case 2:
                return ChatFragment.newInstance("Sports");
            case 3:
                return ChatFragment.newInstance("News");
            case 4:
                return ChatFragment.newInstance("Movies & Series");
            case 5:
                return ChatFragment.newInstance("Game of Thrones");
            case 6:
                return ChatFragment.newInstance("Science");
            case 7:
                return ChatFragment.newInstance("Astrology");
            case 8:
                return ChatFragment.newInstance("Religions");
            case 9:
                return ChatFragment.newInstance("Gossip");
            case 10:
                return ChatFragment.newInstance("Gaming");
            case 11:
                return ChatFragment.newInstance("Fashion");
            case 12:
                return ChatFragment.newInstance("Food");
            case 13:
                return ChatFragment.newInstance("Relationships & Sex");
            case 14:
                return ChatFragment.newInstance("Cars & Bikes");
            case 15:
                return ChatFragment.newInstance("Real Estate");
            case 16:
                return ChatFragment.newInstance("Health & Fitness");
            case 17:
                return ChatFragment.newInstance("Culture");
            case 18:
                return ChatFragment.newInstance("Economy");
            case 19:
                return ChatFragment.newInstance("Politics");
            case 20:
                return ChatFragment.newInstance("Technology & Gadgets");
        }
        return null;
    }

    @Override
    public int getCount() {
        // Show 3 total pages.
        return 21;
        //TODO 21 pages
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Welcome";
            case 1:
                return "General";
            case 2:
                return "Sports";
            case 3:
                return "News";
            case 4:
                return "Movies & Series";
            case 5:
                return "Game of Thrones";
            case 6:
                return "Science";
            case 7:
                return "Astrology";
            case 8:
                return "Religions";
            case 9:
                return "Gossip";
            case 10:
                return "Gaming";
            case 11:
                return "Fashion";
            case 12:
                return "Food";
            case 13:
                return "Relationships & Sex";
            case 14:
                return "Cars & Bikes";
            case 15:
                return "Real Estate";
            case 16:
                return "Health & Fitness";
            case 17:
                return "Culture";
            case 18:
                return "Economy";
            case 19:
                return "Politics";
            case 20:
                return "Technology & Gadgets";
        }
        return null;
    }
}
