package com.ibcon.webObjects;

import com.ibcon.objects.wActivity;
import com.ibcon.objects.wProject;
import com.ibcon.objects.wWBS;
import com.vaadin.data.Item;
import com.vaadin.ui.TreeTable;

/**
 * Created by SSS on 02.02.2017.
 */
public class ActivityTreeTable extends TreeTable {
    private wProject wproject;

    public ActivityTreeTable() {
        this.addContainerProperty("ID", String.class, "");
        this.addContainerProperty("Наименование", String.class, "");
        this.addContainerProperty("ФО", String.class, "");
        this.addContainerProperty("PV", String.class, "");
        setColumnWidth("PV", 25);
        setColumnWidth("ФО", 30);

        this.setWidth("60em");

        this.setPageLength(this.size());

        setCellStyleGenerator((CellStyleGenerator) (table, itemId, propertyId) -> {
            if (propertyId == null) {
                // Styling for row
                Item item = table.getItem(itemId);
                if (wproject.getWbsMap().containsKey(itemId)) {
                    return "highlight-WBS";
                } else {
                    return "highlight-activity";
                }
            } else {
                // styling for column propertyId
                return null;
            }
        });


    }

    public void setData(wProject project) {
        this.wproject = project;

        for (wWBS wbs : project.getWbsMap().values()) {
            this.addItem(new Object[]{wbs.getId(), wbs.getName(), "", ""}, wbs.getObjectId());
        }
        for (wActivity activity : project.getActivities().values()) {
            this.addItem(new Object[]{activity.getTaskId(), activity.getName(), activity.getPv(), activity.getPvType()}, activity.getObjectId());
            this.setChildrenAllowed(activity.getObjectId(), false);
        }
        for (wWBS wbs : project.getWbsMap().values()) {
            if (wbs.getParentId() > 0)
            this.setParent(wbs.getObjectId(), wbs.getParentId());
        }
        for (wActivity activity : project.getActivities().values()) {
            this.setParent(activity.getObjectId(), activity.getWbsId());
        }

//        for (Object itemId : this.getItemIds()) {
//            if (!this.hasChildren(itemId)) this.setChildrenAllowed(itemId, true);
//        }

    }
}
