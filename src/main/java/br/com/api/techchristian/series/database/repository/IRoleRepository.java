package br.com.api.techchristian.series.database.repository;

import br.com.api.techchristian.series.database.models.RolesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface IRoleRepository extends JpaRepository<RolesEntity, Long> {
}
