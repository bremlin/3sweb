package com.ibcon.objects;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by s_shmakov on 01.02.2017.
 */
public class wActivity {

    private int objectId;
    private int wbsId;

    private String taskId;
    private String name;
    private String pvType;
    private String pv;


    public wActivity(ResultSet rs) throws SQLException {
        this.objectId = rs.getInt("task_id");
        this.wbsId = rs.getInt("wbs_id");
        this.taskId = rs.getString("task_code");
        this.name = rs.getString("task_name");
        this.pvType = rs.getString("act_code_value");
        this.pv = rs.getString("pv_value");
    }

    public int getObjectId() {
        return objectId;
    }

    public int getWbsId() {
        return wbsId;
    }

    public String getTaskId() {
        return taskId;
    }

    public String getName() {
        return name;
    }

    public String getPvType() {
        return pvType;
    }

    public String getPv() {
        return pv;
    }
}
