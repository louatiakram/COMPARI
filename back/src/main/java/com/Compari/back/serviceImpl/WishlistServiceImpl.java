package com.Compari.back.serviceImpl;

import com.Compari.back.ResponseDto.WishlistResponseDto;
import com.Compari.back.entity.Products;
import com.Compari.back.entity.User;
import com.Compari.back.entity.Wishlist;
import com.Compari.back.repository.ProductsRepository;
import com.Compari.back.repository.UserRepository;
import com.Compari.back.repository.WishlistRepository;
import com.Compari.back.service.WishlistService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Service
public class WishlistServiceImpl implements WishlistService {

    private static final Logger logger = Logger.getLogger(WishlistServiceImpl.class.getName());

    @Autowired
    private WishlistRepository wishlistRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductsRepository productsRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<WishlistResponseDto> getAllWishlists() {
        List<Wishlist> wishlists = wishlistRepository.findAll();
        return wishlists.stream()
                .map(wishlist -> modelMapper.map(wishlist, WishlistResponseDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<WishlistResponseDto> getWishlistById(int id) {
        return wishlistRepository.findById(id)
                .map(wishlist -> modelMapper.map(wishlist, WishlistResponseDto.class));
    }

    @Override
    public WishlistResponseDto saveToWishlist(Integer userId, Long productId) {
        logger.info("Saving to wishlist for user ID: " + userId + " and product ID: " + productId);

        User user = userRepository.findById(userId)
                .orElseThrow(() -> {
                    logger.severe("User not found with ID: " + userId);
                    return new IllegalArgumentException("User not found");
                });

        Products product = productsRepository.findById(productId)
                .orElseThrow(() -> {
                    logger.severe("Products not found with ID: " + productId);
                    return new IllegalArgumentException("Products not found");
                });

        Wishlist wishlist = wishlistRepository.findByUser(user)
                .orElseGet(() -> {
                    Wishlist newWishlist = new Wishlist();
                    newWishlist.setUser(user);
                    return wishlistRepository.save(newWishlist);
                });

        List<Products> products = wishlist.getProducts();
        if (products == null) {
            products = new ArrayList<>();
            wishlist.setProducts(products);
        }

        if (!products.contains(product)) {
            products.add(product);
        }

        Wishlist savedWishlist = wishlistRepository.save(wishlist);
        return modelMapper.map(savedWishlist, WishlistResponseDto.class);
    }

    @Override
    public void deleteFromWishlist(int id) {
        if (wishlistRepository.existsById(id)) {
            wishlistRepository.deleteById(id);
        } else {
            logger.warning("Wishlist with ID " + id + " does not exist.");
        }
    }
}
