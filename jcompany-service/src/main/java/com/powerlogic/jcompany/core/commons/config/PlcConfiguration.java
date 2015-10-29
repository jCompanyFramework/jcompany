package com.powerlogic.jcompany.core.commons.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.annotation.PostConstruct;

public class PlcConfiguration
{

   private static final PlcConfiguration INSTANCE = new PlcConfiguration();

   private static final String CONFIG_PROPERTIES = "META-INF/configuration.properties";

   public static PlcConfiguration get()
   {
      return INSTANCE;
   }

   private Properties config;

   @PostConstruct
   public void init()
   {
      try
      {
         config = loadConfiguration();
      }
      catch (Exception e)
      {
         throw new IllegalStateException("Error in load config", e);
      }
   }

   protected Properties getConfig()
   {
      if (config == null)
      {
         init();
      }
      return config;
   }

   public String get(String key)
   {
      return get(key, null);
   }

   public String get(String key, String def)
   {
      return getConfig().getProperty(key, def);
   }

   public int getInt(String key)
   {
      return getInt(key, 0);
   }

   public int getInt(String key, int def)
   {
      String val = getConfig().getProperty(key);
      return val == null ? def : Integer.parseInt(val);
   }

   private synchronized Properties loadConfiguration() throws IOException
   {

      InputStream configuration = Thread.currentThread().getContextClassLoader().getResourceAsStream(CONFIG_PROPERTIES);

      if (configuration == null)
      {
         throw new IllegalStateException("Database configuration not found");
      }
      try
      {
         Properties properties = new Properties();
         properties.load(configuration);
         return properties;
      }
      finally
      {
         configuration.close();
      }
   }
}
