/* Suffix tree solution to P1808
 *
 * translated from the python implementation (../../data-structures/suffix_node.py) 
 */

import java.util.HashMap;

public class P1808X {
    public static void main(String[] args) {
        String txt = args[0];
        String pat = args[1];

        boolean res = SuffixTreeNode.search(txt, pat);

        System.out.println(res);
    }
}

class SuffixTreeNode {
    HashMap<Character, SuffixTreeNode> children;
    SuffixTreeNode suffixLink;

    public SuffixTreeNode() {
        this.children   = new HashMap<Character, SuffixTreeNode>();
        this.suffixLink = this;
    }

    public SuffixTreeNode(SuffixTreeNode suffixLink) {
        this.children   = new HashMap<Character, SuffixTreeNode>();
        this.suffixLink = suffixLink;
    }

    public void addLink(Character c, SuffixTreeNode node) {
        this.children.put(c, node);
    }

    public static SuffixTreeNode buildSuffixTree(String txt) {
        if (txt == null)
            return null;

        int len = txt.length();
        if (len < 1)
            return null;

        SuffixTreeNode root    = new SuffixTreeNode();
        SuffixTreeNode longest = new SuffixTreeNode(root); 
        root.addLink(txt.charAt(0), longest);

        SuffixTreeNode current, previous;
        for (int i = 1; i < len; i++) {
            current  = longest;
            previous = null;

            char c = txt.charAt(i);

            while (!current.children.containsKey(c)) {
                SuffixTreeNode node = new SuffixTreeNode();
                current.addLink(c, node);

                if (previous != null)
                    previous.suffixLink = node;

                previous = node;
                current  = current.suffixLink;
            }

            if (current == root && current.children.get(c) == previous)
                previous.suffixLink = root;
            else
                previous.suffixLink = current.children.get(c);

            longest = longest.children.get(c);
        }

        return root;
    }

    public static boolean search(String txt, String pat) {
        SuffixTreeNode current = buildSuffixTree(txt);

        for (int i = 0; i < pat.length(); i++) {
            if (current.children.containsKey(pat.charAt(i)))
                current = current.children.get(pat.charAt(i));
            else
                return false;
        }
        
        return true;
    }
}
