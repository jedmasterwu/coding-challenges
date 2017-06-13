package com.wx.coding.problems;

import java.util.*;

public class FileSystem {

    private class Node {
        String content = null;
        Map<String, Node> map = null;

        Node(String content) {
            this.content = content;
            if (content == null) {
                map = new HashMap<>();
            }
        }
    }

    private Map<String, Node> root;

    public FileSystem() {
        root = new HashMap<>();
    }

    public List<String> ls(String path) {
        String[] dirs = path.split("/");
        Map<String, Node> current = root;
        for (String dir : dirs) {
            if (dir.isEmpty()) {
                continue;
            }
            current = current.get(dir).map;
        }
        List<String> result = current == null ? new ArrayList<>() : new ArrayList<>(current.keySet());
        if (current == null) {
            result.add(dirs[dirs.length - 1]);
        } else {
            Collections.sort(result);
        }
        return result;
    }

    public void mkdir(String path) {
        String[] dirs = path.split("/");
        Map<String, Node> current = root;
        for (String dir : dirs) {
            if (dir.isEmpty()) {
                continue;
            }
            Node node = current.get(dir);
            if (node == null) {
                node = new Node(null);
                current.put(dir, node);
            }
            current = node.map;
        }
    }

    public void addContentToFile(String filePath, String content) {
        String[] dirs = filePath.split("/");
        Map<String, Node> current = root;
        for (int i = 1; i < dirs.length - 1; i++) {
            current = current.get(dirs[i]).map;
        }
        String filename = dirs[dirs.length - 1];
        Node file = current.get(filename);
        if (file == null) {
            current.put(filename, new Node(content));
        } else {
            file.content += content;
        }
    }

    public String readContentFromFile(String filePath) {
        String[] dirs = filePath.split("/");
        Map<String, Node> current = root;
        for (int i = 1; i < dirs.length - 1; i++) {
            current = current.get(dirs[i]).map;
        }

        return current.get(dirs[dirs.length - 1]).content;
    }

    public void print(List<String> dirs) {
        StringBuilder sb = new StringBuilder();
        sb.append('[');
        for (int i = 0; i < dirs.size(); i++) {
            sb.append(dirs.get(i));
            if (i != dirs.size() - 1) {
                sb.append(',').append(' ');
            }
        }
        sb.append(']');
        System.out.println(sb.toString());
    }
}
