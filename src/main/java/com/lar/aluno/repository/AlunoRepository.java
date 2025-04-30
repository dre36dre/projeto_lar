package com.lar.aluno.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.lar.aluno.entity.Aluno;

@Repository 
public interface AlunoRepository extends CrudRepository<Aluno,Long> {

}
