package com.powerlogic.jcompany.core.commons.search;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class PlcPagination<T>
{
   private static final int DEFAULT_SEARCH_LIMIT = 50;

   public static <T> PlcPagination<T> to(Class<T> type)
   {
      return new PlcPagination<>(type);
   }

   private Class<T> type;

   private Map<String, PlcSearchFilter> filters;

   private List<PlcSearchSort> ordination;

   private int page = 1;

   private int limit = DEFAULT_SEARCH_LIMIT;

   private int offset = 0;

   protected PlcPagination(Class<T> type)
   {
      this.type = type;
   }

   public PlcPagination<T> limit(int size)
   {
      page = 1;
      limit = size;
      offset = 0;
      return this;
   }

   public PlcPagination<T> paged(int pageNumber, int pageLimit)
   {
      page = Math.max(1, pageNumber);
      limit = Math.max(0, pageLimit);
      offset = (page - 1) * limit;
      return this;
   }

   public PlcPagination<T> orderBy(String order)
   {
      this.ordination = PlcSearchSort.split(order);

      return this;
   }

   public PlcPagination<T> filter(String name, Object value)
   {
      PlcSearchFilter filter = PlcSearchFilter.from(name, value);
      if (filter != null)
      {
         if (filters == null)
         {
            filters = new LinkedHashMap<String, PlcSearchFilter>();
         }
         filters.put(filter.getName(), filter);
      }
      return this;
   }

   public Class<T> getType()
   {
      return type;
   }

   public List<PlcSearchFilter> getFilters()
   {
      return filters == null ? Collections.<PlcSearchFilter> emptyList() : new ArrayList<PlcSearchFilter>(filters.values());
   }

   public List<PlcSearchSort> getOrdination()
   {
      return ordination == null ? Collections.<PlcSearchSort> emptyList() : new ArrayList<PlcSearchSort>(ordination);
   }

   public int getPage()
   {
      return page;
   }

   public int getLimit()
   {
      return limit;
   }

   public int getOffset()
   {
      return offset;
   }

   @Override
   public String toString()
   {
      return "SearchOptions [type=" + type + ", filters=" + filters + ", ordination=" + ordination + ", page=" + page
               + ", limit=" + limit + ", offset=" + offset + "]";
   }
}
