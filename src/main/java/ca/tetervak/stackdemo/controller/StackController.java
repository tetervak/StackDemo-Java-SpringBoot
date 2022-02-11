package ca.tetervak.stackdemo.controller;

import ca.tetervak.stackdemo.model.MyStack;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
public class StackController {

    @GetMapping(value={"/", "/stack"})
    public String index(HttpSession session){
        MyStack stack = (MyStack) session.getAttribute("stack");
        if(stack == null){
            session.setAttribute("stack", new MyStack());
        }
        return "Stack";
    }

    @PostMapping("/process")
    public ModelAndView process(
            @RequestParam String todo,
            @RequestParam(defaultValue = "") String pushed,
            HttpSession session
    ){
        ModelAndView mv = new ModelAndView("Stack");
        MyStack stack = (MyStack) session.getAttribute("stack");
        if (todo.equals("Push")){
            if(stack == null){
                stack = new MyStack();
                session.setAttribute("stack", stack);
            }
            if(!pushed.trim().isEmpty()){
                stack.push(pushed);
            }
        }else if (todo.equals("Pop")){
            if(stack != null && !stack.isEmpty()){
                String popped = stack.pop();
                mv.addObject("popped", popped);
            }
        }
        return mv;
    }
}
