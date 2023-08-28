package com.example.demo;

import com.example.demo.model.Voucher;
import com.example.demo.service.VoucherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/vouchers")
public class VoucherController {

    @Autowired
    VoucherService voucherService;


    @PostMapping
    public Voucher createQuota(@RequestBody Voucher voucher) {

        return voucherService.createVoucher(voucher);
    }
}
