package appAmirSalyakhov.raiffeisenTask.service.socks.impl;

import appAmirSalyakhov.raiffeisenTask.advice.SocksControllerExceptionHandler;
import appAmirSalyakhov.raiffeisenTask.model.Socks;
import appAmirSalyakhov.raiffeisenTask.repository.SocksRepository;
import appAmirSalyakhov.raiffeisenTask.service.socks.SocksService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

@Service
@Transactional
public class SocksServiceImpl implements SocksService {

    @Autowired
    private SocksRepository socksRepository;

    public List<Socks> getSockByColorAndCottonPart(String color, String operation, int cottonPart) {
        switch (operation) {
            case "moreThan":
                return socksRepository.findSocksByColorAndCottonPartIsGreaterThan(color.toLowerCase(), cottonPart);
            case "lessThan":
                return socksRepository.findSocksByColorAndCottonPartIsLessThan(color.toLowerCase(), cottonPart);
            case "equal":
                return Collections.singletonList(socksRepository.findSocksByColorAndCottonPartEquals(color, cottonPart));
        }
        throw new SocksControllerExceptionHandler();
    }

    public void saveOrUpdateSocksQuantityFormWarehouse(Socks incomeSocks) {
        try {
            Socks selectForUpdateSocks = socksRepository.findSocksByColorAndCottonPartEquals(incomeSocks.getColor(), incomeSocks.getCottonPart());
            if (selectForUpdateSocks != null) {
                selectForUpdateSocks.setQuantity(selectForUpdateSocks.getQuantity() + incomeSocks.getQuantity());
                socksRepository.saveAndFlush(selectForUpdateSocks);
            } else socksRepository.saveAndFlush(incomeSocks);
        } catch (SocksControllerExceptionHandler e) {
            throw new SocksControllerExceptionHandler();
        }
    }

    public ResponseEntity<String> subtractSocksFormWarehouse(Socks outcomeSocks) {
        Socks selectForUpdateSocks = socksRepository.findSocksByColorAndCottonPartEquals(outcomeSocks.getColor(), outcomeSocks.getCottonPart());
        if (selectForUpdateSocks != null) {
            if (selectForUpdateSocks.getQuantity() < outcomeSocks.getQuantity()) {
                return new ResponseEntity<>("Quantity must be less or equal than, " + selectForUpdateSocks.getQuantity()
                        + " units in Warehouse", HttpStatus.BAD_REQUEST);
            } else
                selectForUpdateSocks.setQuantity(selectForUpdateSocks.getQuantity() - outcomeSocks.getQuantity());
            socksRepository.saveAndFlush(selectForUpdateSocks);
            return new ResponseEntity<>("Ok", HttpStatus.OK);
        }
        return new ResponseEntity<>("Cannot subtract socks", HttpStatus.BAD_REQUEST);
    }
}
