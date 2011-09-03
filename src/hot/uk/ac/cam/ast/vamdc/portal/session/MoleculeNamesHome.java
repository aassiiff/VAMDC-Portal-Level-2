package uk.ac.cam.ast.vamdc.portal.session;

import java.util.List;

import javax.persistence.Query;

import uk.ac.cam.ast.vamdc.portal.entity.*;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityHome;

@Name("moleculeNamesHome")
public class MoleculeNamesHome extends EntityHome<MoleculeNames> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5426427422819016270L;

	public void setMoleculeNamesId(Integer id) {
		setId(id);
	}

	public Integer getMoleculeNamesId() {
		return (Integer) getId();
	}

	@Override
	protected MoleculeNames createInstance() {
		MoleculeNames moleculeNames = new MoleculeNames();
		return moleculeNames;
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

	public MoleculeNames getDefinedInstance() {
		return isIdDefined() ? getInstance() : null;
	}
	
	@SuppressWarnings("unchecked")
	public List<MoleculeNames> findByMolecName(String moleculeName){
		Query query = this.getEntityManager().createNamedQuery("MoleculeNames.findByMolecName");
		query.setParameter("molecName", moleculeName);
		List<MoleculeNames> moleculeNamesList = query.getResultList();
		return moleculeNamesList;
	}

	@SuppressWarnings("unchecked")
	public List<MoleculeNames> findByMolecNameWildcard(String moleculeName){
		Query query = this.getEntityManager().createNamedQuery("MoleculeNames.findByMolecNameWildcard");
		query.setParameter("molecName", moleculeName + "%");
		List<MoleculeNames> moleculeNamesList = query.getResultList();
		return moleculeNamesList;
	}

}
