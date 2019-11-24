package com.desafio.senior.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.desafio.senior.model.Cidade;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectMapper;
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

  public List<Cidade> filtarCSV(String fileName, String fieldName, String value) {
    ObjectMapper mapper = new ObjectMapper();

    Map<String, String> map = new HashMap<String, String>();
    String csvFile = fileName;
    String line = "";
    String cvsSplitBy = ",";
    boolean header = true;
    List<String> headerFields = new ArrayList<String>();
    List<Cidade> cidadeList = new ArrayList<Cidade>();

    try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
      while ((line = br.readLine()) != null) {
        if (header) {
          headerFields = Arrays.asList(line.split(cvsSplitBy));

          header = false;
        } else {
          String[] cidade = line.split(cvsSplitBy);
          int indexOf = headerFields.indexOf(fieldName);

          if (cidade[indexOf].toUpperCase().contains(value.toUpperCase())) {
            for (int i = 0; i < cidade.length; i++) {
              map.put(headerFields.get(i), cidade[i]);
            }

            Cidade cidadeValues = mapper.convertValue(map, Cidade.class);
            cidadeList.add(cidadeValues);
          }
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
    }

    return cidadeList;
  }
}