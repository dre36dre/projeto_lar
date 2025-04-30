package com.lar.curso.api;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.lar.aluno.entity.Aluno;

/**************************************/
public interface AlunoAPIRest
{
	
	@GetMapping("/listaralunos")
	public ResponseEntity<List<Aluno>> listarAlunos();

	@GetMapping("/listaraluno/{matricula}")
	public ResponseEntity<Aluno> listarAluno(@PathVariable long matricula);
	
	@DeleteMapping("/excluiraluno/{matricula}")
	public void excluirServidor(@PathVariable long matricula);
	
	@PutMapping("/editarAluno/{matricula}")
	public String editarAluno(@PathVariable long matricula, @RequestBody Aluno aluno);
	
	@PostMapping("/cadastraraluno")
	public String cadastrarAluno(@RequestBody Aluno novoAluno);

}
