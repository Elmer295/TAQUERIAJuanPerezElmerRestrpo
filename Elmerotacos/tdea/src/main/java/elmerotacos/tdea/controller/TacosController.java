package elmerotacos.tdea.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.GetMapping;
import elmerotacos.tdea.service.TacoService;


@Controller
@RequestMapping("/tacos")
public class TacosController {

@Autowired
TacoService tacoService;

    @GetMapping("/menu")   
    public String listarTacos(Model model) {
        model.addAttribute("listarTacos", tacoService.getAllTacos());
        return "tacos/list";
    }

    


    
}
