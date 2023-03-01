package org.chris.study.lucene;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.document.LongField;
import org.apache.lucene.document.StoredField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

public class LuceneAnalyzer {

	public void createIndex() throws IOException {

		// specify a directory to store the indices
		Directory indexDir = FSDirectory.open(new File("indices"));

		// initiate a standard analyzer
		Analyzer analyzer = new StandardAnalyzer();

		// create an index write configuration object
		IndexWriterConfig config = new IndexWriterConfig(Version.LATEST, analyzer);

		// then an index writer...
		IndexWriter indexWriter = new IndexWriter(indexDir, config);

		// locate the file to be indexed
		File dir = new File("texts");
		File[] files = dir.listFiles();
		for (File file : files) {

			// create the Document object
			Document document = new Document();

			// create field: file name
			String fileName = file.getName();
			Field fileNameField = new TextField("fileName", fileName, Store.YES);

			// create field: file size
			long fileSize = FileUtils.sizeOf(file);
			Field fileSizeField = new LongField("fileSize", fileSize, Store.YES);

			// create field: file path; stored field is only stored without being analyzed
			// and indexed
			String filePath = file.getPath();
			Field filePathField = new StoredField("filePath", filePath);

			// create field: file content
			String fileContent = FileUtils.readFileToString(file, "UTF-8");
			Field fileContentField = new TextField("fileContent", fileContent, Store.YES);

			// add fields to document
			document.add(fileNameField);
			document.add(fileSizeField);
			document.add(filePathField);
			document.add(fileContentField);

			// add document to index writer, this step creates indices and store them along
			// with the document
			indexWriter.addDocument(document);
		}

		indexWriter.close();
	}

	public static void main(String[] args) throws IOException {
		new LuceneAnalyzer().createIndex();
	}
}
