package appAmirSalyakhov.raiffeisenTask.service.socks.impl;

import appAmirSalyakhov.raiffeisenTask.model.SocksDto;
import appAmirSalyakhov.raiffeisenTask.repository.SocksRepository;
import appAmirSalyakhov.raiffeisenTask.service.socks.SocksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SocksServiceImpl implements SocksService {

    @Autowired
    private SocksRepository socksRepository;

    @Override
    public List<SocksDto> getAllSocksFromWarehouse(){
        return socksRepository.findAll();
    }

    @Override
    public List<SocksDto> getAllByColor(String color){
        return socksRepository.findAllByColor(color);
    }
}
