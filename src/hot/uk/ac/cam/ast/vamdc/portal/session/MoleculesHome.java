package uk.ac.cam.ast.vamdc.portal.session;

import java.util.List;

import javax.persistence.Query;

import uk.ac.cam.ast.vamdc.portal.entity.*;

import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityHome;

@Name("moleculesHome")
public class MoleculesHome extends EntityHome<Molecules> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3774409357664814644L;

	public void setMoleculesId(Integer id) {
		setId(id);
	}

	public Integer getMoleculesId() {
		return (Integer) getId();
	}

	@Override
	protected Molecules createInstance() {
		Molecules molecules = new Molecules();
		return molecules;
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

	public Molecules getDefinedInstance() {
		return isIdDefined() ? getInstance() : null;
	}
	
	@SuppressWarnings("unchecked")
	public List<Molecules> findByStoichiometricFormula(String stoichiometricFormula){
		Query query = this.getEntityManager().createNamedQuery("Molecules.findByStoichiometricFormula");
		query.setParameter("stoichiometricFormula", stoichiometricFormula);
		List<Molecules> stoichiometricFormulaList = query.getResultList();
		return stoichiometricFormulaList;
	}
	
	@SuppressWarnings("unchecked")
	public List<Molecules> findByStoichiometricFormulaWildcard(String stoichiometricFormula){
		Query query = this.getEntityManager().createNamedQuery("Molecules.findByStoichiometricFormulaWildcard");
		query.setParameter("stoichiometricFormula", stoichiometricFormula + "%");
		List<Molecules> stoichiometricFormulaList = query.getResultList();
		return stoichiometricFormulaList;
	}

}
