package com.product.barcodeservice.controller;

import com.product.barcodeservice.model.Barcode;
import com.product.barcodeservice.service.BarcodeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping("/api/Barcodes")
@RequiredArgsConstructor
public class BarcodeController {

    private final BarcodeService barcodeService;

    @GetMapping("/{id}")
    public Optional<Barcode> getCategoryById(@PathVariable("id") Long id) {
        return barcodeService.getBarcodeById(id);
    }
    @GetMapping("/barcode/{barcode}")
    public Optional<Barcode> getBarcodesByBarcode(@PathVariable String barcode) {
        return barcodeService.getBarcodesByBarcode(barcode);
    }

    @GetMapping
    public List<Barcode> getAllCategories() {
        return barcodeService.getAllBarcodes();
    }

    @PostMapping("/create/{productCode}")
    public Barcode createCategoryWithProduct(@PathVariable String productCode) {
        return barcodeService.createBarcodeWithProduct(productCode);
    }

    @PostMapping
    public Barcode createCategory(@RequestBody Barcode category) {
        return barcodeService.createBarcode(category);
    }

    @DeleteMapping("/deletebarcode/{barcode}")
    public void deleteCategoryBarcode(@PathVariable String barcode) {
        Long deletedcategory = getBarcodesByBarcode(barcode).get().getId();
        barcodeService.deleteBarcode(deletedcategory);
    }

    @DeleteMapping("/delete/{id}")
    public void  deleteCategory(@PathVariable("id") Long id) {
        barcodeService.deleteBarcode(id);
    }
}
