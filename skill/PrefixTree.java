package skill;

/**
 * leetcode 208 middle 实现前缀树
 * 
 * Trie（发音类似 "try"）或者说 前缀树 是一种树形数据结构，用于高效地存储和检索字符串数据集中的键。
 * 这一数据结构有相当多的应用情景，例如自动补完和拼写检查。
 */
public class PrefixTree {
    private PrefixTree[] children;
    private boolean isEnd;

    public PrefixTree() {
        children = new PrefixTree[26];
        isEnd = false;
    }

    public void insert(String word) {
        PrefixTree node = this;

        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            int index = ch - 'a';
            if (node.children[index] == null) {
                node.children[index] = new PrefixTree();
            }
            node = node.children[index]; // 相当于往下走一步
        }

        node.isEnd = true;
    }

    public boolean search(String word) {
        PrefixTree node = searchPrefix(word);
        return node != null && node.isEnd;
    }

    /**
     * 如果之前已经插入的字符串 word 的前缀之一为 prefix ，返回 true ；否则，返回 false
     * 
     * @param prefix
     * @return
     */
    public boolean startsWith(String prefix) {
        return searchPrefix(prefix) != null;
    }

    private PrefixTree searchPrefix(String prefix) {
        PrefixTree node = this;
        for (int i = 0; i < prefix.length(); i++) {
            char ch = prefix.charAt(i);
            int index = ch - 'a';
            if (node.children[index] == null) {
                return null;
            }

            node = node.children[index];
        }

        return node;
    }
}
