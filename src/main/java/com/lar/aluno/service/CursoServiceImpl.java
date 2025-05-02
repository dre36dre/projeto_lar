package com.lar.aluno.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class CursoServiceImpl implements CursoService {

	@Autowired
	private CursoRepository cursoRepository;
	
	@Override
	public List<Curso> listAll() 
	{
		List<Curso> cursos = new ArrayList<>();
		cursoRepository.findAll().forEach(curso::add);
		return cursos;	
	}

	@Override
	public Optional<Curso> listByMatricula(long matricula) 
	{
		return cursoRepository.findById(matricula);		
	}

	@Override
	public void save(Curso curso) {
		cursoRepository.save(curso);
	}

	@Override
	public void update(Curso curso) {
		
		Optional<Curso> cursoEncontrado = 
				cursoRepository.findById(curso.getMatricula());	
		
		cursoEncontrado.ifPresent(
			p -> {
				cursoRepository.save(curso);
			}
		);		
	}

	@Override
	public void delete(long matricula) {
		Optional<Aluno> cursoEncontrado = 
				cursoRepository.findById(matricula);	
		
		cursoEncontrado.ifPresent(
			p -> {
				cursoRepository.delete(cursoEncontrado.get());
			}
		);	
		
	}

}
