/*******************************************************************************
 * Copyright (c) 2011 Actuate Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *  Actuate Corporation  - initial API and implementation
 *******************************************************************************/
package org.eclipse.birt.report.data.adapter.internal.script;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

import org.eclipse.birt.core.exception.BirtException;
import org.eclipse.birt.data.engine.api.IBinding;
import org.eclipse.birt.data.engine.api.timefunction.ITimePeriod;
import org.eclipse.birt.data.engine.api.timefunction.TimePeriodType;
import org.eclipse.birt.report.data.adapter.api.AdapterException;
import org.eclipse.birt.report.data.adapter.api.DataSessionContext;
import org.eclipse.birt.report.data.adapter.api.timeFunction.IArgumentInfo;
import org.eclipse.birt.report.data.adapter.api.timeFunction.IBuildInBaseTimeFunction;
import org.eclipse.birt.report.data.adapter.api.timeFunction.ITimeFunction;
import org.eclipse.birt.report.data.adapter.api.timeFunction.TimeFunctionManager;
import org.eclipse.birt.report.data.adapter.impl.ModelAdapter;
import org.eclipse.birt.report.model.api.ComputedColumnHandle;
import org.eclipse.birt.report.model.api.Expression;
import org.eclipse.birt.report.model.api.activity.SemanticException;
import org.eclipse.birt.report.model.api.elements.DesignChoiceConstants;
import org.eclipse.birt.report.model.api.elements.structures.CalculationArgument;
import org.eclipse.birt.report.model.api.elements.structures.ComputedColumn;
import org.eclipse.birt.report.model.api.olap.CubeHandle;

public class TimeFunctionManagerTest extends TestCase
{
	private CubeHandle cube1;//with year, quarter, month
	
	public void testCalculationTypeInCube1() throws SemanticException, AdapterException
	{
		cube1 = ModelUtil.prepareCube1( );
		
		List levelsInxTab = new ArrayList( );
		levelsInxTab.add( "year" );
		List<ITimeFunction> function1 = TimeFunctionManager.getCalculationTypes( cube1.getDimension( "TimeDimension" ),levelsInxTab, true );
		for( int i=0; i< function1.size( ); i++ )
		{
			if( function1.get( i ).getName( ).equals( IBuildInBaseTimeFunction.PREVIOUS_MONTH ) )
			{
				assertTrue( function1.get( i ).getArguments( ).size( ) ==1 );
			}
			if( function1.get( i ).getName( ).equals( IBuildInBaseTimeFunction.TRAILING_N_PERIOD_FROM_N_PERIOD_AGO ) )
			{
				assertTrue( function1.get( i ).getArguments( ).size( ) ==4 );
				assertTrue( function1.get( i ).getArguments( ).get( 0 ).getName( ).equals( IArgumentInfo.N_PERIOD1 ) );
				assertTrue( function1.get( i ).getArguments( ).get( 1 ).getName( ).equals( IArgumentInfo.PERIOD_1 ) );
				assertTrue( function1.get( i ).getArguments( ).get( 1 ).getPeriodChoices( ).size( ) == 3 );
				assertTrue( function1.get( i ).getArguments( ).get( 2 ).getName( ).equals( IArgumentInfo.N_PERIOD2 ) );
				assertTrue( function1.get( i ).getArguments( ).get( 3 ).getName( ).equals( IArgumentInfo.PERIOD_2 ) );
			}
		}
		List<ITimeFunction> function2 = TimeFunctionManager.getCalculationTypes( cube1.getDimension( "TimeDimension" ),levelsInxTab, false );
		
		for( int i=0; i< function2.size( ); i++ )
		{
			if( function2.get( i ).getName( ).equals( IBuildInBaseTimeFunction.PREVIOUS_YEAR ) )
			{
				assertTrue( function2.get( i ).getArguments( ).size( ) ==1 );
			}
			if( function2.get( i ).getName( ).equals( IBuildInBaseTimeFunction.TRAILING_N_PERIOD_FROM_N_PERIOD_AGO ) )
			{
				assertTrue( function2.get( i ).getArguments( ).size( ) ==4 );
				assertTrue( function2.get( i ).getArguments( ).get( 0 ).getName( ).equals( IArgumentInfo.N_PERIOD1 ) );
				assertTrue( function2.get( i ).getArguments( ).get( 1 ).getName( ).equals( IArgumentInfo.PERIOD_1 ) );
				assertTrue( function2.get( i ).getArguments( ).get( 1 ).getPeriodChoices( ).size( ) == 1 );
				assertTrue( function2.get( i ).getArguments( ).get( 2 ).getName( ).equals( IArgumentInfo.N_PERIOD2 ) );
				assertTrue( function2.get( i ).getArguments( ).get( 3 ).getName( ).equals( IArgumentInfo.PERIOD_2 ) );
			}			
		}
	}
	
