package com.ibcon.objects;

import com.ibcon.connection.MsSQLConnect;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

/**
 * Created by s_shmakov on 01.02.2017.
 */
public class wProject {

    private int objectId;
    private String id;
    private String name;
    private String dbName;
    private int EPSObjectId;
    private int WBSObjectId;

    private HashMap<Integer, wActivity> activities = new HashMap<>();
    private HashMap<Integer, wWBS> wbsMap = new HashMap<>();

    public wProject(ResultSet rs) throws SQLException {
        this.objectId = rs.getInt("proj_id");
        this.dbName = rs.getString("db_name");
        this.id = rs.getString("proj_short_name");
        this.name = rs.getString("proj_name");
        this.EPSObjectId = rs.getInt("eps_id");
        this.WBSObjectId = rs.getInt("proj_wbs_id");
    }

    public void loadProjectData() {
        loadWBSFromSQL();
        loadActivityFromSQL();
    }

    private void loadWBSFromSQL() {
        String WBSSQL = "SELECT wbs_id, wbs_short_name, wbs_name, wbs_parent_id, seq_num " +
                "FROM [wbs] " +
                "WHERE proj_id = " + objectId + " AND db_name = '" + dbName + "'";

        try (Statement statement = MsSQLConnect.getConnection().createStatement()) {
            ResultSet rs = statement.executeQuery(WBSSQL);
            wbsMap = new HashMap<>();
            while (rs.next()) {
                wWBS wbs = new wWBS(rs);
                wbsMap.put(wbs.getObjectId(), wbs);
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void loadActivityFromSQL() {
        String ActivitySQL = "SELECT task_id, task_code, task_name, wbs_id, act_code_value, pv_value " +
                "FROM [task] " +
                "WHERE proj_id = " + objectId + " AND db_name = '" + dbName + "'";

        try (Statement statement = MsSQLConnect.getConnection().createStatement()) {
            ResultSet rs = statement.executeQuery(ActivitySQL);
            activities = new HashMap<>();
            while (rs.next()) {
                wActivity activity = new wActivity(rs);
                activities.put(activity.getObjectId(), activity);
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int getObjectId() {
        return objectId;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getEPSObjectId() {
        return EPSObjectId;
    }

    public int getWBSObjectId() {
        return WBSObjectId;
    }

    public HashMap<Integer, wActivity> getActivities() {
        return activities;
    }

    public HashMap<Integer, wWBS> getWbsMap() {
        return wbsMap;
    }
}
