package org.chirs.study.recipes.xml;

/**
 * The XML processor interface defining common XML process operations.
 */
public interface XMLProcessor<T> {
	
	/**
	 * Shows way to initialize and setup the XML processor itself.
	 * @return
	 */
	public XMLProcessor<T> setup();
	
	/**
	 * Walks through a given XML document.
	 * @param document
	 */
	public void walk(T document);
	
	/**
	 * Creates an XML document from scratch.
	 * @return
	 */
	public T create();

	/**
	 * Modifies XML document part using given value.
	 * @param document
	 * @param path
	 * @param value
	 * @return
	 */
	public Object modify(T document, String path, Object value);
}
