package com.MRS.NeckbeardEngine;
import java.util.ArrayList;

public class KeyValuePair {
     
     public String key;
     public String value;
     
     public KeyValuePair (String key, String value) {
          this.key = key;
          this.value = value;
     }
     
     public static ArrayList<KeyValuePair> removeDuplicates (ArrayList<KeyValuePair> pairs) {
          for (int i = 0; i < pairs.size(); i++) {
               KeyValuePair p1 = pairs.get(i);
               for (int j = i + 1; j < pairs.size(); j++) {
                    KeyValuePair p2 = pairs.get(j);
                    if (p1.key.equals(p2.key) && p1.value.equals(p2.value)) {
                         System.out.println(pairs.get(i).key + "removed");
                         pairs.remove(j);
                         j--;
                    }
               }
          }
          return pairs;
     }
     
}
