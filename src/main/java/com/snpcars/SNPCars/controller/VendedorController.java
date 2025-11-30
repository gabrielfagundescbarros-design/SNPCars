package com.snpcars.SNPCars.controller;

import com.snpcars.SNPCars.model.Vendedor;
import com.snpcars.SNPCars.service.VendedorService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin") // Todas as rotas neste Controller começarão com /admin
public class VendedorController {
    
    @Autowired
    private VendedorService vendedorService;
    
    // Método auxiliar (copiado do seu MainController)
    private boolean naoEstaLogado(HttpSession session) {
        return session.getAttribute("usuarioLogado") == null;
    }

    // --- CRUD VENDEDORES ---

    /**
     * Rota: /admin/vendedores
     * Exibe a tabela de vendedores e o formulário de cadastro/edição.
     * O objeto 'vendedor' pode vir do editar (via Flash Attribute) ou ser criado aqui.
     */
    @GetMapping("/vendedores")
    public String adminVendedores(Model model, HttpSession session) {
        if (naoEstaLogado(session)) return "redirect:/login"; // Proteção

        // Se o objeto 'vendedor' não foi passado via Flash Attribute (edição), cria um novo
        if (!model.containsAttribute("vendedor")) {
            model.addAttribute("vendedor", new Vendedor());
        }

        // Lista de vendedores para preencher a tabela
        model.addAttribute("vendedores", vendedorService.listarVendedores());
        
        return "admin/crudVendedores";
    }

    /**
     * Rota: /admin/vendedor/salvar
     * Salva (ou atualiza) um vendedor.
     */
    @PostMapping("/vendedor/salvar")
    public String salvarVendedor(@ModelAttribute Vendedor vendedor, RedirectAttributes ra, HttpSession session) {
        if (naoEstaLogado(session)) return "redirect:/login"; // Proteção

        String mensagem;
        if (vendedor.getId() > 0) {
            vendedorService.atualizarVendedor(vendedor);
            mensagem = "Vendedor atualizado com sucesso!";
        } else {
            vendedorService.inserirVendedor(vendedor);
            mensagem = "Novo vendedor cadastrado com sucesso!";
        }
        ra.addFlashAttribute("mensagemSucesso", mensagem);
        return "redirect:/admin/vendedores";
    }

    /**
     * Rota: /admin/vendedor/editar/{id}
     * Busca o vendedor pelo ID e prepara o formulário para edição.
     * 🔑 CHAVE: Usa RedirectAttributes para passar o objeto Vendedor para o GET /admin/vendedores.
     */
    @GetMapping("/vendedor/editar/{id}")
    public String editarVendedor(@PathVariable int id, RedirectAttributes ra, HttpSession session) {
        if (naoEstaLogado(session)) return "redirect:/login"; // Proteção

        Vendedor vendedor = vendedorService.buscarVendedorPorId(id);
        
        // Passa o objeto Vendedor para o método adminVendedores através do redirect
        ra.addFlashAttribute("vendedor", vendedor);
        
        return "redirect:/admin/vendedores";
    }

    /**
     * Rota: /admin/vendedor/excluir/{id}
     * Exclui um vendedor.
     */
    @GetMapping("/vendedor/excluir/{id}")
    public String excluirVendedor(@PathVariable int id, RedirectAttributes ra, HttpSession session) {
        if (naoEstaLogado(session)) return "redirect:/login"; // Proteção

        vendedorService.deletarVendedor(id);
        ra.addFlashAttribute("mensagemSucesso", "Vendedor excluído com sucesso!");
        return "redirect:/admin/vendedores";
    }
}