package com.example.carservice.service.consumable;

import com.example.carservice.entity.Consumable;
import com.example.carservice.entity.Order;
import com.example.carservice.repositories.ConsumableRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class ConsumableServiceImpl implements ConsumableService {

    @Autowired
    ConsumableRepository consumableRepository;

    @Override
    public List<Consumable> getAllConsumables() {
        return consumableRepository.findAll();
    }

    @Override
    public void saveConsumable(Consumable consumable) {
        consumableRepository.save(consumable);
    }

    @Override
    public Consumable getConsumable(long id) {
        Consumable consumable = new Consumable();
        Optional<Consumable> optional = consumableRepository.findById(id);
        if (optional.isPresent()) {
            consumable = optional.get();
        }
        return consumable;
    }

    @Override
    public void deleteConsumable(long id) {
        consumableRepository.deleteById(id);
    }
}
