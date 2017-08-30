package com.lzh.thread.sharing;

import java.util.HashSet;

public class ObjectPublish2 {    
    private  HashSet<Person> persons=  new HashSet<Person>();
    public HashSet<Person>  getPersons()
    {
        return this.persons;
    }
    
}
