package com.example.rh_tech.Service;

import java.util.List;
import java.util.Optional;
import com.example.rh_tech.Model.CargosModel;
import com.example.rh_tech.Repository.CargosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CargosService {

    @Autowired
    private CargosRepository Repository;

    public List<CargosModel> listar() {
        return Repository.findAll();
    }

    public CargosModel salvar(CargosModel cargo) {
        return Repository.save(cargo);
    }

    public Optional<CargosModel> buscarPorId(Long id) {
        return Repository.findById(id);
    }

    public boolean deletar(Long id) {
        if (Repository.existsById(id)) {
            Repository.deleteById(id);
            return true;
        } else{
        return false;
    }
}

}


