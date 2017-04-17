package com.scipionyx.butterflyeffect.api.stocks.model.research;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Cacheable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import com.scipionyx.butterflyeffect.api.stocks.model.valuable.Valuable;

/**
 * 
 * @author Renato Mendes - rmendes@bottomline.com / renato.mendes.1123@gmail.com
 *
 */
@Entity
@Table(name = "S_RESEARCH", uniqueConstraints = { @UniqueConstraint(columnNames = { "USER", "NAME" }) })
@Cacheable(value = true)
public class Research implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	@Column(name = "ID")
	private String id;

	@Column(name = "NAME", length = 500)
	@NotNull
	@NotBlank
	private String name;

	@Column(name = "DESCRIPTION", length = 1000)
	private String description;

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, targetEntity = Note.class, fetch = FetchType.EAGER)
	private List<Note> notes;

	@ManyToOne(fetch = FetchType.EAGER)
	private Valuable valuable;

	@Column(name = "USER", length = 250)
	private String user;

	public String getId() {
		return id;
	}

	public void setId(String id) {
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

	public List<Note> getNotes() {
		return notes;
	}

	public void setNotes(List<Note> notes) {
		this.notes = notes;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public Valuable getValuable() {
		return valuable;
	}

	public void setValuable(Valuable valuable) {
		this.valuable = valuable;
	}

}
