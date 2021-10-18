package appAmirSalyakhov.raiffeisenTask.controller;

import appAmirSalyakhov.raiffeisenTask.model.Socks;
import appAmirSalyakhov.raiffeisenTask.service.socks.SocksService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.List;

@RestController
@Validated
@RequestMapping(value = "/socks")
public class SocksController {

    private final SocksService socksService;

    public SocksController(SocksService socksService) {
        this.socksService = socksService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Socks>> getSocksByRequestValue(@RequestParam(name = "color") String color,
                                                                  @RequestParam(name = "operation") String operation,
                                                                  @RequestParam(name = "cottonPart")@Min(0) @Max(100) Integer cottonPart){
        List<Socks> responseSocksList = socksService.getSockByColorAndCottonPart(color, operation, cottonPart);
        return new ResponseEntity<>(responseSocksList, HttpStatus.OK);
    }

    @PostMapping(value = "/income", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> saveOrUpdateSocksInWarehouse(@RequestBody Socks socks) {
        socksService.saveOrUpdateSocksQuantityFormWarehouse(socks);
        return new ResponseEntity<>("Socks successful added in Warehouse",HttpStatus.OK);
    }

    @PostMapping(value = "/outcome", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> subtractSocksFromWarehouse(@RequestBody Socks socks) {
        return socksService.subtractSocksFormWarehouse(socks);
    }
}
