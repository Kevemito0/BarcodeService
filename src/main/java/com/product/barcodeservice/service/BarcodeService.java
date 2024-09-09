package com.product.barcodeservice.service;

import com.product.barcodeservice.model.Barcode;
import com.product.barcodeservice.repo.BarcodeRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;
@Service
@RequiredArgsConstructor
public class BarcodeService {

    private final BarcodeRepo barcodeRepo;
    /* //if we don't use requiredArgsConstructor we can DI like this
    @Autowired
            public BarcodeService(BarcodeRepo barcodeRepo) {
        this.barcodeRepo = barcodeRepo;
    }
    */

    private Random random = new Random();
    UUID uuid = UUID.randomUUID();

    public Barcode createBarcode(Barcode category) {
        return barcodeRepo.save(category);
    }
    private boolean isScaleBarcodeExists(String scaleCode) {
        return barcodeRepo.findAll().stream().anyMatch(barcode1 -> barcode1.getScaleCode().equals(scaleCode));
    }
    private boolean isCashregBarcodeCodeExists(String cashregCode) {
        return barcodeRepo.findAll().stream().anyMatch(barcode1 -> barcode1.getScaleCode().equals(cashregCode));
    }
    private boolean isProductBarcodeExists(String productBarcode) {
        return barcodeRepo.findAll().stream().anyMatch(barcode1 -> barcode1.getScaleCode().equals(productBarcode));
    }
    public void deleteBarcode(Long id) {
        barcodeRepo.deleteById(id);
    }

    public Optional<Barcode> getBarcodesByBarcode(String barcode) {
        System.out.println(barcode.length() + " barcode: " + barcode);
        if (barcode.length() == 9) {
            return barcodeRepo.findAll().stream().filter(barcodes -> barcodes.getProductCode().equals(barcode)).findFirst();
        } else if (barcode.length() == 8) {
            return barcodeRepo.findAll().stream().filter(barcodes -> barcodes.getScaleCode().equals(barcode)).findFirst();
        } else if (barcode.length() == 4) {
            return barcodeRepo.findAll().stream().filter(barcodes -> barcodes.getCashregCode().equals(barcode)).findFirst();
        }
        else return Optional.empty();
    }


    public Optional<Barcode> getBarcodeById(Long id) {
        return barcodeRepo.findById(id);
    }

    public List<Barcode> getAllBarcodes() {
        return barcodeRepo.findAll();
    }

    public Barcode createBarcodeWithProduct(String productCode) {
        Barcode barcode = new Barcode();
        barcode.setProductCode(generateRandomBarcode(9,0,""));
        barcode.setCashregCode(generateRandomBarcode(4,0,""));
        barcode.setScaleCode(generateRandomBarcode(8,5,productCode));
        createBarcode(barcode);
        return barcode;
    }
    public String generateRandomBarcode(int length,int startIndex,String productCode)
    {
        String barcodeLetter = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        StringBuilder barcode;
        do {
            barcode = new StringBuilder();
            if (startIndex == 5) {
                barcode.append(productCode);
            }
            for (int i = startIndex; i < length; i++) {
                int index = random.nextInt(barcodeLetter.length());
                barcode.append(barcodeLetter.charAt(index));
            }
        }   while (isScaleBarcodeExists(barcode.toString()) || isCashregBarcodeCodeExists(barcode.toString()) || isProductBarcodeExists(barcode.toString()));
        return barcode.toString();
    }
}
