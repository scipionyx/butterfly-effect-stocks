package com.scipionyx.butterflyeffect.frontend.stocks.main.ui.components;

import com.scipionyx.butterflyeffect.api.stocks.model.Stock;
import com.vaadin.ui.ComboBox;

/**
 * 
 * @author Renato Mendes
 *
 */
public class StockComboBox extends ComboBox {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// public StockComboBox(String caption, Collection<Stock> stocks) {
	// super(caption, getShortcuts(stocks));
	// }

	/**
	 * 
	 */
	@Override
	public Object getValue() {
		Object value = super.getValue();
		return new Stock((String) value);
	}

	// /**
	// *
	// * @param stocks
	// * @return
	// */
	// private static List<String> getShortcuts(Collection<Stock> stocks) {
	// return stocks.stream().map(stock ->
	// stock.getCode()).collect(Collectors.toList());
	// }

}
