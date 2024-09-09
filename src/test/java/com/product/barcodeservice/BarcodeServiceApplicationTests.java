package com.product.barcodeservice;
import com.product.barcodeservice.model.Barcode;
import com.product.barcodeservice.repo.BarcodeRepo;
import com.product.barcodeservice.service.BarcodeService;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@SpringBootTest
class BarcodeServiceApplicationTests {

    @InjectMocks
    private BarcodeService barcodeService;

    @Mock
    private BarcodeRepo barcodeRepo;

    private Barcode sampleBarcode;

    @BeforeEach
    void setUp() {

    }

    @Test
    void generateBarcodeTest() {
        when(barcodeService.createBarcodeWithProduct("ME231")).thenReturn(sampleBarcode);
        sampleBarcode = barcodeService.createBarcodeWithProduct("ME231");

        assertNotNull(sampleBarcode);
        System.out.println(sampleBarcode.getScaleCode());
    }


}
