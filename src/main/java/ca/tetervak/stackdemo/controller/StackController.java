package ca.tetervak.stackdemo.controller;

import ca.tetervak.stackdemo.model.StackData;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class StackController {

    private final Logger log = LoggerFactory.getLogger(StackController.class);


    @GetMapping(value = {"/", "/stack"})
    public String displayStack(
            HttpSession session,
            @RequestParam(defaultValue = "") String popped,
            Model model
    ) {
        log.trace("index() is called");
        log.debug("popped = " + (popped.isEmpty() ? "empty" : popped));
        StackData stack = getStackData(session);
        model.addAttribute("items", stack.getItems());
        model.addAttribute("popped", popped);
        return "Stack";
    }

    private static StackData getStackData(HttpSession session) {
        StackData stack = (StackData) session.getAttribute("stack");
        if(stack == null){
            stack = new StackData();
            session.setAttribute("stack", stack);
        }
        return stack;
    }

    @PostMapping("/process")
    public String processInput(
            @RequestParam String todo,
            @RequestParam(defaultValue = "") String pushed,
            HttpSession session
    ) {
        log.trace("process() is called");
        log.debug("todo = " + todo);
        StackData stack = getStackData(session);
        if (todo.equals("Push")) {
            if (!pushed.trim().isEmpty()) {
                stack.push(pushed);
                log.debug("the value [" + pushed + "] is pushed");
            }
        } else if (todo.equals("Pop")) {
            if (!stack.isEmpty()) {
                String popped = stack.pop();
                log.debug("the value [" + popped + "] is popped");
                return "redirect:stack?popped=" + popped;
            }
        }
        return "redirect:stack";
    }
}
