package com.snpcars.SNPCars.controller;

import com.snpcars.SNPCars.model.Carro;
import com.snpcars.SNPCars.model.Vendedor; // Necessário para listar no formulário de carros
import com.snpcars.SNPCars.service.CarroService;
import com.snpcars.SNPCars.service.VendedorService; // Necessário para listar no formulário de carros
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin") // Todas as rotas neste Controller começarão com /admin
public class CarroController {
    
    @Autowired
    private CarroService carroService;
    
    @Autowired
    private VendedorService vendedorService; // Para carregar vendedores no <select>

    // Método auxiliar (copiado do seu MainController)
    private boolean naoEstaLogado(HttpSession session) {
        return session.getAttribute("usuarioLogado") == null;
    }

    // --- CRUD CARROS ---

    /**
     * Rota: /admin/carros
     * Exibe a tabela de carros e o formulário de cadastro/edição.
     */
    @GetMapping("/carros")
    public String adminCarros(Model model, HttpSession session) {
        if (naoEstaLogado(session)) return "redirect:/login"; // Proteção

        // Se o objeto 'carro' não foi passado via Flash Attribute (edição), cria um novo
        if (!model.containsAttribute("carro")) {
            model.addAttribute("carro", new Carro());
        }

        // Carrega a lista de carros para a tabela
        model.addAttribute("carros", carroService.listarCarros());
        
        // Carrega a lista de vendedores para o dropdown (select) do formulário
        model.addAttribute("vendedoresDisponiveis", vendedorService.listarVendedores());
        
        return "admin/crudCarros";
    }

    /**
     * Rota: /admin/carro/salvar
     * Salva (ou atualiza) um carro.
     */
    @PostMapping("/carro/salvar")
    public String salvarCarro(@ModelAttribute Carro carro, RedirectAttributes ra, HttpSession session) {
        if (naoEstaLogado(session)) return "redirect:/login"; // Proteção

        String mensagem;
        if (carro.getId() > 0) {
            carroService.atualizarCarro(carro);
            mensagem = "Carro atualizado com sucesso!";
        } else {
            carroService.inserirCarro(carro);
            mensagem = "Novo carro cadastrado com sucesso!";
        }
        ra.addFlashAttribute("mensagemSucesso", mensagem);
        return "redirect:/admin/carros";
    }

    /**
     * Rota: /admin/carro/editar/{id}
     * Busca o carro pelo ID e prepara o formulário para edição.
     * 🔑 CHAVE: Usa RedirectAttributes para passar o objeto Carro para o GET /admin/carros.
     */
    @GetMapping("/carro/editar/{id}")
    public String editarCarro(@PathVariable int id, RedirectAttributes ra, HttpSession session) {
        if (naoEstaLogado(session)) return "redirect:/login"; // Proteção

        Carro carro = carroService.buscarCarroPorId(id);
        
        // CORREÇÃO: Passa o objeto Carro para o método adminCarros através do redirect
        ra.addFlashAttribute("carro", carro);
        
        return "redirect:/admin/carros"; 
    }

    /**
     * Rota: /admin/carro/excluir/{id}
     * Exclui um carro.
     */
    @GetMapping("/carro/excluir/{id}")
    public String excluirCarro(@PathVariable int id, RedirectAttributes ra, HttpSession session) {
        if (naoEstaLogado(session)) return "redirect:/login"; // Proteção

        carroService.deletarCarro(id);
        ra.addFlashAttribute("mensagemSucesso", "Carro excluído com sucesso!");
        return "redirect:/admin/carros";
    }
}