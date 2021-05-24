package com.mobiquity.integeration.packer;

import com.mobiquity.exception.APIException;
import com.mobiquity.packer.Packer;
import com.mobiquity.helper.DataHelper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.FileNotFoundException;
import java.io.IOException;

import static com.mobiquity.helper.Consts.*;

@SpringBootTest
public class PackerTest {

    @Autowired
    private Packer packer;

    @Test
    public void validExampleFile_shouldReturnExpectedOutput() throws APIException, IOException {
        String actual = packer.pack(EXAMPLE_FILE_PATH);
        Assertions.assertEquals(DataHelper.expectedInputFileResult(), actual);
    }

    @Test
    public void invalidFilePath_shouldReturnFileNotFoundException() {
        Assertions.assertThrows(FileNotFoundException.class, () ->
                packer.pack(INVALID_FILE_PATH));
    }

    @Test
    public void invalidFile_emptyPath_shouldReturnAPIException() {
        Assertions.assertThrows(APIException.class, () ->
                packer.pack(EMPTY_FILE_PATH));
    }

    @Test
    public void invalidFile_nullPath_shouldReturnAPIException() {
        Assertions.assertThrows(APIException.class, () ->
                packer.pack(null));
    }

    @Test
    public void validExampleEmptyFile_shouldReturnExpectedOutput() throws APIException, IOException {
        String actual = packer.pack(EMPTY_INPUT_FILE_PATH);
        Assertions.assertEquals(EMPTY, actual);
    }
}
