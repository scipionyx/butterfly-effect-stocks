package com.scipionyx.butterflyeffect.frontend.stocks.main.ui.view.research;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;

import com.scipionyx.butterflyeffect.api.stocks.model.research.Research;
import com.scipionyx.butterflyeffect.frontend.core.ui.view.common.AbstractView;
import com.scipionyx.butterflyeffect.frontend.stocks.main.services.ResearchClientService;
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
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
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
	private ResearchClientService service;

	/**
	 * 
	 */
	@Override
	public void doBuildWorkArea(VerticalLayout workArea) throws Exception {

		addButton(ValoTheme.BUTTON_FRIENDLY, new Button("Add", event -> addNewResearch()));

		tabSheet = new ResearchTabSheet();
		tabSheet.build(service);

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

			TextField userTF = new TextField("User");
			userTF.setReadOnly(true);
			binder.forField(userTF).bind(Research::getUser, Research::setUser);

			Button confirmBt = new Button("Confirm", event -> confirm());
			confirmBt.setStyleName(ValoTheme.BUTTON_FRIENDLY);

			//
			FormLayout formLayout = new FormLayout(nameTF, descriptionTF, userTF, confirmBt);
			formLayout.setMargin(true);

			//
			setContent(formLayout);

		}

		/**
		 * 
		 */
		private void confirm() {

			BinderValidationStatus<Research> validate = binder.validate();
			if (validate.isOk()) {
				tabSheet.addTab(service.save(research));
				close();
				Notification.show(windowTitle, "Research successfully added", Type.TRAY_NOTIFICATION);
			} else if (validate.hasErrors()) {
				Notification.show(windowTitle, validate.getValidationErrors().get(0).getErrorMessage(),
						Type.HUMANIZED_MESSAGE);
			}

		}

	}

}
