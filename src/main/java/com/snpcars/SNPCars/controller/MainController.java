package com.snpcars.SNPCars.controller;

import com.snpcars.SNPCars.service.CarroService;
import com.snpcars.SNPCars.service.VendedorService; 
import jakarta.servlet.http.HttpSession; 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class MainController { 

    @Autowired
    private CarroService carroService; 
    
    @Autowired
    private VendedorService vendedorService;

    // ====================================================
    // 0. AUTENTICAÇÃO (LOGIN / LOGOUT)
    // ====================================================

    @GetMapping("/login")
    public String loginPage() {
        return "login"; 
    }

    @PostMapping("/efetuarLogin")
    public String efetuarLogin(@RequestParam String usuario,
                             @RequestParam String senha,
                             HttpSession session,
                             RedirectAttributes ra) {
        // Validação HARDCODED 
        if ("admin".equals(usuario) && "123456".equals(senha)) {
            session.setAttribute("usuarioLogado", true);
            // CORREÇÃO: Redireciona para Gerenciar Carros
            return "redirect:/admin/carros";
        } else {
            ra.addFlashAttribute("erro", "Usuário ou senha inválidos!");
            return "redirect:/login";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate(); 
        return "redirect:/";
    }

    // ====================================================
    // 1. ROTAS PÚBLICAS
    // ====================================================

    @GetMapping("/")
    public String index(){ return "index"; }

    @GetMapping("/contato")
    public String contato(){ return "contato"; }
    
    @GetMapping("/vendedores")
    public String vendedores(Model model){
        model.addAttribute("vendedores", vendedorService.listarVendedores());
        return "vendedores";
    }

    @GetMapping("/catalogo")
    public String catalogo(Model model){
        model.addAttribute("carros", carroService.listarCarros());
        return "catalogo";
    }

    // ====================================================
    // 2. ROTAS ADMINISTRATIVAS (PROTEGIDAS)
    // ====================================================
    
    // Método auxiliar para verificar se está logado
    private boolean naoEstaLogado(HttpSession session) {
        return session.getAttribute("usuarioLogado") == null;
    }

    /**
     * Rota: /admin
     * Redireciona para /admin/carros
     */
    @GetMapping("/admin")
    public String adminRaiz(HttpSession session) {
        if (naoEstaLogado(session)) return "redirect:/login";
        return "redirect:/admin/carros";
    }

    
}