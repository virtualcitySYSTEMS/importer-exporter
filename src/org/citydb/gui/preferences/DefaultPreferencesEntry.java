/*
 * 3D City Database - The Open Source CityGML Database
 * http://www.3dcitydb.org/
 * 
 * (C) 2013 - 2015,
 * Chair of Geoinformatics,
 * Technische Universitaet Muenchen, Germany
 * http://www.gis.bgu.tum.de/
 * 
 * The 3D City Database is jointly developed with the following
 * cooperation partners:
 * 
 * virtualcitySYSTEMS GmbH, Berlin <http://www.virtualcitysystems.de/>
 * M.O.S.S. Computer Grafik Systeme GmbH, Muenchen <http://www.moss.de/>
 * 
 * The 3D City Database Importer/Exporter program is free software:
 * you can redistribute it and/or modify it under the terms of the
 * GNU Lesser General Public License as published by the Free
 * Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Lesser General Public License for more details.
 */
package org.citydb.gui.preferences;

import org.citydb.api.plugin.extension.preferences.PreferencesEntry;
import org.citydb.api.plugin.extension.preferences.PreferencesEvent;

public class DefaultPreferencesEntry extends PreferencesEntry {
	public AbstractPreferencesComponent component;
	
	public DefaultPreferencesEntry(AbstractPreferencesComponent component) {
		this.component = component;
	}
	
	@Override
	public boolean isModified() {
		return component.isModified();
	}

	@Override
	public boolean handleEvent(PreferencesEvent event) {
		switch (event) {
		case APPLY_SETTINGS:
			component.setSettings();
			break;
		case RESTORE_SETTINGS:
			component.loadSettings();
			break;
		case SET_DEFAULT_SETTINGS:
			component.resetSettings();
			break;
		}
		
		return true;
	}

	@Override
	public String getLocalizedTitle() {
		return component.getTitle();
	}

	@Override
	public final AbstractPreferencesComponent getViewComponent() {
		return component;
	}
	
	@Override
	public final void addChildEntry(PreferencesEntry child) {
		if (!(child instanceof DefaultPreferencesEntry))
			throw new IllegalArgumentException("Only DefaultPreferencesEntry instances are allowed as child entries.");
		
		super.addChildEntry(child);
	}

	public void doTranslation() {
		component.doTranslation();
	}

}
