package com.scipionyx.butterflyeffect.frontend.stocks.main.ui.view.research;

import com.scipionyx.butterflyeffect.api.stocks.model.research.Research;
import com.vaadin.data.Binder;
import com.vaadin.data.BinderValidationStatus;
import com.vaadin.ui.Button;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Window;
import com.vaadin.ui.themes.ValoTheme;

/**
 * 
 * @author Renato Mendes - rmendes@bottomline.com / renato.mendes.1123@gmail.com
 *
 */
public class ResearchWindow extends Window {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Research research;

	private Binder<Research> binder;

	private WindowType type;

	private String windowTitle;

	private ResearchTabSheet researchTabSheet;

	/**
	 * 
	 * @param type
	 * @param research
	 */
	public ResearchWindow(WindowType type, Research research, ResearchTabSheet researchTabSheet) {
		this.type = type;
		this.research = research;
		this.researchTabSheet = researchTabSheet;
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
			researchTabSheet.addTab(research); 
			close();
			Notification.show(windowTitle, "Research successfully added", Type.TRAY_NOTIFICATION);
		} else if (validate.hasErrors()) {
			Notification.show(windowTitle, validate.getValidationErrors().get(0).getErrorMessage(),
					Type.HUMANIZED_MESSAGE);
		}

	}

}
