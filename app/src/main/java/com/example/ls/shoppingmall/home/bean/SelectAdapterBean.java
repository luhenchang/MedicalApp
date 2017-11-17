package com.example.ls.shoppingmall.home.bean;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by ls on 2017/11/12.
 */

public class SelectAdapterBean {
    public String BodyName;
    public String[] starry=new String[]{};

    public String getBodyName() {
        return BodyName;
    }

    public void setBodyName(String bodyName) {
        BodyName = bodyName;
    }

    public String[] getStarry() {
        return starry;
    }

    public void setStarry(String[] starry) {
        this.starry = starry;
    }

    @Override
    public String toString() {
        return "SelectAdapterBean{" +
                "BodyName='" + BodyName + '\'' +
                ", starry=" + Arrays.toString(starry) +
                '}';
    }
}
