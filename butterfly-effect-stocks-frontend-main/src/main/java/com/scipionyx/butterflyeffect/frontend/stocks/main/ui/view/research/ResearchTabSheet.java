package com.scipionyx.butterflyeffect.frontend.stocks.main.ui.view.research;

import com.scipionyx.butterflyeffect.api.stocks.model.research.Research;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TabSheet;

class ResearchTabSheet extends TabSheet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * 
	 */
	public void build() {
		setSizeFull();
	}

	/**
	 * 
	 * @param research
	 */
	public void addTab(Research research) {

		ResearchDetail detail = new ResearchDetail(research);
		detail.build();
		// Related Strategies

		ResearchChart chart = new ResearchChart(research);
		chart.build();

		HorizontalLayout layout = new HorizontalLayout(detail, chart);
		layout.setSizeFull();
		layout.setMargin(true);
		layout.setSpacing(true);
		layout.setExpandRatio(detail, 1);
		layout.setExpandRatio(chart, 3);

		//
		Tab tab = this.addTab(layout, research.getName());
		tab.setClosable(true);

	}

}
