package com.zhangjiaolong.frame.web;

import java.beans.PropertyEditorSupport;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

public class CustomDateEditor extends PropertyEditorSupport {
	private final static Logger logger = LoggerFactory.getLogger(CustomDateEditor.class);
	
	private static final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");  
    private static final DateFormat DATE_TIME_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
  
    private DateFormat dateFormat;  
    private boolean allowEmpty = true;  
  
    public CustomDateEditor() {  
    }  
  
    public CustomDateEditor(DateFormat dateFormat) {  
        this.dateFormat = dateFormat;  
    }  
  
    public CustomDateEditor(boolean allowEmpty){
    	this.allowEmpty = allowEmpty;
    }
    
    public CustomDateEditor(DateFormat dateFormat, boolean allowEmpty) {  
        this.dateFormat = dateFormat;  
        this.allowEmpty = allowEmpty;  
    }  
  
    /** 
     * Parse the Date from the given text, using the specified DateFormat. 
     */  
    @Override  
    public void setAsText(String text) throws IllegalArgumentException {  
        if (this.allowEmpty && !StringUtils.hasText(text)) {  
            setValue(null);  
        } else { 
        	Date parseDate = null;
            try {
                if(this.dateFormat != null)
                	parseDate = this.dateFormat.parse(text);
                else {  
                      try{
                    	  parseDate = DATE_TIME_FORMAT.parse(text);
                      }catch(ParseException ex){
                    	  parseDate = DATE_FORMAT.parse(text);
                      }
                }
                setValue(parseDate);
            } catch (ParseException ex) {
            	 logger.error("setAsText:parse异常2",ex);
                throw new IllegalArgumentException("Could not parse date: " + ex.getMessage(), ex);  
            }  
        }  
    }  
  
    /** 
     * Format the Date as String, using the specified DateFormat. 
     */  
    @Override  
    public String getAsText() {  
    	Date value = (Date) getValue();  
        DateFormat dateFormat = this.dateFormat;  
        if(dateFormat == null)  
            dateFormat = DATE_TIME_FORMAT;  
        return (value != null ? dateFormat.format(value) : "");  
    }    

}
