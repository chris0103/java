package org.chris.tools.randomania.bookscanner;

public class Book implements Comparable<Book> {

	private String title;
	private int start = 0;
	private int pages;
	private boolean unread = true;
	
	public Book(String title, int start, int pages, boolean unread) {
		this.title = title;
		this.start = start;
		this.pages = pages;
		this.setUnread(unread);
	}
	
	public Book(String title, int start, int pages) {
		this(title, start, pages, true);
	}
	
	public Book(String title, int pages, boolean unread) {
		this(title, 0, pages, unread);
	}
	
	public Book(String title, int pages) {
		this(title, 0, pages);
	}
	
	public int getUnreadPageCount() {
		if (!unread) {
			return 0;
		} else {
			return pages - start;
		}
	}

	@Override
	public int compareTo(Book o) {
		return new Integer(this.pages - this.start).compareTo(new Integer(o.getPages() - o.start));
	}


	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getPages() {
		return pages;
	}

	public void setPages(int pages) {
		this.pages = pages;
	}

	public boolean isUnread() {
		return unread;
	}

	public void setUnread(boolean unread) {
		this.unread = unread;
	}
}
