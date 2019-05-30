package com.baeldung.opencsv;

import com.baeldung.opencsv.beans.CsvBean;
import com.baeldung.opencsv.beans.NamedColumnBean;
import com.baeldung.opencsv.beans.SimplePositionBean;
import com.baeldung.opencsv.examples.sync.BeanExamples;
import com.baeldung.opencsv.examples.sync.CsvReaderExamples;
import com.baeldung.opencsv.examples.sync.CsvWriterExamples;
import com.baeldung.opencsv.helpers.Helpers;
import com.baeldung.opencsv.pojos.CsvTransfer;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.MappingStrategy;

import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Ling {

        /*
         *  Bean Examples.
         */

    public static String simpleSyncPositionBeanExample() {
        Path path = null;
        try {
            path = Helpers.twoColumnCsvPath();
        } catch (Exception ex) {
            Helpers.err(ex);
        }
        return BeanExamples.beanBuilderExample(path, SimplePositionBean.class).toString();
    }

    public static List<CsvBean> namedSyncColumnBeanExample() {
//        Path path = null;
//        try {
//            path = Helpers.namedColumnCsvPath();
//        } catch (Exception ex) {
//            Helpers.err(ex);
//        }
    	ColumnPositionMappingStrategy ms = new ColumnPositionMappingStrategy();
        return beanBuilderExample("c:/temp/test.csv", NamedColumnBean.class, ms);
    }

    
    
    public static List<CsvBean> beanBuilderExample(String pathString, Class clazz, MappingStrategy ms) {
        CsvTransfer csvTransfer = new CsvTransfer();
        try {
            ms.setType(clazz);
            
            Path path = Paths.get(pathString);

            Reader reader = Files.newBufferedReader(path);
            CsvToBean cb = new CsvToBeanBuilder(reader).withType(clazz)
                .withMappingStrategy(ms)
                .build();

            csvTransfer.setCsvList(cb.parse());
            reader.close();

        } catch (Exception ex) {
            Helpers.err(ex);
        }
        return csvTransfer.getCsvList();
    }
    public static String writeSyncCsvFromBeanExample() {
        Path path = null;
        try {
            path = Helpers.fileOutBeanPath();
        } catch (Exception ex) {
            Helpers.err(ex);
        }
        return BeanExamples.writeCsvFromBean(path);
    }

        /*
         *  CSV Reader Examples.
         */

    public static String oneByOneSyncExample() {
        Reader reader = null;
        try {
            reader = Files.newBufferedReader(Helpers.twoColumnCsvPath());
        } catch (Exception ex) {
            Helpers.err(ex);
        }
        return CsvReaderExamples.oneByOne(reader).toString();
    }

    public static String readAllSyncExample() {
        Reader reader = null;
        try {
            reader = Files.newBufferedReader(Helpers.twoColumnCsvPath());
        } catch (Exception ex) {
            Helpers.err(ex);
        }
        return CsvReaderExamples.readAll(reader).toString();
    }

         /*
         *  CSV Writer Examples.
         */


    public static String csvWriterSyncOneByOne() {
        Path path = null;
        try {
            path = Helpers.fileOutOnePath();
        } catch (Exception ex) {
            Helpers.err(ex);
        }
        return CsvWriterExamples.csvWriterOneByOne(Helpers.fourColumnCsvString(), path);
    }

    public static String csvWriterSyncAll() {
        Path path = null;
        try {
            path = Helpers.fileOutAllPath();
        } catch (Exception ex) {
            Helpers.err(ex);
        }
        return CsvWriterExamples.csvWriterAll(Helpers.fourColumnCsvString(), path);
    }

    public static void main(String[] args) {
//        simpleSyncPositionBeanExample();
    	List<CsvBean> myBeans = namedSyncColumnBeanExample();
    	 for (CsvBean myBean : myBeans) { 		      
    		 System.out.print(myBean)		;
        }
    	
        writeSyncCsvFromBeanExample();
//        oneByOneSyncExample();
//        readAllSyncExample();
//        csvWriterSyncOneByOne();
//        csvWriterSyncAll();
    }
}
