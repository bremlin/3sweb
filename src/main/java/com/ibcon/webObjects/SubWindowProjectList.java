package com.ibcon.webObjects;

import com.ibcon.helper.ProjectTreeHelper;
import com.ibcon.objects.wProject;
import com.vaadin.ui.Button;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

/**
 * Created by s_shmakov on 01.03.2017.
 */
public class SubWindowProjectList extends Window {
    private wProject selectedProject;
    public SubWindowProjectList(int EPSObjectId) {
        super("Выберите проект");
        center();
        setClosable(false);

        ProjectTreeHelper pth = new ProjectTreeHelper(EPSObjectId);

        ProjectTree projectTree = pth.getProjectTree();

        Button openProject = new Button("Открыть");
        openProject.addClickListener(clickEvent -> {
//            selectedProject = projectTree.get
        });

        VerticalLayout verticalLayout = new VerticalLayout();
        verticalLayout.addComponent(projectTree);
        verticalLayout.addComponent(new Button(String.valueOf("Закрыть"), event -> close()));

        setContent(verticalLayout);
    }

    public wProject getSelectedProject() {
        return selectedProject;
    }
}
