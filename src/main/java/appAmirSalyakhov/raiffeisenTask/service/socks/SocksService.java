package appAmirSalyakhov.raiffeisenTask.service.socks;

import appAmirSalyakhov.raiffeisenTask.model.Socks;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface SocksService {

    List<Socks> getSockByColorAndCottonPart(String color, String operation, int cottonPart);

    void saveOrUpdateSocksQuantityFormWarehouse(Socks socks);

    ResponseEntity<String> subtractSocksFormWarehouse(Socks socks);
}
