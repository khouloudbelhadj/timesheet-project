package tn.esprit.spring.services;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Optional;

import tn.esprit.spring.entities.User;

public class UserRepositoryMock {
    private final ArrayList<User> users = new ArrayList<>();
    private long currentId = 1;

    public User save(User user) {
        if (user.getId() == null) { // Ajout d'un nouvel utilisateur
            user.setId(currentId++);
            users.add(user);
        } else { // Mise à jour d'un utilisateur existant
            for (int i = 0; i < users.size(); i++) {
                if (users.get(i).getId().equals(user.getId())) {
                    users.set(i, user);
                    return user;
                }
            }
        }
        return user;
    }

    public void deleteById(Long id) {
        Iterator<User> iterator = users.iterator();
        while (iterator.hasNext()) {
            if (iterator.next().getId().equals(id)) {
                iterator.remove();
                return;
            }
        }
    }

    public Optional<User> findById(Long id) {
        return users.stream()
                .filter(user -> user.getId().equals(id))
                .findFirst();
    }
}
