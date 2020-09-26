package newexam.demo.web;


import newexam.demo.model.entity.ClassificationName;
import newexam.demo.service.TaskService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
public class HomeController {
    private final TaskService taskService;

    public HomeController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/")
    public ModelAndView index(HttpSession httpSession, ModelAndView modelAndView){

        if(httpSession.getAttribute("user")==null){
            modelAndView.setViewName("index");
        } else {
            modelAndView.addObject("bugs",this.taskService.getTasks(ClassificationName.BUG));
            modelAndView.addObject("features",this.taskService.getTasks(ClassificationName.FEATURE));
            modelAndView.addObject("supports",this.taskService.getTasks(ClassificationName.SUPPORT));
            modelAndView.addObject("others",this.taskService.getTasks(ClassificationName.OTHER));
            modelAndView.addObject("all",this.taskService.findAllTasks());
            modelAndView.setViewName("home");
        }

        return modelAndView;
    }





}
