package myexam.web;

import myexam.model.binding.ProductAddBindingModel;
import myexam.model.service.ProductServiceModel;
import myexam.service.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/products")
public class ProductsController {
    private final ProductService productService;
    private final ModelMapper modelMapper;

    public ProductsController(ProductService productService, ModelMapper modelMapper) {
        this.productService = productService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/add")
    public String add(Model model){
        if(!model.containsAttribute("productAddBindingModel")){
            model.addAttribute("productAddBindingModel",new ProductAddBindingModel());
        }

        return "product-add";
    }

    @PostMapping("/add")
    public String addConfirm(@Valid @ModelAttribute("productAddBindingModel")ProductAddBindingModel productAddBindingModel,
                             BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        if(bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("productAddBindingModel",productAddBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.productAddBindingModel",bindingResult);
            return "redirect:add";
        }

        this.productService.addItem(this.modelMapper.map(productAddBindingModel, ProductServiceModel.class));

        return "redirect:/";
    }

    @GetMapping("/buy/{id}")
    public String buy(@PathVariable("id")String id){
        this.productService.delete(id);
        return "redirect:/";
    }

    @GetMapping("/buyAll")
    public String buyAll(){
        this.productService.buyAll();
        return "redirect:/";
    }

}
