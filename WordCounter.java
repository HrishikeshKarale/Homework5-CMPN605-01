/**
 * WordCounter.java
 * @author Hrishikesh Karale
 * @id hhk9433
 * @date 03/10/2015
 * @version v1.5
 * 
 * Assumption:
 * 			1) A words is a character string separated by space.
 * 			2) Words are case sensitive.
 */

import java.util.HashMap;
import java.util.TreeMap;
import java.util.Comparator;
import java.util.Map;
import java.util.Scanner;
import java.io.File;

/**
 * This class implements a Comparator and is used to sort using value.
 */
class MyComparator implements Comparator<String> 
{
    Map<String, Integer> word;
    
    /**
     * This is a parameterized constructor for Our class and this initializes
     * the map to our store map.
     */
    public MyComparator(Map<String, Integer> store)
    {
        this.word = store;
    }

    /**
     * This method is overridden from Comparator. It compares the values from 
     * our map.
     */
    public int compare(String a, String b)
    {
        if (word.get(a) >= word.get(b)) 
        {
            return -1;
        }
        else 
        {
            return 1;
        }
    }
}

/**
 * This is our class Containing main(). It stores words in map and then sorts 
 * then and does various other operations on it.
 */
public class WordCounter
{
    /**
     * This is our main() it reads our file and stores words which are seperated 
     * using our default delimiter as space. I then stores the words in a treemap
     * It stores the sorted map in another treemap called sorted_map displays it
     * and then diaplays the map sorted as per key from a-z. Then it prints 5 most
     * frequent word in reverse order. Later it prints words in rotated order.
     * Then we pring the words with length 5 and then we print word of max length.
     */
    public static void main(String []args)
    {
        //stores max length of word
        int max_length=0;
        //keeps track of no of words
        int count=0;
        //stores most frequent five words
        String map[]= new String[5];
        //stores words.
        String word= "";
        // scanner variable declared.
        Scanner scr;
        //declares a treemap which has String key and Integer values. Used to store words
        Map<String, Integer> store= new TreeMap<String, Integer>();
        //object of Mycomparator class which implements Comparator.
        MyComparator comp =  new MyComparator(store);
        //declares a treemap which has String key and Integer values. stored value sorted map
        TreeMap<String,Integer> sorted_map = new TreeMap<String,Integer>(comp);

        try
        {
            //loads file in scanner
            scr= new Scanner(new File("file.txt"));
            //loops if we have word after our current word.
            while (scr.hasNext())
            {  
                //loads word.
                word= scr.next();
                
                if(word.length()==1)
                	continue;
                int sub= 0;
                if( word.contains("/"))
                {
                	for(int i=0; i<word.length(); i++)
                		if(word.charAt(i)=='/')
                		{
                			sub=i;
                			break;
                		}
                	//used to store part of the word
                	String words= word.substring(0,sub);
                 	if (store.containsKey(words))
                        store.put(words,(store.get(words)+(Integer)1) );
                    else
                        store.put(words,1);
                 	word= word.substring(sub+1, word.length());
                	if (store.containsKey(word))
                        store.put(word,(store.get(word)+(Integer)1) );
                    else
                        store.put(word,1);
                }
                //checks if word already exists in our map.
                else
                {
                	if(word.contains("("))
                		word= word.substring(1,word.length()-1);
                	if (store.containsKey(word))
                    store.put(word,(store.get(word)+(Integer)1) );
                else
                    store.put(word,1);
                }
            }

            sorted_map.putAll(store);
 
            //prints map sorted by value/most frequent occurance.
            System.out.println("\n----MOST FREQUENT----:");
            for(Map.Entry<String, Integer> entry : sorted_map.entrySet())
            {
                if (count<5)
                    map[count++]= entry.getKey();
                System.out.println(entry.getKey()+" - "+entry.getValue());
            }


            //prints map sorted by key.
            System.out.println("\n----SORTED----:");
            for(Map.Entry<String, Integer> entry : store.entrySet())
            {
                if ((entry.getKey()).length()>max_length)
                    max_length= entry.getKey().length();
                System.out.println(entry.getKey()+" - "+entry.getValue());
            }

            //prints most 5 frequent word in reverse.
            System.out.println("\n----Reverse----");
            for(int i=0; i<=4; i++)
                System.out.println(new StringBuffer(map[i]).reverse().toString());
          
            //prints most 5 frequent word rotated.
            System.out.println("\n----Rotate----");
            for(int i=4; i>=0; i--)
                System.out.println(map[i]);
            
            //prints words of length 5
            System.out.println("\n----Length 5----");
            for(Map.Entry<String, Integer> entry : store.entrySet())
            {
                if((entry.getKey()).length()==5)
                    System.out.println(entry.getKey());
            }
            
            //prints words/word of max length
            System.out.println("\n----Strings of Max Length----");
            for(Map.Entry<String, Integer> entry : store.entrySet())
            {
                if ((entry.getKey()).length()==max_length)
                    System.out.println(entry.getKey());
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        } 
        finally
        {
           // scr.close();
        }
    }
}
