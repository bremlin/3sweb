package com.ibcon.webObjects;

import com.vaadin.ui.Button;

/**
 * Created by s_shmakov on 13.02.2017.
 */
public class EPSTitulButton extends Button {
    private int projectObjectId;

    public EPSTitulButton(String name, int epsObjectId) {
        setCaption(name);
        addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(ClickEvent clickEvent) {

            }
        });
    }


}
