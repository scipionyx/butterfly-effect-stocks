package com.scipionyx.butterflyeffect.frontend.stocks.main.ui.view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.client.RestClientException;

import com.scipionyx.butterflyeffect.api.infrastructure.services.client.CrudParameter;
import com.scipionyx.butterflyeffect.api.stocks.model.Portfolio;
import com.scipionyx.butterflyeffect.api.stocks.model.Position;
import com.scipionyx.butterflyeffect.api.stocks.model.Stock;
import com.scipionyx.butterflyeffect.frontend.core.ui.view.common.AbstractView;
import com.scipionyx.butterflyeffect.frontend.stocks.main.services.PortfolioClientService;
import com.scipionyx.butterflyeffect.frontend.stocks.main.services.StocksClientService;
import com.scipionyx.butterflyeffect.ui.view.MenuConfiguration;
import com.scipionyx.butterflyeffect.ui.view.ViewConfiguration;
import com.vaadin.data.Binder;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.DateField;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.TabSheet.Tab;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
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
@MenuConfiguration(position = com.scipionyx.butterflyeffect.ui.view.MenuConfiguration.Position.TOP_MAIN, label = "Portfolio", group = "", order = 1, icon = VaadinIcons.STOCK)

//
@Configurable(value = PortfolioView.VIEW_NAME)
public class PortfolioView extends AbstractView {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final String VIEW_NAME = "butterfly-effect-frontend-stocks-main:portfolio";

	private TabSheet tabSheet;

	private Map<Portfolio, Grid<Position>> map;

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
	public void doEnter(ViewChangeEvent event) {
		//refresh();
	}

	/**
	 * @return
	 * 
	 */
	private void refresh() {
		// TODO - Order by portfolio order
		// Get all the profiles and update information
		for (Portfolio portifolio : map.keySet()) {
			// refresh portfolio data
			map.get(portifolio).setItems(portifolio.getPositions());
		}
	}

	/**
	 * 
	 * @param portfolio
	 */
	private void addPortfolio(Portfolio portfolio) {
		// Save it
		// TODO

		Grid<Position> grid = createGrid();
		Tab addTab = tabSheet.addTab(grid, portfolio.getName(), VaadinIcons.STOCK, portfolio.getOrder());
		addTab.setClosable(!portfolio.isDefaultPortfolio());

		map.put(portfolio, grid);

	}

	/**
	 * 
	 * @param sheet
	 * @param a
	 */
	private void closeIt(TabSheet sheet, Component a) {
		Portfolio portfolio = null;
		map.remove(portfolio);
	}

	/**
	 * 
	 */
	@Override
	public void doBuildWorkArea(VerticalLayout workArea) throws Exception {

		addButton(ValoTheme.BUTTON_FRIENDLY, new Button("Manage Protifolio", event -> managePortifolio()));
		addButton(ValoTheme.BUTTON_FRIENDLY, new Button("Add Stock", event -> addStock()));
		// addButton(ValoTheme.BUTTON_PRIMARY, new Button("Refresh", event ->
		// refresh(null)));

		List<Portfolio> portfolios = loadPortfolios();

		map = new HashMap<>();
		tabSheet = new TabSheet();
		tabSheet.setCloseHandler(this::closeIt);

		for (Portfolio portfolio : portfolios) {
			addPortfolio(portfolio);
		}

		// Always select the first tab
		tabSheet.setSelectedTab(0);

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
	 * @return
	 */
	private Grid<Position> createGrid() {
		Grid<Position> grid = new Grid<>();

		grid.setSizeFull();

		grid.addColumn(Position::getId).setCaption("Id").setHidden(true);
		grid.addColumn(Position::getStock).setCaption("Symbol").setWidthUndefined();
		grid.addColumn(Position::getQuantity).setCaption("Quantity").setWidthUndefined();
		grid.addColumn(Position::getPrice).setCaption("Price").setWidthUndefined();
		grid.addColumn(Position::getBuy).setCaption("Buy").setWidthUndefined();
		grid.addColumn(Position::getSell).setCaption("Sell").setWidthUndefined();
		grid.addColumn(Position::getCurrentPrice).setCaption("Current Price").setWidthUndefined();
		grid.addColumn(Position::getCurrentValue).setCaption("Current Value").setWidthUndefined();
		grid.addColumn(Position::getUpdate).setCaption("Last update").setWidthUndefined();

		return grid;
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
	private void loadGridPortfolio(Portfolio portfolio) {

		Component component = ((Tab) tabSheet.getSelectedTab()).getComponent();
		@SuppressWarnings("unchecked")
		Grid<Portfolio> grid = (Grid<Portfolio>) component;

		grid.setItems(new ArrayList<Portfolio>());
		if (portfolio.getPositions() != null) {
			for (com.scipionyx.butterflyeffect.api.stocks.model.Position position : portfolio.getPositions()) {
				// grid.addRow(position.getId(),
				// position.getStock().getSymbol(),
				// position.getStock().getName(),
				// position.getQuantity(), position.getPrice(),
				// position.getBuy(), position.getSell(), 0d, 0d, 0d,
				// Status.MONITORING);
			}
		}
	}

	/**
	 * 
	 * @return
	 */
	private List<Portfolio> loadPortfolios() {

		//
		String username = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
		List<CrudParameter> crudParameters = new ArrayList<>();
		crudParameters.add(new CrudParameter("user", username));

		List<Portfolio> portfolios = null;

		try {
			portfolios = portfolioClientService.findAllByOrderBy(crudParameters, "order");
		} catch (Exception e) {
			Notification.show(e.getMessage(), Type.TRAY_NOTIFICATION);
			e.printStackTrace();
		}

		if (portfolios == null || portfolios.size() == 0) {

			Portfolio portfolio_ = new Portfolio();
			portfolio_.setPositions(new ArrayList<>());
			portfolio_.setUser(username);
			portfolio_.setName("Default");
			portfolio_.setDefaultPortfolio(true);
			portfolio_.setDescription("Default Portfolio");
			portfolio_.setOrder(0);

			portfolios = new ArrayList<>();
			portfolios.add(portfolio_);

		}

		return portfolios;

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
			Binder<Position> binder = new Binder<>(Position.class);
			binder.setBean(position);

			//
			List<Stock> stocks = stockService.findAllOrderBy("symbol");

			ComboBox<Stock> stockComboBox = new ComboBox<>("Stock", stocks);
			binder.bind(stockComboBox, "stock");

			TextField quantityTextField = new TextField("Quantity"); // binder.buildAndBind("Quantity",
																		// "quantity",
																		// TextField.class);
			// quantityTextField.setNullRepresentation("");

			TextField priceTextField = new TextField("Price"); // binder.buildAndBind("Price",
																// "price",
																// TextField.class);
			// priceTextField.setNullRepresentation("");

			DateField buyDateField = new DateField("Buy");
			DateField sellDateField = new DateField("Sell");

			//
			FormLayout formLayout = new FormLayout();
			formLayout.setMargin(true);

			formLayout.addComponents(stockComboBox, quantityTextField, priceTextField, buyDateField, sellDateField);

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
		private void confirm(ClickEvent event, Binder<Position> binder) {
			try {
				binder.validate();

				// Find selected Tab
				Component selectedTab = tabSheet.getSelectedTab();
				// Find selected Portfolio
				// Portfolio portfolio = invertedMap.get(selectedTab);

				// portfolio.getPositions().add(position);
				// portfolioClientService.save(portfolio);
				// refresh(portfolio);

				close();

			} catch (Exception e) {
				Notification.show("Please provided the required information", Type.HUMANIZED_MESSAGE);
			}

		}

	}

}
