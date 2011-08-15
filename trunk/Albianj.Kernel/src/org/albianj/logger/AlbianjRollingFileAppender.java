package org.albianj.logger;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.RollingFileAppender;

public class AlbianjRollingFileAppender extends RollingFileAppender
{
	 public
	  synchronized
	  void setFile(String fileName, boolean append, boolean bufferedIO, int bufferSize)
	                                                                 throws IOException {
	    StringBuilder sbFileName = new StringBuilder();
		 if(fileName.endsWith(".log"))
	    {
			 SimpleDateFormat dateFormat=new SimpleDateFormat("yyyyMMddHHmmss");   
			 sbFileName.append(fileName.substring(0, fileName.length() - 4))
			 .append("_").append(dateFormat.format(new Date())).append(".log");	    	
	    }
		 
		 super.setFile(sbFileName.toString(), append, this.bufferedIO, this.bufferSize);
	  }
}
