package com.ibcon;

import com.ibcon.objects.wEPS;
import com.ibcon.objects.wProject;
import com.ibcon.service.LoadEPSTitul;
import com.ibcon.service.exampleConnect;
import com.ibcon.webObjects.ActivityTreeTable;
import com.ibcon.webObjects.EPSTitulButton;
import com.ibcon.webObjects.SmetTreeTable;
import com.ibcon.webObjects.SubWindowProjectList;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.Sizeable;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.*;

import javax.servlet.annotation.WebServlet;

/**
 * This UI is the application entry point. A UI may either represent a browser window 
 * (or tab) or some part of a html page where a Vaadin application is embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is intended to be 
 * overridden to add component to the user interface and initialize non-component functionality.
 */
@Theme("mytheme")
public class MyUI extends UI {

    public static wProject wproject;
    public static ActivityTreeTable activityTreeTable;
    public static SmetTreeTable smetTreeTable;
    public static VerticalLayout mainLayout;

    @Override
    protected void init(VaadinRequest vaadinRequest) {

        GridLayout grid = new GridLayout(5, 5);
        grid.setWidth(1100, Sizeable.UNITS_PIXELS);
        grid.setHeight(200, Sizeable.UNITS_PIXELS);

        final HorizontalLayout layout = new HorizontalLayout();
        
        LoadEPSTitul loadEPSTitul = new LoadEPSTitul("IBCON");

        int i = 0;
        int j = 0;
        for (wEPS EPS : loadEPSTitul.getEpsList()) {
            EPSTitulButton epsTitulButton = new EPSTitulButton(EPS.getId(), EPS.getObjectId());
            epsTitulButton.addClickListener(event -> {
                openProject(EPS.getObjectId());
            });
            epsTitulButton.addStyleName("eps-titul-button");
            epsTitulButton.setWidth(180, AbstractComponent.UNITS_PIXELS);
            grid.addComponent(epsTitulButton, i, j);
            i++;
            if (i == 4) {
                i = 0;
                j++;
            }
//            layout.addComponent(new EPSTitulButton(EPS.getId()));
        }

        exampleConnect exampleConnect = new exampleConnect();
        final wProject project = exampleConnect.getProjectList().get(73424);
        project.loadProjectData();

        activityTreeTable = new ActivityTreeTable();
        activityTreeTable.setData(project);

        layout.addComponent(grid);
        layout.setComponentAlignment(grid, Alignment.MIDDLE_CENTER);
        layout.addComponent(activityTreeTable);
        layout.setSizeFull();
        setContent(layout);
    }

    public void openProject(int titulEPS) {
        SubWindowProjectList sub = new SubWindowProjectList(titulEPS);
        UI.getCurrent().addWindow(sub);
    }

    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
}
