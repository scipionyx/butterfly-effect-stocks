package com.scipionyx.butterflyeffect.frontend.stocks.main.ui.view.research;

import org.springframework.beans.factory.annotation.Configurable;

import com.scipionyx.butterflyeffect.api.stocks.model.research.Research;
import com.scipionyx.butterflyeffect.frontend.core.ui.view.common.AbstractView;
import com.scipionyx.butterflyeffect.ui.view.MenuConfiguration;
import com.scipionyx.butterflyeffect.ui.view.ViewConfiguration;
import com.vaadin.annotations.Title;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Button;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

@UIScope
@SpringComponent(value = ResearchView.VIEW_NAME)
@SpringView(name = ResearchView.VIEW_NAME)

/**
 * 
 * @author Renato Mendes - rmendes@bottomline.com / renato.mendes.1123@gmail.com
 *
 */

//
@ViewConfiguration(title = "Research")
@MenuConfiguration(position = com.scipionyx.butterflyeffect.ui.view.MenuConfiguration.Position.TOP_MAIN, label = "Research", group = "", order = 1, icon = VaadinIcons.CHART)

//
@Configurable(value = ResearchView.VIEW_NAME)
@Title(value = ResearchView.WINDOW_CAPTION)
public class ResearchView extends AbstractView {

	private static final long serialVersionUID = 1L;
	public static final String VIEW_NAME = "butterfly-effect-frontend-stocks-main:research-hub";
	protected static final String WINDOW_CAPTION = "Research";

	private ResearchTabSheet tabSheet;

	/**
	 * 
	 */
	@Override
	public void doBuildWorkArea(VerticalLayout workArea) throws Exception {

		addButton(ValoTheme.BUTTON_FRIENDLY, new Button("Add", event -> addNewResearch()));

		tabSheet = new ResearchTabSheet();
		tabSheet.build();

		workArea.addComponents(tabSheet);

	}

	/**
	 * 
	 */
	private void addNewResearch() {
		Research research = new Research();
		ResearchWindow window = new ResearchWindow(WindowType.NEW, research, tabSheet);
		window.build();
		getUI().addWindow(window);
	}

	/**
	 * 
	 */
	@Override
	public void doEnter(ViewChangeEvent event) {

	}

}
