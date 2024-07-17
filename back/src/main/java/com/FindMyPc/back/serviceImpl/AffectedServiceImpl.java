package com.FindMyPc.back.serviceImpl;


import com.FindMyPc.back.entity.Affected;
import com.FindMyPc.back.repository.AffectedRepository;
import com.FindMyPc.back.service.AffectedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AffectedServiceImpl implements AffectedService {

    @Autowired
    private AffectedRepository affectedRepository;

    @Override
    public List<Affected> getAllAffected() {
        return affectedRepository.findAll();
    }

    @Override
    public Optional<Affected> getAffectedById(int id) {
        return affectedRepository.findById(id);
    }

    @Override
    public Affected saveAffected(Affected affected) {
        return affectedRepository.save(affected);
    }

    @Override
    public void deleteAffected(int id) {
        affectedRepository.deleteById(id);
    }

    @Override
    public Page<Affected> getAffectedPaginated(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return affectedRepository.findAll(pageable);
    }
}
