package i.learn.spring.boot.graphql.repository;

import i.learn.spring.boot.graphql.model.Ship;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShipRepository extends JpaRepository<Ship, Integer> {
}
