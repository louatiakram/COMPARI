package com.Compari.back.serviceImpl;

import com.Compari.back.entity.Products;
import com.Compari.back.entity.Rating;
import com.Compari.back.entity.Store;
import com.Compari.back.entity.User;
import com.Compari.back.repository.ProductsRepository;
import com.Compari.back.service.RatingService;
import com.Compari.back.ResponseDto.RatingResponseDto;
import com.Compari.back.repository.RatingRepository;
import com.Compari.back.repository.StoreRepository;
import com.Compari.back.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RatingServiceImpl implements RatingService {

    @Autowired
    private RatingRepository ratingRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductsRepository productsRepository;

    @Autowired
    private StoreRepository storeRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<RatingResponseDto> getAllRatings() {
        List<Rating> ratings = ratingRepository.findAll();
        return ratings.stream()
                .map(rating -> modelMapper.map(rating, RatingResponseDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<RatingResponseDto> getRatingById(int id) {
        return ratingRepository.findById(id)
                .map(rating -> modelMapper.map(rating, RatingResponseDto.class));
    }

    @Override
    public RatingResponseDto saveProductRating(RatingResponseDto ratingResponseDto, Integer userId, Long productId) {
        User user = userRepository.findById(userId).orElse(null);
        Products products = productsRepository.findById(productId).orElse(null);

        Rating rating = modelMapper.map(ratingResponseDto, Rating.class);
        rating.setUser(user);
        rating.setProducts(products);

        Rating savedRating = ratingRepository.save(rating);
        return modelMapper.map(savedRating, RatingResponseDto.class);
    }

    @Override
    public RatingResponseDto saveStoreRating(RatingResponseDto ratingResponseDto, Integer storeId, Integer userId) {
        User user = userRepository.findById(userId).orElse(null);
        Store store = storeRepository.findById(storeId).orElse(null);

        Rating rating = modelMapper.map(ratingResponseDto, Rating.class);
        rating.setUser(user);
        rating.setStore(store);

        Rating savedRating = ratingRepository.save(rating);
        return modelMapper.map(savedRating, RatingResponseDto.class);
    }

    @Override
    public RatingResponseDto updateProductRating(RatingResponseDto ratingResponseDto, Integer userId, Long productId) {
        User user = userRepository.findById(userId).orElse(null);
        Products products = productsRepository.findById(productId).orElse(null);

        if (ratingRepository.existsById(ratingResponseDto.getRatingID())) {
            Rating rating = modelMapper.map(ratingResponseDto, Rating.class);
            rating.setUser(user);
            rating.setProducts(products);

            Rating updatedRating = ratingRepository.save(rating);
            return modelMapper.map(updatedRating, RatingResponseDto.class);
        }
        return null; // or throw an exception if appropriate
    }

    @Override
    public RatingResponseDto updateStoreRating(RatingResponseDto ratingResponseDto, Integer storeId, Integer userId) {
        User user = userRepository.findById(userId).orElse(null);
        Store store = storeRepository.findById(storeId).orElse(null);

        if (ratingRepository.existsById(ratingResponseDto.getRatingID())) {
            Rating rating = modelMapper.map(ratingResponseDto, Rating.class);
            rating.setUser(user);
            rating.setStore(store);

            Rating updatedRating = ratingRepository.save(rating);
            return modelMapper.map(updatedRating, RatingResponseDto.class);
        }
        return null; // or throw an exception if appropriate
    }

    @Override
    public void deleteRating(int id) {
        if (ratingRepository.existsById(id)) {
            ratingRepository.deleteById(id);
        }
        // Handle the case where the rating does not exist
    }
}
