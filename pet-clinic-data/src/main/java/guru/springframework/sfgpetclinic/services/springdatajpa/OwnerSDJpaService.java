package guru.springframework.sfgpetclinic.services.springdatajpa;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.repositories.OwnerRepository;
import guru.springframework.sfgpetclinic.repositories.PetRepository;
import guru.springframework.sfgpetclinic.repositories.PetTypeRepository;
import guru.springframework.sfgpetclinic.services.OwnerService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Profile("springdatajpa")
@AllArgsConstructor
public class OwnerSDJpaService implements OwnerService {
    private final OwnerRepository ownerRepository;
    private final PetRepository petRepository;
    private final PetTypeRepository petTypeRepository;

    @Override
    public Owner findById(Long id) {
        return this.ownerRepository.findById(id).orElse(null);
    }

    @Override
    public Owner save(Owner object) {
        return this.ownerRepository.save(object);
    }

    @Override
    public Set<Owner> findAll() {
        Set<Owner> owners = new HashSet<>();
        this.ownerRepository.findAll().forEach(owners::add);
        return owners;
    }

    @Override
    public void delete(Owner object) {
        this.ownerRepository.delete(object);
    }

    @Override
    public void deleteById(Long id) {
        this.ownerRepository.deleteById(id);
    }

    @Override
    public Owner findByLastName(String lastName) {
        return this.ownerRepository.findByLastName(lastName);
    }

    @Override
    public List<Owner> findAllByLastNameLike(String lastName) {
        return this.ownerRepository.findAllByLastNameLike(lastName);
    }
}
