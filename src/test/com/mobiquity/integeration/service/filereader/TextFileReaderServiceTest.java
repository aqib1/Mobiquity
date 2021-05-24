package com.mobiquity.integeration.service.filereader;

import com.mobiquity.exception.APIException;
import com.mobiquity.service.filereader.Impl.TextFileReaderService;
import com.mobiquity.helper.DataHelper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import static com.mobiquity.helper.Consts.*;

@SpringBootTest
public class TextFileReaderServiceTest {

    @Autowired
    private TextFileReaderService textFileReaderService;

    @Test
    public void validFileProvided_shouldReturnCorrectOutput() throws IOException, APIException {
        List<String> actual = textFileReaderService.readFile(EXAMPLE_FILE_PATH);
        Assertions.assertEquals(4, actual.size());
        Assertions.assertEquals(DataHelper.textReaderFileReaderInput(), actual);
    }

    @Test
    public void invalidFilePath_shouldReturnFileNotFoundException() {
        Assertions.assertThrows(FileNotFoundException.class, () ->
                textFileReaderService.readFile(INVALID_FILE_PATH));
    }

    @Test
    public void invalidFile_emptyPath_shouldReturnAPIException() {
        Assertions.assertThrows(APIException.class, () ->
                textFileReaderService.readFile(EMPTY_FILE_PATH));
    }

    @Test
    public void validExampleEmptyFile_shouldReturnExpectedOutput() throws APIException, IOException {
        List<String> actual = textFileReaderService.readFile(EMPTY_INPUT_FILE_PATH);
        Assertions.assertEquals(EMPTY_LIST, actual);
    }
}
