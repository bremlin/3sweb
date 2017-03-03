//package com.ibcon;
//
//import com.ibcon.objects.wEPS;
//import com.ibcon.service.LoadEPSTitul;
//import com.ibcon.webObjects.EPSTitulButton;
//import com.ibcon.webObjects.SubWindowProjectList;
//import com.vaadin.annotations.Theme;
//import com.vaadin.annotations.VaadinServletConfiguration;
//import com.vaadin.server.Sizeable;
//import com.vaadin.server.VaadinRequest;
//import com.vaadin.server.VaadinServlet;
//import com.vaadin.ui.*;
//
//import javax.servlet.annotation.WebServlet;
//
///**
// * Created by s_shmakov on 16.02.2017.
// */
//@Theme("mytheme")
//public class OpenProject extends UI {
//
//    private Window projectTreeWindow;
//
//    @Override
//    protected void init(VaadinRequest vaadinRequest) {
//
//        GridLayout grid = new GridLayout(5, 5);
//        grid.setWidth(1100, Sizeable.UNITS_PIXELS);
//        grid.setHeight(200, Sizeable.UNITS_PIXELS);
//
//        final HorizontalLayout layout = new HorizontalLayout();
//
//        LoadEPSTitul loadEPSTitul = new LoadEPSTitul("IBCON");
//
//        int i = 0;
//        int j = 0;
//        for (final wEPS EPS : loadEPSTitul.getEpsList()) {
//            EPSTitulButton epsTitulButton = new EPSTitulButton(EPS.getId(), EPS.getObjectId());
//            epsTitulButton.addClickListener(clickEvent -> {
//                SubWindowProjectList sub = new SubWindowProjectList(EPS.getObjectId());
//                UI.getCurrent().addWindow(sub);
//            });
//
//            epsTitulButton.addStyleName("eps-titul-button");
//            epsTitulButton.setWidth(180, AbstractComponent.UNITS_PIXELS);
//            grid.addComponent(epsTitulButton, i, j);
//            i++;
//            if (i == 4) {
//                i = 0;
//                j++;
//            }
////            layout.addComponent(new EPSTitulButton(EPS.getId()));
//        }
//
//        layout.addComponent(grid);
//        layout.setComponentAlignment(grid, Alignment.MIDDLE_CENTER);
//        layout.setSizeFull();
//        setContent(layout);
//    }
//
//    private void openProjectTreeWindow(int parentId) {
//        System.out.println("buttOk");
//        projectTreeWindow = new Window("Выберите проект");
//        VerticalLayout verticalLayout = new VerticalLayout();
//        verticalLayout.setMargin(true);
//        projectTreeWindow.setContent(verticalLayout);
//        verticalLayout.addComponent(new Label("Meatball sub"));
//        projectTreeWindow.center();
//        addWindow(projectTreeWindow);
//
//    }
//
//        @WebServlet(urlPatterns = "/openproject", name = "OpenProjectServlet", asyncSupported = true)
//        @VaadinServletConfiguration(ui = OpenProject.class, productionMode = false)
//        public static class OpenProjectServlet extends VaadinServlet {
//    }
//}
