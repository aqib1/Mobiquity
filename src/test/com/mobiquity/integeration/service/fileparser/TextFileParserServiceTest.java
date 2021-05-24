package com.mobiquity.integeration.service.fileparser;

import com.mobiquity.domain.Package;
import com.mobiquity.exception.FileParserException;
import com.mobiquity.service.fileparser.Impl.TextFileParserService;
import com.mobiquity.helper.DataHelper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static com.mobiquity.helper.Consts.*;

@SpringBootTest
public class TextFileParserServiceTest {
    @Autowired
    private TextFileParserService textFileParserService;

    @Test
    public void parseFileData_emptyDataList_shouldReturnEmptyList() {
        List<Package> packages = textFileParserService.parseFileData(EMPTY_LIST);
        Assertions.assertTrue(packages.isEmpty());
        Assertions.assertEquals(EMPTY_PACKAGE, packages);
    }

    @Test
    public void parseFileData_validDataList_shouldReturnPackageList() {
        List<Package> packages = textFileParserService.parseFileData(DataHelper.rawPackages());
        Assertions.assertEquals(DataHelper.packages(), packages);
    }

    @Test
    public void parseFileData_missingWeightDelimiter_shouldThrowFileParserException() {
        Assertions.assertThrows(FileParserException.class, () ->
                textFileParserService.parseFileData(DataHelper.rawPackageWithInvalidWeightDelimiter()));
    }

    @Test
    public void parseFileData_invalidIndex_shouldThrowFileParserException() {
        Assertions.assertThrows(FileParserException.class, () ->
                textFileParserService.parseFileData(DataHelper.rawPackageWithInvalidIndex()));
    }

    @Test
    public void parseFileData_invalidWeight_shouldThrowFileParserException() {
        Assertions.assertThrows(FileParserException.class, () ->
                textFileParserService.parseFileData(DataHelper.rawPackageWithInvalidWeight()));
    }

    @Test
    public void parseFileData_invalidCost_shouldThrowFileParserException() {
        Assertions.assertThrows(FileParserException.class, () ->
                textFileParserService.parseFileData(DataHelper.rawPackageWithInvalidCost()));
    }

    @Test
    public void parseFileData_invalidProductAttr_shouldThrowFileParserException() {
        Assertions.assertThrows(FileParserException.class, () ->
                textFileParserService.parseFileData(DataHelper.rawPackageWithInvalidProductAttr()));
    }

    @Test
    public void parseFileData_weightWithExceedLimit_shouldReturnEmptyList() {
        List<Package> packages = textFileParserService.parseFileData(DataHelper.rawPackageWithExceedWeightLimit());
        Assertions.assertTrue(packages.get(0).getProducts().isEmpty());
        Assertions.assertEquals(EMPTY_PRODUCTS, packages.get(0).getProducts());
    }

    @Test
    public void parseFileData_withMoreThan15Products_shouldReturnEmptyList() {
        List<Package> packages = textFileParserService.parseFileData(DataHelper.rawPackageWithMoreThan15Products());
        Assertions.assertFalse(packages.get(0).getProducts().isEmpty());
        Assertions.assertEquals(MAX_PRODUCTS_FOR_PACKAGE, packages.get(0).getProducts().size());
    }
}
