//
// Copyright (C) 2011, 2012 Mocean Mobile. All Rights Reserved. 
//
package com.MASTAdView.core;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import android.content.Context;


public class FileUtils
{
	
	public static String md5(String data)
	{
		try 
		{
			MessageDigest digester = MessageDigest.getInstance("MD5");
			digester.update(data.getBytes());
			byte[] messageDigest = digester.digest();
			return byteArrayToHexString(messageDigest);
		}
		catch(NoSuchAlgorithmException e) {	}
		
		return null;
	}
	
	
	public static String byteArrayToHexString(byte[] array)
	{
		StringBuilder sb = new StringBuilder(); 
		
		for (byte b : array) 
		{ 
		    sb.append(String.format("%02x", b)); 
		} 
		
		return sb.toString();
	}

	
	public static String readTextFromJar(Context context, String source)
	{
		System.out.println("Reading file from jar: " + source);
		
		try
		{	
			StringBuffer sb = new StringBuffer();
			InputStream in = FileUtils.class.getResourceAsStream(source);
			BufferedReader r = new BufferedReader(new InputStreamReader(in));  
			String line; 
			while ((line = r.readLine()) != null)
			{ 
			    sb.append(line);
			    sb.append("\n");
			} 
			
			return sb.toString();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		return null;
	}
	
	
	public static String copyTextFromJarIntoAssetDir(Context context, String alias, String source)
	{
		System.out.println("Copying file to assets: " + source);
		
		try
		{
			InputStream in = FileUtils.class.getResourceAsStream(source);
			File writeFile = new File(context.getFilesDir(), alias);
			return writeToDisk(in, writeFile);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		return null;
	}

	
	public static String writeToDisk(InputStream in, File writeFile) throws IllegalStateException, IOException 
	{
		int i = 0;
		byte buff[] = new byte[1024];
		FileOutputStream out = new FileOutputStream(writeFile);

		if (in != null)
		{
			do
			{
				int numread = in.read(buff);
				if (numread <= 0)
					break;
	
				out.write(buff, 0, numread);
				i++;
			}
			while (true);
			
			in.close();
		}
		else
		{
			throw new IOException("NULL input stream in writeToDisk");
		}
		
		out.flush();
		out.close();
		return writeFile.getAbsolutePath();
	}
}
