package wpartone.web;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import wpartone.model.binding.RoleAddBindingModel;
import wpartone.service.RoleService;
import wpartone.service.UserService;

@Controller
@RequestMapping("/roles")
public class RolesController {
    private final UserService userService;
    private final RoleService roleService;
    private final ModelMapper modelMapper;

    @Autowired
    public RolesController(UserService userService, RoleService roleService, ModelMapper modelMapper) {
        this.userService = userService;
        this.roleService = roleService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/add")
    @PreAuthorize("hasRole('ADMIN')")
    public ModelAndView add(ModelAndView modelAndView){

        modelAndView.addObject("usernames",this.userService.findAllUsernames());
        modelAndView.setViewName("role-add");
        return modelAndView;
    }

    @PostMapping("/add")
    @PreAuthorize("hasRole('ADMIN')")
    public String addConfirm( @ModelAttribute("roleAddBindingModel") RoleAddBindingModel roleAddBindingModel){

        //Todo validate
        this.userService
                .addRoleToUser(roleAddBindingModel.getUsername(),roleAddBindingModel.getAuthority());
        return "redirect:/";
    }

}
