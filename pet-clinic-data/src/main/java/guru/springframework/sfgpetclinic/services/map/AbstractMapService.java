package guru.springframework.sfgpetclinic.services.map;

import guru.springframework.sfgpetclinic.model.BaseEntity;

import java.util.*;
//
public abstract class AbstractMapService<T extends BaseEntity, ID extends Long> {
    protected Map<Long, T> map = new HashMap<>();

    T findById(ID id) {
        return this.map.get(id);
    }

    T save(T object) {
        if (object != null) {
            if (object.getId() == null) {
                object.setId(getNextId());
            }

            this.map.put(object.getId(), object);
        } else {
            throw new RuntimeException("Object cannot be null");
        }

        return object;
    }

    private Long getNextId() {
        Long nextId = null;

        try {
            nextId = Collections.max(this.map.keySet()) + 1;
        } catch (NoSuchElementException e) {
            nextId = 1L;
        }

        return nextId;
    }

    Set<T> findAll() {
        return new HashSet<>(this.map.values());
    }

    void delete(T object) {
        this.map.entrySet().removeIf(e -> e.getValue().equals(object));
    }

    void deleteById(ID id) {
        this.map.remove(id);
    }
}
