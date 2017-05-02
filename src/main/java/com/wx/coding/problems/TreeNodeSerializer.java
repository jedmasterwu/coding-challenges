package com.wx.coding.problems;

import java.io.IOException;
import java.io.StringReader;

public class TreeNodeSerializer {
    /**
     * This method will be invoked first, you should design your own algorithm
     * to serialize a binary tree which denote by a root node to a string which
     * can be easily deserialized by your own "deserialize" method later.
     */
    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        serialize(root, sb);
        sb.setLength(sb.length() - 1);
        return sb.toString();
    }

    private void serialize(TreeNode root, StringBuilder sb) {
        if (root == null) {
            sb.append("#,");
            return;
        }

        sb.append(root.val).append(",");
        serialize(root.left, sb);
        serialize(root.right, sb);
    }

    /**
     * This method will be invoked second, the argument data is what exactly
     * you serialized at method "serialize", that means the data is not given by
     * system, it's given by your own serialize method. So the format of data is
     * designed by yourself, and deserialize it here as you serialize it in
     * "serialize" method.
     */
    public TreeNode deserialize(String data) {
        StringReader reader = new StringReader(data);
        return deserialize(reader);
    }

    private TreeNode deserialize(StringReader reader) {
        StringBuilder sb = new StringBuilder();
        char current;
        do {
            try {
                current = (char) reader.read();
                if (current == '#') {
                    int skipped = reader.read(); // read comma or eof
                    assert skipped == -1 || skipped == ',';
                    return null;
                }

                if (current != ',') {
                    sb.append(current);
                }
            } catch (IOException e) {
                break;
            }
        } while (current != (char) -1 && current != ',');

        int val = Integer.parseInt(sb.toString());
        TreeNode node = new TreeNode(val);
        node.left = deserialize(reader);
        node.right = deserialize(reader);
        return node;
    }
}
