package com.lar.aluno.controller;

import java.util.List;
import java.util.Optional;
import java.util.Map;
import java.util.LinkedHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.lar.aluno.entity.Curso;
import com.lar.aluno.entity.Aluno;
import com.lar.aluno.service.CursoService;
import com.lar.aluno.service.AlunoService;
import com.lar.curso.api.CursoAPIRest;
import com.lar.curso.api.AlunoAPIRest;

@RestController
public class AppController implements AlunoAPIRest, CursoAPIRest{
	
	private AlunoService alunoService;
	private CursoService cursoService;
	
	@Autowired
	public void setServidorPublicoService(AlunoService alunoService)
	{
		this.alunoService = alunoService;
	}
	
	@Autowired
	public void setCursoService(CursoService cursoService)
	{
		this.cursoService = cursoService;
	}

	/*********** API ALUNO *************/
	@GetMapping("/listarAlunos")
	public ResponseEntity<List<Aluno>> listarAlunos() {
		List<Aluno> alunos = alunoService.listAll();
		return new ResponseEntity<List<Aluno>>(alunos,HttpStatus.OK);
	}

	@GetMapping("/listaraluno/{matricula}")
	public ResponseEntity<Aluno> listarAluno(long matricula) {
		
		Optional<Aluno> alunoEncontrado = alunoService.listByMatricula(matricula);
		
		if (alunoEncontrado.isPresent())
			return new ResponseEntity<Aluno>(alunoEncontrado.get(),HttpStatus.OK);
		else
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Aluno Não Encontrado");
	}

	@DeleteMapping("/excluirAluno/{matricula}")
	public void excluirAluno(long matricula) {
		
		Optional<Aluno> alunoEncontrado = alunoService.listByMatricula(matricula);
		
		if (alunoEncontrado.isPresent())
		{
			alunoService.delete(matricula);
			throw new ResponseStatusException(HttpStatus.OK,"Aluno Excluído");
		}
		else
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Aluno Não Encontrado");
	}

	@PutMapping("/editarAluno/{matricula}")
	public String editarServidor(long matricula, @RequestBody Aluno alunoAlterado) {
		
		Optional<Aluno> alunoEncontrado = alunoService.listByMatricula(matricula);
		
		if (alunoEncontrado.isPresent())
		{
			alunoService.update(alunoAlterado);
			throw new ResponseStatusException(HttpStatus.OK,"Aluno Alterado");
		}
		else
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Aluno não Encontrado");

	}

	@PostMapping("/cadastraraluno")
	public String cadastrarAluno(@RequestBody Aluno novoaluno) {
		
		Optional<Aluno> alunoEncontrado = alunoService.listByMatricula(novoaluno.getMatricula());
		
		if (!alunoEncontrado.isPresent())
		{
			alunoService.save(novoaluno);
			throw new ResponseStatusException(HttpStatus.OK,"Aluno Cadastrado");
		}
		else
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Aluno Já Existente");	
	}
	
	/*********** API CURSO *************/
	@GetMapping("/listarCursos")
	public ResponseEntity<List<Curso>> listarCursos() {
		List<Curso> cursos = cursoService.listAll();
		return new ResponseEntity<List<Curso>>(cursos,HttpStatus.OK);
	}

	@GetMapping("/listarCurso/{idcurso}")
	public ResponseEntity<Curso> listarCurso(long idcurso) {
		
		Optional<Curso> cursoEncontrado = cursoService.listByIdCurso(idcurso);
		
		if (cursoEncontrado.isPresent())
			return new ResponseEntity<Curso>(cursoEncontrado.get(),HttpStatus.OK);
		else
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Curso Não Encontrado");
	}

	@DeleteMapping("/excluirCurso/{idcurso}")
	public void excluirCurso(long idcurso) {
		
		Optional<Curso> cursoEncontrado = cursoService.listByIdCurso(idcurso);
		
		if (cursoEncontrado.isPresent())
		{
			cursoService.delete(idcurso);
			throw new ResponseStatusException(HttpStatus.OK,"Curso Excluído");
		}
		else
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Curso Não Encontrado");
	}

	@PutMapping("/editarCurso/{idcurso}")
	public String editarCurso(long idcurso, @RequestBody Curso cursoAlterado) {
		
		Optional<Curso> cursoEncontrado = cursoService.listByIdCurso(idcurso);
		
		if (cursoEncontrado.isPresent())
		{
			cursoService.update(cursoAlterado);
			throw new ResponseStatusException(HttpStatus.OK,"Curso Alterado");
		}
		else
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Curso Não Encontrado");

	}

	@PostMapping("/cadastrarCurso")
	public String cadastrarCurso(@RequestBody Curso novocurso) {
		
		cursoService.save(novocurso);
		throw new ResponseStatusException(HttpStatus.CREATED,"Curso Cadastrado");
	}
	
	
	@ExceptionHandler(ResponseStatusException.class)
	public ResponseEntity<Object> handleNotFoundException(ResponseStatusException ex) {
		Map<String, Object> body = new LinkedHashMap<>();
		body.put("message", ex.getReason());
		body.put("status", ex.getStatusCode());
		return new ResponseEntity<>(body, ex.getStatusCode());
	}

	@Override
	public ResponseEntity<List<Aluno>> listarAlunos() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<Aluno> listarAluno(long matricula) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void excluirServidor(long matricula) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String cadastrarAluno(Aluno novoaluno) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String editarAluno(long matricula, Aluno aluno) {
		// TODO Auto-generated method stub
		return null;
	}
}
