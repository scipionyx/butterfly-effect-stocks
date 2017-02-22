package com.scipionyx.butterflyeffect.frontend.stocks.main.ui.view;

import java.text.DateFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.client.RestClientException;

import com.scipionyx.butterflyeffect.api.stocks.model.Portfolio;
import com.scipionyx.butterflyeffect.api.stocks.model.Position;
import com.scipionyx.butterflyeffect.api.stocks.model.Status;
import com.scipionyx.butterflyeffect.api.stocks.model.Stock;
import com.scipionyx.butterflyeffect.frontend.core.ui.view.common.AbstractView;
import com.scipionyx.butterflyeffect.frontend.stocks.main.services.PortfolioClientService;
import com.scipionyx.butterflyeffect.frontend.stocks.main.services.StocksClientService;
import com.scipionyx.butterflyeffect.ui.view.MenuConfiguration;
import com.scipionyx.butterflyeffect.ui.view.ViewConfiguration;
import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.fieldgroup.FieldGroup.CommitException;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.renderers.DateRenderer;
import com.vaadin.ui.renderers.NumberRenderer;
import com.vaadin.ui.themes.ValoTheme;

/**
 * 
 * @author rmendes
 *
 */
@UIScope
@SpringComponent(value = PortfolioView.VIEW_NAME)
@SpringView(name = PortfolioView.VIEW_NAME)

//
@ViewConfiguration(title = "Portfolio")
@MenuConfiguration(position = com.scipionyx.butterflyeffect.ui.view.MenuConfiguration.Position.TOP_MAIN, label = "Portfolio", group = "", order = 1)

//
@Configurable(value = PortfolioView.VIEW_NAME)
public class PortfolioView extends AbstractView {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final String VIEW_NAME = "butterfly-effect-frontend-stocks-main:portfolio";

	private TabSheet tabSheet;

	private Grid grid;

	private Portfolio portfolio;

	/**
	 * 
	 */
	@Autowired(required = true)
	private StocksClientService stockService;

	@Autowired(required = true)
	private PortfolioClientService portfolioClientService;

	/**
	 * 
	 */
	@Override
	public void doBuildWorkArea(VerticalLayout workArea) throws Exception {

		addButton(ValoTheme.BUTTON_FRIENDLY, new Button("Manage Protifolio", event -> managePortifolio()));
		addButton(ValoTheme.BUTTON_FRIENDLY, new Button("Add Stock", event -> addStock()));
		addButton(ValoTheme.BUTTON_PRIMARY, new Button("Refresh", event -> refresh()));

		portfolio = loadPortfolio();

		//
		grid = new Grid("Portifolio");
		grid.setSizeFull();
		// grid.setWidth(100, Unit.PERCENTAGE);
		grid.addColumn("Id", Long.class).setHidden(true);
		grid.addColumn("Symbol", String.class);
		grid.addColumn("Name", String.class);
		grid.addColumn("Quantity", Long.class);
		grid.addColumn("Price", Double.class).setRenderer(new NumberRenderer(NumberFormat.getCurrencyInstance()));
		grid.addColumn("Buy", Date.class).setRenderer(new DateRenderer(DateFormat.getDateInstance(DateFormat.SHORT)));
		grid.addColumn("Sell", Date.class).setRenderer(new DateRenderer(DateFormat.getDateInstance(DateFormat.SHORT)));
		grid.addColumn("Total Investiment", Double.class)
				.setRenderer(new NumberRenderer(NumberFormat.getCurrencyInstance()));
		grid.addColumn("Current Price", Double.class)
				.setRenderer(new NumberRenderer(NumberFormat.getCurrencyInstance()));
		grid.addColumn("Current Value", Double.class)
				.setRenderer(new NumberRenderer(NumberFormat.getCurrencyInstance()));
		grid.addColumn("Status", Status.class);

		tabSheet = new TabSheet();
		tabSheet.addTab(grid, "Default");

		//
		Panel left = new Panel();
		left.setSizeFull();
		left.setCaption("Analysis");
		left.setStyleName(ValoTheme.PANEL_WELL);
		left.addStyleName(ValoTheme.PANEL_SCROLL_INDICATOR);

		//
		Panel right = new Panel();
		right.setSizeFull();
		right.setCaption("Related News");
		right.setStyleName(ValoTheme.PANEL_WELL);
		right.addStyleName(ValoTheme.PANEL_SCROLL_INDICATOR);

		//
		HorizontalLayout horizontalLayout = new HorizontalLayout(left, right);
		horizontalLayout.setSizeFull();
		horizontalLayout.setSpacing(true);
		horizontalLayout.setExpandRatio(left, 2);
		horizontalLayout.setExpandRatio(right, 1);

		workArea.addComponents(tabSheet, horizontalLayout);
		workArea.setExpandRatio(tabSheet, 1);
		workArea.setExpandRatio(horizontalLayout, 4);

	}

