package com.findme.application.Models;

/**
 * Created by bonbona on 6/19/2016.
 */
public class NavItem {

        public String mTitle;
        public String mSubtitle;
    public int mIcon;
    public String mCount;
        boolean isCounterVisible = false;

        public NavItem(String title, String subtitle, int icon) {
            mTitle = title;
            mSubtitle = subtitle;
            mIcon = icon;
        }
    public NavItem(String title, String subtitle, int icon,boolean isCounterVisible, String count) {
        mTitle = title;
        mSubtitle = subtitle;
        mIcon = icon;
        this.isCounterVisible = isCounterVisible;
        mCount = count;
    }



    public String getmCount() {
        return mCount;
    }

    public void setmCount(String mCount) {
        this.mCount = mCount;
    }

    public boolean getCounterVisibility(){
        return this.isCounterVisible;
    }
    public void setCounterVisibility(boolean isCounterVisible){
        this.isCounterVisible = isCounterVisible;
    }

    }

