package com.mobiquity.unit.service.filereader;

import com.mobiquity.exception.APIException;
import com.mobiquity.service.filereader.FileReaderService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class FileReaderServiceTest {

    @Mock
    private FileReaderService fileReaderService;

    @Test
    public void testReadFile() throws IOException, APIException {
        fileReaderService.readFile(null);
        verify(fileReaderService, times(1)).readFile(null);
    }
}
