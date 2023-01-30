package ca.tetervak.stackdemo.controller;

import ca.tetervak.stackdemo.model.StackData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;

@Controller
public class StackController {

    @Resource(name = "stackData")
    StackData stackData;

    @GetMapping(value={"/", "/stack"})
    public String index(
            @RequestParam(defaultValue = "") String popped,
            Model model
    ){
        model.addAttribute("items", stackData.getItems());
        model.addAttribute("popped", popped);
        return "Stack";
    }

    @PostMapping("/process")
    public String process(
            @RequestParam String todo,
            @RequestParam(defaultValue = "") String pushed
    ){
        if (todo.equals("Push")){
            if(!pushed.trim().isEmpty()){
                stackData.push(pushed);
            }
        }else if (todo.equals("Pop")){
            if(!stackData.isEmpty()){
                String popped = stackData.pop();
                return "redirect:stack?popped=" + popped;
            }
        }
       return "redirect:stack";
    }
}
