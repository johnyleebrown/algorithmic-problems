package string.trie;

/**
 * 208
 */
class ImplementTrie
{
    static class TrieNode
    {
        TrieNode[] nodes;
        boolean isEnd;
        TrieNode()
        {
            nodes = new TrieNode[26];
        }
        void addAt(char c)
        {
            nodes[c - 'a'] = new TrieNode();
        }
        TrieNode get(char c)
        {
            return nodes[c - 'a'];
        }
        boolean contains(char c)
        {
            return get(c) != null;
        }
        void setEnd()
        {
            isEnd = true;
        }
        boolean isEnd()
        {
            return isEnd;
        }
    }

    private TrieNode root;

    public ImplementTrie()
    {
        root = new TrieNode();
    }

    public void insert(String word)
    {
        TrieNode node = root;
        for (int i = 0; i < word.length(); i++)
        {
            char x = word.charAt(i);
            if (!node.contains(x))
            {
                node.addAt(x);
            }
            node = node.get(x);
        }
        node.setEnd();
    }

    public boolean search(String word)
    {
        TrieNode node = root;
        for (int i = 0; i < word.length(); i++)
        {
            char x = word.charAt(i);
            if (!node.contains(x))
            {
                return false;
            }
            node = node.get(x);
        }
        return node.isEnd();
    }

    public boolean startsWith(String prefix)
    {
        TrieNode node = root;
        for (int i = 0; i < prefix.length(); i++)
        {
            char x = prefix.charAt(i);
            if (!node.contains(x))
            {
                return false;
            }
            node = node.get(x);
        }
        return true;
    }
}

