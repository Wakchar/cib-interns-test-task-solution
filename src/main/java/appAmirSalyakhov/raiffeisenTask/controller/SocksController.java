package appAmirSalyakhov.raiffeisenTask.controller;

import appAmirSalyakhov.raiffeisenTask.model.SocksDto;
import appAmirSalyakhov.raiffeisenTask.repository.SocksRepository;
import appAmirSalyakhov.raiffeisenTask.service.socks.SocksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/socks")
public class SocksController {

    @Autowired
    private SocksService socksService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<SocksDto> getAllSocks() {
        return socksService.getAllSocksFromWarehouse();
    }

    @GetMapping(params = {"color"},produces = MediaType.APPLICATION_JSON_VALUE)
    public List<SocksDto> getSocksByRequestValue(@Param("color") String color){
        return socksService.getAllByColor(color);
    }
}
