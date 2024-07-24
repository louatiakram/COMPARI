package com.FindMyPc.back.serviceImpl;

import com.FindMyPc.back.ResponseDto.WishlistResponseDto;
import com.FindMyPc.back.entity.Wishlist;
import com.FindMyPc.back.entity.Product;
import com.FindMyPc.back.entity.User;
import com.FindMyPc.back.repository.WishlistRepository;
import com.FindMyPc.back.repository.ProductRepository;
import com.FindMyPc.back.repository.UserRepository;
import com.FindMyPc.back.service.WishlistService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class WishlistServiceImpl implements WishlistService {

    @Autowired
    private WishlistRepository wishlistRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;

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
        Optional<Wishlist> wishlistOptional = wishlistRepository.findById(id);
        return wishlistOptional.map(wishlist -> modelMapper.map(wishlist, WishlistResponseDto.class));
    }

    @Override
    public WishlistResponseDto saveToWishlist(WishlistResponseDto wishlistResponseDto) {
        User user = userRepository.findById(wishlistResponseDto.getUser().getUserID())
                .orElseThrow(() -> new RuntimeException("User not found"));
        Product product = productRepository.findById(wishlistResponseDto.getProduct().getProductID())
                .orElseThrow(() -> new RuntimeException("Product not found"));

        Wishlist wishlist = new Wishlist();
        wishlist.setUser(user);
        wishlist.setProduct(product);

        Wishlist savedWishlist = wishlistRepository.save(wishlist);
        return modelMapper.map(savedWishlist, WishlistResponseDto.class);
    }

    @Override
    public void deleteFromWishlist(int id) {
        if (wishlistRepository.existsById(id)) {
            wishlistRepository.deleteById(id);
        }
        // Handle the case where the wishlist entry does not exist
    }
}
