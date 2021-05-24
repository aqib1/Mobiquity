package com.mobiquity.packer;

import com.mobiquity.domain.Package;
import com.mobiquity.exception.APIException;
import com.mobiquity.service.fileparser.FileParserService;
import com.mobiquity.service.filereader.FileReaderService;
import com.mobiquity.service.weightcalculator.WeightCalculationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
public class Packer {

  private static final Logger logger = LoggerFactory.getLogger(Packer.class);

  @Autowired
  private WeightCalculationService weightCalculationService;

  @Autowired
  private FileParserService fileParserService;

  @Autowired
  private FileReaderService fileReaderService;

  public String pack(String filePath) throws APIException, IOException {
    logger.debug("File path received: {}", filePath);
    List<Package> packages = fileParserService.parseFileData(fileReaderService.readFile(filePath));
    logger.debug("Parsed package: {}", packages);
    return weightCalculationService.calculateWeight(packages);
  }
}
