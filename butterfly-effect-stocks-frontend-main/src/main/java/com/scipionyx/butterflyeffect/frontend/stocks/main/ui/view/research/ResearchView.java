package com.scipionyx.butterflyeffect.frontend.stocks.main.ui.view.research;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;

import com.byteowls.vaadin.chartjs.ChartJs;
import com.byteowls.vaadin.chartjs.config.LineChartConfig;
import com.byteowls.vaadin.chartjs.data.Dataset;
import com.byteowls.vaadin.chartjs.data.LineDataset;
import com.byteowls.vaadin.chartjs.data.LineDataset.CubicInterpolationMode;
import com.scipionyx.butterflyeffect.api.infrastructure.services.client.Operation;
import com.scipionyx.butterflyeffect.api.infrastructure.services.client.Value;
import com.scipionyx.butterflyeffect.api.stocks.model.research.ChartPeriod;
import com.scipionyx.butterflyeffect.api.stocks.model.research.Research;
import com.scipionyx.butterflyeffect.api.stocks.model.valuable.Valuable;
import com.scipionyx.butterflyeffect.api.stocks.model.valuable.data.historicalprice.CryptoCurrencyPrice;
import com.scipionyx.butterflyeffect.frontend.core.ui.view.common.AbstractView;
import com.scipionyx.butterflyeffect.frontend.core.ui.view.common.ScipionyxPanel;
import com.scipionyx.butterflyeffect.frontend.stocks.main.services.CryptoCurrencyClientService;
import com.scipionyx.butterflyeffect.frontend.stocks.main.services.CryptoCurrencyPriceClientService;
import com.scipionyx.butterflyeffect.frontend.stocks.main.services.ResearchClientService;
import com.scipionyx.butterflyeffect.frontend.stocks.main.services.ValuableClientService;
import com.scipionyx.butterflyeffect.ui.view.MenuConfiguration;
import com.scipionyx.butterflyeffect.ui.view.ViewConfiguration;
import com.vaadin.annotations.Title;
import com.vaadin.data.Binder;
import com.vaadin.data.BinderValidationStatus;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.ItemCaptionGenerator;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.MenuBar.MenuItem;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
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

	@Autowired(required = true)
	private ResearchClientService researchService;

	@Autowired(required = true)
	private ValuableClientService valuableClientService;

	@Autowired(required = true)
	private CryptoCurrencyClientService cryptoCurrencyClientService;

	@Autowired(required = true)
	private CryptoCurrencyPriceClientService cryptoCurrencyPriceClientService;

	/**
	 * 
	 */
	@Override
	public void doBuildWorkArea(VerticalLayout workArea) throws Exception {

		addButton(ValoTheme.BUTTON_FRIENDLY, new Button("Add", event -> addNewResearch()));

		tabSheet = new ResearchTabSheet();
		tabSheet.build(researchService);

		workArea.addComponents(tabSheet);

	}

	/**
	 * 
	 */
	private void addNewResearch() {
		Research research = new Research();
		ResearchWindow window = new ResearchWindow(WindowType.NEW, research);
		window.build();
		getUI().addWindow(window);
	}

	/**
	 * 
	 */
	@Override
	public void doEnter(ViewChangeEvent event) {
		tabSheet.refresh();
	}

	private class ResearchWindow extends Window {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		private Research research;

		private Binder<Research> binder;

		private WindowType type;

		private String windowTitle;

		/**
		 * 
		 * @param type
		 * @param research
		 */
		public ResearchWindow(WindowType type, Research research) {
			this.type = type;
			this.research = research;
		}

		public void build() {
			setClosable(true);
			setWidth(320, Unit.PIXELS);
			setModal(false);
			setResizable(false);
			center();
			switch (type) {
			case NEW:
				windowTitle = "Add new Research";
				break;
			case EDIT:
				windowTitle = "Edit Research";
				break;
			default:
				break;
			}
			setCaption(windowTitle);

			binder = new Binder<>();
			binder.setBean(research);

			TextField nameTF = new TextField("Name");
			binder.forField(nameTF).asRequired("Research name is required").bind(Research::getName, Research::setName);
			nameTF.focus();

			TextArea descriptionTF = new TextArea("Description");
			binder.forField(descriptionTF).bind(Research::getDescription, Research::setDescription);

			ComboBox<Valuable> valuableCB = new ComboBox<>("Valuable", valuableClientService.findAll());
			valuableCB.setRequiredIndicatorVisible(true);
			valuableCB.setItemCaptionGenerator(new ItemCaptionGenerator<Valuable>() {

				/**
				 * 
				 */
				private static final long serialVersionUID = 1L;

				@Override
				public String apply(Valuable item) {
					return item.getName();
				}
			});
			binder.forField(valuableCB).bind(Research::getTarget, Research::setTarget);

			TextField userTF = new TextField("User");
			userTF.setReadOnly(true);
			binder.forField(userTF).bind(Research::getUser, Research::setUser);

			Button confirmBt = new Button("Confirm", event -> {
				try {
					confirm();
				} catch (Exception e) {
					e.printStackTrace();
				}
			});
			confirmBt.setStyleName(ValoTheme.BUTTON_FRIENDLY);

			//
			FormLayout formLayout = new FormLayout(nameTF, descriptionTF, valuableCB, userTF, confirmBt);
			formLayout.setMargin(true);

			//
			setContent(formLayout);

		}

		/**
		 * @throws Exception
		 * 
		 */
		private void confirm() throws Exception {

			BinderValidationStatus<Research> validate = binder.validate();
			if (validate.isOk()) {
				research.setUser(username);
				research.setNotes(new ArrayList<>());
				research.setChartPeriod(ChartPeriod.FIVE_DAYS);
				tabSheet.addTab(researchService.save(research));
				close();
				Notification.show(windowTitle, "Research successfully added", Type.TRAY_NOTIFICATION);
			} else if (validate.hasErrors()) {
				Notification.show(windowTitle, validate.getValidationErrors().get(0).getErrorMessage(),
						Type.HUMANIZED_MESSAGE);
			}

		}

	}

	/**
	 * 
	 * 
	 * 
	 * @author Renato Mendes - rmendes@bottomline.com /
	 *         renato.mendes.1123@gmail.com
	 *
	 */
	private class ResearchTabSheet extends TabSheet {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		private Map<Tab, Research> map;

		/**
		 * 
		 * 
		 */
		public void build(ResearchClientService service) {
			setSizeFull();

			map = new HashMap<>();

			// Add all existing tabs;
			List<Research> findAll = service.findAll();
			findAll.forEach(r -> {
				try {
					addTab(r);
				} catch (Exception e) {
					e.printStackTrace();
				}
			});

			addSelectedTabChangeListener(new TabSheet.SelectedTabChangeListener() {

				/**
				 * 
				 */
				private static final long serialVersionUID = 1L;

				@Override
				public void selectedTabChange(SelectedTabChangeEvent event) {
					HorizontalLayout selectedTab = (HorizontalLayout) event.getTabSheet().getSelectedTab();
					try {
						((ResearchChart) selectedTab.getComponent(1)).refresh();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});

			setCloseHandler(this::closeHandler);
		}

		public void refresh() {
			((ResearchTabLayout) this.getSelectedTab()).enter();
		}

		/**
		 * 
		 * @param sheet
		 * @param component
		 */
		private void closeHandler(TabSheet sheet, Component tabContent) {
			Research research = ((ResearchTabLayout) tabContent).getResearch();
			researchService.delete(research);
			Notification.show("Remove Research", "Research [" + research.getName() + "] successfully removed",
					Type.TRAY_NOTIFICATION);
			sheet.removeComponent(tabContent);
		}

		/**
		 * 
		 * @param research
		 * @throws Exception
		 */
		public void addTab(Research research) throws Exception {

			ResearchDetail detail = new ResearchDetail(research);
			detail.build();
			// Related Strategies

			ResearchChart chart = new ResearchChart(research);
			chart.build();

			ResearchTabLayout layout = new ResearchTabLayout(research, detail, chart);
			layout.setSizeFull();
			layout.setMargin(true);
			layout.setSpacing(true);
			layout.setExpandRatio(detail, 1);
			layout.setExpandRatio(chart, 4);

			//

			Tab tab = this.addTab(layout, research.getName());
			tab.setClosable(true);
			map.put(tab, research);
			this.setSelectedTab(tab);

		}

		/**
		 * 
		 * @author Renato Mendes - rmendes@bottomline.com /
		 *         renato.mendes.1123@gmail.com
		 *
		 */
		private class ResearchTabLayout extends HorizontalLayout {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			private Research research;

			public ResearchTabLayout(Research research, Component... components) {
				super(components);
				this.research = research;
			}

			public void enter() {
				
			}

			public Research getResearch() {
				return research;
			}

		}

		/**
		 * 
		 * @author Renato Mendes - rmendes@bottomline.com /
		 *         renato.mendes.1123@gmail.com
		 *
		 */
		private class ResearchDetail extends ScipionyxPanel {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			private Research research;

			private Binder<Research> binder;

			private boolean readOnly;

			/**
			 * 
			 * @param research
			 */
			public ResearchDetail(Research research) {
				this.research = research;
			}

			/**
			 * 
			 */
			public void doBuild() {

				binder = new Binder<>();
				binder.setBean(research);

				setCaption("Details");

				TextField nameTF = new TextField("Name");
				nameTF.setSizeFull();
				binder.forField(nameTF).asRequired("Research name is required").bind(Research::getName,
						Research::setName);

				TextArea descriptionTF = new TextArea("Description");
				descriptionTF.setSizeFull();
				binder.forField(descriptionTF).bind(Research::getDescription, Research::setDescription);

				ComboBox<Valuable> valuableCB = new ComboBox<>("Valuable", valuableClientService.findAll());
				valuableCB.setSizeFull();
				valuableCB.setRequiredIndicatorVisible(true);
				valuableCB.setItemCaptionGenerator(new ItemCaptionGenerator<Valuable>() {

					/**
					 * 
					 */
					private static final long serialVersionUID = 1L;

					@Override
					public String apply(Valuable item) {
						return item.getName();
					}
				});
				binder.forField(valuableCB).bind(Research::getTarget, Research::setTarget);

				TextField userTF = new TextField("User");
				userTF.setReadOnly(true);
				userTF.setSizeFull();
				binder.forField(userTF).bind(Research::getUser, Research::setUser);

				FormLayout formLayout = new FormLayout(nameTF, descriptionTF, valuableCB, userTF);
				addBodyComponent(formLayout);

				readOnly = true;
				binder.setReadOnly(readOnly);

				addButton(VaadinIcons.EDIT, new Button("Edit", event -> edit()));

			}

			/**
			 * 
			 */
			private void edit() {
				readOnly = !readOnly;
				binder.setReadOnly(readOnly);
			}

		}

	}

	/**
	 * 
	 * 
	 * @author Renato Mendes - rmendes@bottomline.com /
	 *         renato.mendes.1123@gmail.com
	 *
	 */
	private class ResearchChart extends ScipionyxPanel {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		private Research research;

		private ChartJs chart;

		/**
		 * 
		 * @param research
		 */
		public ResearchChart(Research research) {
			this.research = research;
		}

		@Override
		public void doBuild() throws Exception {

			setCaption("Chart");

			addBodyComponent(getChart());
			addButton(VaadinIcons.REFRESH, new Button("Refesh", event -> {
				try {
					refresh();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}));
			addButton(VaadinIcons.TOOLBOX, new Button("Tool Box", event -> showToolBox()));

			MenuBar timeRange = new MenuBar();
			timeRange.addItem("1 day", e -> changeChartPeriod(timeRange, e, ChartPeriod.ONE_DAY));
			timeRange.addItem("5 days", e -> changeChartPeriod(timeRange, e, ChartPeriod.FIVE_DAYS));
			timeRange.addItem("3 month", e -> changeChartPeriod(timeRange, e, ChartPeriod.THREE_MONTHS));
			timeRange.addItem("1 year", e -> changeChartPeriod(timeRange, e, ChartPeriod.ONE_YEAR));
			timeRange.addItem("5 years", e -> changeChartPeriod(timeRange, e, ChartPeriod.FIVE_YEARS));
			timeRange.addItem("Max", e -> changeChartPeriod(timeRange, e, ChartPeriod.ONE_DAY));

			addMenuBar(timeRange);

		}

		/**
		 * 
		 * @param timeRange
		 * @param e
		 * @param period
		 */
		private void changeChartPeriod(MenuBar timeRange, MenuItem e, ChartPeriod period) {
			timeRange.getItems().forEach(i -> i.setChecked(false));
			e.setChecked(true);
			research.setChartPeriod(period);
			researchService.save(research);
			try {
				refresh();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}

		/**
		 * 
		 */
		private void showToolBox() {
		}

		/**
		 * @throws Exception
		 * 
		 */
		public void refresh() throws Exception {
			clearBodyComponents();
			addBodyComponent(getChart());
		}

		/**
		 * 
		 */
		public void enter() {
			chart.refreshData();
		}

		/**
		 * 
		 * @param dateFormat
		 * @param price
		 * @param labels
		 * @param data
		 */
		private void loadData(SimpleDateFormat dateFormat, CryptoCurrencyPrice price, List<String> labels,
				List<Double> data) {
			labels.add(dateFormat.format(price.getDate()));
			data.add(price.getPrice());
		}

		/**
		 * 
		 * @return
		 * @throws Exception
		 */
		public Component getChart() throws Exception {

			List<CryptoCurrencyPrice> findAll = null;

			Map<String, Value> map = new HashMap<>();

			Calendar calendar = Calendar.getInstance();

			switch (research.getChartPeriod()) {
			case ONE_DAY:
				calendar.add(Calendar.DAY_OF_WEEK, -1);
				break;
			case FIVE_DAYS:
				calendar.add(Calendar.DAY_OF_WEEK, -5);
				break;
			case ONE_MONTH:
				calendar.add(Calendar.MONTH, -1);
				break;
			case THREE_MONTHS:
				calendar.add(Calendar.MONTH, -3);
				break;
			case ONE_YEAR:
				calendar.add(Calendar.YEAR, -1);
				break;
			case FIVE_YEARS:
				calendar.add(Calendar.YEAR, -5);
				break;
			case ALL:
				calendar.add(Calendar.YEAR, -10);
				break;
			}

			map.put("date", new Value(Operation.GREATER_THAN_EQUALS, calendar.getTime()));
			findAll = cryptoCurrencyPriceClientService.findAllByOrderBy(map, "date");

			// Build Labels
			List<String> labels = new ArrayList<>();
			List<Double> data = new ArrayList<>();
			SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd-yy HH:mm");
			findAll.forEach(price -> loadData(dateFormat, price, labels, data));

			//
			LineChartConfig lineConfig = new LineChartConfig();

			//
			lineConfig.data().labelsAsList(labels). //
					addDataset(new LineDataset(). //
							label("Cubic interpolation (monotone)").//
							borderColor(DemoUtils.RGB_RED).//
							backgroundColor("rgba(0, 0, 0, 0)").//
							cubicInterpolationMode(CubicInterpolationMode.MONOTONE).//
							fill(false))
					. //
					and().options().responsive(true).title().display(true). //
					and().done();

			// add data
			for (Dataset<?, ?> ds : lineConfig.data().getDatasets()) {
				LineDataset lds = (LineDataset) ds;
				lds.dataAsList(data);
			}

			//
			ChartJs chart = new ChartJs(lineConfig);
			chart.setSizeFull();

			return chart;
		}

	}

	public ResearchClientService getService() {
		return researchService;
	}

	public void setService(ResearchClientService service) {
		this.researchService = service;
	}

}
