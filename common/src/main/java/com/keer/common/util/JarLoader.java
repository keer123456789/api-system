package com.keer.common.util;


import com.keer.common.transfer.JarTest;

import java.io.File;
import java.io.FileFilter;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;

/**
 * 提供Jar隔离的加载机制，会把传入的路径、及其子路径、以及路径中的jar文件加入到class path。
 */
public class JarLoader extends URLClassLoader {
    public JarLoader(String[] paths) {
        this(paths, JarLoader.class.getClassLoader());
    }

    public JarLoader(String[] paths, ClassLoader parent) {
        super(getURLs(paths), parent);
    }

    private static URL[] getURLs(String[] paths) {
        if (paths == null || paths.length == 0) {
            throw new IllegalArgumentException("jar包路径不能为空.");
        }
        List<String> dirs = new ArrayList<String>();
        for (String path : paths) {
            dirs.add(path);
            JarLoader.collectDirs(path, dirs);
        }

        List<URL> urls = new ArrayList<URL>();
        for (String path : dirs) {
            urls.addAll(doGetURLs(path));
        }

        return urls.toArray(new URL[0]);
    }

    private static void collectDirs(String path, List<String> collector) {
        if (null == path || path.equals("")) {
            return;
        }

        File current = new File(path);
        if (!current.exists() || !current.isDirectory()) {
            return;
        }

        for (File child : current.listFiles()) {
            if (!child.isDirectory()) {
                continue;
            }

            collector.add(child.getAbsolutePath());
            collectDirs(child.getAbsolutePath(), collector);
        }
    }

    private static List<URL> doGetURLs(final String path) {
        if (path == null || path.equals("")) {
            throw new IllegalArgumentException(path + " dir not find");
        }
        File jarPath = new File(path);
        if (!jarPath.exists() || !jarPath.isDirectory()) {
            throw new IllegalArgumentException("jar包路径必须存在且为目录");
        }


        /* set filter */
        FileFilter jarFilter = new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return pathname.getName().endsWith(".jar");
            }
        };

        /* iterate all jar */
        File[] allJars = new File(path).listFiles(jarFilter);
        List<URL> jarURLs = new ArrayList<URL>(allJars.length);

        for (int i = 0; i < allJars.length; i++) {
            try {
                jarURLs.add(allJars[i].toURI().toURL());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return jarURLs;
    }

    public static void main(String[] args) throws Exception, InstantiationException {
        JarLoader loader = new JarLoader(new String[]{"C:\\code\\api-system\\out\\artifacts\\jartest_jar"});
        Class<?> transformerClass =loader.loadClass("com.keer.jartest.JartestImpl");
        JarTest jarTest= (JarTest) transformerClass.newInstance();
        System.out.println(jarTest.test("sb"));
    }
}
