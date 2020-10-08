package AED2_UT3;

import ClassesAED2.TArbolTrie;
import ClassesAED2.TArbolTrieHashMap;
import java.util.HashMap;
import java.util.Map;

public class PDs {
    public static void main (String [] args){
        //String[] words = {"hola","como","estas","hace","frio","la","bola","a","noche","casa","clara"};
        String[] words = {"hola","holas","hol","haz","holasa","holasetar","ho","las","el"};
        String[] search = {"hola","como","noche","casa","clara","nacho"};
        sortLength(words);
        wordCount(words, search);
        TArbolTrieHashMap trie = new TArbolTrieHashMap();
        for (String word : words) {
            trie.insertar(word, 0);
        }
        trie.predecir("ha").forEach((word) -> {System.out.println(word);});
    }
    
    public static void deleteNull (Map map){
        map.keySet().forEach((key) -> {
            if(map.get(key) == null)
                map.remove(key);
        });
    }
    
    public static Map<String, String> swap (Map<String, String> map){
        Map<String, String> nMap = new HashMap<>();
        map.entrySet().forEach((entry) -> {
            nMap.put(entry.getValue(), entry.getKey());
        });
        return nMap;
    }
    
    public static void sortLength (String[] words){
        TArbolTrie trie = new TArbolTrie();
        for (String word : words) {
            trie.insertar(word);
        }
        trie.imprimir();
    }   
    
    public static void wordCount(String[] words, String[] search){
        Map<String, Integer> map = new HashMap<>();
        for(String word : words){
            if(map.containsKey(word)){
                map.replace(word, map.get(word));
            } else {
                map.put(word, 1);
            }
        }
        
        for(String word : search) {
            if(map.get(word) == null){
                System.out.println(0);
            } else {
                System.out.println(map.get(word));
            }    
        }
    }
}