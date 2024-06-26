/*******************************************************************************
 * Copyright (c) 2004, 2009 Actuate Corporation.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * https://www.eclipse.org/legal/epl-2.0/.
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 *
 * Contributors:
 *  Actuate Corporation  - initial API and implementation
 *******************************************************************************/

package org.eclipse.birt.report.engine.api;

import org.eclipse.birt.core.archive.IDocArchiveReader;

/**
 *
 *
 */
public interface ICubeExtractionResults extends IExtractionResults {

	/**
	 * get the archive reader of the report document.
	 *
	 * @return
	 */
	IDocArchiveReader getReportDocReader();

}
