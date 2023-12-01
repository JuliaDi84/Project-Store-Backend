package com.sda.app.service;

import com.sda.app.entity.Cart;
import com.sda.app.entity.Favourite;
import com.sda.app.repository.CartRepository;
import com.sda.app.repository.FavouriteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class FavouriteService {
    @Autowired
    private FavouriteRepository favouriteRepository;

    public FavouriteService(FavouriteRepository favouriteRepository) {
        this.favouriteRepository = favouriteRepository;
    }

    public List<Favourite> findAll() {
        return this.favouriteRepository.findAll();
    }

    public Optional<Favourite> findById(Integer id) {
        return favouriteRepository.findById(id);
    }

    public Favourite createFavourite(Favourite favourite) {
        return favouriteRepository.save(favourite);
    }

    public Favourite updateFavourite(Favourite favourite) {
        return favouriteRepository.save(favourite);
    }

    public void deleteFavourite(Favourite favourite) {
        favouriteRepository.delete(favourite);
    }

    public void deleteFavouriteById(Integer id) {
        favouriteRepository.deleteById(id);
    }
}