	/**
	 * 
	 */
	private void managePortifolio() {
		tabSheet.addTab(new VerticalLayout(), "New Portfolio").setClosable(true);
	}

	/**
	 * 
	 */
	private void refresh() {
		loadGridPortifolio();
	}

	/**
	 * 
	 */
	private void loadGridPortifolio() {
		grid.setCaption("Number of Positions:" + portfolio.getPositions().size());
		grid.getContainerDataSource().removeAllItems();
		if (portfolio.getPositions() != null) {
			for (com.scipionyx.butterflyeffect.api.stocks.model.Position position : portfolio.getPositions()) {
				grid.addRow(position.getId(), position.getStock().getSymbol(), position.getStock().getName(),
						position.getQuantity(), position.getPrice(), position.getBuy(), position.getSell(), 0d, 0d, 0d,
						Status.MONITORING);
			}
		}
	}

	/**
	 * 
	 * @return
	 */
	private Portfolio loadPortfolio() {
		Portfolio portfolio_ = new Portfolio();
		portfolio_.setPositions(new ArrayList<>());
		portfolio_
				.setUser(((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername());
		return portfolio_;
	}

	/**
	 * 
	 */
	private void addStock() {

		try {

			Position position = new Position();
			StockWindow stockWindow = new StockWindow(position);
			stockWindow.build();
			stockWindow.center();
			getUI().addWindow(stockWindow);

		} catch (Exception e) {
			Notification.show(e.getMessage(), Type.ERROR_MESSAGE);
			e.printStackTrace();
		}

	}

	/**
	 * 
	 * @author rmendes
	 *
	 */
	private class StockWindow extends Window {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		private Position position;

		/**
		 * 
		 * @param position
		 */
		StockWindow(Position position) {
			this.position = position;
		}

		/**
		 * 
		 * @return
		 * @throws Exception
		 * @throws RestClientException
		 */
		void build() throws RestClientException, Exception {

			//
			this.setWidth(350, Unit.PIXELS);
			this.setModal(false);
			this.setResizable(false);
			this.setCaption("Add new position");

			//
			BeanFieldGroup<Position> binder = new BeanFieldGroup<>(Position.class);
			binder.setItemDataSource(position);
			binder.setBuffered(true);

			//
			List<Stock> stocks = stockService.findAllOrderBy("symbol");

			ComboBox stockComboBox = new ComboBox("Stock", stocks);
			binder.bind(stockComboBox, "stock");

			TextField quantityTextField = binder.buildAndBind("Quantity", "quantity", TextField.class);
			quantityTextField.setNullRepresentation("");

			TextField priceTextField = binder.buildAndBind("Price", "price", TextField.class);
			priceTextField.setNullRepresentation("");

			//
			FormLayout formLayout = new FormLayout();
			formLayout.setMargin(true);

			formLayout.addComponent(stockComboBox);
			formLayout.addComponent(quantityTextField);
			formLayout.addComponent(priceTextField);
			formLayout.addComponent(binder.buildAndBind("Buy", "buy"));
			formLayout.addComponent(binder.buildAndBind("Sell", "sell"));

			Button confirmButton = new Button("Confirm", event -> confirm(event, binder));
			confirmButton.setStyleName(ValoTheme.BUTTON_FRIENDLY);

			HorizontalLayout horizontalLayout = new HorizontalLayout(confirmButton);
			horizontalLayout.setMargin(true);
			horizontalLayout.setComponentAlignment(confirmButton, Alignment.MIDDLE_RIGHT);

			VerticalLayout content = new VerticalLayout(formLayout, horizontalLayout);

			this.setContent(content);

		}

		/**
		 * @param event
		 * @param binder
		 * 
		 */
		private void confirm(ClickEvent event, FieldGroup binder) {
			try {
				binder.commit();
				portfolio.getPositions().add(position);
				portfolioClientService.save(portfolio);
				refresh();
				close();
			} catch (CommitException e) {
				Notification.show("Please provided the required information", Type.HUMANIZED_MESSAGE);
			}

		}

	}

	/**
	 * 
	 */
	@Override
	public void doEnter(ViewChangeEvent event) {
		refresh();
	}

}
