package com.wx.coding.tests;

import com.wx.coding.problems.FileSystem;
import org.testng.annotations.Test;

public class FileSystemTest {

    @Test
    public void testFileSystem() {
        FileSystem fs = new FileSystem();

//        fs.ls("/");
//        fs.mkdir("/a/b/c");
//        fs.addContentToFile("/a/b/c/d", "Hello");
//        fs.ls("/");
//        fs.readContentFromFile("/a/b/c/d");

        fs.mkdir("/goowmfn");
        fs.print(fs.ls("/goowmfn"));
        fs.print(fs.ls("/"));
        fs.mkdir("/z");
        fs.print(fs.ls("/"));
        fs.print(fs.ls("/"));
        fs.addContentToFile("/goowmfn/c","shetopcy");
        fs.print(fs.ls("/z"));
        fs.print(fs.ls("/goowmfn/c"));
        fs.print(fs.ls("/goowmfn"));
    }
}
