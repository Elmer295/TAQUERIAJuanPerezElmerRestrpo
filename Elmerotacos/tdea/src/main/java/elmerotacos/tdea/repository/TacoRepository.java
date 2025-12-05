package elmerotacos.tdea.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import elmerotacos.tdea.model.TacoModel;
@Repository
public interface TacoRepository extends JpaRepository<TacoModel, Long> {




    
} 
