package com.powerlogic.jcompany.core.messages;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class PlcMessageMap
{
   private Map<PlcMessageType, List<PlcMessageEntry>> messages = new HashMap<>();

   public Map<PlcMessageType, List<PlcMessageEntry>> getMessages()
   {
      return messages;
   }

   public void setMessages(Map<PlcMessageType, List<PlcMessageEntry>> messages)
   {
      this.messages = messages;
   }

   public void add(PlcMessageEntry message)
   {
      getTypeList(message.getType()).add(message);
   }

   public void addAll(PlcMessageMap messageMap)
   {
      for (Entry<PlcMessageType, List<PlcMessageEntry>> messages : messageMap.messages.entrySet())
      {
         getTypeList(messages.getKey()).addAll(messages.getValue());
      }
   }

   public void addAll(Collection<PlcMessageEntry> messages)
   {
      for (PlcMessageEntry message : messages)
      {
         add(message);
      }
   }

   public boolean contains(PlcMessageType type)
   {
      List<PlcMessageEntry> list = messages.get(type);
      return list != null && !list.isEmpty();
   }

   public List<PlcMessageEntry> get(PlcMessageType type)
   {
      List<PlcMessageEntry> list = messages.get(type);
      if (list == null || list.isEmpty())
      {
         return Collections.emptyList();
      }
      return new ArrayList<>(list);
   }

   public void clear()
   {
      messages.clear();
   }

   public void addMessage(PlcMessageKey key, PlcMessageType type)
   {
      addMessage(key, type, null);
   }

   public void addMessage(PlcMessageKey key, PlcMessageType type, String[] args)
   {
      add(new PlcMessageEntry(key, type, args));
   }

   protected List<PlcMessageEntry> getTypeList(PlcMessageType type)
   {
      List<PlcMessageEntry> list = messages.get(type);

      if (list == null)
      {
         messages.put(type, list = new LinkedList<PlcMessageEntry>());
      }
      return list;
   }
}
