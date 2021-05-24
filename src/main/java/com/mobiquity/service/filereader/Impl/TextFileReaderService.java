package com.mobiquity.service.filereader.Impl;

import com.mobiquity.exception.APIException;
import com.mobiquity.service.filereader.FileReaderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class TextFileReaderService implements FileReaderService {

    private static final Logger logger = LoggerFactory.getLogger(TextFileReaderService.class);

    @Override
    public List<String> readFile(String filePath) throws IOException, APIException {
        if (Objects.isNull(filePath) || filePath.isBlank()) {
            logger.error("File path is empty/null");
            throw new APIException("File path is empty/null..");
        }
        List<String> results = new ArrayList<>();
        File file = new File(filePath);
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            String line;
            while((line = bufferedReader.readLine()) != null) {
                results.add(line);
            }
        }

        logger.debug("File data as list: {}", results);
        return results;
    }
}
