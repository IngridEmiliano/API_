package com.example.rh_tech.Controller;

import com.example.rh_tech.Model.FuncionariosModel;
import com.example.rh_tech.Service.FuncionariosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = {"http://127.0.0.1:5501", "http://localhost:5501"})
@RestController
@RequestMapping("/api/funcionarios")
public class FuncionariosController {

    @Autowired
    private FuncionariosService service;

    @GetMapping
    public List<FuncionariosModel> listar() {
        return service.listar();
    }

    @PostMapping
    public FuncionariosModel cadastrar(@RequestBody FuncionariosModel funcionarios) {
        return service.salvar(funcionarios);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FuncionariosModel> buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        if (service.deletar(id)) {
            return ResponseEntity.noContent().build(); // 204 - No Content
        } else {
            return ResponseEntity.notFound().build();  
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<FuncionariosModel> atualizar(@PathVariable Long id, @RequestBody FuncionariosModel funcionariosAtualizado) {
        return service.atualizar(id, funcionariosAtualizado)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
