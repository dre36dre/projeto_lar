package com.lar.aluno.service;

import java.util.List;
import java.util.Optional;

import com.lar.aluno.entity.Aluno;

public interface AlunoService {
	
	List<Aluno> listAll();
	Optional<Aluno> listByMatricula(long matricula);
	void save(Aluno aluno);
	void update(Aluno aluno);
	void delete(long matricula);

}
