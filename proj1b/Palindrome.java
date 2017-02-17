/**
 * Created by Arjun on 2/7/2017.
 */
public class Palindrome {

    public static Deque<Character> wordToDeque(String word) {
        Deque<Character> wordDeque = new ArrayDequeSolution<>();
        for (int i = 0; i < word.length(); i++) {
            Character c = word.charAt(i);
            wordDeque.addLast(c);
        }
        return wordDeque;
    }

    public static boolean isPalindrome(String word) {
        if (word.length() == 0 || word.length() == 1) {
            return true;
        }
        Deque<Character> wordDeque = wordToDeque(word);
        for (int i = 0; i < wordDeque.size(); i++) {
            if (!(wordDeque.get(i)).equals(wordDeque.get(wordDeque.size() - (i + 1)))) {
                if (i != wordDeque.size() - (i + 1)) {
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean isPalindrome(String word, CharacterComparator cc) {
        if (word.length() == 0 || word.length() == 1) {
            return true;
        }
        Deque<Character> wordDeque = wordToDeque(word);
        for (int i = 0; i < wordDeque.size(); i++) {
            if (!cc.equalChars((wordDeque.get(i)), wordDeque.get(wordDeque.size() - (i + 1)))) {
                if (i != wordDeque.size() - (i + 1)) {
                    return false;
                }
            }
        }
        return true;
    }

}

