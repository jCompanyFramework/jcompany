package com.powerlogic.jcompany.core.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public abstract class HttpUtil
{

   private final static String USER_AGENT = "Mozilla/5.0";

   public static final String sendGet(String url) throws Exception
   {
      URL obj = new URL(url);
      HttpURLConnection con = (HttpURLConnection) obj.openConnection();
      con.setRequestMethod("GET");
      // add request header
      con.setRequestProperty("User-Agent", USER_AGENT);
      con.setRequestProperty("Content-Type", "text/html; charset=utf-8");

      BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
      String inputLine;
      StringBuffer response = new StringBuffer();

      while ((inputLine = in.readLine()) != null)
      {
         response.append(inputLine);
      }
      in.close();
      return response.toString();

   }
}
