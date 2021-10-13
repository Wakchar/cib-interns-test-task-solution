package appAmirSalyakhov.raiffeisenTask.service.socks;

import appAmirSalyakhov.raiffeisenTask.model.SocksDto;

import java.util.List;

public interface SocksService {
    List<SocksDto> getAllSocksFromWarehouse();

    List<SocksDto> getAllByColor(String color);
}
