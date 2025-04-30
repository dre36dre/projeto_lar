package com.lar.aluno.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lar.aluno.entity.Aluno;
import com.lar.aluno.repository.AlunoRepository;


@Service
public class CursoServiceImpl implements AlunoService {

	@Autowired
	private AlunoRepository servidorRepository;
	
	@Override
	public List<Aluno> listAll() 
	{
		List<Aluno> servidorespublicos = new ArrayList<>();
		servidorRepository.findAll().forEach(servidorespublicos::add);
		return servidorespublicos;	
	}

	@Override
	public Optional<Aluno> listByMatricula(long matricula) 
	{
		return servidorRepository.findById(matricula);		
	}

	@Override
	public void save(Aluno servidor) {
		servidorRepository.save(servidor);
	}

	@Override
	public void update(Aluno servidor) {
		
		Optional<Aluno> servidorEncontrado = 
				servidorRepository.findById(servidor.getMatricula());	
		
		servidorEncontrado.ifPresent(
			p -> {
				servidorRepository.save(servidor);
			}
		);		
	}

	@Override
	public void delete(long matricula) {
		Optional<Aluno> servidorEncontrado = 
				servidorRepository.findById(matricula);	
		
		servidorEncontrado.ifPresent(
			p -> {
				servidorRepository.delete(servidorEncontrado.get());
			}
		);	
		
	}

}
