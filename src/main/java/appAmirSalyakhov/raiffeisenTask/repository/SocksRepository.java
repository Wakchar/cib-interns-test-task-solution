package appAmirSalyakhov.raiffeisenTask.repository;

import appAmirSalyakhov.raiffeisenTask.model.SocksDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SocksRepository extends JpaRepository<SocksDto, Long> {

    List<SocksDto> findAllByColor(String color);
}
