package guru.springframework.sfgpetclinic.model;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
//@Builder    // gives us the builder pattern
@Entity
@Table(name = "owners")
public class Owner extends Person {
    @Column(name = "address")
    private String address;

    @Column(name = "city")
    private String city;

    @Column(name = "telephone")
    private String telephone;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "owner")
    private Set<Pet> pets = new HashSet<>();

    // The constructor is annotated because we want to bring in
    // the id, firstName, lastName from entity Person
    @Builder
    public Owner(Long id, String firstName, String lastName, String address,
                 String city, String telephone, Set<Pet> pets) {
        super(id, firstName, lastName);
        this.address = address;
        this.city = city;
        this.telephone = telephone;

        if (pets != null) {
            this.pets = pets;
        }
    }


    public Pet getPet(String name) {
        return this.getPet(name, false);
    }


    public Pet getPet(String name, boolean ignoreNew) {
        name = name.toLowerCase();
        for (Pet pet : this.pets) {
            if (!ignoreNew || !pet.isNew()) {
                String compName = pet.getName().toLowerCase();
                if (compName.equals(name)) {
                    return pet;
                }
            }
        }

        return null;
    }
}
