package com.pagina.pagina4.Service;

import com.pagina.pagina4.Model.Opcion;
import com.pagina.pagina4.Repository.OpcionRepository;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OpcionService {

    private final OpcionRepository opcionRepository;

    public OpcionService(OpcionRepository opcionRepository) {
        this.opcionRepository = opcionRepository;
    }

    // Listar todas las opciones de tacos
    public List<Opcion> listarOpciones() {
        return opcionRepository.findAll();
    }

    // Obtener un taco por su ID
    public Opcion obtenerPorId(Long id) {
        return opcionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Taco no encontrado con id: " + id));
    }

    // Buscar un taco por nombre (opcional)
    public Opcion buscarPorNombre(String nombre) {
        return opcionRepository.findByNombre(nombre);
    }
}