package ling;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.opencsv.CSVWriter;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

// https://www.callicoder.com/java-read-write-csv-file-opencsv/

public class OpenCSVReadAndParseToBean {
	private static final String SAMPLE_CSV_FILE_PATH = "C:/radws/workspace/Jiraxmlcsv/test/src/main/java/ling/users-with-header.csv";

	public static void main(String[] args) throws IOException {

		List<CSVUser> userList = new ArrayList<CSVUser>();

		// read
		try (Reader reader = Files.newBufferedReader(Paths.get(SAMPLE_CSV_FILE_PATH));) {
			CsvToBean<CSVUser> csvToBean = new CsvToBeanBuilder(reader).withType(CSVUser.class)
					.withIgnoreLeadingWhiteSpace(true).build();

			Iterator<CSVUser> csvUserIterator = csvToBean.iterator();

			while (csvUserIterator.hasNext()) {
				CSVUser csvUser = csvUserIterator.next();
				csvUser.setDescription("hello " + csvUser.getName());
				userList.add(csvUser);
			}

			for (CSVUser user : userList) {
				System.out.println(user.toString());
				System.out.println("==========================");
			}

		}
		// write
		try (Writer writer = Files.newBufferedWriter(
				Paths.get("C:/temp/users-output.csv"));) {
			StatefulBeanToCsv<CSVUser> beanToCsv = new StatefulBeanToCsvBuilder(writer)
					.withQuotechar(CSVWriter.NO_QUOTE_CHARACTER).build();

			beanToCsv.write(userList);
		} catch (CsvDataTypeMismatchException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (CsvRequiredFieldEmptyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}