package com.FindMyPc.back.service;


import com.FindMyPc.back.entity.Affected;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface AffectedService {
    List<Affected> getAllAffected();

    Optional<Affected> getAffectedById(int id);

    Affected saveAffected(Affected affected);

    void deleteAffected(int id);

    Page<Affected> getAffectedPaginated(int page, int size);
}
