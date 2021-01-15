package guru.springframework.sfgpetclinic.services.springdatajpa;

import guru.springframework.sfgpetclinic.model.Pet;
import guru.springframework.sfgpetclinic.repositories.PetRepository;
import guru.springframework.sfgpetclinic.services.PetService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@Profile("springdatajpa")
@AllArgsConstructor
public class PetSDJpaService implements PetService {
    private final PetRepository petRepository;

    @Override
    public Pet findById(Long id) {
        return this.petRepository.findById(id).orElse(null);
    }

    @Override
    public Pet save(Pet object) {
        return this.petRepository.save(object);
    }

    @Override
    public Set<Pet> findAll() {
        Set<Pet> pets = new HashSet<>();
        this.petRepository.findAll().forEach(pets::add);
        return pets;
    }

    @Override
    public void delete(Pet object) {
        this.petRepository.delete(object);
    }

    @Override
    public void deleteById(Long id) {
        this.petRepository.deleteById(id);
    }
}
