package com.lzh.thread.sharing;

import java.util.HashSet;
import java.util.Iterator;

public class ObjectPublish {

    public static HashSet<String> strs ;
    public void init()
    {
    	strs = new HashSet<String>();
    	strs.add("1111");
    	strs.add("2222");
    	strs.add("3333");
    	strs.add("4444");
    }
    public static void main(String args[]){
    	ObjectPublish op = new ObjectPublish();
    	op.init();
    	ObjectPublish.strs.add("tttt");
    	Iterator it = ObjectPublish.strs.iterator();
    	while(it.hasNext()) {
    		System.out.println(it.next());
    	}
    	
    }
}
