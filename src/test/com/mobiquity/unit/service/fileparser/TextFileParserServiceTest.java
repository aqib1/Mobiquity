package com.mobiquity.unit.service.fileparser;

import com.mobiquity.service.fileparser.FileParserService;
import com.mobiquity.service.fileparser.Impl.TextFileParserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TextFileParserServiceTest {
    @Mock
    private FileParserService service;

    @Test
    public void testParseFileData() {
        service.parseFileData(null);

        verify(service, times(1))
                .parseFileData(null);
    }
}
