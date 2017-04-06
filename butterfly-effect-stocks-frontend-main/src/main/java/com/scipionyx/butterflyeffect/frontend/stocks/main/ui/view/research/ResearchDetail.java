package com.scipionyx.butterflyeffect.frontend.stocks.main.ui.view.research;

import com.scipionyx.butterflyeffect.api.stocks.model.research.Research;
import com.scipionyx.butterflyeffect.frontend.core.ui.view.common.ScipionyxPanel;
import com.vaadin.data.Binder;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.ui.Button;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;

/**
 * 
 * @author Renato Mendes - rmendes@bottomline.com / renato.mendes.1123@gmail.com
 *
 */
public class ResearchDetail extends ScipionyxPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Research research;

	private Binder<Research> binder;

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
		binder.setReadOnly(true);

		setCaption("Details");

		TextField nameTF = new TextField("Name");
		binder.forField(nameTF).asRequired("Research name is required").bind(Research::getName, Research::setName);

		TextArea descriptionTF = new TextArea("Description");
		binder.forField(descriptionTF).bind(Research::getDescription, Research::setDescription);

		TextField userTF = new TextField("User");
		userTF.setReadOnly(true);
		binder.forField(userTF).bind(Research::getUser, Research::setUser);

		FormLayout formLayout = new FormLayout(nameTF, descriptionTF, userTF);
		addBodyComponent(formLayout);

		addButton(VaadinIcons.EDIT, new Button("Edit", event -> edit()));

	}

	/**
	 * 
	 */
	private void edit() {
		binder.setReadOnly(false);
	}

}
