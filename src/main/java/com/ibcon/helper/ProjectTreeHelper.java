package com.ibcon.helper;

import com.ibcon.connection.MsSQLConnect;
import com.ibcon.objects.wEPS;
import com.ibcon.objects.wProject;
import com.ibcon.webObjects.ProjectTree;

import java.sql.*;
import java.util.HashMap;

/**
 * Created by s_shmakov on 01.03.2017.
 */
public class ProjectTreeHelper {
    private ProjectTree projectTree;

    public ProjectTreeHelper(int EPSObjectId) {
        HashMap<Integer, wEPS> EPSMap = new HashMap<>();
        HashMap<Integer, wProject> projectMap = new HashMap<>();

        loadEPS(EPSMap, EPSObjectId);
        loadProjects(projectMap, EPSMap);

        projectTree = new ProjectTree(EPSMap, projectMap);
    }

    private void loadEPS(HashMap EPSMap, int parentId) {
        String EPSSQL = "SELECT [id], [name], [code], [parent_id], [seq_num] FROM [Factage2].[dbo].[eps] where parent_id = " + parentId + " AND db_name ='IBCON'";

        try (Statement statement = MsSQLConnect.getConnection().createStatement()) {
            ResultSet rs = statement.executeQuery(EPSSQL);
            while (rs.next()) {
                wEPS eps = new wEPS(rs);
                EPSMap.put(eps.getObjectId(), eps);
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void loadProjects(HashMap projects, HashMap<Integer, wEPS> EPSs) {
        String projectsSQL = "SELECT [proj_id], [db_name], [proj_short_name], [proj_name], [eps_id], [proj_wbs_id] " +
                "FROM [Factage2].[dbo].[project] " +
                "WHERE db_name ='IBCON' AND [eps_id] in (";
        int i = 0;
        for (Integer id : EPSs.keySet()) {
            i++;
            if (i == EPSs.size()) {
                String idString = id + ")";
                projectsSQL += idString;
            } else {
                String idString = id + ",";
                projectsSQL += idString;
            }
        }

        try (Statement statement = MsSQLConnect.getConnection().createStatement()) {
            ResultSet rs = statement.executeQuery(projectsSQL);
            while (rs.next()) {
                wProject project = new wProject(rs);
                projects.put(project.getObjectId(), project);
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ProjectTree getProjectTree() {
        return projectTree;
    }


}
