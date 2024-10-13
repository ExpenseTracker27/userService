package github.tanishqtrivedi27.userservice.repositories;

import github.tanishqtrivedi27.userservice.entities.UserInfoDTO;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<UserInfoDTO, String> {
    Optional<UserInfoDTO> findByUserId(String userId);
}
