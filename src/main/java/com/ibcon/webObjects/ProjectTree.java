package com.ibcon.webObjects;

import com.ibcon.objects.wEPS;
import com.ibcon.objects.wProject;
import com.vaadin.ui.Tree;

import java.util.HashMap;

/**
 * Created by s_shmakov on 17.02.2017.
 */
public class ProjectTree extends Tree{
    private int selectedProjectId;

    public ProjectTree(HashMap<Integer, wEPS> EPSMap, HashMap<Integer, wProject> projectMap) {
        for (wEPS eps : EPSMap.values()) {
            this.addItem(eps.getObjectId());
            this.setItemCaption(eps.getObjectId(), eps.getName());
        }
        for (wEPS eps : EPSMap.values()) {
            this.setParent(eps.getObjectId(), eps.getParentId());
        }

        for (wProject project : projectMap.values()) {
            this.addItem(project.getObjectId());
            this.setItemCaption(project.getObjectId(), project.getName());
            this.setChildrenAllowed(project.getObjectId(), false);
        }
        for (wProject project : projectMap.values()) {
            this.setParent(project.getObjectId(), project.getEPSObjectId());
        }

//        addItemClickListener();
    }

    public int getSelectedProjectId() {
        return selectedProjectId;
    }
}
