package com.imdox.wotips.tips;

public class UserTips {
    String userMobile,userEmail,userName, userDisplayName,tipCategory,tipTitle,tipContent,tipStatus,strCreatedDate;

    public String getStrCreatedDate() {
        return strCreatedDate;
    }

    public void setStrCreatedDate(String strCreatedDate) {
        this.strCreatedDate = strCreatedDate;
    }

    public UserTips(String userMobile, String userEmail, String userName, String userDisplayName, String tipCategory, String tipTitle, String tipContent, String tipStatus, String strCreatedDate) {
        this.userMobile = userMobile;
        this.userEmail = userEmail;

        this.userName = userName;
        this.userDisplayName = userDisplayName;
        this.tipCategory = tipCategory;
        this.tipTitle = tipTitle;
        this.tipContent = tipContent;
        this.tipStatus = tipStatus;
        this.strCreatedDate = strCreatedDate;
    }

    public String getUserMobile() {
        return userMobile;
    }

    public void setUserMobile(String userMobile) {
        this.userMobile = userMobile;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserDisplayName() {
        return userDisplayName;
    }

    public void setUserDisplayName(String userDisplayName) {
        this.userDisplayName = userDisplayName;
    }

    public String getTipCategory() {
        return tipCategory;
    }

    public void setTipCategory(String tipCategory) {
        this.tipCategory = tipCategory;
    }

    public String getTipTitle() {
        return tipTitle;
    }

    public void setTipTitle(String tipTitle) {
        this.tipTitle = tipTitle;
    }

    public String getTipContent() {
        return tipContent;
    }

    public void setTipContent(String tipContent) {
        this.tipContent = tipContent;
    }

    public String getTipStatus() {
        return tipStatus;
    }

    public void setTipStatus(String tipStatus) {
        this.tipStatus = tipStatus;
    }
}
