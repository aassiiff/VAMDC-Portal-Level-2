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
@Table(name = "IsotopologueNames", catalog = "vamdcPortalDB")
@NamedQueries({
    @NamedQuery(name = "IsotopologueNames.findAll", query = "SELECT i FROM IsotopologueNames i"),
    @NamedQuery(name = "IsotopologueNames.findById", query = "SELECT i FROM IsotopologueNames i WHERE i.id = :id"),
    @NamedQuery(name = "IsotopologueNames.findByIsoID", query = "SELECT i FROM IsotopologueNames i WHERE i.isoId = :isoId"),
    @NamedQuery(name = "IsotopologueNames.findByInChIKey", query = "SELECT i FROM IsotopologueNames i WHERE i.inChIkey = :inChIkey"),
    @NamedQuery(name = "IsotopologueNames.findByMolecID", query = "SELECT i FROM IsotopologueNames i WHERE i.molecId = :molecId"),
    @NamedQuery(name = "IsotopologueNames.findByIsoName", query = "SELECT i FROM IsotopologueNames i WHERE i.isoName = :isoName")})
public class IsotopologueNames implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3462153875019866947L;
	private Integer id;
	private Integer isoId;
	private String inChIkey;
	private Integer molecId;
	private String isoName;

	public IsotopologueNames() {
	}

	public IsotopologueNames(Integer isoId, String inChIkey, Integer molecId,
			String isoName) {
		this.isoId = isoId;
		this.inChIkey = inChIkey;
		this.molecId = molecId;
		this.isoName = isoName;
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

	@Column(name = "isoID")
	public Integer getIsoId() {
		return this.isoId;
	}

	public void setIsoId(Integer isoId) {
		this.isoId = isoId;
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

	@Column(name = "iso_name", length = 256)
	@Length(max = 256)
	public String getIsoName() {
		return this.isoName;
	}

	public void setIsoName(String isoName) {
		this.isoName = isoName;
	}

}
