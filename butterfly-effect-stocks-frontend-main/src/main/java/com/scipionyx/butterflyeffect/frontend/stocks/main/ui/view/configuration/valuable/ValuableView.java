package com.scipionyx.butterflyeffect.frontend.stocks.main.ui.view.configuration.valuable;

import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;

import com.scipionyx.butterflyeffect.api.stocks.model.valuable.CryptoCurrency;
import com.scipionyx.butterflyeffect.api.stocks.model.valuable.Valuable;
import com.scipionyx.butterflyeffect.frontend.core.ui.view.common.AbstractView;
import com.scipionyx.butterflyeffect.frontend.stocks.main.services.CryptoCurrencyClientService;
import com.scipionyx.butterflyeffect.frontend.stocks.main.ui.view.research.WindowType;
import com.scipionyx.butterflyeffect.ui.view.MenuConfiguration;
import com.scipionyx.butterflyeffect.ui.view.MenuConfiguration.Position;
import com.scipionyx.butterflyeffect.ui.view.ViewConfiguration;
import com.vaadin.annotations.Title;
import com.vaadin.data.Binder;
import com.vaadin.data.BinderValidationStatus;
import com.vaadin.data.provider.QuerySortOrder;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.SerializableSupplier;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Button;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Grid.SelectionMode;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.themes.ValoTheme;

@UIScope
@SpringComponent(value = ValuableView.VIEW_NAME)
@SpringView(name = ValuableView.VIEW_NAME)

/**
 * 
 * 
 * 
 * @author Renato Mendes - rmendes@bottomline.com / renato.mendes.1123@gmail.com
 *
 */

//
@ViewConfiguration(title = "Valuable")
@MenuConfiguration(position = Position.TOP_MAIN, label = "Valuable", group = "", order = 1, icon = VaadinIcons.MONEY_DEPOSIT, parent = StockConfigurationView.VIEW_NAME)

//
@Configurable(value = ValuableView.VIEW_NAME)
@Title(value = ValuableView.WINDOW_CAPTION)
public class ValuableView extends AbstractView {

	private static final long serialVersionUID = 1L;
	public static final String VIEW_NAME = "butterfly-effect-frontend-stocks-main:configuration-valuable";
	protected static final String WINDOW_CAPTION = "Valuable";

	private Grid<CryptoCurrency> gridCryptoCurrency;

	@Autowired(required = true)
	private CryptoCurrencyClientService service;

	/**
	 * 
	 */
	@Override
	public void doBuildWorkArea(VerticalLayout workArea) throws Exception {

		addButton(ValoTheme.BUTTON_FRIENDLY, new Button("Add New", event -> addNew()));
		addButton(ValoTheme.BUTTON_FRIENDLY, new Button("Delete", event -> deleteSelected()));

		//
		gridCryptoCurrency = new Grid<CryptoCurrency>();
		gridCryptoCurrency.setCaption("Crypto Currency");
		gridCryptoCurrency.setSizeFull();
		gridCryptoCurrency.setSelectionMode(SelectionMode.MULTI);
		gridCryptoCurrency.addColumn(Valuable::getId).setCaption("Id").setHidden(true);
		gridCryptoCurrency.addColumn(Valuable::getName).setCaption("Name");
		gridCryptoCurrency.addColumn(Valuable::getSymbol).setCaption("Symbol");
		gridCryptoCurrency.addColumn(Valuable::getDescription).setCaption("Description");

		gridCryptoCurrency.setDataProvider(new Grid.FetchItemsCallback<CryptoCurrency>() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public Stream<CryptoCurrency> fetchItems(List<QuerySortOrder> sortOrder, int offset, int limit) {
				return service.findAll().stream();
			}
		}, new SerializableSupplier<Integer>() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public Integer get() {
				return (new Long(service.count())).intValue();
			}
		});

		workArea.addComponent(gridCryptoCurrency);
	}

	/**
	 * 
	 */
	private void deleteSelected() {

		Set<CryptoCurrency> selectedItems = gridCryptoCurrency.getSelectedItems();
		service.delete(selectedItems);
		gridCryptoCurrency.getDataProvider().refreshAll();

	}

	/**
	 * 
	 */
	private void addNew() {
		CryptoCurrencyWindow window = new CryptoCurrencyWindow();
		window.build(WindowType.NEW, new CryptoCurrency());
		getUI().addWindow(window);
	}

	/**
	 * 
	 */
	@Override
	public void doEnter(ViewChangeEvent event) {
	}

	/**
	 * 
	 * 
	 * 
	 * @author Renato Mendes - rmendes@bottomline.com /
	 *         renato.mendes.1123@gmail.com
	 *
	 */
	private class CryptoCurrencyWindow extends Window {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		private CryptoCurrency cryptoCurrency;

		private Binder<CryptoCurrency> binder;

		private String windowTitle;

		/**
		 * 
		 * @param type
		 * @param gridCryptoCurrency
		 * @param cryptoCurrency2
		 */
		public void build(WindowType type, CryptoCurrency cryptoCurrency) {

			this.cryptoCurrency = cryptoCurrency;

			setClosable(true);
			setWidth(320, Unit.PIXELS);
			setModal(false);
			setResizable(false);
			center();

			switch (type) {
			case NEW:
				windowTitle = "Add new Crypto Currency";
				break;
			case EDIT:
				windowTitle = "Edit Crypto Currency";
				break;
			default:
				break;
			}
			setCaption(windowTitle);

			binder = new Binder<>();
			binder.setBean(cryptoCurrency);

			TextField nameTF = new TextField("Name");
			binder.forField(nameTF).asRequired("Crypto Currency name is required").bind(Valuable::getName,
					Valuable::setName);
			nameTF.focus();

			TextField symbolTF = new TextField("Symbol");
			binder.forField(symbolTF).asRequired("Crypto Currency symbol is required").bind(Valuable::getSymbol,
					Valuable::setSymbol);

			TextArea descriptionTF = new TextArea("Description");
			binder.forField(descriptionTF).bind(Valuable::getDescription, Valuable::setDescription);

			Button confirmBt = new Button("Confirm", event -> confirm());
			confirmBt.setStyleName(ValoTheme.BUTTON_FRIENDLY);

			//
			FormLayout formLayout = new FormLayout(nameTF, symbolTF, descriptionTF, confirmBt);
			formLayout.setMargin(true);

			//
			setContent(formLayout);

		}

		/**
		 * 
		 */
		private void confirm() {

			BinderValidationStatus<CryptoCurrency> validate = binder.validate();
			if (validate.isOk()) {
				service.save(cryptoCurrency);
				gridCryptoCurrency.getDataProvider().refreshAll();
				close();
				Notification.show(windowTitle, "Crypto Currency successfully added", Type.TRAY_NOTIFICATION);
			} else if (validate.hasErrors()) {
				Notification.show(windowTitle, validate.getValidationErrors().get(0).getErrorMessage(),
						Type.HUMANIZED_MESSAGE);
			}

		}

	}

}
