package uk.ac.cam.ast.vamdc.portal.session;

import uk.ac.cam.ast.vamdc.portal.entity.*;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityHome;

@Name("isotopologueNamesHome")
public class IsotopologueNamesHome extends EntityHome<IsotopologueNames> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1390445781622479803L;

	public void setIsotopologueNamesId(Integer id) {
		setId(id);
	}

	public Integer getIsotopologueNamesId() {
		return (Integer) getId();
	}

	@Override
	protected IsotopologueNames createInstance() {
		IsotopologueNames isotopologueNames = new IsotopologueNames();
		return isotopologueNames;
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

	public IsotopologueNames getDefinedInstance() {
		return isIdDefined() ? getInstance() : null;
	}

}
