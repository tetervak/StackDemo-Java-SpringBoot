package ca.tetervak.stackdemo.controller;

import ca.tetervak.stackdemo.model.StackData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;

@Controller
public class StackController {

    private final Logger log = LoggerFactory.getLogger(StackController.class);

    @Resource(name = "stackData")
    StackData stackData;

    @GetMapping(value = {"/", "/stack"})
    public String index(
            @RequestParam(defaultValue = "") String popped,
            Model model
    ) {
        log.trace("index() is called");
        log.debug("popped = " + (popped.isEmpty() ? "empty" : popped));
        model.addAttribute("items", stackData.getItems());
        model.addAttribute("popped", popped);
        return "Stack";
    }

    @PostMapping("/process")
    public String process(
            @RequestParam String todo,
            @RequestParam(defaultValue = "") String pushed
    ) {
        log.trace("process() is called");
        log.debug("todo = " + todo);
        if (todo.equals("Push")) {
            if (!pushed.trim().isEmpty()) {
                stackData.push(pushed);
                log.debug("the value [" + pushed + "] is pushed");
            }
        } else if (todo.equals("Pop")) {
            if (!stackData.isEmpty()) {
                String popped = stackData.pop();
                log.debug("the value [" + popped + "] is popped");
                return "redirect:stack?popped=" + popped;
            }
        }
        return "redirect:stack";
    }
}
