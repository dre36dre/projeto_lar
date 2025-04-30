package com.lar.aluno.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.lar.aluno.entity.Curso;

@Repository
public interface CursoRepository extends CrudRepository<Curso,Long> {

}
