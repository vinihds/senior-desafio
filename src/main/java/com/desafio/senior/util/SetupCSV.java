package com.desafio.senior.util;

import java.io.File;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

import org.springframework.stereotype.Component;

@Component
public class SetupCSV {

  public <T> List<T> loadObjectList(Class<T> type, String fileName) {
    try {
      CsvSchema bootstrapSchema = CsvSchema.emptySchema().withHeader();
      CsvMapper mapper = new CsvMapper();

      File file = Paths.get(fileName).toFile();// ClassPathResource(fileName).getFile();
      MappingIterator<T> readValues = mapper.reader(type).with(bootstrapSchema).readValues(file);

      return readValues.readAll();
    } catch (Exception e) {
      e.printStackTrace();

      return Collections.emptyList();
    }
  }
}