package plateforme_citoyenne_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import plateforme_citoyenne_api.entity.Vote;

public interface VoteRepository extends JpaRepository<Vote, Long> {

}
