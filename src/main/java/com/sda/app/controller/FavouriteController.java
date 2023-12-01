package com.sda.app.controller;

import com.sda.app.dto.CartDto;
import com.sda.app.dto.FavouriteDto;
import com.sda.app.entity.Cart;
import com.sda.app.entity.Favourite;
import com.sda.app.entity.User;
import com.sda.app.service.CartService;
import com.sda.app.service.FavouriteService;
import com.sda.app.service.UserService;
import com.sda.app.utils.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping("/favourites")
public class FavouriteController {
    @Autowired
    private FavouriteService favouriteService;

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public ResponseEntity<ApiResponse> getAllFavourites() {
        List<Favourite> favouriteList = this.favouriteService.findAll();
        ApiResponse response = new ApiResponse.Builder()
                .status(200)
                .message("Lista de favorite")
                .data(favouriteList)
                .build();
        return ResponseEntity.ok(response);
    }

    @PostMapping("/")
    public ResponseEntity<ApiResponse> createFavourite(@RequestBody FavouriteDto favouriteDto) {
        System.out.println(favouriteDto.getUserId());

        try {
            Optional<User> optionalUser = this.userService.findById(favouriteDto.getUserId());

            if(optionalUser.isPresent()) {
                User user = optionalUser.get();

                Favourite favourite = new Favourite();

                favourite.setUser(user);
                favourite.setItems(favouriteDto.getItems());

                ApiResponse response = new ApiResponse.Builder()
                        .status(200)
                        .message("Favourites successfully created")
                        .data(favouriteService.createFavourite(favourite))
                        .build();
                return ResponseEntity.ok(response);
            } else {
                throw new Exception("Invalid user");
            }
        } catch (Exception exception) {
            ApiResponse response = new ApiResponse.Builder()
                    .status(500)
                    .message(exception.getMessage())
                    .data(null)
                    .build();
            return ResponseEntity.ok(response);
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ApiResponse> updateFavourite(@RequestBody FavouriteDto favouriteDto, @PathVariable("id") Integer id) {
        try {
            Optional<User> optionalUser = this.userService.findById(favouriteDto.getUserId());

            if(optionalUser.isPresent()) {
                User user = optionalUser.get();

                Favourite favourite = new Favourite();

                favourite.setId(id);
                favourite.setUser(user);
                favourite.setItems(favouriteDto.getItems());

                ApiResponse response = new ApiResponse.Builder()
                        .status(200)
                        .message("Cos actualizat cu success")
                        .data(favouriteService.updateFavourite(favourite))
                        .build();
                return ResponseEntity.ok(response);
            } else {
                throw new Exception("Invalid user");
            }
        } catch (Exception exception) {
            ApiResponse response = new ApiResponse.Builder()
                    .status(500)
                    .message(exception.getMessage())
                    .data(null)
                    .build();
            return ResponseEntity.ok(response);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteFavourite(@PathVariable("id") Integer id) {
        favouriteService.deleteFavouriteById(id);

        ApiResponse response = new ApiResponse.Builder()
                .status(200)
                .message("Favourite successfully deleted")
                .data(null)
                .build();
        return ResponseEntity.ok(response);
    }
}
