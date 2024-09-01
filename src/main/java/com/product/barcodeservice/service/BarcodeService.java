package com.product.barcodeservice.service;

import com.product.barcodeservice.model.Barcode;
import com.product.barcodeservice.repo.BarcodeRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;
@Service
@RequiredArgsConstructor
public class BarcodeService {

    private final BarcodeRepo barcodeRepo;

    private Random random = new Random();
    UUID uuid = UUID.randomUUID();

    public Barcode createBarcode(Barcode category) {
        Barcode savedCategory = barcodeRepo.save(category);
        return savedCategory;
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

    public Barcode createBarcodeWithProduct(Long id){
        Barcode barcode = new Barcode();
        barcode.setProductCode(generateRandomBarcode(9,0));
        barcode.setCashregCode(generateRandomBarcode(4,0));
        barcode.setScaleCode(generateRandomBarcode(8,0));
        createBarcode(barcode);
        return barcode;
    }
    private String generateRandomBarcode(int length,int startIndex)
    {
        String barcodeLetter = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        StringBuilder barcode = new StringBuilder ();
        for(int i = startIndex; i < length; i++)
        {
            int index = random.nextInt(barcodeLetter.length());
            barcode.append(barcodeLetter.charAt(index));
        }
        return barcode.toString();
    }
}
