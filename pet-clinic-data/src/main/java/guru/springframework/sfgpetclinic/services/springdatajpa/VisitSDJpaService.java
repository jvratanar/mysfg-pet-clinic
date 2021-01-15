package guru.springframework.sfgpetclinic.services.springdatajpa;

import guru.springframework.sfgpetclinic.model.Visit;
import guru.springframework.sfgpetclinic.repositories.VisitRepository;
import guru.springframework.sfgpetclinic.services.VisitService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@Profile("springdatajpa")
@AllArgsConstructor
public class VisitSDJpaService implements VisitService {
    private final VisitRepository visitRepository;

    @Override
    public Visit findById(Long id) {
        return this.visitRepository.findById(id).orElse(null);
    }

    @Override
    public Visit save(Visit object) {
        return this.visitRepository.save(object);
    }

    @Override
    public Set<Visit> findAll() {
        Set<Visit> visits = new HashSet<>();
        this.visitRepository.findAll().forEach(visits::add);
        return visits;
    }

    @Override
    public void delete(Visit object) {
        this.visitRepository.delete(object);
    }

    @Override
    public void deleteById(Long id) {
        this.visitRepository.deleteById(id);
    }
}
