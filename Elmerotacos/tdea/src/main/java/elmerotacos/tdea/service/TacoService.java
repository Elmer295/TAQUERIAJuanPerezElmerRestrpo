package elmerotacos.tdea.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import elmerotacos.tdea.model.TacoModel;
import elmerotacos.tdea.repository.TacoRepository;

@Service
public class TacoService {
    
    @Autowired
    TacoRepository tacoRepository;

    public List<TacoModel> getAllTacos( ) {
       return tacoRepository.findAll();
    }
}
