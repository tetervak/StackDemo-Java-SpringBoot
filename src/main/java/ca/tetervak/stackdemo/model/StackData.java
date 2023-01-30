package ca.tetervak.stackdemo.model;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Component
@SessionScope
public class StackData implements Serializable {

    private final LinkedList<String> list = new LinkedList<>();

    public StackData() {
        list.push("Item-1");
        list.push("Item-2");
        list.push("Item-3");
    }

    public synchronized String pop() {
        if (list.isEmpty()) {
            return null;
        } else {
            return list.pop();
        }
    }

    public synchronized void push(String value) {
        list.push(value);
    }

    public synchronized List<StackItem> getItems() {
        int count = list.size();
        List<StackItem> items = new ArrayList<>(count);
        for (String value : list) {
            items.add(new StackItem(count--, value));
        }
        return items;
    }

    public synchronized boolean isEmpty() {
        return list.isEmpty();
    }
}
