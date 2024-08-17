package com.FindMyPc.back.serviceImpl;

import com.FindMyPc.back.ResponseDto.RatingResponseDto;
import com.FindMyPc.back.entity.Product;
import com.FindMyPc.back.entity.Rating;
import com.FindMyPc.back.entity.Store;
import com.FindMyPc.back.entity.User;
import com.FindMyPc.back.repository.ProductRepository;
import com.FindMyPc.back.repository.RatingRepository;
import com.FindMyPc.back.repository.StoreRepository;
import com.FindMyPc.back.repository.UserRepository;
import com.FindMyPc.back.service.RatingService;
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
    private ProductRepository productRepository;

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
        Product product = productRepository.findById(productId).orElse(null);

        Rating rating = modelMapper.map(ratingResponseDto, Rating.class);
        rating.setUser(user);
        rating.setProduct(product);

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
        Product product = productRepository.findById(productId).orElse(null);

        if (ratingRepository.existsById(ratingResponseDto.getRatingID())) {
            Rating rating = modelMapper.map(ratingResponseDto, Rating.class);
            rating.setUser(user);
            rating.setProduct(product);

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
