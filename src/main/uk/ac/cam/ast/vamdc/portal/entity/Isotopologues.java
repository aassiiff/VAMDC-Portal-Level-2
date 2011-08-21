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
@Table(name = "Isotopologues", catalog = "vamdcPortalDB")
@NamedQueries({
    @NamedQuery(name = "Isotopologues.findAll", query = "SELECT i FROM Isotopologues i"),
    @NamedQuery(name = "Isotopologues.findById", query = "SELECT i FROM Isotopologues i WHERE i.id = :id"),
    @NamedQuery(name = "Isotopologues.findByInChI", query = "SELECT i FROM Isotopologues i WHERE i.inChI = :inChI"),
    @NamedQuery(name = "Isotopologues.findByInChIKey", query = "SELECT i FROM Isotopologues i WHERE i.inChIkey = :inChIkey"),
    @NamedQuery(name = "Isotopologues.findByMolecID", query = "SELECT i FROM Isotopologues i WHERE i.molecId = :molecId"),
    @NamedQuery(name = "Isotopologues.findByAbundance", query = "SELECT i FROM Isotopologues i WHERE i.abundance = :abundance"),
    @NamedQuery(name = "Isotopologues.findByIsoName", query = "SELECT i FROM Isotopologues i WHERE i.isoName = :isoName"),
    @NamedQuery(name = "Isotopologues.findByIsoNameHtml", query = "SELECT i FROM Isotopologues i WHERE i.isoNameHtml = :isoNameHtml")})
public class Isotopologues implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2806097618040687668L;
	private Integer id;
	private String inChI;
	private String inChIkey;
	private Integer molecId;
	private Double abundance;
	private String cml;
	private String isoName;
	private String isoNameHtml;
	private String url;

	public Isotopologues() {
	}

	public Isotopologues(String inChI, String inChIkey, Integer molecId,
			Double abundance, String cml, String isoName, String isoNameHtml,
			String url) {
		this.inChI = inChI;
		this.inChIkey = inChIkey;
		this.molecId = molecId;
		this.abundance = abundance;
		this.cml = cml;
		this.isoName = isoName;
		this.isoNameHtml = isoNameHtml;
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

	@Column(name = "InChI", length = 256)
	@Length(max = 256)
	public String getInChI() {
		return this.inChI;
	}

	public void setInChI(String inChI) {
		this.inChI = inChI;
	}

	@Column(name = "InChIKey", length = 27)
	@Length(max = 27)
	public String getInChIkey() {
		return this.inChIkey;
	}

	public void setInChIkey(String inChIkey) {
		this.inChIkey = inChIkey;
	}

	@Column(name = "molecID")
	public Integer getMolecId() {
		return this.molecId;
	}

	public void setMolecId(Integer molecId) {
		this.molecId = molecId;
	}

	@Column(name = "abundance", precision = 22, scale = 0)
	public Double getAbundance() {
		return this.abundance;
	}

	public void setAbundance(Double abundance) {
		this.abundance = abundance;
	}

	@Column(name = "cml", length = 65535)
	@Length(max = 65535)
	public String getCml() {
		return this.cml;
	}

	public void setCml(String cml) {
		this.cml = cml;
	}

	@Column(name = "iso_name", length = 128)
	@Length(max = 128)
	public String getIsoName() {
		return this.isoName;
	}

	public void setIsoName(String isoName) {
		this.isoName = isoName;
	}

	@Column(name = "iso_name_html", length = 512)
	@Length(max = 512)
	public String getIsoNameHtml() {
		return this.isoNameHtml;
	}

	public void setIsoNameHtml(String isoNameHtml) {
		this.isoNameHtml = isoNameHtml;
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
