package elmerotacos.tdea.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import elmerotacos.tdea.model.UserModel;
@Repository
public interface UserRepository extends JpaRepository<UserModel, Long> {
    // Additional query methods can be defined here if needed

  
}