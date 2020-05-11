package com.haitao.www.myformer.ui.ui_common.ModuleTest.timeLine;

/**
 * Created by Administrator on 2017/12/12 0012.
 */

public class TraceData {
    //到达时间
    private String time;
    //到达地点
    private String station;

    public TraceData(String time, String station) {
        this.time = time;
        this.station = station;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getStation() {
        return station;
    }

    public void setStation(String station) {
        this.station = station;
    }
}
