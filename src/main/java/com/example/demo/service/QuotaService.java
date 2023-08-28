package com.example.demo.service;

import com.example.demo.model.Quotas;
import com.example.demo.repository.QuotaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class QuotaService {

    @Autowired
    private QuotaRepository quotaRepository;

    public Quotas createQuota(Quotas quotas) {
        quotas.setQuotaId(UUID.randomUUID().toString());
        return quotaRepository.createQuotas(quotas);
    }
}
