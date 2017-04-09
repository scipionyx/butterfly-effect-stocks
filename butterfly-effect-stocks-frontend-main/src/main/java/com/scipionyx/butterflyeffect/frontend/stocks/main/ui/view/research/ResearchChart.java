package com.scipionyx.butterflyeffect.frontend.stocks.main.ui.view.research;

import java.util.ArrayList;
import java.util.List;

import com.byteowls.vaadin.chartjs.ChartJs;
import com.byteowls.vaadin.chartjs.config.BarChartConfig;
import com.byteowls.vaadin.chartjs.data.BarDataset;
import com.byteowls.vaadin.chartjs.data.Dataset;
import com.byteowls.vaadin.chartjs.data.LineDataset;
import com.byteowls.vaadin.chartjs.options.Position;
import com.scipionyx.butterflyeffect.api.stocks.model.research.Research;
import com.scipionyx.butterflyeffect.frontend.core.ui.view.common.ScipionyxPanel;
import com.vaadin.icons.VaadinIcons;
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
		addButton(VaadinIcons.REFRESH, new Button("REFRESH", event -> refresh()));
	}

	/**
	 * 
	 */
	public void refresh() {
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
	 * @return
	 */
	public Component getChart() {

		BarChartConfig config = new BarChartConfig();
		config.data().labels("January", "February", "March", "April", "May", "June", "July")
				.addDataset(new BarDataset().type().label("Dataset 1").backgroundColor("rgba(151,187,205,0.5)")
						.borderColor("white").borderWidth(2))
				.addDataset(new LineDataset().type().label("Dataset 2").backgroundColor("rgba(151,187,205,0.5)")
						.borderColor("white").borderWidth(2))
				.addDataset(new BarDataset().type().label("Dataset 3").backgroundColor("rgba(220,220,220,0.5)")).and();

		config.options().responsive(true).title().display(true).position(Position.LEFT)
				.text("Chart.js Combo Bar Line Chart").and().done();

		List<String> labels = config.data().getLabels();
		for (Dataset<?, ?> ds : config.data().getDatasets()) {
			List<Double> data = new ArrayList<>();
			for (int i = 0; i < labels.size(); i++) {
				data.add((double) (Math.random() > 0.5 ? 1.0 : -1.0) * Math.round(Math.random() * 100));
			}

			if (ds instanceof BarDataset) {
				BarDataset bds = (BarDataset) ds;
				bds.dataAsList(data);
			}

			if (ds instanceof LineDataset) {
				LineDataset lds = (LineDataset) ds;
				lds.dataAsList(data);
			}
		}

		chart = new ChartJs(config);
		chart.setSizeFull();
		chart.setJsLoggingEnabled(true);

		return chart;
	}

}
