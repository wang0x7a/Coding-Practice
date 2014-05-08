#! /usr/bin/env python

class SuffixNode:
    def __init__(self, suffix_link = None):
        self.suffix_link = None
        self.children = {}

        if suffix_link is not None:
            self.suffix_link = suffix_link
        else:
            self.suffix_link = self

    def add_link(self, c, v):
        """link this node to node v via string c"""
        self.children[c] = v

def build_suffix_trie(s):
    assert len(s) > 0
    
    # explicitly build the two-node suffix tree
    root = SuffixNode()
    longest = SuffixNode(suffix_link = root)
    root.add_link(s[0], longest)

    # for evry character left in the string
    for c in s[1:]:
        current = longest
        previous = None

        while c not in current.children:
            # create new node r1 with transition current->c->r
            r = SuffixNode()
            current.add_link(c, r)

            # if we came from some previous node, make that node's
            # suffix link point here
            if previous is not None:
                previous.suffix_link = r

            previous = r
            current = current.suffix_link

        # make the last suffix link
        if current is root and current.children[c] is previous:
            previous.suffix_link = root
        else:
            previous.suffix_link = current.children[c]

        # move to the newly added child of the longest path
        # which is the new longest path
        longest = longest.children[c]

    return root

def search(txt, pat):
    current = build_suffix_trie(txt)

    for c in pat[0:]:
        if c in current.children:
            current = current.children[c]
        else:
            return False

    return True

if __name__ == "__main__":
    res = search("abba", "bb")
    #res = search("abba", "ba")
    #res = search("abaaabx", "aaa")

    print res
