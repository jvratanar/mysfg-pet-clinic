package guru.springframework.sfgpetclinic.services.springdatajpa;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.repositories.OwnerRepository;
import guru.springframework.sfgpetclinic.repositories.PetRepository;
import guru.springframework.sfgpetclinic.repositories.PetTypeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

// Sets up JUnit 5 environment fot Mockito
@ExtendWith(MockitoExtension.class)
class OwnerSDJpaServiceTest {
    public static final String LAST_NAME = "Smith";
    @Mock
    OwnerRepository ownerRepository;

    @Mock
    PetRepository petRepository;

    @Mock
    PetTypeRepository petTypeRepository;

    @InjectMocks // injects the mocks for repositories
    OwnerSDJpaService ownerSDJpaService;

    Owner returnOwner;

    @BeforeEach
    void setUp() {
        this.returnOwner = Owner.builder().id(1l).lastName(LAST_NAME).build();
    }

    @Test
    void findById() {
        when(this.ownerRepository.findById(anyLong())).thenReturn(Optional.of(this.returnOwner));

        Owner owner = this.ownerSDJpaService.findById(this.returnOwner.getId());

        assertNotNull(owner);
    }

    @Test
    void findByIdNotFound() {
        when(this.ownerRepository.findById(anyLong())).thenReturn(Optional.empty());

        Owner owner = this.ownerSDJpaService.findById(this.returnOwner.getId());

        assertNull(owner);
    }

    @Test
    void save() {
        Owner ownerToSave = Owner.builder().id(1l).build();

        when(this.ownerRepository.save(any())).thenReturn(ownerToSave);

        Owner savedOwner = this.ownerSDJpaService.save(ownerToSave);

        assertNotNull(savedOwner);
        verify(this.ownerRepository).save(any());
    }

    @Test
    void findAll() {
        Set<Owner> returnOwnersSet = new HashSet<>();
        returnOwnersSet.add(Owner.builder().id(1l).build());
        returnOwnersSet.add(Owner.builder().id(2l).build());
        when(this.ownerRepository.findAll()).thenReturn(returnOwnersSet);

        Set<Owner> owners = this.ownerSDJpaService.findAll();

        assertNotNull(owners);
        assertEquals(returnOwnersSet.size(), owners.size());
    }

    @Test
    void delete() {
        this.ownerSDJpaService.delete(this.returnOwner);

        // verify that the delete method of a repository was called
        verify(this.ownerRepository, times(1)).delete(any());
    }

    @Test
    void deleteById() {
        this.ownerSDJpaService.deleteById(this.returnOwner.getId());

        // verify that the delete method of a repository was called
        verify(this.ownerRepository).deleteById(anyLong());
    }

    @Test
    void findByLastName() {
        when(this.ownerRepository.findByLastName(any())).thenReturn(this.returnOwner);

        Owner smith = this.ownerSDJpaService.findByLastName(LAST_NAME);

        assertEquals(LAST_NAME, smith.getLastName());
        verify(this.ownerRepository).findByLastName(any());
    }

    @Test
    void findByLastNameLike() {
    }
}