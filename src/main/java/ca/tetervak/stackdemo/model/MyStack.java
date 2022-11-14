package ca.tetervak.stackdemo.model;

import java.util.LinkedList;

public class MyStack {

    private final LinkedList<String> data = new LinkedList<>();

    public MyStack() {
        data.push("Item 1");
        data.push("Item 2");
        data.push("Item 3");
    }

    public String pop(){
        if(data.isEmpty()){
            return null;
        }else{
            return data.pop();
        }
    }

    public void push(String value){
        data.push(value);
    }

    public Iterable<String> getData(){
        return data;
    }

    public boolean isEmpty(){
        return data.isEmpty();
    }

}
