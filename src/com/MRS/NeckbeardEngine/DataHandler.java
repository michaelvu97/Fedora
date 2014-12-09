package com.MRS.NeckbeardEngine;
import java.io.*;
import java.util.ArrayList;
public class DataHandler {
     
     public static ArrayList<KeyValuePair> parseFile (File f) {
          /*
           * Returns a list of key value pairs, based on a file.
           */
          FileReader fr = null;
          BufferedReader br = null;
          try {
               fr = new FileReader(f);
               
          } catch (IOException e) {
               e.printStackTrace();
          }
          if (fr != null) {
               br = new BufferedReader(fr);
          }
          
          ArrayList<KeyValuePair> pairs = new ArrayList<KeyValuePair>();
          
          String s;
          try {
               while ((s = br.readLine ())!=null) {
                    /*
                     * Line by line parsing
                     */
                    String key = "";
                    String value = "";
                    int i = 1;
                    if (s.charAt(0) == '[') {
                         while (s.charAt(i)!=']') {
                              key = key + s.charAt(i);
                              i++;
                         }
                         i += 1;
                         while (i < s.length()) {
                              value = value + s.charAt(i);
                              i++;
                         }
                         pairs.add(new KeyValuePair(key,value));
                    } else {
                         break;    
                    }
               }
               br.close();
          } catch (IOException e) {
               e.printStackTrace();
          }
          return KeyValuePair.removeDuplicates(pairs);
     }
     
     public static void addPairsToFile(ArrayList<KeyValuePair> pairs, File f) {
          /*
           * Writes the pairs to a file
           */
          FileWriter fw;
          PrintWriter pw;
          pairs = KeyValuePair.removeDuplicates(pairs);
          try {
               fw = new FileWriter(f);
               pw = new PrintWriter(fw);
               
               ArrayList<String> strings = new ArrayList<String>();
               for (int i = 0; i < pairs.size(); i++) {
                    KeyValuePair k = pairs.get(i);
                    strings.add("["+k.key+"]"+k.value);
               }
               for (int i = 0; i < strings.size(); i++) {
                    pw.println(strings.get(i));
               }
               pw.close();
               
          } catch (IOException e) {
               e.printStackTrace();
          }
          
          
     }
     
}
