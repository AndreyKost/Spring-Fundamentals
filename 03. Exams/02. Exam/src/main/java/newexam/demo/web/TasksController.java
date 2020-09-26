package newexam.demo.web;

import newexam.demo.model.binding.TaskAddBindingModel;
import newexam.demo.model.service.TaskServiceModel;
import newexam.demo.model.view.TaskViewModel;
import newexam.demo.service.TaskService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/tasks")
public class TasksController {
    private final TaskService taskService;
    private final ModelMapper modelMapper;

    public TasksController(TaskService taskService, ModelMapper modelMapper) {
        this.taskService = taskService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/add")
    public String add(Model model){
        if(!model.containsAttribute("taskAddBindingModel")){
            model.addAttribute("taskAddBindingModel",new TaskAddBindingModel());
        }

        return "add-task";
    }

    @PostMapping("/add")
    public String addConfirm(@Valid @ModelAttribute("taskAddBindingModel")TaskAddBindingModel taskAddBindingModel,
                             BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        if(bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("taskAddBindingModel",taskAddBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.taskAddBindingModel",bindingResult);
            return "redirect:add";
        }

        this.taskService.addTask(this.modelMapper.map(taskAddBindingModel, TaskServiceModel.class));

        return "redirect:/";
    }


    /*@GetMapping("/viewAll")
    public String view(Model model){
        if(!model.containsAttribute("taskViewModel")){
            model.addAttribute("taskViewModel",new TaskViewModel());
        }

        return "add-task";
    }*/

}
