package com.mobiquity.helper;

import com.mobiquity.domain.Package;
import com.mobiquity.domain.Product;

import java.util.Arrays;
import java.util.List;

public final class DataHelper {

    public static String expectedInputFileResult() {
        return "4\n-\n2,7\n8,9";
    }

    public static List<String> textReaderFileReaderInput() {
        return Arrays.asList(
                "81 : (1,53.38,€45) (2,88.62,€98) (3,78.48,€3) (4,72.30,€76) (5,30.18,€9) (6,46.34,€48)",
                "8 : (1,15.3,€34)",
                "75 : (1,85.31,€29) (2,14.55,€74) (3,3.98,€16) (4,26.24,€55) (5,63.69,€52) (6,76.25,€75) (7,60.02,€74) (8,93.18,€35) (9,89.95,€78)",
                "56 : (1,90.72,€13) (2,33.80,€40) (3,43.15,€10) (4,37.97,€16) (5,46.81,€36) (6,48.77,€79) (7,81.80,€45) (8,19.36,€79) (9,6.76,€64)");
    }

    public static List<String> rawPackages() {
        return Arrays.asList("81 : (1,53.38,€45)");
    }

    public static List<Package> packages() {
        return Arrays.asList(new Package(81,
                Arrays.asList(new Product(1, 53.38, 45))));
    }

    public static List<Package> packagesForWightCal() {
        return Arrays.asList(new Package(81,
                Arrays.asList(
                        new Product(1, 53.38, 45),
                        new Product(2, 88.62, 98),
                        new Product(3, 78.48, 3),
                        new Product(4, 72.30, 76),
                        new Product(5, 30.18, 9),
                        new Product(6,46.34,48))));
    }

    public static List<String> rawPackageWithInvalidWeightDelimiter() {
        return Arrays.asList("81  (1,53.38,€45)");
    }

    public static List<String> rawPackageWithInvalidIndex() {
        return Arrays.asList("81 : (a,53.38,€45)");
    }

    public static List<String> rawPackageWithInvalidWeight() {
        return Arrays.asList("81 : (1,ww,€45)");
    }

    public static List<String> rawPackageWithInvalidCost() {
        return Arrays.asList("81 : (1,53.34,abc)");
    }

    public static List<String> rawPackageWithInvalidProductAttr() {
        return Arrays.asList("81 : (1,53.34,€45,121)");
    }

    public static List<String> rawPackageWithExceedWeightLimit() {
        return Arrays.asList("81 : (1,101,€45)");
    }

    public static List<String> rawPackageWithMoreThan15Products() {
        return Arrays.asList("81 : (1,53.38,€45) (2,88.62,€98) (3,78.48,€3) (4,72.30,€76) (5,30.18,€9) (6,46.34,€48) (6,46.34,€48) (6,46.34,€48) (6,46.34,€48) (6,46.34,€48) (6,46.34,€48) (6,46.34,€48) (6,46.34,€48) (6,46.34,€48) (6,46.34,€48) (6,46.34,€48) (6,46.34,€48)");
    }

    private DataHelper() {

    }
}
