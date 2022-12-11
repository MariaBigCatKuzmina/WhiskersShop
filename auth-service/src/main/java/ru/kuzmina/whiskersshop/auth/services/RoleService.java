package ru.kuzmina.whiskersshop.auth.services;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import ru.kuzmina.whiskersshop.api.AppError;
import ru.kuzmina.whiskersshop.auth.entities.Role;
import ru.kuzmina.whiskersshop.auth.repositories.RoleRepository;

@Service
@RequiredArgsConstructor
public class RoleService {
    private final RoleRepository roleRepository;
    public Role getUserRole(String name){
//        return roleRepository.findByName(name).orElseThrow(()->new AppError(HttpStatus.NOT_FOUND.value(),
//                String.format("Роль %s ненайдена", name)));
        return roleRepository.findByName(name).get();

    }
}