	public void testCalculationTypeInCube2() throws SemanticException, AdapterException
	{
		cube1 = ModelUtil.prepareCube1( );
		
		List levelsInxTab = new ArrayList( );
		levelsInxTab.add( "year" );
		levelsInxTab.add( "quarter" );
		List<ITimeFunction> function1 = TimeFunctionManager.getCalculationTypes( cube1.getDimension( "TimeDimension" ),levelsInxTab, true );
		assertTrue( function1.size( ) ==17 );
		
		for( int i=0; i< function1.size( ); i++ )
		{
			if( function1.get( i ).getName( ).equals( IBuildInBaseTimeFunction.PREVIOUS_MONTH ) )
			{
				assertTrue( function1.get( i ).getArguments( ).size( ) ==1 );
			}
			if( function1.get( i ).getName( ).equals( IBuildInBaseTimeFunction.TRAILING_N_PERIOD_FROM_N_PERIOD_AGO ) )
			{
				assertTrue( function1.get( i ).getArguments( ).size( ) ==4 );
				assertTrue( function1.get( i ).getArguments( ).get( 0 ).getName( ).equals( IArgumentInfo.N_PERIOD1 ) );
				assertTrue( function1.get( i ).getArguments( ).get( 1 ).getName( ).equals( IArgumentInfo.PERIOD_1 ) );
				assertTrue( function1.get( i ).getArguments( ).get( 1 ).getPeriodChoices( ).size( ) == 3 );
				assertTrue( function1.get( i ).getArguments( ).get( 2 ).getName( ).equals( IArgumentInfo.N_PERIOD2 ) );
				assertTrue( function1.get( i ).getArguments( ).get( 3 ).getName( ).equals( IArgumentInfo.PERIOD_2 ) );
			}
		}
		List<ITimeFunction> function2 = TimeFunctionManager.getCalculationTypes( cube1.getDimension( "TimeDimension" ),levelsInxTab, false );
		assertTrue( function2.size( ) ==12 );
		
		for( int i=0; i< function2.size( ); i++ )
		{
			if( function2.get( i ).getName( ).equals( IBuildInBaseTimeFunction.PREVIOUS_YEAR ) )
			{
				assertTrue( function2.get( i ).getArguments( ).size( ) ==1 );
			}
			if( function2.get( i ).getName( ).equals( IBuildInBaseTimeFunction.TRAILING_N_PERIOD_FROM_N_PERIOD_AGO ) )
			{
				assertTrue( function2.get( i ).getArguments( ).size( ) ==4 );
				assertTrue( function2.get( i ).getArguments( ).get( 0 ).getName( ).equals( IArgumentInfo.N_PERIOD1 ) );
				assertTrue( function2.get( i ).getArguments( ).get( 1 ).getName( ).equals( IArgumentInfo.PERIOD_1 ) );
				assertTrue( function2.get( i ).getArguments( ).get( 1 ).getPeriodChoices( ).size( ) == 2 );
				assertTrue( function2.get( i ).getArguments( ).get( 2 ).getName( ).equals( IArgumentInfo.N_PERIOD2 ) );
				assertTrue( function2.get( i ).getArguments( ).get( 3 ).getName( ).equals( IArgumentInfo.PERIOD_2 ) );
				assertTrue( function2.get( i ).getArguments( ).get( 1 ).getPeriodChoices( ).size( ) == 2 );
			}			
		}
	}
	
