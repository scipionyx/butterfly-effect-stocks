package com.scipionyx.butterflyeffect.frontend.stocks.main.ui.view.strategyhub;

import com.scipionyx.butterflyeffect.api.stocks.model.Strategy;
import com.scipionyx.butterflyeffect.frontend.core.ui.view.common.ScipionyxPanel;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.ui.Button;
import com.vaadin.ui.Grid;
import com.vaadin.ui.themes.ValoTheme;

public class MyStrategies extends ScipionyxPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	public void doBuild() {

		setCaption("My Strategies");

		Grid<Strategy> grid = new Grid<>();
		grid.setSizeFull();
		grid.setStyleName(ValoTheme.TABLE_BORDERLESS);

		// grid.addColumn("Name", new TextRenderer());
		// grid.addColumn("Description", new TextRenderer());

		addBodyComponent(grid);

		addButton(VaadinIcons.PLUS, new Button("A", event -> addNew()));

	}

	private void addNew() {

	}

}
