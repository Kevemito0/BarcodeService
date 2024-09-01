package com.product.barcodeservice.repo;

import com.product.barcodeservice.model.Barcode;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BarcodeRepo extends JpaRepository<Barcode, Long> {

}
