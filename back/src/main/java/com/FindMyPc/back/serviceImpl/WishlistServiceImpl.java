package com.FindMyPc.back.serviceImpl;
import com.FindMyPc.back.ResponseDto.WishlistResponseDto;
import com.FindMyPc.back.entity.Product;
import com.FindMyPc.back.entity.User;
import com.FindMyPc.back.entity.Wishlist;
import com.FindMyPc.back.repository.ProductRepository;
import com.FindMyPc.back.repository.UserRepository;
import com.FindMyPc.back.repository.WishlistRepository;
import com.FindMyPc.back.service.WishlistService;
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
    private ProductRepository productRepository;

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
    public WishlistResponseDto saveToWishlist(Integer userId, Integer productId) {
        logger.info("Saving to wishlist for user ID: " + userId + " and product ID: " + productId);

        User user = userRepository.findById(userId)
                .orElseThrow(() -> {
                    logger.severe("User not found with ID: " + userId);
                    return new IllegalArgumentException("User not found");
                });

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> {
                    logger.severe("Product not found with ID: " + productId);
                    return new IllegalArgumentException("Product not found");
                });

        Wishlist wishlist = wishlistRepository.findByUser(user)
                .orElseGet(() -> {
                    Wishlist newWishlist = new Wishlist();
                    newWishlist.setUser(user);
                    return wishlistRepository.save(newWishlist);
                });

        List<Product> products = wishlist.getProducts();
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
