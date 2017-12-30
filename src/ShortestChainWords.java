/*
* Assumption
* 1. Length of start and end is same
* */

/*
* 1. If length is not same then do we need to consider addition and deletion?
*
* */


import java.util.*;

public class ShortestChainWords {
    public static void main(String[] args) {
        String[] strs1 = new String[]{"hot", "dot", "dog", "lot", "cog", "log"};
        // Add all words as unvisited nodes
        Set<String> dict = new HashSet<>();
        for (int i = 0; i < strs1.length; i++) {
            dict.add(strs1[i]);
        }
        String start = "hot";
        String end = "cog";
        ShortestChainWords shortestChainWords = new ShortestChainWords();
        System.out.println(shortestChainWords.findShortestPath(dict, start, end));

        String[] strs2 = new String[]{"poon", "plee", "same", "poie", "plea", "plie","poin"};
        // Add all words as unvisited nodes
        dict.clear();
        for (int i = 0; i < strs2.length; i++) {
            dict.add(strs2[i]);
        }
        start = "poon";
        end = "plea";
        System.out.println(shortestChainWords.findShortestPath(dict, start, end));
    }

    // utilize graph traversal and find the minimum path
    public List<String> findShortestPath(Set<String> dict, String start, String end){

        // If start or end does not exits in dictionary then return as there is no existing path
        if(!(dict.contains(start) && dict.contains(end))){
            return null;
        }

        // Create a queue and add first element to queue
        Queue<LengthWord> queue = new LinkedList<>();
        LengthWord lengthWord = new LengthWord(start, 0);
        queue.add(lengthWord);
        dict.remove(start);
        lengthWord.getList().add(start);

        // While queue is not empty poll from the queue
        while(!queue.isEmpty()){
            LengthWord word = queue.poll();
            //Iterate all the words in dictionary
            Iterator<String> iterator = dict.iterator();
            while (iterator.hasNext()){
                String dictWord = iterator.next();
                if(isReachable(word.getWord(), dictWord)){
                    // Add the word to queue
                    LengthWord tempLengthWord  = new LengthWord(dictWord, word.getLength() + 1);
                    queue.add(tempLengthWord);
                    tempLengthWord.getList().addAll(word.getList());
                    tempLengthWord.getList().add(dictWord);

                    // Node is visited remove from the visited node set
                    iterator.remove();

                    // We reached end, return result
                    if(dictWord.equals(end)) {
                        System.out.println(tempLengthWord.getLength());
                        return tempLengthWord.getList();
                    }
                }
            }
        }
        return null;
    }


    // Validate if word is reachable or not
    public boolean isReachable(String s1, String s2){
        // Check if length equal
        s1 = s1.toLowerCase();
        s2 = s2.toLowerCase();
        if(s1.length() != s2.length())
            return false;
        // Initialize diff count to be zero
        int diffCount = 0;

        // Check for the number of character different
        for (int i = 0; i < s1.length(); i++)
        {
            if (s1.charAt(i) != s2.charAt(i))
                diffCount++;
            if (diffCount > 1)
                return false;
        }
        return diffCount == 1;
    }

    public void addToPath(List<String> result){

    }
}

class LengthWord{
    private String word;
    private List<String> list = new ArrayList<>();
    private int length;

    public LengthWord(String word, int length) {
        this.word = word;
        this.length = length;
    }

    public String getWord() {
        return word;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public List<String> getList() {
        return list;
    }

    @Override
    public String toString() {
        return "LengthWord{" +
                "word='" + word + '\'' +
                ", list=" + list +
                ", length=" + length +
                '}';
    }
}
