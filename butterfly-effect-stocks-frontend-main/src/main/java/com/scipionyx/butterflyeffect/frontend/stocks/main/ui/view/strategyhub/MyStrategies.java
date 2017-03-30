package com.scipionyx.butterflyeffect.frontend.stocks.main.ui.view.strategyhub;

import com.vaadin.ui.Grid;
import com.vaadin.ui.Panel;
import com.vaadin.ui.renderers.TextRenderer;

public class MyStrategies extends Panel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	public MyStrategies build() {

		this.setCaption("My Strategies");

		Grid<Object> grid = new Grid<>();

		grid.addColumn("Test", new TextRenderer());
		grid.addColumn("Test", new TextRenderer());

		this.setContent(grid);

		return this;
	}

}
