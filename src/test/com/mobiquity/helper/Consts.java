package com.mobiquity.helper;
import com.mobiquity.domain.Package;
import com.mobiquity.domain.Product;

import java.util.List;
import java.util.ArrayList;

public final class Consts {
    public static final String EXAMPLE_FILE_PATH = "src/test/resources/example_input";
    public static final String INVALID_FILE_PATH = "src/test/resources/wrong_input";
    public static final String EMPTY_INPUT_FILE_PATH = "src/test/resources/empty_example_input";
    public static final String EMPTY_FILE_PATH = "";
    public static final String EMPTY = "";
    public static final List<String> EMPTY_LIST = new ArrayList<>();
    public static final List<Package> EMPTY_PACKAGE = new ArrayList<>();
    public static final List<Product> EMPTY_PRODUCTS = new ArrayList<>();
    public static final int MAX_PRODUCTS_FOR_PACKAGE = 15;
    private Consts() {

    }
}
