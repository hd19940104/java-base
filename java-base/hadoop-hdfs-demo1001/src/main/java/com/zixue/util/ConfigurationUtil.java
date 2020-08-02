package com.zixue.util;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;


import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
/**
 * 读取配置文件
 * @author 一只会飞的小猴子
 *
 */
public class ConfigurationUtil
{
    private static Logger logger = Logger.getLogger(ConfigurationUtil.class);
    private static Map items = new HashMap();
    private static String CONFIG_FILE_NAME = "configuration.xml";

    static
    {
        loadConfig();
    }

    /**
     * 读入配置文件
     */
    private static void loadConfig()
    {
        try
        {
            InputStream is = ConfigurationUtil.class.getResourceAsStream("/" + CONFIG_FILE_NAME);
            if(is!=null)
            {
                SAXReader reader = new SAXReader();
                
                Document document = reader.read(is);
                Element systemElement = document.getRootElement();
                List catList = systemElement.elements("category");
                for (Iterator catIter = catList.iterator(); catIter.hasNext();)
                {
                    Element catElement = (Element) catIter.next();
                    String catName = catElement.attributeValue("name");
                    if (!"".equals(catName))
                    {
                        continue;
                    }
                    
                    List itemList = catElement.elements("item");
                    for (Iterator itemIter = itemList.iterator(); itemIter.hasNext();)
                    {
                        Element itemElement = (Element) itemIter.next();
                        String itemName = itemElement.attributeValue("name");
                        String value = itemElement.attributeValue("value");
                        if (!"".equals(itemName))
                        {
                            items.put(catName + "." + itemName, value);
                        }
                    }
                }
            }
        }
        catch (Exception ex)
        {
            logger.error("", ex);
        }
    }

    /**
     * 获得字串配置值
     *
     * @param name
     * @return
     */
    public static String getString(String name)
    {
        String value = (String) items.get(name);
        return (value == null) ? "" : value;
    }

    /**
     * 获得字串配置值，若为空，则返回缺省值
     *
     * @param name
     * @param defaultValue
     * @return
     */
    public static String getString(String name, String defaultValue)
    {
        String value = (String) items.get(name);
        if (value != null && value.length() > 0)
            return value;
        else
            return defaultValue;
    }

    /**
     * 获得整型配置值
     *
     * @param name
     * @return
     */
    public static int getInt(String name)
    {
        String value = getString(name);
        try
        {
            return Integer.parseInt(value);
        }
        catch (NumberFormatException ex)
        {
            return 0;
        }
    }

    /**
     * 获得整型配置值
     *
     * @param name
     * @return
     */
    public static int getInt(String name, int defaultValue)
    {
        String value = getString(name);
        try
        {
            return Integer.parseInt(value);
        }
        catch (NumberFormatException ex)
        {
        }
        return defaultValue;
    }

    /**
     * 获得布尔型配置值
     *
     * @param name
     * @return
     */
    public static boolean getBoolean(String name)
    {
        String value = getString(name);
        return Boolean.valueOf(value).booleanValue();
    }
    
    /**
     * 获得双精度浮点数配置值
     * 
     * @param name
     * @return
     */
    public static double getDouble(String name, double defaultValue)
    {
        String value = getString(name);
        try
        {
            return Double.parseDouble(value);
        }
        catch (NumberFormatException ex)
        {
        }
        return defaultValue;
    }
    
    public  Map getItems(){
        return items;
    }
}
