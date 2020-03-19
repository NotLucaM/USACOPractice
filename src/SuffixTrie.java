import org.junit.Test;
import static org.junit.Assert.*;

public class SuffixTrie {

    static final int ALPHABET_SIZE = 26;
    private Node root;

    public SuffixTrie() {
        this.root = new Node();
    }

    public void insert(String key) {
        int level;
        int length = key.length();
        int index;

        Node pCrawl = root;

        for (level = 0; level < length; level++) {
            index = key.charAt(level) - 'a';
            if (pCrawl.children[index] == null)
                pCrawl.children[index] = new Node();

            pCrawl = pCrawl.children[index];
        }

        pCrawl.endOfWord = true;
    }

    public boolean search(String key) {
        int level;
        int length = key.length();
        int index;
        Node pCrawl = root;

        for (level = 0; level < length; level++) {
            index = key.charAt(level) - 'a';

            if (pCrawl.children[index] == null)
                return false;

            pCrawl = pCrawl.children[index];
        }

        return (pCrawl != null && pCrawl.endOfWord);
    }

    @Test
    public void testTrie() {
        SuffixTrie suffixTrie = new SuffixTrie();

        suffixTrie.insert("hi");
        assertTrue(suffixTrie.search("hi"));
        assertTrue(suffixTrie.search("i"));
        assertTrue(suffixTrie.search("h"));
    }

    private static class Node {
        Node[] children = new Node[ALPHABET_SIZE];

        boolean endOfWord;

        Node() {
            endOfWord = false;
            for (int i = 0; i < ALPHABET_SIZE; i++) {
                children[i] = null;
            }
        }
    }
}
