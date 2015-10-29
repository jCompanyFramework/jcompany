package com.powerlogic.jcompany.core.commons.search;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class PlcPagedResult<T>
{
   private long count;

   private int pages;

   private int page;

   private int pageSize;

   private List<T> list;

   public PlcPagedResult()
   {
      super();
   }

   public PlcPagedResult(PlcPagination<T> searchOptions, long count, List<T> result)
   {
      this.count = count;
      this.pages = (int) (count / searchOptions.getLimit() + (count % searchOptions.getLimit() > 0 ? 1 : 0));
      this.page = searchOptions.getPage();
      this.pageSize = result.size();
      this.list = result;
   }

   public long getCount()
   {
      return count;
   }

   public int getPages()
   {
      return pages;
   }

   public int getPage()
   {
      return page;
   }

   public int getPageSize()
   {
      return pageSize;
   }

   public List<T> getList()
   {
      return list;
   }
}
