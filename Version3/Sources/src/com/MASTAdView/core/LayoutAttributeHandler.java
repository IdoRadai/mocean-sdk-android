//
// Copyright (C) 2011, 2012 Mocean Mobile. All Rights Reserved.
//
package com.MASTAdView.core;

import java.util.Hashtable;

import com.MASTAdView.MASTAdRequest;

import android.content.Context;
import android.util.AttributeSet;


public class LayoutAttributeHandler
{
	// Attributes we support processing from an XML layout
	public static final String xml_layout_attribute_logLevel 				= "logLevel";
	public static final String xml_layout_attribute_site					= "site";
	public static final String xml_layout_attribute_zone					= "zone";
	public static final String xml_layout_attribute_test					= "test";
	public static final String xml_layout_attribute_maxSizeX				= "maxSizeX";
	public static final String xml_layout_attribute_maxSizeY				= "maxSizeY";
	public static final String xml_layout_attribute_type					= "type";
	public static final String xml_layout_attribute_locationDetection		= "locationDetection";
	public static final String xml_layout_attribute_locationMinWaitMillis	= "locationMinWaitMillis";
	public static final String xml_layout_attribute_locationMinMoveMeters	= "locationMinMoveMeters";
	public static final String xml_layout_attribute_adserverURL				= "adserverURL";
	public static final String xml_layout_attribute_updateTime				= "updateTime";
	public static final String xml_layout_attribute_latitude				= "latitude";
	public static final String xml_layout_attribute_longitude				= "longitude";
	public static final String xml_layout_attribute_ua						= "ua";
	public static final String xml_layout_attribute_customParameters		= "customParameters";
	
	
	private Context context;
	private AdViewContainer container;
	
	
	public LayoutAttributeHandler(Context c, AdViewContainer v)
	{
		context = c;
		container = v;
	}
	
	
	// Extract attributes from XML layout data, apply them to the request object or ad view container as appropriate
	public void processAttributes(AttributeSet attributes)
	{
		if (attributes != null)
		{
			Integer logLevel = getIntParameter(attributes.getAttributeValue(null, xml_layout_attribute_logLevel));
			if (logLevel!=null) container.setLogLevel(logLevel);
			
			Integer site = getIntParameter(attributes.getAttributeValue(null, xml_layout_attribute_site));
			if (site != null) container.adserverRequest.setProperty(MASTAdRequest.parameter_site, site);
			
			Integer zone = getIntParameter(attributes.getAttributeValue(null, xml_layout_attribute_zone));
			if (zone != null) container.adserverRequest.setProperty(MASTAdRequest.parameter_zone, zone);
			
			Boolean isTestModeEnabled = getBooleanParameter(attributes.getAttributeValue(null, xml_layout_attribute_test));
			if (isTestModeEnabled != null) container.adserverRequest.setProperty(MASTAdRequest.parameter_test, isTestModeEnabled);
			
			Integer maxSizeX = getIntParameter(attributes.getAttributeValue(null, xml_layout_attribute_maxSizeX));
			if (maxSizeX != null) container.adserverRequest.setProperty(MASTAdRequest.parameter_size_x, maxSizeX);
			
			Integer maxSizeY = getIntParameter(attributes.getAttributeValue(null, xml_layout_attribute_maxSizeY));
			if (maxSizeY != null) container.adserverRequest.setProperty(MASTAdRequest.parameter_size_y, maxSizeY);
			
			Integer type = getIntParameter(attributes.getAttributeValue(null, xml_layout_attribute_type));
			if (type != null) container.adserverRequest.setProperty(MASTAdRequest.parameter_type, type);
			
			String adserverURL = attributes.getAttributeValue(null, xml_layout_attribute_adserverURL);
			if (adserverURL != null) container.adserverRequest.setProperty(MASTAdRequest.parameter_ad_server_url, adserverURL);
			
			Integer updateTime = getIntParameter(attributes.getAttributeValue(null, xml_layout_attribute_updateTime));
			if (updateTime != null) container.setUpdateTime(updateTime);
			
			String latitude = attributes.getAttributeValue(null, xml_layout_attribute_latitude);
			if (latitude != null) container.adserverRequest.setProperty(MASTAdRequest.parameter_latitude, latitude);
			
			String longitude = attributes.getAttributeValue(null, xml_layout_attribute_longitude);
			if (longitude != null) container.adserverRequest.setProperty(MASTAdRequest.parameter_longitude, longitude);
			
			String ua = attributes.getAttributeValue(null, xml_layout_attribute_ua);
			if (longitude != null) container.adserverRequest.setProperty(MASTAdRequest.parameter_longitude, longitude);
			
			String customParameters = attributes.getAttributeValue(null, xml_layout_attribute_customParameters);
			Hashtable<String, String> cp = null;
			if(customParameters != null)
			{
				cp = new Hashtable<String, String>();
				String str[] = customParameters.split(",");
				for(int x=0; x < str.length/2; x++)
				{
					cp.put(str[x*2], str[x*2+1]);
				}	
			}
			if (cp != null) container.adserverRequest.setProperty(MASTAdRequest.parameter_custom, cp);
			
			Boolean locationDetection = getBooleanParameter(attributes.getAttributeValue(null, xml_layout_attribute_locationDetection));
			Integer locationMinWaitMillis = getIntParameter(attributes.getAttributeValue(null, xml_layout_attribute_locationMinWaitMillis));
			Float locationMinMoveMeters = getFloatParameter(attributes.getAttributeValue(null, xml_layout_attribute_locationMinMoveMeters));
			if (locationDetection != null) container.setLocationDetection(locationDetection, locationMinWaitMillis, locationMinMoveMeters);
		}
	}
	

	private Float getFloatParameter(String stringValue)
	{
		if(stringValue != null)
		{
			try
			{
				return  Float.parseFloat(stringValue);
			}
			catch (Exception e)
			{
				return null;
			}
		}
		else
		{
			return null;
		}
	}
	
	
	private Integer getIntParameter(String stringValue) 
	{
		if(stringValue != null) 
		{
			try
			{
				return (int) Long.decode(stringValue).longValue();
			}
			catch (Exception e)
			{
				return null;
			}
		} 
		else 
		{
			return null;
		}
	}
	
	
	private Boolean getBooleanParameter(String stringValue)
	{
		if(stringValue != null)
		{
			return Boolean.parseBoolean(stringValue);
		}
		else 
		{
			return null;
		}
	}
}
