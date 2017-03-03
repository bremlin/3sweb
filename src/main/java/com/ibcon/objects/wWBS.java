package com.ibcon.objects;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by s_shmakov on 01.02.2017.
 */
public class wWBS {

    private int objectId;
    private int parentId;
    private int seqNum;

    private String id;
    private String name;

    public wWBS(ResultSet rs) throws SQLException {
        this.objectId = rs.getInt("wbs_id");
        this.parentId = rs.getInt("wbs_parent_id");
        this.seqNum = rs.getInt("seq_num");
        this.id = rs.getString("wbs_short_name");
        this.name = rs.getString("wbs_name");
    }

    public int getObjectId() {
        return objectId;
    }

    public int getParentId() {
        return parentId;
    }

    public int getSeqNum() {
        return seqNum;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
