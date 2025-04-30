package com.lar.aluno.client;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import com.lar.aluno.entity.Curso;
import com.lar.aluno.entity.Aluno;

import org.springframework.core.ParameterizedTypeReference;

@Controller
public class AppCliente {
	
	@Autowired
	private RestTemplate restTemplate;
	
	/*********** API ALUNO *************/
	@GetMapping("/listagemAlunos")
	public String listarAlunos(Model model) {
		
		String url = "http://localhost:8080/listarAlunos"; 
		
	    ResponseEntity<List<Aluno>> response = restTemplate.exchange(
	        url,
	        HttpMethod.GET,
	        null,
	        new ParameterizedTypeReference<List<Aluno>>() {}
	    );
		
		List<Aluno> alunos = response.getBody();
		
		model.addAttribute("listarAlunos",alunos);
		
		return "/aluno/alunos";	
	}

	@GetMapping("/listarAluno/{matricula}")
	public String listarAluno(@PathVariable long matricula, Model model) {
		
		String url = "http://localhost:8080/listarAluno/{matricula}"; 
		
	    ResponseEntity<Aluno> response = restTemplate.exchange(
	        url,
	        HttpMethod.GET,
	        null,
	        new ParameterizedTypeReference<Aluno>() {},matricula
	    );
		
		Aluno aluno = response.getBody();
		
		model.addAttribute("servidorpublico",aluno);
		
		return "/aluno/aluno";
	
	}

	@GetMapping("/exclusaoAluno/{matricula}")
	public String excluirAluno(@PathVariable long matricula) {
		
	//	String url = "http://localhost:8080/excluirAluno/{matricula}"; 
		
		return "redirect:/listagemAlunos";
	}
	
	@GetMapping("/formNovoAluno")
	public String formNovoAluno(Model model)
	{
		model.addAttribute("aluno",new Aluno());
		return "aluno/novoaluno";
	}
	
	@PostMapping("/cadastroAluno")
	public String cadastrarAluno(Aluno novoAluno, Model model) {
	   
		String url = "http://localhost:8080/cadastrarAluno"; 
		
		ResponseEntity<Aluno> response = restTemplate.exchange(
	        url,
	        HttpMethod.POST,
	        new HttpEntity<>(novoAluno),
	        Aluno.class
	    );

	    if (response.getStatusCode() == HttpStatus.OK)
	    	return "redirect:/listagemAlunos";
	    else
	    {
	    	model.addAttribute("mensagem",response.getStatusCode());
	    	return "/erro/mensagem";
	    }
	}

	@GetMapping("/formEditarAluno/{matricula}")
	public String formEditarAluno(@PathVariable long matricula, Model model)
	{
		String url = "http://localhost:8080/listarAluno/{matricula}"; 
		
	    ResponseEntity<Aluno> response = restTemplate.exchange(
	        url,
	        HttpMethod.GET,
	        null,
	        new ParameterizedTypeReference<Aluno>() {},matricula
	    );
		
		Aluno aluno = response.getBody();
		
		model.addAttribute("aluno",aluno);
		
		return "/aluno/editaraluno";
	}
	
	@PostMapping("/edicaoaluno/{matricula}")
	public String editarAluno(@PathVariable long matricula, Aluno aluno)
	{
		return "redirect:/listagemServidores";
	}
	
	/*********** API curso *************/
	@GetMapping("/listagemCursos")
	public String listarCursos(Model model) {
		
		String url = "http://localhost:8080/listarCursos"; 
		
	    ResponseEntity<List<Curso>> response = restTemplate.exchange(
	        url,
	        HttpMethod.GET,
	        null,
	        new ParameterizedTypeReference<List<Curso>>() {}
	    );
		
		List<Curso> cursos = response.getBody();
		
		model.addAttribute("cursos",cursos);
		
		return "/curso/cursos";	
	}

	@GetMapping("/listaCurso/{idcurso}")
	public String listarCurso(@PathVariable long idcurso, Model model) {
		
		String url = "http://localhost:8080/listarCurso/{idcurso}"; 
		
	    ResponseEntity<Curso> response = restTemplate.exchange(
	        url,
	        HttpMethod.GET,
	        null,
	        new ParameterizedTypeReference<Curso>() {},idcurso
	    );
		
		Curso curso = response.getBody();
		
		model.addAttribute("curso",curso);
		
		return "/curso/curso";
	
	}

	@GetMapping("/exclusaoCurso/{idcurso}")
	public String excluirCurso(@PathVariable long idcurso) {
		
		return "redirect:/listagemCursos";
	}
	
	@GetMapping("/formNovoCurso")
	public String formNovoCurso(Model model)
	{
		model.addAttribute("curso",new Curso());
		return "curso/novocurso";
	}
	
	@PostMapping("/cadastroCurso")
	public String cadastrarCurso(Curso novocurso, Model model) {
	   
		String url = "http://localhost:8080/cadastrarCurso"; 
		
		ResponseEntity<Curso> response = restTemplate.exchange(
	        url,
	        HttpMethod.POST,
	        new HttpEntity<>(novocurso),
	        Curso.class
	    );

	    if (response.getStatusCode() == HttpStatus.CREATED)
	    	return "redirect:/listagemCursos";
	    else
	    {
	    	model.addAttribute("mensagem",response.getStatusCode());
	    	return "/erro/mensagem";
	    }
	}

	@GetMapping("/formEditarCurso/{idcurso}")
	public String formEditarCurso(@PathVariable long idcurso, Model model)
	{
		String url = "http://localhost:8080/listarCurso/{idcurso}"; 
		
	    ResponseEntity<Curso> response = restTemplate.exchange(
	        url,
	        HttpMethod.GET,
	        null,
	        new ParameterizedTypeReference<Curso>() {},idcurso
	    );
		
		Curso curso = response.getBody();
		
		model.addAttribute("curso",curso);
		
		return "/curso/editarcurso";
	}
	
	@PostMapping("/edicaoCurso/{idcurso}")
	public String editarServidor(@PathVariable long idcurso, Curso curso)
	{
		return "redirect:/listagemCursos";
	}
	

	@ExceptionHandler(ResponseStatusException.class)
	public ResponseEntity<Object> handleNotFoundException(ResponseStatusException ex) {
		Map<String, Object> body = new LinkedHashMap<>();
		body.put("message", ex.getReason());
		body.put("status", ex.getStatusCode());
		return new ResponseEntity<>(body, ex.getStatusCode());
	} 
	
}