	public void testCalculationTypeInCube3() throws SemanticException, AdapterException
	{
		cube1 = ModelUtil.prepareCube2( );
		
		List levelsInxTab = new ArrayList( );
		levelsInxTab.add( "year" );
		levelsInxTab.add( "quarter" );
		List<ITimeFunction> function1 = TimeFunctionManager.getCalculationTypes( cube1.getDimension( "TimeDimension" ),levelsInxTab, true );
		assertTrue( function1.size( ) ==20 );
		
		ITimeFunction function = function1.get( 16 );
		assertTrue( function.getName( ).equals( "CURRENT PERIOD FROM N PERIODS AGO" ));
		
		List<IArgumentInfo> arguments = function.getArguments( );
		assert( arguments.size( ) ==3 );
		assertTrue( arguments.get( 0 ).getName( ).equals( IArgumentInfo.PERIOD_1 ) );
		assertTrue(arguments.get( 0 ).getPeriodChoices( ).size( ) == 3 );
		assertTrue( arguments.get( 1 ).getName( ).equals( IArgumentInfo.N_PERIOD2 ) );
		assertTrue( arguments.get( 2 ).getName( ).equals( IArgumentInfo.PERIOD_2 ) );
		assertTrue( arguments.get( 2 ).getPeriodChoices( ).size( ) == 4 );
	}
	
	public void testInvalidCalculationTypeInCube1() throws SemanticException, AdapterException
	{
		cube1 = ModelUtil.prepareCube1( );
		
		List levelsInxTab = new ArrayList( );
		levelsInxTab.add( "month" );
		List<ITimeFunction> function2 = TimeFunctionManager.getCalculationTypes( cube1.getDimension( "TimeDimension" ),levelsInxTab, false );
		assertTrue( function2.size( )==0 );
	}
	
	public void testAdapterTimeFunction() throws BirtException
	{
		ComputedColumnHandle computedHandle = ModelUtil.createComputedColumnHandle( );
		computedHandle.setAggregateFunction( "SUM" );
		computedHandle.setCalculationType( IBuildInBaseTimeFunction.TRAILING_N_PERIOD_FROM_N_PERIOD_AGO );
		computedHandle.setReferenceDateType( DesignChoiceConstants.REFERENCE_DATE_TYPE_FIXED_DATE );
		computedHandle.getReferenceDateValue( )
				.setExpression( new Expression( "\"2003-02-17\"", null ) );
		computedHandle.setProperty( ComputedColumn.TIME_DIMENSION_MEMBER,
				"dimension[\"time\"]" );
		CalculationArgument argument1 = new CalculationArgument();
		argument1.setName( IArgumentInfo.N_PERIOD1 );
		argument1.setValue( new Expression("10", null ) );
		
		CalculationArgument argument2 = new CalculationArgument();
		argument2.setName( IArgumentInfo.PERIOD_1 );
		argument2.setValue( new Expression( "MONTH", null ) );
		
		CalculationArgument argument3 = new CalculationArgument();
		argument3.setName( IArgumentInfo.N_PERIOD2 );
		argument3.setValue( new Expression("5", null ) );
		
		CalculationArgument argument4 = new CalculationArgument();
		argument4.setName( IArgumentInfo.PERIOD_2 );
		argument4.setValue( new Expression("YEAR", null ) );
		
		computedHandle.addCalculationArgument( argument1 );
		computedHandle.addCalculationArgument( argument2 );
		computedHandle.addCalculationArgument( argument3 );
		computedHandle.addCalculationArgument( argument4 );
		
		IBinding binding = new ModelAdapter( new DataSessionContext( DataSessionContext.MODE_DIRECT_PRESENTATION ) ).adaptBinding( computedHandle );
		ITimePeriod basePeriod = binding.getTimeFunction( ).getBaseTimePeriod( );
		ITimePeriod relativePeriod = binding.getTimeFunction( ).getRelativeTimePeriod( );
		
		assertTrue( basePeriod.getType( ).equals( TimePeriodType.MONTH ) );
		assertTrue( basePeriod.countOfUnit( ) == -10 );
		assertTrue( relativePeriod.getType( ).equals( TimePeriodType.YEAR ) );
		assertTrue( relativePeriod.countOfUnit( ) == -5 );		
	}
}
