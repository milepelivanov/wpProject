package mk.ukim.finki.dosie.service.impl;

import mk.ukim.finki.dosie.model.Professor;
import mk.ukim.finki.dosie.model.exceptions.ProfessorNotFoundException;
import mk.ukim.finki.dosie.repository.ProfessorRepository;
import mk.ukim.finki.dosie.service.ProfessorService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfessorServiceImpl implements ProfessorService {
    private final ProfessorRepository professorRepository;

    public ProfessorServiceImpl(ProfessorRepository professorRepository) {
        this.professorRepository = professorRepository;
    }

    @Override
    public Professor findProfessorById(String username) throws ProfessorNotFoundException {
        return this.professorRepository.findById(username).orElseThrow(() -> new ProfessorNotFoundException("Professor cannot be found"));
    }

    @Override
    public List<Professor> findAll() {
        return this.professorRepository.findAll();
    }
}
