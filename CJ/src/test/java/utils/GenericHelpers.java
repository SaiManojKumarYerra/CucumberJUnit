package utils;

import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import driverFactory.TestContext;
import hooks.Hooks;

public class GenericHelpers extends Hooks {

	public GenericHelpers(TestContext testContext) {
		super(testContext);
		// TODO Auto-generated constructor stub
	}

	/********************************************************
	 * Method Name		: getDateTime()
	 * Purpose			:
	 ********************************************************/
	public static String getDateTime(String dtFormat)
	{
		Date dt = null;
		SimpleDateFormat sdf = null;
		try {
			dt = new Date();
			sdf = new SimpleDateFormat(dtFormat);
			return sdf.format(dt);
		}catch(Exception e)
		{
			System.out.println("Exception in getDateTime() method. " + e);
			return null;
		}
		finally {
			dt = null;
			sdf = null;
		}
	}
	
	/*****************************************************
	 * Method Name				: readPropFile()
	 * 
	 *****************************************************/
	public static String readPropFile(String fileName, String strKey) {
		
		FileInputStream fin = null;
		Properties prop = null;
		try
		{
			fin = new FileInputStream(System.getProperty("user.dir")+"\\Configuration\\"+fileName);
			prop = new Properties();
			prop.load(fin);
			
			return prop.getProperty(strKey);
	
		}catch(Exception e)
		{
			System.out.println("Exception in readPropFile() : "+e);
			return null;
		}finally {
			try {
				fin.close();
				fin = null;
			}catch(Exception e)
			{
				System.out.println("Exception in readPropFile() method. " + e);
				return null;
			}
		}
	}
}
