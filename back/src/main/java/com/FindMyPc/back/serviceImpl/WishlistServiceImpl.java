package com.FindMyPc.back.serviceImpl;

import com.FindMyPc.back.entity.Wishlist;
import com.FindMyPc.back.repository.WishlistRepository;
import com.FindMyPc.back.service.WishlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WishlistServiceImpl implements WishlistService {

    @Autowired
    private WishlistRepository wishlistRepository;

    @Override
    public List<Wishlist> getAllWishlists() {
        return wishlistRepository.findAll();
    }

    @Override
    public Optional<Wishlist> getWishlistById(int id) {
        return wishlistRepository.findById(id);
    }

    @Override
    public Wishlist saveWishlist(Wishlist wishlist) {
        return wishlistRepository.save(wishlist);
    }

    @Override
    public void deleteWishlist(int id) {
        wishlistRepository.deleteById(id);
    }
}
