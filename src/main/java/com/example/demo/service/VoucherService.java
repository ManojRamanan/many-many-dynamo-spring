package com.example.demo.service;

import com.example.demo.model.Feature;
import com.example.demo.model.Voucher;
import com.example.demo.repository.QuotaRepository;
import com.example.demo.repository.VoucherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;

import java.util.Map;
import java.util.UUID;

@Service
public class VoucherService {

    @Autowired
    VoucherRepository voucherRepository;

    @Autowired
    QuotaRepository quotaRepository;

    public Voucher createVoucher(Voucher voucher) {
        voucher.setId(UUID.randomUUID().toString());
        voucherRepository.createVoucher(voucher);
        Map<String, AttributeValue> quota = quotaRepository.getQuota(voucher.getQuotaId());
        voucherRepository.voucherToFeatureMapper(voucher, quota);
        return voucher;
    }
}
