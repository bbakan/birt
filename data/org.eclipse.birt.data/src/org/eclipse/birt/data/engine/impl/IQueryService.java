/*******************************************************************************
 * Copyright (c) 2004 Actuate Corporation.
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
package org.eclipse.birt.data.engine.impl;

import org.eclipse.birt.data.engine.core.DataException;
import org.mozilla.javascript.Scriptable;

/**
 *
 */
public interface IQueryService {

	/**
	 * @return
	 */
	boolean isClosed();

	/**
	 * @return
	 */
	int getNestedLevel();

	/**
	 * @return
	 */
	Scriptable getQueryScope();

	/**
	 * @return
	 * @throws DataException
	 */
	IExecutorHelper getExecutorHelper() throws DataException;

	/**
	 * @param nestedCount
	 * @return
	 */
	DataSetRuntime[] getDataSetRuntime(int nestedCount);

}
