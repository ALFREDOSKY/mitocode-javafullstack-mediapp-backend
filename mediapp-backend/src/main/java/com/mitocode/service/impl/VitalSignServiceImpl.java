package com.mitocode.service.impl;

import com.mitocode.model.VitalSign;
import com.mitocode.repo.IGenericRepo;
import com.mitocode.repo.IVitalSignRepo;
import com.mitocode.service.IVitalSignService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VitalSignServiceImpl extends CRUDImpl<VitalSign,Integer> implements IVitalSignService {

    private final IVitalSignRepo vitalSignRepo;

    @Override
    public Page<VitalSign> listPage(Pageable pageable) {
        return vitalSignRepo.findAll(pageable);
    }

    @Override
    protected IGenericRepo<VitalSign, Integer> getRepo() {
        return vitalSignRepo;
    }
}
