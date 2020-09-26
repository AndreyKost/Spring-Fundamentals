package wpartone.web;

import org.modelmapper.ModelMapper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import wpartone.model.binding.CommentAddBindingModel;
import wpartone.model.binding.HomeworkAddBindingModel;
import wpartone.model.service.CommentServiceModel;
import wpartone.model.service.ExerciseServiceModel;
import wpartone.model.service.HomeworkServiceModel;
import wpartone.model.service.UserServiceModel;
import wpartone.service.CommentService;
import wpartone.service.ExerciseService;
import wpartone.service.HomeworkService;
import wpartone.service.UserService;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.security.Principal;
import java.time.LocalDateTime;

@Controller
@RequestMapping("/homework")
public class HomeworkController {
    private final ExerciseService exerciseService;
    private final ModelMapper modelMapper;
    private final HomeworkService homeworkService;
    private final CommentService commentService;
    private final UserService userService;

    public HomeworkController(ExerciseService exerciseService, ModelMapper modelMapper, HomeworkService homeworkService, CommentService commentService, UserService userService) {
        this.exerciseService = exerciseService;
        this.modelMapper = modelMapper;
        this.homeworkService = homeworkService;
        this.commentService = commentService;
        this.userService = userService;
    }

    @GetMapping("/add")
    @PreAuthorize("isAuthenticated()")
    public String add(Model model){
        if(!model.containsAttribute("homeworkAddBindingModel")){
            model.addAttribute("homeworkAddBindingModel",new HomeworkAddBindingModel());
            model.addAttribute("isLate",false);
        }

        model.addAttribute("allExNames",this.exerciseService.findAllExerciseNames());
        return "homework-add";
    }

    @PostMapping("/add")
    @PreAuthorize("isAuthenticated()")
    public String addConfirm(@Valid @ModelAttribute("homeworkAddBindingModel")HomeworkAddBindingModel homeworkAddBindingModel,
                             BindingResult bindingResult, RedirectAttributes redirectAttributes, Principal principal){

        ExerciseServiceModel exerciseServiceModel=this.exerciseService.findByName(homeworkAddBindingModel.getExercise());

        boolean isLate=exerciseServiceModel.getDueDate().isBefore(LocalDateTime.now());

        if(bindingResult.hasErrors() || isLate){
            redirectAttributes.addFlashAttribute("homeworkAddBindingModel",homeworkAddBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.homeworkAddBindingModel",bindingResult);
            redirectAttributes.addFlashAttribute("isLate",isLate);
            return "redirect:add";
        }

        HomeworkServiceModel homeworkServiceModel=this.modelMapper
                .map(homeworkAddBindingModel,HomeworkServiceModel.class);
        homeworkServiceModel.setAddedOn(LocalDateTime.now());
        homeworkServiceModel.setExercise(exerciseServiceModel);
        String name = principal.getName();
        UserServiceModel userByUsername = userService.findByUsername(name);
        homeworkServiceModel.setAuthor(userByUsername);

        this.homeworkService.add(homeworkServiceModel);
        return "redirect:/home";
    }

    @GetMapping("/check")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String check(Model model){

        if(!model.containsAttribute("commentAddBindingModel")){
         model.addAttribute("commentAddBindingModel",new CommentAddBindingModel());
        }

        model.addAttribute("homework",this.homeworkService.findOneToCheck());

        return "homework-check";
    }

    @PostMapping("/check")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String checkConfirm(@Valid @ModelAttribute("commentAddBindingModel")CommentAddBindingModel commentAddBindingModel
    ,BindingResult bindingResult,RedirectAttributes redirectAttributes, Principal principal){

        if(bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("commentAddBindingModel",commentAddBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.commentAddBindingModel", bindingResult);

            return "redirect:check";
        }

        CommentServiceModel commentServiceModel=this.modelMapper.map(commentAddBindingModel,CommentServiceModel.class);
        commentServiceModel.setHomework(this.homeworkService.findById(commentAddBindingModel.getHomeworkId()));

        String name = principal.getName();
        UserServiceModel userByUsername = userService.findByUsername(name);
        commentServiceModel.setAuthor(userByUsername);


        this.commentService.add(commentServiceModel);

        return "redirect:/home";

    }

}
