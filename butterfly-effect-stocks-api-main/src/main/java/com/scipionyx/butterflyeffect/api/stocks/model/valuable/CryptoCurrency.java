package com.scipionyx.butterflyeffect.api.stocks.model.valuable;

import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * 
 * @author Renato Mendes - rmendes@bottomline.com / renato.mendes.1123@gmail.com
 *
 */
@Entity
@Table(name = "S_VALUABLE_CRYPTO", uniqueConstraints = { @UniqueConstraint(columnNames = { "SYMBOL" }) })
@Cacheable(value = true)
public class CryptoCurrency extends Valuable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
