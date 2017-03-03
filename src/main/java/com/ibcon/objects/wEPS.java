package com.ibcon.objects;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by s_shmakov on 01.02.2017.
 */
public class wEPS {

    private int objectId;
    private int parentId;
    private int seq;

    private String id;
    private String name;

    public wEPS(ResultSet rs) throws SQLException {
        this.objectId = rs.getInt("id");
        this.parentId = rs.getInt("parent_id");
        this.id = rs.getString("code");
        this.name = rs.getString("name");
        this.seq = rs.getInt("seq_num");
    }

    public int getObjectId() {
        return objectId;
    }

    public int getParentId() {
        return parentId;
    }

    public int getSeq() {
        return seq;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
