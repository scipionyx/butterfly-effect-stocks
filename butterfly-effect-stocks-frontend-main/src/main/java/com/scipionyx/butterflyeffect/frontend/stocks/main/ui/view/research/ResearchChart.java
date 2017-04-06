package com.scipionyx.butterflyeffect.frontend.stocks.main.ui.view.research;

import com.scipionyx.butterflyeffect.api.stocks.model.research.Research;
import com.scipionyx.butterflyeffect.frontend.component.chartjs.ChartJs;
import com.scipionyx.butterflyeffect.frontend.core.ui.view.common.ScipionyxPanel;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;

/**
 * 
 * 
 * 
 * @author Renato Mendes - rmendes@bottomline.com / renato.mendes.1123@gmail.com
 *
 */
public class ResearchChart extends ScipionyxPanel {

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
	public void doBuild() {

		setCaption("Chart");

		addBodyComponent(getChart());
		addBodyComponent(new Button("PRESS HERE", event -> press()));

	}

	private void press() {
		chart.setValue(chart.getValue() + "A");
	}

	/**
	 * 
	 * @return
	 */
	public Component getChart() {

		chart = new ChartJs();

		chart.setValue(research.getName());

		return chart;
	}

}
