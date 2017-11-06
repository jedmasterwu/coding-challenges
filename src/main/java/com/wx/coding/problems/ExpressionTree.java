package com.wx.coding.problems;

import java.util.Stack;

public class ExpressionTree {

    public ExpressionTreeNode build(String[] expression) {
        Stack<ExpressionTreeNode> nums = new Stack<>();
        Stack<ExpressionTreeNode> ops = new Stack<>();

        boolean evaluate = false;
        for (String str : expression) {
            if (str.equals("(")) {
                evaluate = false;
            } else if ("*/".contains(str)) {
                evaluate = true;
                ops.push(new ExpressionTreeNode(str));
            } else if ("+-".contains(str)) {
                ops.push(new ExpressionTreeNode(str));
            } else if (str.equals(")")) {
                evaluate = true;
            } else {
                nums.push(new ExpressionTreeNode(str));
            }

            if (evaluate) {
                evaluate = false;
                ExpressionTreeNode eval = ops.pop();
                eval.right = nums.pop();
                eval.left = nums.pop();
                nums.push(eval);
            }
        }

        while (!ops.isEmpty()) {
            ExpressionTreeNode eval = ops.pop();
            eval.right = nums.pop();
            eval.left = nums.pop();
            nums.push(eval);
        }

        return nums.peek();
    }

    private class ExpressionTreeNode {
        String symbol;
        ExpressionTreeNode left, right;

        ExpressionTreeNode(String symbol) {
            this.symbol = symbol;
            this.left = this.right = null;
        }
    }
}
