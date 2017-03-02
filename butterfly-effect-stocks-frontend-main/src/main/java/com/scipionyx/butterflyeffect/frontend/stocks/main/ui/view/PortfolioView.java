package com.scipionyx.butterflyeffect.frontend.stocks.main.ui.view;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClientException;

import com.scipionyx.butterflyeffect.api.stocks.model.Exchange;
import com.scipionyx.butterflyeffect.api.stocks.model.Portfolio;
import com.scipionyx.butterflyeffect.api.stocks.model.Position;
import com.scipionyx.butterflyeffect.api.stocks.model.Stock;
import com.scipionyx.butterflyeffect.frontend.core.ui.view.common.AbstractView;
import com.scipionyx.butterflyeffect.frontend.stocks.main.services.PortfolioClientService;
import com.scipionyx.butterflyeffect.frontend.stocks.main.services.StocksClientService;
import com.scipionyx.butterflyeffect.ui.view.MenuConfiguration;
import com.scipionyx.butterflyeffect.ui.view.ViewConfiguration;
import com.vaadin.data.Binder;
import com.vaadin.data.BinderValidationStatus;
import com.vaadin.data.converter.StringToDoubleConverter;
import com.vaadin.data.converter.StringToLongConverter;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.UIScope;
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
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.themes.ValoTheme;

