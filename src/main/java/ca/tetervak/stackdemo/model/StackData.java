package ca.tetervak.stackdemo.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class StackData implements Serializable {

    private final LinkedList<String> list = new LinkedList<>();

    public StackData() {
        list.push("Item-1");
        list.push("Item-2");
        list.push("Item-3");
    }

    public synchronized String pop(){
        if(list.isEmpty()){
            return null;
        }else{
            return list.pop();
        }
    }

    public synchronized void push(String value){
        list.push(value);
    }

    public synchronized Iterable<StackItem> getItems(){
        int count = list.size();
        List<StackItem> items = new ArrayList<>(count);
        for(String value: list){
            items.add(new StackItem(count--, value));
        }
        return items;
    }

    public synchronized boolean isEmpty(){
        return list.isEmpty();
    }
}
