package com.ibcon.service;

import com.ibcon.connection.MsSQLConnect;
import com.ibcon.objects.wEPS;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by s_shmakov on 13.02.2017.
 */
public class LoadEPSTitul {

    private ArrayList<wEPS> epsList = new ArrayList<>();

    public LoadEPSTitul(String dbName) {
        String projSQL = "SELECT [id], [name], [code], [parent_id], [seq_num] FROM [Factage2].[dbo].[eps] where parent_id = 0 AND db_name ='" + dbName + "'";

        try (Statement statement = MsSQLConnect.getConnection().createStatement()) {
            ResultSet rs = statement.executeQuery(projSQL);
            while (rs.next()) {
                wEPS eps = new wEPS(rs);
                epsList.add(eps);
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<wEPS> getEpsList() {
        Collections.sort(epsList, new Comparator<wEPS>() {
            @Override
            public int compare(wEPS EPS1, wEPS EPS2) {
                return Integer.compare(EPS1.getSeq(), EPS2.getSeq());
            }
        });
        return epsList;
    }

}
