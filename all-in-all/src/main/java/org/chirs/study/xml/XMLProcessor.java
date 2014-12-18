package org.chirs.study.xml;

/**
 * The XML processor interface defining common XML document process operations.
 * @param <T> The interface or class representing the document object processed by a concrete processor.
 */
public interface XMLProcessor<T> {
	
	/**
	 * Initializes the XML processor itself.
	 */
	public void setup();
	
	/**
	 * Walks through a given XML document.
	 * @param document
	 */
	public void walkDocument(T document);
	
	/**
	 * Creates an XML document from scratch.
	 * @return
	 */
	public T createDocument();

	/**
	 * Modifies XML document part using given value.
	 * @param document
	 * @param path
	 * @param value
	 * @return
	 */
	public Object modifyDocument(T document, String path, Object value);
}
