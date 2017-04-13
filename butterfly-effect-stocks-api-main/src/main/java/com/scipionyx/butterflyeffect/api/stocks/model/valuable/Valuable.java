package com.scipionyx.butterflyeffect.api.stocks.model.valuable;

import java.io.Serializable;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * 
 * @author Renato Mendes - rmendes@bottomline.com / renato.mendes.1123@gmail.com
 *
 */
@Entity
@Table(name = "S_VALUABLE", uniqueConstraints = { @UniqueConstraint(columnNames = { "SYMBOL" }) })
@Cacheable(value = true)
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Valuable implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	@Column(name = "ID")
	private Long id;

	@NotNull
	@NotBlank
	@NotEmpty
	@Column(name = "NAME")
	private String name;

	@Column(name = "DESCRIPTION")
	private String description;

	@NotNull
	@NotBlank
	@NotEmpty
	@Column(name = "SYMBOL", length = 10)
	private String symbol;

	/**
	 * 
	 * @param symbol
	 */
	public Valuable(String symbol) {
		setSymbol(symbol);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

}