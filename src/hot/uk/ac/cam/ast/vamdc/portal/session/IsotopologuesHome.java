package uk.ac.cam.ast.vamdc.portal.session;

import java.util.List;

import javax.persistence.Query;

import uk.ac.cam.ast.vamdc.portal.entity.*;

import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityHome;

@Name("isotopologuesHome")
public class IsotopologuesHome extends EntityHome<Isotopologues> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5692686671120264329L;

	public void setIsotopologuesId(Integer id) {
		setId(id);
	}

	public Integer getIsotopologuesId() {
		return (Integer) getId();
	}

	@Override
	protected Isotopologues createInstance() {
		Isotopologues isotopologues = new Isotopologues();
		return isotopologues;
	}

	public void load() {
		if (isIdDefined()) {
			wire();
		}
	}

	public void wire() {
		getInstance();
	}

	public boolean isWired() {
		return true;
	}

	public Isotopologues getDefinedInstance() {
		return isIdDefined() ? getInstance() : null;
	}
	
	@SuppressWarnings("unchecked")
	public List<Isotopologues> findByMolecName(int moleculeId){
		Query query = this.getEntityManager().createNamedQuery("Isotopologues.findByMolecID");
		query.setParameter("molecId", moleculeId);
		List<Isotopologues> isotopologuesList = query.getResultList();
		return isotopologuesList;
	}

}
