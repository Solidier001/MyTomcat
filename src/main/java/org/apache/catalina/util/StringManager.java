package org.apache.catalina.util;

import java.util.Hashtable;

/**
 * 1、每个类的错误消息需要存储
 * 2、properties文件用于存储错误消息
 * 3、大量properties文件难以维护
 * 4、将properties文件分到不同包
 * 5、StringManager托管消息
 * 6、StringManager单例化包内共享
 * */
public class StringManager {
    private  static Hashtable managers= new Hashtable();

    private String packageName;

    public StringManager(String packageName) {
        this.packageName=packageName;
    }

    public synchronized static StringManager getInstance(String packageName){
        StringManager mgr=(StringManager) managers.get(packageName);
        if (mgr==null){
            mgr=new StringManager(packageName);
            managers.put(packageName,mgr);
        }
        return mgr;
    }

    public String getString(String name) {
        return null;
    }
}
