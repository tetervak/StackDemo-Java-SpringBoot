package ca.tetervak.stackdemo.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Component
@SessionScope
public class StackData implements Serializable {

    private final Logger log = LoggerFactory.getLogger(StackData.class);

    private final LinkedList<String> list = new LinkedList<>();

    public StackData() {
        log.trace("constructor is called");
        list.push("Item-1");
        list.push("Item-2");
        list.push("Item-3");
    }

    public String pop() {
        log.trace("pop() method is called");
        // it is more efficient, in this case
        synchronized (this){
            if (list.isEmpty()) {
                return null;
            } else {
                return list.pop();
            }
        }
    }

    public void push(String value) {
        log.trace("push({}) is called", value);
        synchronized (this){
            list.push(value);
        }
    }

    public List<StackItem> getItems() {
        log.trace("getItems() is called");
        synchronized (this) {
            int count = list.size();
            List<StackItem> items = new ArrayList<>(count);
            for (String value : list) {
                items.add(new StackItem(count--, value));
            }
            return items;
        }
    }

    public synchronized boolean isEmpty() {
        log.trace("isEmpty() is called");
        synchronized (this) {
            return list.isEmpty();
        }
    }

    @Override
    public String toString() {
        synchronized (this) {
            return "StackData{" +
                    "list=" + list +
                    '}';
        }
    }
}
