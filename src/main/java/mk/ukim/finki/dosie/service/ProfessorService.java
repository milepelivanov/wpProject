package mk.ukim.finki.dosie.service;

import mk.ukim.finki.dosie.model.Professor;
import mk.ukim.finki.dosie.model.exceptions.ProfessorNotFoundException;

import java.util.List;

public interface ProfessorService {

    Professor findProfessorById(String username) throws ProfessorNotFoundException;
    List<Professor> findAll();

}
