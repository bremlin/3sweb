package com.ibcon.service;

import com.ibcon.connection.MsSQLConnect;
import com.ibcon.objects.wProject;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

/**
 * Created by s_shmakov on 02.02.2017.
 */
public class exampleConnect {

    private HashMap<Integer, wProject> projectList;

    public exampleConnect() {
        String projSQL = "SELECT * FROM [project] where proj_id = 73424";

        try (Statement statement = MsSQLConnect.getConnection().createStatement()) {
            ResultSet rs = statement.executeQuery(projSQL);
            projectList = new HashMap<>();
            while (rs.next()) {
                wProject wproject = new wProject(rs);
                projectList.put(wproject.getObjectId(), wproject);
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public HashMap<Integer, wProject> getProjectList() {
        return projectList;
    }

}
