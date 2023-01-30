package ca.tetervak.stackdemo.controller;

import ca.tetervak.stackdemo.model.StackData;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;

@Controller
public class StackController {

    @Resource(name = "stackData")
    StackData stackData;

    @GetMapping(value={"/", "/stack"})
    public ModelAndView index(){
        return new ModelAndView("Stack", "items", stackData.getItems());
    }

    @PostMapping("/process")
    public ModelAndView process(
            @RequestParam String todo,
            @RequestParam(defaultValue = "") String pushed
    ){
        ModelAndView mv = new ModelAndView("Stack");
        if (todo.equals("Push")){
            if(!pushed.trim().isEmpty()){
                stackData.push(pushed);
            }
        }else if (todo.equals("Pop")){
            if(!stackData.isEmpty()){
                String popped = stackData.pop();
                mv.addObject("popped", popped);
            }
        }
        mv.addObject("items", stackData.getItems());
        return mv;
    }
}
