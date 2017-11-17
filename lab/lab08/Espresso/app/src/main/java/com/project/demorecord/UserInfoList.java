package com.project.demorecord;

import java.util.List;

public class UserInfoList {
    public List<UserInfo> getUserInfoList() {
        return userInfoList;
    }

    public void setUserInfoList(List<UserInfo> userInfoList) {
        this.userInfoList = userInfoList;
    }

    public void clearUserInfoList(List<UserInfo> userInfoList){
        if(!userInfoList.isEmpty()) userInfoList.clear();
    }

    private List<UserInfo> userInfoList;
}
