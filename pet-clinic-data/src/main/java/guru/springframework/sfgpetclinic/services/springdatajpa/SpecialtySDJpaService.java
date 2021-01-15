package guru.springframework.sfgpetclinic.services.springdatajpa;

import guru.springframework.sfgpetclinic.model.Speciality;
import guru.springframework.sfgpetclinic.repositories.SpecialtyRepository;
import guru.springframework.sfgpetclinic.services.SpecialtyService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@Profile("springdatajpa")
@AllArgsConstructor
public class SpecialtySDJpaService implements SpecialtyService {
    private final SpecialtyRepository specialtyRepository;

    @Override
    public Speciality findById(Long id) {
        return this.specialtyRepository.findById(id).orElse(null);
    }

    @Override
    public Speciality save(Speciality object) {
        return this.specialtyRepository.save(object);
    }

    @Override
    public Set<Speciality> findAll() {
        Set<Speciality> specialities = new HashSet<>();
        this.specialtyRepository.findAll().forEach(specialities::add);
        return specialities;
    }

    @Override
    public void delete(Speciality object) {
        this.specialtyRepository.delete(object);
    }

    @Override
    public void deleteById(Long id) {
        this.specialtyRepository.deleteById(id);
    }
}
