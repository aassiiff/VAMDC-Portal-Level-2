package uk.ac.cam.ast.vamdc.portal.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import org.hibernate.validator.Length;

@Entity
@Table(name = "Molecules", catalog = "vamdcPortalDB")
@NamedQueries({
    @NamedQuery(name = "Molecules.findAll", query = "SELECT m FROM Molecules m"),
    @NamedQuery(name = "Molecules.findById", query = "SELECT m FROM Molecules m WHERE m.id = :id"),
    @NamedQuery(name = "Molecules.findByStoichiometricFormula", query = "SELECT m FROM Molecules m WHERE m.stoichiometricFormula = :stoichiometricFormula"),
    @NamedQuery(name = "Molecules.findByStoichiometricFormulaWildcard", query = "SELECT m FROM Molecules m WHERE m.stoichiometricFormula LIKE :stoichiometricFormula"),
    @NamedQuery(name = "Molecules.findByInChIKeyStem", query = "SELECT m FROM Molecules m WHERE m.inChIkeyStem = :inChIkeyStem"),
    @NamedQuery(name = "Molecules.findByMolecName", query = "SELECT m FROM Molecules m WHERE m.molecName = :molecName"),
    @NamedQuery(name = "Molecules.findByMolecNameHtml", query = "SELECT m FROM Molecules m WHERE m.molecNameHtml = :molecNameHtml")})
public class Molecules implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2013434527998709315L;
	private Integer id;
	private String stoichiometricFormula;
	private String inChIkeyStem;
	private String molecName;
	private String molecNameHtml;
	private String url;

	public Molecules() {
	}

	public Molecules(String stoichiometricFormula, String inChIkeyStem,
			String molecName, String molecNameHtml, String url) {
		this.stoichiometricFormula = stoichiometricFormula;
		this.inChIkeyStem = inChIkeyStem;
		this.molecName = molecName;
		this.molecNameHtml = molecNameHtml;
		this.url = url;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "stoichiometric_formula", length = 40)
	@Length(max = 40)
	public String getStoichiometricFormula() {
		return this.stoichiometricFormula;
	}

	public void setStoichiometricFormula(String stoichiometricFormula) {
		this.stoichiometricFormula = stoichiometricFormula;
	}

	@Column(name = "InChIKeyStem", length = 14)
	@Length(max = 14)
	public String getInChIkeyStem() {
		return this.inChIkeyStem;
	}

	public void setInChIkeyStem(String inChIkeyStem) {
		this.inChIkeyStem = inChIkeyStem;
	}

	@Column(name = "molec_name", length = 20)
	@Length(max = 20)
	public String getMolecName() {
		return this.molecName;
	}

	public void setMolecName(String molecName) {
		this.molecName = molecName;
	}

	@Column(name = "molec_name_html", length = 128)
	@Length(max = 128)
	public String getMolecNameHtml() {
		return this.molecNameHtml;
	}

	public void setMolecNameHtml(String molecNameHtml) {
		this.molecNameHtml = molecNameHtml;
	}

	@Column(name = "url", length = 65535)
	@Length(max = 65535)
	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}
