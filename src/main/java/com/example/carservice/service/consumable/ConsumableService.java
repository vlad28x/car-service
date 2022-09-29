package com.example.carservice.service.consumable;

import com.example.carservice.entity.Consumable;

import java.util.List;

public interface ConsumableService {


    public List<Consumable> getAllConsumables();

    public void saveConsumable(Consumable consumable);

    public Consumable getConsumable(long id);

    public void deleteConsumable(long id);
}
