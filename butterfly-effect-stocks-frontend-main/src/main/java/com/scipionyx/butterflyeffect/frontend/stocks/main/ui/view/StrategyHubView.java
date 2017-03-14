package com.scipionyx.butterflyeffect.frontend.stocks.main.ui.view;

import org.springframework.beans.factory.annotation.Configurable;

import com.scipionyx.butterflyeffect.frontend.core.ui.view.common.AbstractView;
import com.scipionyx.butterflyeffect.ui.view.MenuConfiguration;
import com.scipionyx.butterflyeffect.ui.view.ViewConfiguration;
import com.vaadin.annotations.Title;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.VerticalLayout;

/**
 * 
 * @author rmendes
 *
 */

@UIScope
@SpringComponent(value = StrategyHubView.VIEW_NAME)
@SpringView(name = StrategyHubView.VIEW_NAME)

//
@ViewConfiguration(title = "Strategy Hub")
@MenuConfiguration(position = com.scipionyx.butterflyeffect.ui.view.MenuConfiguration.Position.TOP_MAIN, label = "Strategy", group = "", order = 1, icon = VaadinIcons.MONEY_EXCHANGE)

//
@Configurable(value = PortfolioView.VIEW_NAME)
@Title(value = PortfolioView.WINDOW_CAPTION)
public class StrategyHubView extends AbstractView {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static final String VIEW_NAME = "butterfly-effect-frontend-stocks-main:strategy-hub";

	protected static final String WINDOW_CAPTION = "Portifolio Management";

	/**
	 * 
	 */
	@Override
	public void doBuildWorkArea(VerticalLayout workArea) throws Exception {

		// My Strategies
		// Strategy rank
		// My Friends Strategies
		// Bookmark
		// Search

	}

	/**
	 * 
	 */
	@Override
	public void doEnter(ViewChangeEvent event) {
		// TODO Auto-generated method stub

	}

}
