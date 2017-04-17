package com.scipionyx.butterflyeffect.frontend.stocks.main.ui.view.portfolio;

import java.text.DateFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.client.RestClientException;

import com.scipionyx.butterflyeffect.api.stocks.model.market.Exchange;
import com.scipionyx.butterflyeffect.api.stocks.model.portfolio.Portfolio;
import com.scipionyx.butterflyeffect.api.stocks.model.portfolio.Position;
import com.scipionyx.butterflyeffect.api.stocks.model.valuable.Stock;
import com.scipionyx.butterflyeffect.frontend.core.ui.view.common.AbstractView;
import com.scipionyx.butterflyeffect.frontend.stocks.main.services.ExchangeClientService;
import com.scipionyx.butterflyeffect.frontend.stocks.main.services.PortfolioClientService;
import com.scipionyx.butterflyeffect.frontend.stocks.main.services.StocksClientService;
import com.scipionyx.butterflyeffect.ui.view.MenuConfiguration;
import com.scipionyx.butterflyeffect.ui.view.ViewConfiguration;
import com.vaadin.annotations.Title;
import com.vaadin.data.Binder;
import com.vaadin.data.BinderValidationStatus;
import com.vaadin.data.converter.LocalDateToDateConverter;
import com.vaadin.data.converter.StringToDoubleConverter;
import com.vaadin.data.converter.StringToLongConverter;
import com.vaadin.data.provider.ListDataProvider;
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
import com.vaadin.ui.renderers.DateRenderer;
import com.vaadin.ui.renderers.NumberRenderer;
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
@Title(value = PortfolioView.WINDOW_CAPTION)
public class PortfolioView extends AbstractView {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final String VIEW_NAME = "butterfly-effect-frontend-stocks-main:portfolio";
	protected static final String WINDOW_CAPTION = "Portifolio Management";

	private TabSheet tabSheet;

	@Autowired(required = true)
	private transient StocksClientService stockService;
	@Autowired(required = true)
	private transient ExchangeClientService exchangeService;
	@Autowired(required = true)
	private transient PortfolioClientService portfolioClientService;

	/**
	 * 
	 */
	@Override
	public void doEnter(ViewChangeEvent event) {
	}

	/**
	 * 
	 * @param portfolio
	 */
	private void addPortfolio(Portfolio portfolio) {

		
		PortfolioGrid grid = new PortfolioGrid(portfolio);
		VerticalLayout layout = new VerticalLayout(grid);
		
		Tab tab = tabSheet.addTab(layout, portfolio.getName(), VaadinIcons.STOCK);
		// portfolio.setPosition(tabSheet.getTabPosition(tab));
		tab.setClosable(!portfolio.isDefaultPortfolio());

	}

