package br.com.devmedia.controller;

import br.com.devmedia.domain.PlayList;
import br.com.devmedia.service.PlayListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.naming.Binding;
import javax.validation.Valid;

@Controller
@RequestMapping("playlists")
public class PlayListController {

    @Autowired
    private PlayListService playListService;

    @GetMapping("/listar")
    public ModelAndView listar(ModelMap model){
        model.addAttribute("playlists", playListService.recuperar());
        return new ModelAndView("/playlist/list", model);
    }

    @GetMapping("/cadastro")
    public String preSalvar(@ModelAttribute("playlist") PlayList playList){
        return "/playlist/add";
    }

    @PostMapping("/salvar")
    public String salvar(@Valid @ModelAttribute("playlist") PlayList playList,
                         BindingResult result, RedirectAttributes attr){
        if(result.hasErrors())
            return "/playlist/add";

        playListService.salvar(playList);
        attr.addFlashAttribute("mensagem", "PlayList criada com sucesso.");
        return "redirect:/playlists/listar";
    }

    @GetMapping("/{id}/atualizar")
    public ModelAndView preAtualizar(@PathVariable("id") long id, ModelMap model){
        PlayList playList = playListService.recuperarPorId(id);
        model.addAttribute("playlist", playList);
        return new ModelAndView("/playlist/add", model);
    }

    @PutMapping("/salvar")
    public ModelAndView atualizar(@Valid @ModelAttribute("playlist") PlayList playList,
                         BindingResult result, RedirectAttributes attr){
        if(result.hasErrors())
            return new ModelAndView("/playlist/add");

        playListService.atualizar(playList);
        attr.addFlashAttribute("mensagem", "PlayList atualizada com sucesso.");
        return new ModelAndView("redirect:/playlists/listar");
    }
}
