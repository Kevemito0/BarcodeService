package com.product.barcodeservice.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "Barcodes")
public class Barcode {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "cashRegCode", nullable = false,length = 4,unique = true)
    private String cashregCode;

    @Column(name = "scaleCode", nullable = false,length = 8,unique = true)
    private String scaleCode;

    @Column(name = "productCode", nullable = false,length = 9,unique = true)
    private String productCode;
}
