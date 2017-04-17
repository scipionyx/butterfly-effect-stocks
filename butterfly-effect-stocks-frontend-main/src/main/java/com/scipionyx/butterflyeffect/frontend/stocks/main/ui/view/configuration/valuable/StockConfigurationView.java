package com.scipionyx.butterflyeffect.frontend.stocks.main.ui.view.configuration.valuable;

import org.springframework.beans.factory.annotation.Configurable;

import com.scipionyx.butterflyeffect.frontend.core.ui.view.common.AbstractView;
import com.scipionyx.butterflyeffect.ui.view.MenuConfiguration;
import com.scipionyx.butterflyeffect.ui.view.MenuConfiguration.Position;
import com.scipionyx.butterflyeffect.ui.view.ViewConfiguration;
import com.vaadin.annotations.Title;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.VerticalLayout;

@UIScope
@SpringComponent(value = StockConfigurationView.VIEW_NAME)
@SpringView(name = StockConfigurationView.VIEW_NAME)

/**
 * 
 * @author Renato Mendes - rmendes@bottomline.com / renato.mendes.1123@gmail.com
 *
 */

//
@ViewConfiguration(title = "Stocks")
@MenuConfiguration(position = Position.TOP_RIGHT, label = "Stocks", group = "", order = 1, icon = VaadinIcons.MONEY_EXCHANGE, parent = com.scipionyx.butterflyeffect.frontend.configuration.ui.view.RootView.VIEW_NAME)

//
@Configurable(value = StockConfigurationView.VIEW_NAME)
@Title(value = StockConfigurationView.WINDOW_CAPTION)
public class StockConfigurationView extends AbstractView {

	private static final long serialVersionUID = 1L;
	public static final String VIEW_NAME = "butterfly-effect-frontend-stocks-main:configuration-root";
	protected static final String WINDOW_CAPTION = "Research";

	/**
	 * 
	 */
	@Override
	public void doBuildWorkArea(VerticalLayout workArea) throws Exception {

	}

	/**
	 * 
	 */
	@Override
	public void doEnter(ViewChangeEvent event) {

	}

}
