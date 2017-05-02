package com.wx.coding.tests;

import com.wx.coding.problems.TreeNode;
import com.wx.coding.problems.TreeNodeSerializer;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;

public class TreeNodeSerializationTest {
    private static TreeNode root;
    static {
        root = new TreeNode(1);
        root.right = new TreeNode(2);
    }

    @Test
    public void testDeserialize() {
        TreeNodeSerializer serializer = new TreeNodeSerializer();
        String serialized = serializer.serialize(root);

        TreeNode node = serializer.deserialize(serialized);

        AssertJUnit.assertEquals(serialized, serializer.serialize(node));
    }
}
