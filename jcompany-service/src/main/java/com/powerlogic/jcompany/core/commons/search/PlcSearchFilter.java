package com.powerlogic.jcompany.core.commons.search;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PlcSearchFilter
{
   private static final int DEFAULT_INDEX_BEGIN = 1;

   private static final int DEFAULT_INDEX_MIDDLE = 2;

   private static final int DEFAULT_INDEX_END = 3;

   private static final String DEFAULT_TYPE = "string";

   public static enum Operator
   {
      EQ, NE, GT, LT, GTE, LTE, IN, NIN, TEXT, REGEX, EXISTS;

      private String lower;

      private Operator()
      {
         this.lower = name().toLowerCase();
      }

      public String lowerName()
      {
         return lower;
      }
   }

   private static final Pattern NAME_PATTERN = Pattern.compile("(\\w+)(:\\w+)?(:\\w+)?");

   public static PlcSearchFilter from(String name, Object value)
   {
      if (name == null || name.trim().isEmpty())
      {
         return null;
      }
      return new PlcSearchFilter(name, value);
   }

   private String name;

   private String type;

   private Operator operator;

   private Object value;

   private PlcSearchFilter(String name, Object value)
   {
      Matcher matcher = NAME_PATTERN.matcher(name.trim());

      if (matcher.matches())
      {
         this.name = matcher.group(DEFAULT_INDEX_BEGIN).trim();
         this.operator = valueOfOperator(matcher.group(DEFAULT_INDEX_MIDDLE), value);
         this.type = valueOfType(matcher.group(DEFAULT_INDEX_END), DEFAULT_TYPE);
      }
      else
      {
         throw new IllegalArgumentException(String.format(
                  "Invalid field pattern \"%s\" expected \"name(@type)?(:operator)?:\"", name));
      }
      this.value = parseValue(value);
   }

   private Object parseValue(Object value)
   {
      return value;
   }

   private String valueOfType(String type, String defaultValue)
   {
      return type != null ? type.substring(1).trim() : defaultValue;
   }

   private Operator valueOfOperator(String op, Object value)
   {
      Operator def = (value == null) ? Operator.EXISTS : Operator.EQ;
      return op != null ? Operator.valueOf(op.substring(1).trim().toUpperCase()) : def;
   }

   public String getName()
   {
      return name;
   }

   public String getType()
   {
      return type;
   }

   public Operator getOperator()
   {
      return operator;
   }

   public Object getValue()
   {
      return value;
   }

   @Override
   public String toString()
   {
      return name + ":" + type + ":" + operator.name() + "=" + value;
   }
}