/**
 * 
 * @author Renato Mendes
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

	private Map<Portfolio, Tab> map;

	private TabSheet tabSheet;

	/**
	 * 
	 */
	@Autowired(required = true)
	private transient StocksClientService stockService;

	/**
	 * 
	 */
	@Autowired(required = true)
	private transient PortfolioClientService portfolioClientService;

	/**
	 * 
	 */
	@Override
	public void doEnter(ViewChangeEvent event) {
		// refresh();
	}

	/**
	 * 
	 * @param portfolio
	 */
	private void addPortfolio(Portfolio portfolio) {

		Grid<Position> grid = createGrid();
		grid.setCaption(portfolio.getName());

		Tab tab = tabSheet.addTab(grid, portfolio.getName(), VaadinIcons.STOCK);
		portfolio.setPosition(tabSheet.getTabPosition(tab));
		tab.setClosable(!portfolio.isDefaultPortfolio());

		map.put(portfolio, tab);

	}

	/**
	 * 
	 * @param sheet
	 * @param a
	 */
	private void closeIt(TabSheet sheet, Component tabContent) {
		Tab tab = sheet.getTab(tabContent);
		for (Portfolio portfolio_ : map.keySet()) {
			if (tab.equals(map.get(portfolio_))) {
				portfolioClientService.delete(portfolio_.getId());
				break;
			}
		}
		Notification.show("Remove Portfolio", "Portfolio [" + tab.getCaption() + "] successfully removed",
				Type.TRAY_NOTIFICATION);
		sheet.removeTab(tab);
	}

	/**
	 * 
	 */
	@Override
	public void doBuildWorkArea(VerticalLayout workArea) throws Exception {

		addButton(ValoTheme.BUTTON_FRIENDLY, new Button("Add Portfolio", event -> addPortfolio()));
		addButton(ValoTheme.BUTTON_FRIENDLY, new Button("Add Stock", event -> addPosition()));

		List<Portfolio> portfolios = loadPortfolios();

		map = new HashMap<>();
		tabSheet = new TabSheet();
		tabSheet.setCloseHandler(this::closeIt);

		for (Portfolio portfolio : portfolios) {
			addPortfolio(portfolio);
		}

		// Always select the first tab
		tabSheet.setSelectedTab(0);

		// //
		Panel left = new Panel();
		left.setSizeFull();
		left.setCaption("Analysis");
		left.setStyleName(ValoTheme.PANEL_WELL);
		left.addStyleName(ValoTheme.PANEL_SCROLL_INDICATOR);

		// //
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

		// grid.addColumn(Position::getId).setCaption("Id").setHidden(true);
		grid.addColumn(Position::getStock).setCaption("Symbol");
		grid.addColumn(Position::getQuantity).setCaption("Quantity");
		grid.addColumn(Position::getPrice).setCaption("Price");
		grid.addColumn(Position::getBuy).setCaption("Buy");
		grid.addColumn(Position::getSell).setCaption("Sell");
		grid.addColumn(Position::getCurrentPrice).setCaption("Current Price");
		grid.addColumn(Position::getCurrentValue).setCaption("Current Value");
		grid.addColumn(Position::getUpdate).setCaption("Last update");

		return grid;
	}

	/**
	 * @throws Exception
	 * @throws RestClientException
	 * 
	 */
	private void addPortfolio() {
		try {
			String username = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal())
					.getUsername();
			NewPortfolioWindow window = new NewPortfolioWindow(
					new Portfolio(username, Boolean.FALSE, tabSheet.getComponentCount()));
			window.build();
			getUI().addWindow(window);
		} catch (Exception e) {
			Notification.show("Add Portfolio", e.getMessage(), Type.ERROR_MESSAGE);
		}

	}

	/**
	 * 
	 * @return
	 */
	private List<Portfolio> loadPortfolios() {

		//
		String username = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();

		MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
		map.add("user", username);

		List<Portfolio> portfolios = null;

		try {

			portfolios = portfolioClientService.findAllByOrderBy(map, "position");

			if (portfolios == null || portfolios.size() == 0) {

				Portfolio portfolio_ = new Portfolio();
				portfolio_.setPositions(new ArrayList<>());
				portfolio_.setUser(username);
				portfolio_.setName("Default");
				portfolio_.setDefaultPortfolio(true);
				portfolio_.setDescription("Default Portfolio");
				portfolio_.setPosition(0);

				portfolios = new ArrayList<>();
				portfolios.add(portfolio_);

				portfolioClientService.save(portfolio_);

			}

		} catch (Exception e) {
			Notification.show("Portfolio", e.getMessage(), Type.ERROR_MESSAGE);
		}

		return portfolios;

	}

	/**
	 * 
	 */
	private void addPosition() {
		try {
			Position position = new Position();
			PositionWindow window = new PositionWindow(position);
			window.build();
			getUI().addWindow(window);
		} catch (Exception e) {
			Notification.show("Add Position", e.getMessage(), Type.ERROR_MESSAGE);
			e.printStackTrace();
		}

	}

	/**
	 * 
	 * @author rmendes
	 *
	 */
	private class NewPortfolioWindow extends Window {

		private static final long serialVersionUID = 1L;

		private Portfolio portfolio;

		private Binder<Portfolio> binder;

		/**
		 * 
		 * @param portfolio
		 */
		public NewPortfolioWindow(Portfolio portfolio) {
			this.portfolio = portfolio;
		}

		/**
		 * 
		 * @return
		 * @throws Exception
		 * @throws RestClientException
		 */
		public void build() throws RestClientException, Exception {
			//
			this.setWidth(320, Unit.PIXELS);
			this.setModal(false);
			this.setResizable(false);
			center();
			this.setCaption("Add new Portfolio");
			//
			binder = new Binder<>();
			binder.setBean(portfolio);

			TextField nameTF = new TextField("Name");
			binder.forField(nameTF).asRequired("Portfolio name is required").bind(Portfolio::getName,
					Portfolio::setName);

			TextArea descriptionTF = new TextArea("Description");
			binder.forField(descriptionTF).bind(Portfolio::getDescription, Portfolio::setDescription);

			TextField userTF = new TextField("User");
			userTF.setReadOnly(true);
			binder.forField(userTF).bind(Portfolio::getUser, Portfolio::setUser);

			Button confirmBt = new Button("Confirm", event -> confirm());
			confirmBt.setStyleName(ValoTheme.BUTTON_FRIENDLY);

			//
			FormLayout formLayout = new FormLayout(nameTF, descriptionTF, userTF, confirmBt);
			formLayout.setMargin(true);

			//
			setContent(formLayout);

		}

		private void confirm() {
			try {
				BinderValidationStatus<Portfolio> validate = binder.validate();
				if (validate.isOk()) {
					Portfolio save = portfolioClientService.save(portfolio);
					Tab tab = tabSheet.addTab(createGrid());
					tab.setClosable(true);
					tab.setCaption(portfolio.getName());
					tabSheet.setSelectedTab(tab);
					map.put(save, tab);
					close();
					Notification.show("Add Portfolio", "Portfolio successfully added", Type.TRAY_NOTIFICATION);
				} else if (validate.hasErrors()) {
					Notification.show("Add Portfolio", validate.getValidationErrors().get(0).getErrorMessage(),
							Type.HUMANIZED_MESSAGE);
				}
			} catch (Exception e) {
				Notification.show("New Portfolio", e.getMessage(), Type.ERROR_MESSAGE);
			}
		}

	}

	/**
	 * 
	 * @author rmendes
	 *
	 */
	private class PositionWindow extends Window {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		private Position position;

		private Binder<Position> binder;

		/**
		 * 
		 * @param position
		 */
		PositionWindow(Position position) {
			this.position = position;
		}

		/**
		 * 
		 * @param exchangeCB
		 * @param stockCB
		 * @throws Exception
		 */
		private void exchangeChange(ComboBox<Exchange> exchangeCB, ComboBox<Stock> stockCB) throws Exception {
			if (exchangeCB.getSelectedItem().isPresent()) {
				Exchange exchange = exchangeCB.getSelectedItem().get();
				MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
				map.add("exchange", exchange);
				//
				List<Stock> stocks = stockService.findAllByOrderBy(map, "symbol");
				stockCB.setItems(stocks);
			} else {
				stockCB.setItems(new ArrayList<>());
				stockCB.setSelectedItem(null);
				stockCB.setEnabled(false);
			}
		}

		/**
		 * 
		 * @return
		 * @throws Exception
		 * @throws RestClientException
		 */
		void build() throws RestClientException, Exception {

			//
			setWidth(320, Unit.PIXELS);
			setModal(false);
			setResizable(false);
			center();
			setCaption("Add new position");

			//
			binder = new Binder<>();
			binder.setBean(position);

			ComboBox<Exchange> exchangeCB = new ComboBox<>("Exchange");
			//
			ComboBox<Stock> stockCB = new ComboBox<>("Stock");
			exchangeCB.addSelectionListener(listener -> {
				try {
					exchangeChange(exchangeCB, stockCB);
				} catch (Exception e) {
					Notification.show("Add new position", e.getMessage(), Type.ERROR_MESSAGE);
				}
			});
			stockCB.setEnabled(false);
			stockCB.setEmptySelectionCaption("<Provide>");
			binder.forField(stockCB).asRequired("Provide the Stock symbol").bind(Position::getStock,
					Position::setStock);

			TextField quantityTF = new TextField("Quantity");
			binder.forField(quantityTF).withNullRepresentation("")
					.withConverter(new StringToLongConverter("This is a number"))
					.bind(Position::getQuantity, Position::setQuantity);

			TextField priceTF = new TextField("Price");
			binder.forField(priceTF).withNullRepresentation("")
					.withConverter(new StringToDoubleConverter("Please provide the Stock Price"))
					.bind(Position::getPrice, Position::setPrice);

			DateField buyDF = new DateField("Buy");
			binder.forField(buyDF);

			DateField sellDF = new DateField("Sell");
			binder.forField(sellDF);

			//
			Button confirmButton = new Button("Confirm", event -> confirm(event, binder));
			confirmButton.setStyleName(ValoTheme.BUTTON_FRIENDLY);

			FormLayout formLayout = new FormLayout(exchangeCB, stockCB, quantityTF, priceTF, buyDF, sellDF,
					confirmButton);
			formLayout.setMargin(true);

			this.setContent(formLayout);

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
				// Component selectedTab = tabSheet.getSelectedTab();
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
