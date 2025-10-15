package org.archivos.ecommercegt.controllers;

import lombok.RequiredArgsConstructor;
import org.archivos.ecommercegt.config.ApplicationConfig;
import org.archivos.ecommercegt.models.Roletype;
import org.archivos.ecommercegt.repository.RoleTypeRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(ApplicationConfig.BASE_URL + "/role")
@RequiredArgsConstructor
public class RoleTypeController {

    private final RoleTypeRepository roleTypeRepository;

    @GetMapping
    public ResponseEntity<List<String>> getRoleTypes() {

        List<Roletype> roles  = roleTypeRepository.findAll();
        System.out.println("se obtuviero los roles:");
        for (Roletype role : roles) {
            System.out.println(role.toString());
        }
        List<String> roleTypes = new ArrayList<>();

        for(Roletype role : roles) {
            System.out.println(role.getName());
            roleTypes.add(role.getName());
        }
        return ResponseEntity.ok(roleTypes);
    }

}
