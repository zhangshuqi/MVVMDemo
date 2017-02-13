package com.qxinli.community;

/**
 * Created by Administrator on 2017/2/13 0013.
 */
public class ListViewHolder2 {
    private String name ="张小胖";
    private String time="20";

    public ListViewHolder2(String name, String time) {
        this.name = name;
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
