package com.scipionyx.butterflyeffect.frontend.stocks.main.ui.view.strategyhub;

import com.scipionyx.butterflyeffect.frontend.core.ui.view.common.ScipionyxPanel;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.ui.Button;

public class MyStrategyRank extends ScipionyxPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	public void doBuild() {
		setCaption("Rank");
		addButton(VaadinIcons.REFRESH, new Button("", event -> refresh()));
	}

	/**
	 * 
	 */
	private void refresh() {

	}

}
