package edu.criandoapi.criandoapi.controller;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import edu.criandoapi.criandoapi.controller.dto.ProvaDto;
import edu.criandoapi.criandoapi.controller.dto.ProvaSimplesDto;
import edu.criandoapi.criandoapi.domain.repository.ProvaRepository;
import edu.criandoapi.criandoapi.domain.repository.UserRepository;
import edu.criandoapi.criandoapi.domain.user.RegisterDto;
import edu.criandoapi.criandoapi.domain.user.User;
import edu.criandoapi.criandoapi.service.ProvaService;

@RestController
@RequestMapping("/prova")
public class ProvaController {

    @Autowired
    ProvaService provaService;

    @Autowired
    ProvaRepository provaRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public ResponseEntity<List<ProvaDto>> findAll() {
        var prova = provaService.findAll();
        var provaDto = prova.stream().map(ProvaDto::new).collect(Collectors.toList());

        return ResponseEntity.ok(provaDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProvaDto> findById(@PathVariable Long id) {
        var prova = provaService.findById(id);

        return ResponseEntity.ok(new ProvaDto(prova));
    }

    @PostMapping
    public ResponseEntity<ProvaDto> create(@RequestBody ProvaDto provaDto) {

        var prova = provaService.create(provaDto.toModel());
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(prova.getId())
                .toUri();

        return ResponseEntity.created(location).body(new ProvaDto(prova));

    }

    @PutMapping("/{id}")
    public ResponseEntity<ProvaDto> updateProva(@PathVariable Long id, @RequestBody ProvaDto provaToUpdate) {
        var prova = provaService.updateProva(id, provaToUpdate.toModel());

        return ResponseEntity.ok(new ProvaDto(prova));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProva(@PathVariable Long id) {
        provaService.deleteProva(id);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/simples")
    public ResponseEntity<List<ProvaSimplesDto>> getAllProvasSimples() {
        List<ProvaSimplesDto> provas = provaService.getAllProvasSimples();

        return ResponseEntity.ok(provas);
    }

    @PostMapping("/memes")
    public ResponseEntity<?> memes(@RequestBody RegisterDto model) {

        System.out.println(model.login());
        System.out.println(model.password());
        System.out.println(model.role());

        if (this.userRepository.findByLogin(model.login()) != null) {
            return ResponseEntity.badRequest().build();
        }

        String encryptedPassword = new BCryptPasswordEncoder().encode(model.password());
        User newUser = new User(model.login(), encryptedPassword, model.role());

        this.userRepository.save(newUser);

        return ResponseEntity.ok().build();
    }

}
