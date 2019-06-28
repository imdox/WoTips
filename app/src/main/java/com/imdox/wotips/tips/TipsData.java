package com.imdox.wotips.tips;

public class TipsData {
    private  String strHeading,strValue;

    public String getStrHeading() {
        return strHeading;
    }

    public void setStrHeading(String strHeading) {
        this.strHeading = strHeading;
    }

    public String getStrValue() {
        return strValue;
    }

    public void setStrValue(String strValue) {
        this.strValue = strValue;
    }

    public TipsData(String strHeading, String strValue) {
        this.strHeading = strHeading;
        this.strValue = strValue;

    }
}
