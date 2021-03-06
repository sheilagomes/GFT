package com.gft.estudo.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.gft.estudo.model.Registro;
import com.gft.estudo.model.StatusRegistro;
import com.gft.estudo.repository.Registros;

@Controller
@RequestMapping("/estudo")
public class EstudoController {
	
	@Autowired
	private Registros registros;
	
	@RequestMapping("/novo")
	public ModelAndView novo() {
		ModelAndView mv = new ModelAndView("CadastroEstudo");
		mv.addObject(new Registro());
		return mv;
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView salvar(@Validated Registro registro, Errors errors) {
		ModelAndView mv = new ModelAndView("CadastroEstudo");
		if (errors.hasErrors()) {
			return mv;
		}
		registros.save(registro);
		mv.addObject("mensagem", "Registro salvo!");
		return mv;
	}
	
	@RequestMapping
	public ModelAndView pesquisar() {
		List<Registro> todosRegistros = registros.findAll();
		ModelAndView mv = new ModelAndView("PesquisaEstudo");
		mv.addObject("registros", todosRegistros);
		return mv;
	}
	
	@ModelAttribute("todosStatusRegistro")
	public List<StatusRegistro> todosStatusRegistro() {
		return Arrays.asList(StatusRegistro.values());
	}
}

//import java.util.Optional;
//
//import org.springframework.dao.DataIntegrityViolationException;
//import org.springframework.validation.Errors;
//import org.springframework.validation.annotation.Validated;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.servlet.ModelAndView;
//import org.springframework.web.servlet.mvc.support.RedirectAttributes;
//
//import com.algaworks.cobranca.model.StatusTitulo;
//import com.algaworks.cobranca.repository.filter.TituloFilter;
//import com.algaworks.cobranca.service.CadastroTituloService;
//
//import groovyjarjarpicocli.CommandLine.Model;
//
//@Controller
//@RequestMapping("/titulos")
//public class TituloController {
//	
//	private static final String CADASTRO_VIEW = "CadastroTitulo";
//	
//	@Autowired
//	private CadastroTituloService cadastroTituloService;  
//	
//	@RequestMapping("/novo")
//	public ModelAndView novo() {
//		ModelAndView mv = new ModelAndView(CADASTRO_VIEW);
//		mv.addObject(new Titulo());
//		return mv;
//	}
//	
//	@RequestMapping(method = RequestMethod.POST)
//	public String salvar(@Validated Titulo titulo, Errors errors, RedirectAttributes attributes) {
//		if (errors.hasErrors()) {
//			return CADASTRO_VIEW;
//		}
//		try {
//			cadastroTituloService.salvar(titulo);
//			attributes.addFlashAttribute("mensagem", "Título salvo!");
//			return "redirect:/titulos/novo";
//		} catch (IllegalArgumentException e) {
//			errors.rejectValue("dataVencimento", null, "Formato de data inválido");
//			return CADASTRO_VIEW;
//		}
//	}
//	
//	@RequestMapping
//	public ModelAndView pesquisar(@ModelAttribute("filtro") TituloFilter filtro) {
//		List<Titulo> todosTitulos = cadastroTituloService.filtrar(filtro);
//		ModelAndView mv = new ModelAndView("PesquisaTitulos");
//		mv.addObject("titulos", todosTitulos);
//		return mv;
//	}
//	
//	@RequestMapping("{codigo}")
//	public ModelAndView edicao(@PathVariable("codigo") Titulo titulo) {
//		ModelAndView mv = new ModelAndView(CADASTRO_VIEW);
//		mv.addObject(titulo);
//		return mv;		
//	}
//	
//	@RequestMapping(value="excluir/{codigo}", method = RequestMethod.GET)
//	public String excluir(@PathVariable Long codigo, RedirectAttributes attributes) {
//		cadastroTituloService.excluir(codigo);
//		attributes.addFlashAttribute("mensagem", "Título excluído!");
//		return "redirect:/titulos";
//	}
//public ModelAndView pesquisar(@ModelAttribute("filtro") TituloFilter filtro) {
//	List<Titulo> todosTitulos = cadastroTituloService.filtrar(filtro);
//	ModelAndView mv = new ModelAndView("PesquisaTitulos");
//	mv.addObject("titulos", todosTitulos);
//	return mv;
//	
//	@RequestMapping(value="/{codigo}/receber", method = RequestMethod.PUT)
//	public @ResponseBody String receber(@PathVariable Long codigo) {
//		return cadastroTituloService.receber(codigo);
//	}
//
//	}
//}
