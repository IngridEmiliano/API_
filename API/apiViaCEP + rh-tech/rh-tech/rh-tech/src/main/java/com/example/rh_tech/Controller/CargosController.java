package com.example.rh_tech.Controller;

import com.example.rh_tech.Model.CargosModel;
import com.example.rh_tech.Service.CargosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = {"http://127.0.0.1:5501", "http://localhost:5501"})
@RestController
@RequestMapping("/api/cargos")
public class CargosController {

    @Autowired
    private CargosService service;

    @GetMapping
    public List<CargosModel> listar() {
        return service.listar();
    }

    @PostMapping
    public CargosModel cadastrar(@RequestBody CargosModel cargo) {
        return service.salvar(cargo);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CargosModel> buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<CargosModel> atualizar(@PathVariable Long id, @RequestBody CargosModel cargoAtualizado) {
        return service.buscarPorId(id)
                .map(cargoExistente -> {
                    cargoExistente.setNome(cargoAtualizado.getNome());
                    cargoExistente.setDescricao(cargoAtualizado.getDescricao());
                    return ResponseEntity.ok(service.salvar(cargoExistente));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        if (service.deletar(id)) {
            return ResponseEntity.noContent().build(); // 204 - No Content
        } else {
            return ResponseEntity.notFound().build();   // 404 - Not Found
        }
    }
}
