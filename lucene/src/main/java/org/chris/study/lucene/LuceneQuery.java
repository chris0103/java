package org.chris.study.lucene;

import java.io.File;
import java.io.IOException;

import org.apache.lucene.analysis.en.EnglishAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.BooleanClause.Occur;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.MatchAllDocsQuery;
import org.apache.lucene.search.NumericRangeQuery;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

public class LuceneQuery {

	public void matchAllDocsQuery() throws IOException {
		
		// specify a directory where the indices are stored
		Directory directory = FSDirectory.open(new File("indices"));
		
		// create an index reader
		IndexReader indexReader = DirectoryReader.open(directory);
		
		// create an index searcher
		IndexSearcher indexSearcher = new IndexSearcher(indexReader);
		
		// create MatchAllDocsQuery query object which will search all documents
		Query query = new MatchAllDocsQuery();
		
		// execute the search
		System.out.println("Query match all doc as: " + query);
		TopDocs topDocs = indexSearcher.search(query, 10);
		
		// print total hits
		System.out.println("Total count: " + topDocs.totalHits);
		
		for (ScoreDoc scoreDoc : topDocs.scoreDocs) {
			// lookup document via doc ID, which is scoreDoc.doc
            Document document = indexSearcher.doc(scoreDoc.doc);
            System.out.println(document.get("fileName"));
            // System.out.println(document.get("fileContent"));
            System.out.println(document.get("fileSize"));
            System.out.println(document.get("filePath"));
            System.out.println("----------------------------------");
        }
		
		// close the index searcher
		indexReader.close();
	}
	
    public void searchDocsQuery() throws IOException {
    	
		// specify a directory where the indices are stored
		Directory directory = FSDirectory.open(new File("indices"));
		
		// create an index reader
		IndexReader indexReader = DirectoryReader.open(directory);
		
		// create an index searcher
		IndexSearcher indexSearcher = new IndexSearcher(indexReader);
		
		// create TermQuery query object which will search specified key words in specified field
        Query query = new TermQuery(new Term("fileContent", "strange"));
        
		// execute the search
        System.out.println("Query match all doc as: " + query);
        TopDocs topDocs = indexSearcher.search(query, 10);
        
        // print total hits
        System.out.println("Total count: " + topDocs.totalHits);
        
        for (ScoreDoc scoreDoc : topDocs.scoreDocs) {
			// lookup document via doc ID, which is scoreDoc.doc
            Document document = indexSearcher.doc(scoreDoc.doc);
            System.out.println(document.get("fileName"));
            // System.out.println(document.get("fileContent"));
            System.out.println(document.get("fileSize"));
            System.out.println(document.get("filePath"));
            System.out.println("----------------------------------");
        }
        
        // close the index searcher
        indexReader.close();
    }
    
    public void testNumericRangeQuery() throws IOException {
    	
		// specify a directory where the indices are stored
		Directory directory = FSDirectory.open(new File("indices"));
		
		// create an index reader
		IndexReader indexReader = DirectoryReader.open(directory);
		
		// create an index searcher
		IndexSearcher indexSearcher = new IndexSearcher(indexReader);
        
		// create NumericRangeQuery query object
        Query query = NumericRangeQuery.newLongRange("fileSize", 45000L, 55000L, true, true);

        // execute the search
        System.out.println("Query match all doc as: " + query);
        TopDocs topDocs = indexSearcher.search(query, 10);
        
        // print total hits
        System.out.println("Total count: " + topDocs.totalHits);
        
        for (ScoreDoc scoreDoc : topDocs.scoreDocs) {
        	// lookup document via doc ID, which is scoreDoc.doc
            Document document = indexSearcher.doc(scoreDoc.doc);
            //文件名称
            System.out.println(document.get("fileName"));
            //文件内容
            // System.out.println(document.get("fileContent"));
            //文件大小
            System.out.println(document.get("fileSize"));
            //文件路径
            System.out.println(document.get("filePath"));
            System.out.println("----------------------------------");
        }

        // close the index searcher
        indexReader.close();
    }
    
    public void testBooleanQuery() throws IOException {
    	
    	// specify a directory where the indices are stored
        Directory directory = FSDirectory.open(new File("indices"));
        
        // create an index reader
        IndexReader indexReader = DirectoryReader.open(directory);
        
        // create an index searcher
        IndexSearcher indexSearcher = new IndexSearcher(indexReader);
        
        // create BooleanQuery query object
        BooleanQuery query = new BooleanQuery();
        
        // create sub queries
        Query query1 = new TermQuery(new Term("fileContent", "network"));
        Query query2 = new TermQuery(new Term("fileContent", "strange"));
        
        // compose sub queries
        query.add(query1, Occur.MUST);
        query.add(query2, Occur.MUST);
        
        // execute the search
        System.out.println("Query match all doc as: " + query);
        TopDocs topDocs = indexSearcher.search(query, 10);
        
        // print total hits
        System.out.println("Total count: " + topDocs.totalHits);

        for (ScoreDoc scoreDoc : topDocs.scoreDocs) {
        	
        	// lookup document via doc ID, which is scoreDoc.doc
            Document document = indexSearcher.doc(scoreDoc.doc);
            //文件名称
            System.out.println(document.get("fileName"));
            //文件内容
            // System.out.println(document.get("fileContent"));
            //文件大小
            System.out.println(document.get("fileSize"));
            //文件路径
            System.out.println(document.get("filePath"));
            System.out.println("----------------------------------");
        }
        
        // close the index searcher
        indexReader.close();
    }
    
    public void testQueryParser() throws Exception {
    	
    	// specify a directory where the indices are stored
        Directory directory = FSDirectory.open(new File("indices"));
        
        // create an index reader
        IndexReader indexReader = DirectoryReader.open(directory);
        
        // create an index searcher
        IndexSearcher indexSearcher = new IndexSearcher(indexReader);
        
        // create QueryParser query object using English analyzer
        QueryParser queryParser = new QueryParser("fileName", new EnglishAnalyzer());
        Query query = queryParser.parse("fileContent:strange");

        // execute the search
        System.out.println("Query match all doc as: " + query);
        TopDocs topDocs = indexSearcher.search(query, 10);
        
        // print total hits
        System.out.println("Total count: " + topDocs.totalHits);
        for (ScoreDoc scoreDoc : topDocs.scoreDocs) {
            Document document = indexSearcher.doc(scoreDoc.doc);
            System.out.println(document.get("fileName"));
            // System.out.println(document.get("fileContent"));
            System.out.println(document.get("fileSize"));
            System.out.println(document.get("filePath"));
            System.out.println("----------------------------------");
        }
        
        // close the index searcher
        indexReader.close();
    }    
	
	public static void main(String[] args) throws Exception {
		LuceneQuery query = new LuceneQuery();
		// query.matchAllDocsQuery();
		// query.searchDocsQuery();
		// query.testNumericRangeQuery();
		// query.testBooleanQuery();
		query.testQueryParser();
	}
}
