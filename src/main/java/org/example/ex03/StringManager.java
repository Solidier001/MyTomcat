package org.example.ex03;

import java.util.Hashtable;

public class StringManager {
    private  static Hashtable managers= new Hashtable();

    public StringManager(String packageName) {

    }

    public synchronized static StringManager getInstance(String packageName){
        StringManager mgr=(StringManager) managers.get(packageName);
        if (mgr==null){
            mgr=new StringManager(packageName);
            managers.put(packageName,mgr);
        }
        return mgr;
    }
}
