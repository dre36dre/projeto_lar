package com.lar.aluno.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lar.aluno.entity.Curso;
import com.lar.aluno.repository.CursoRepository;

@Service
public class AlunoServiceImpl implements CursoService {

	@Autowired
	private CursoRepository cursoRepository;
	
	@Override
	public List<Curso> listAll() 
	{
		List<Curso> cursos = new ArrayList<>();
		cursoRepository.findAll().forEach(cursos::add);
		return cursos;	
	}

	@Override
	public Optional<Curso> listByIdCurso(long idcurso) 
	{
		return cursoRepository.findById(idcurso);		
	}

	@Override
	public void save(Curso curso) {
		cursoRepository.save(curso);
	}

	@Override
	public void update(Curso curso) {
		
		Optional<Curso> cursoEncontrado = 
				cursoRepository.findById(curso.getIdcurso());	
		
		cursoEncontrado.ifPresent(
			p -> {
				cursoRepository.save(curso);
			}
		);		
	}

	@Override
	public void delete(long idcurso) {
		Optional<Curso> cursoEncontrado = 
				cursoRepository.findById(idcurso);	
		
		cursoEncontrado.ifPresent(
			p -> {
				cursoRepository.delete(cursoEncontrado.get());
			}
		);	
		
	}

}
