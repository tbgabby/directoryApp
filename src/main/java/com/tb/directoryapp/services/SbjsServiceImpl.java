package com.tb.directoryapp.services;

import com.tb.directoryapp.domain.Sbjs;
import com.tb.directoryapp.repositories.SbjsRepository;
import org.springframework.stereotype.Service;

@Service
public class SbjsServiceImpl implements SbjsService {
    private SbjsRepository sbjsRepository;

    public SbjsServiceImpl(SbjsRepository sbjsRepository) {
        this.sbjsRepository = sbjsRepository;
    }

    // Save Sbjs Object...
    @Override
    public Sbjs saveOrUpdate(Sbjs sbjs) {
        return sbjsRepository.save(sbjs);
    }

}
