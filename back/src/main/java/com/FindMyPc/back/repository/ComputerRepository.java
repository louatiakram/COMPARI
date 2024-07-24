package tech.louatiakram.scrapping.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.louatiakram.scrapping.entities.Computer;

import java.util.List;

public interface ComputerRepository extends JpaRepository<Computer, Long> {
    List<Computer> findByNameAndPrice(String name, Double price);
}