	/**
	 * 
	 */
	@Override
	public void doBuildWorkArea(VerticalLayout workArea) throws Exception {

		addButton(ValoTheme.BUTTON_FRIENDLY, new Button("Add Portfolio", event -> addPortfolio()));
		addButton(ValoTheme.BUTTON_FRIENDLY, new Button("Add Stock", event -> addPosition()));

		tabSheet = new TabSheet();
		tabSheet.setCloseHandler(this::closeTab);

		List<Portfolio> portfolios = loadPortfolios();
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
	 * @param sheet
	 * @param a
	 */
	private void closeTab(TabSheet sheet, Component tabContent) {
		PortfolioGrid portfolioGrid = (PortfolioGrid) tabContent;
		portfolioClientService.delete(portfolioGrid.getPortfolio().getId());
		Notification.show("Remove Portfolio",
				"Portfolio [" + portfolioGrid.getPortfolio().getName() + "] successfully removed",
				Type.TRAY_NOTIFICATION);
		sheet.removeComponent(tabContent);
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
	 * @return current Portfolio list for the current user
	 */
	private List<Portfolio> loadPortfolios() {

		//
		String username = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();

		Map<String, Object> map = new HashMap<>();
		map.put("user", username);

		List<Portfolio> portfolios = null;

		try {

			portfolios = portfolioClientService.findAllByOrderBy(map, "position");

			if (portfolios == null || portfolios.size() == 0) {

				Portfolio portfolio = new Portfolio();
				portfolio.setPositions(new ArrayList<>());
				portfolio.setUser(username);
				portfolio.setName("Default");
				portfolio.setDefaultPortfolio(true);
				portfolio.setDescription("Default Portfolio");
				portfolio.setPosition(0);

				Portfolio save = portfolioClientService.save(portfolio);

				portfolios = Arrays.asList(save);

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
			getUI().addWindow((new NewPositionWindow(new Position())).build());
		} catch (Exception e) {
			Notification.show("Add Position", e.getMessage(), Type.ERROR_MESSAGE);
			e.printStackTrace();
		}

	}

	/**
	 * 
	 * This dialog will add a new Portfolio
	 * 
	 * @author Renato Mendes
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

			BinderValidationStatus<Portfolio> validate = binder.validate();
			if (validate.isOk()) {
				portfolio.setPositions(new ArrayList<>());
				Portfolio save = portfolioClientService.save(portfolio);
				Tab tab = tabSheet.addTab(new PortfolioGrid(save), portfolio.getName(), VaadinIcons.STOCK);
				tab.setClosable(true);
				tabSheet.setSelectedTab(tab);
				close();
				Notification.show("Add Portfolio", "Portfolio successfully added", Type.TRAY_NOTIFICATION);
			} else if (validate.hasErrors()) {
				Notification.show("Add Portfolio", validate.getValidationErrors().get(0).getErrorMessage(),
						Type.HUMANIZED_MESSAGE);
			}

		}

	}

	/**
	 * 
	 * This dialog will add new Positions
	 * 
	 * @author Renato Mendes
	 *
	 */
	private class NewPositionWindow extends Window {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		private Position position;

		private Binder<Position> binder;

		private final String WINDOW_CAPTION = "Add new position";

		/**
		 * 
		 * @param position
		 */
		NewPositionWindow(Position position) {
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
				Map<String, Object> map = new HashMap<>();
				map.put("exchange", exchange);
				//
				List<Stock> stocks = stockService.findAllByOrderBy(map, "symbol");
				stockCB.setItems(stocks);
				stockCB.setEnabled(true);
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
		public NewPositionWindow build() throws RestClientException, Exception {

			//
			setWidth(320, Unit.PIXELS);
			setModal(false);
			setResizable(false);
			center();
			setCaption(WINDOW_CAPTION);

			//
			binder = new Binder<>();
			binder.setBean(position);

			List<Exchange> exchanges = exchangeService.findAll();
			ComboBox<Exchange> exchangeCB = new ComboBox<>("Exchange", exchanges);
			exchangeCB.setItemCaptionGenerator(item -> item.getCode());
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
			binder.forField(buyDF).withConverter(new LocalDateToDateConverter()).bind(Position::getBuy,
					Position::setBuy);

			DateField sellDF = new DateField("Sell");
			binder.forField(sellDF).withConverter(new LocalDateToDateConverter()).bind(Position::getSell,
					Position::setSell);

			//
			Button confirmButton = new Button("Confirm", event -> confirm(event, binder));
			confirmButton.setStyleName(ValoTheme.BUTTON_FRIENDLY);

			FormLayout formLayout = new FormLayout(exchangeCB, stockCB, quantityTF, priceTF, buyDF, sellDF,
					confirmButton);
			formLayout.setMargin(true);

			this.setContent(formLayout);

			return this;

		}

		/**
		 * @param event
		 * @param binder
		 * 
		 */
		private void confirm(ClickEvent event, Binder<Position> binder) {

			BinderValidationStatus<Position> validate = binder.validate();

			if (validate.isOk()) {

				// Find & Save selected Tab
				PortfolioGrid grid = (PortfolioGrid) tabSheet.getSelectedTab();
				grid.addPosition(validate.getBinder().getBean());

				// Finalize all
				Notification.show(WINDOW_CAPTION, "New position added successfuly", Type.TRAY_NOTIFICATION);
				close();

			} else {

				Notification.show("Please provided the required information", Type.HUMANIZED_MESSAGE);

			}

		}

	}

	/**
	 * This Grid carries the portfolio information
	 * 
	 * @author Renato Mendes
	 *
	 */
	private class PortfolioGrid extends Grid<Position> {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		private Portfolio portfolio;

		public PortfolioGrid(Portfolio portfolio) {

			this.portfolio = portfolio;

			this.addColumn(position -> position.getStock().getExchange().getCode()).setCaption("Exchange")
					.setHidable(true);
			this.addColumn(position -> position.getStock().getSymbol()).setCaption("Symbol");
			this.addColumn(position -> position.getStock().getName()).setCaption("Name").setHidable(true);

			this.addColumn(position -> position.getStock().getExchange().getCountry().getName()).setCaption("Country")
					.setHidable(true);
			this.addColumn(position -> position.getStock().getExchange().getCountry().getName()).setCaption("Currency")
					.setHidable(true);

			this.addColumn(Position::getQuantity, new NumberRenderer(NumberFormat.getIntegerInstance()))
					.setCaption("Quantity").setHidable(true);
			this.addColumn(Position::getCost, new NumberRenderer(NumberFormat.getCurrencyInstance())).setCaption("Cost")
					.setHidable(true);
			this.addColumn(Position::getBuy, new DateRenderer(DateFormat.getDateInstance(DateFormat.SHORT)))
					.setCaption("Buy").setHidable(true);
			this.addColumn(
					position -> (position.getQuantity() != null && position.getCost() != null)
							? position.getQuantity() * position.getCost() : null,
					new NumberRenderer(NumberFormat.getCurrencyInstance())).setCaption("Investiment").setHidable(true);

			this.addColumn(Position::getPrice, new NumberRenderer(NumberFormat.getCurrencyInstance()))
					.setCaption("Price").setHidable(true);
			this.addColumn(Position::getSell, new DateRenderer(DateFormat.getDateInstance(DateFormat.SHORT)))
					.setCaption("Sell").setHidable(true);
			this.addColumn(Position::getCurrentPrice, new NumberRenderer(NumberFormat.getCurrencyInstance()))
					.setCaption("Current Price").setHidable(true);
			this.addColumn(Position::getCurrentValue, new NumberRenderer(NumberFormat.getCurrencyInstance()))
					.setCaption("Current Value").setHidable(true);
			this.addColumn(Position::getUpdate, new DateRenderer(DateFormat.getDateInstance(DateFormat.SHORT)))
					.setCaption("Last update").setHidable(true);

			// this.setCaption(portfolio.getName());

			//
			this.setSizeFull();
			// this.setFrozenColumnCount(1);

			this.setDataProvider(new ListDataProvider<>(portfolio.getPositions()));

		}

		public void addPosition(Position newPosition) {

			// refresh current portfolio
			portfolio = portfolioClientService.findOne(portfolio.getId());

			// add new
			portfolio.getPositions().add(newPosition);

			// save
			portfolio = portfolioClientService.save(portfolio);

			// refresh;
			setDataProvider(new ListDataProvider<>(portfolio.getPositions()));

		}

		/**
		 * @return the portfolio
		 */
		public Portfolio getPortfolio() {
			return portfolio;
		}

	}

}
