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


    @GetMapping("/")
    public String displayStack(
            Model model,
            HttpSession session
    ) {
        log.trace("displayStack() is called");
        StackData stack = getStackData(session);
        model.addAttribute("items", stack.getItems());
        return "Stack";
    }

    private StackData getStackData(HttpSession session) {
        log.trace("getStackData() is called");
        StackData stack = (StackData) session.getAttribute("stack");
        if(stack == null){
            log.trace("StackData is not found in the Session; making new StackData");
            stack = new StackData();
            session.setAttribute("stack", stack);
        }else{
            log.trace("Previous StackData is found in the Session");
        }
        return stack;
    }

    @PostMapping("/process")
    public String processInput(
            @RequestParam String todo,
            @RequestParam(defaultValue = "") String pushed,
            Model model,
            HttpSession session
    ) {
        log.trace("processInput() is called");
        log.debug("todo = " + todo);
        StackData stack = getStackData(session);

        if (todo.equals("Push")) {
            if (!pushed.trim().isEmpty()) {
                stack.push(pushed);
                log.debug("the value [" + pushed + "] is pushed");
                model.addAttribute("popped", "");
            }
        } else if (todo.equals("Pop")) {
            if (!stack.isEmpty()) {
                String popped = stack.pop();
                log.debug("the value [" + popped + "] is popped");
                model.addAttribute("popped", popped);
            }
        }
        model.addAttribute("items", stack.getItems());
        return "Stack";
    }
}
