package myexam.web;

import myexam.model.entity.CategoryName;
import myexam.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


import javax.servlet.http.HttpSession;

@Controller
public class HomeController {
    private final ProductService productService;

    public HomeController(ProductService productService) {
        this.productService = productService;
    }



    @GetMapping("/")
    public ModelAndView index(HttpSession httpSession,ModelAndView modelAndView){

        if(httpSession.getAttribute("user")==null){
            modelAndView.setViewName("index");
        } else {
            modelAndView.addObject("foods",this.productService.getProducts(CategoryName.FOOD));
            modelAndView.addObject("drinks",this.productService.getProducts(CategoryName.DRINK));
            modelAndView.addObject("households",this.productService.getProducts(CategoryName.HOUSEHOLD));
            modelAndView.addObject("others",this.productService.getProducts(CategoryName.OTHER));
            modelAndView.addObject("all",this.productService.findAllProductsPriceCount());

            modelAndView.setViewName("home");
        }

        return modelAndView;
    }





}
