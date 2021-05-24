package com.mobiquity.service.filereader;
import com.mobiquity.exception.APIException;

import java.io.IOException;
import java.util.List;

@FunctionalInterface
public interface FileReaderService {
    List<String> readFile(String filePath) throws IOException, APIException;
}
