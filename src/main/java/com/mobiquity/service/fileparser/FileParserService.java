package com.mobiquity.service.fileparser;

import com.mobiquity.domain.Package;

import java.util.List;

@FunctionalInterface
public interface FileParserService {
    List<Package> parseFileData(List<String> data);
}
