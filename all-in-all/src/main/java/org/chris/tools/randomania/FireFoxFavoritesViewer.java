package org.chris.tools.randomania;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class FireFoxFavoritesViewer {
	
	private static final String USER_PLACEHOLDER = "<USER>";
	private static final String PROFILE_PLACEHOLDER = "<PROFILE_NAME>";
	private static final String SQLITE_URL = "jdbc:sqlite:C:\\Users\\" + USER_PLACEHOLDER 
			+ "\\AppData\\Roaming\\Mozilla\\Firefox\\Profiles\\" + PROFILE_PLACEHOLDER + "\\places.sqlite";
	
	private static final String SQL = "SELECT parent_bm.title, child_bm.position, child_bm.title, child_bm.type, child_bm.fk, plc.url FROM moz_bookmarks parent_bm"
			+ " LEFT JOIN moz_bookmarks child_bm ON parent_bm.id = child_bm.parent"
			+ " LEFT JOIN moz_places plc ON child_bm.fk = plc.id"
			+ " WHERE (parent_bm.title is not null and parent_bm.title != '') AND ((child_bm.title IS NOT null AND child_bm.title != '')) AND child_bm.fk IS NOT null"
			+ " ORDER BY parent_bm.title, child_bm.position";
	
	private String user;
	private String profile;
	private static List<Site> subscriptions = new ArrayList<Site>();
	private static Map<String, Integer> subMetas = new HashMap<String, Integer>();
	
	private Map<String, List<Site>> siteMap = new TreeMap<String, List<Site>>();

	static {
		subMetas.put("Latest Headlines", 62);
		subMetas.put("Java World", 70);
		subMetas.put("财经网", 25);
		subMetas.put("程序员", 20);
		subMetas.put("外刊IT评论", 0);
		subMetas.put("伯乐在线", 20);
		subMetas.put("果壳网", 20);
		subMetas.put("华尔街日报", 25);
		subMetas.put("Slashdot", 15);
		subMetas.put("Coding Horror", 10);
		subMetas.put("Study Hacks", 10);
		subMetas.put("超越防火墙", 25);
		subMetas.put("FT中文网", 10);
		for (String sub : subMetas.keySet()) {
			int size = subMetas.get(sub);
			for (int i = 1; i <= size; i++) {
				subscriptions.add(new Site(sub, "#index" + i, "subscription", -1));
			}
		}
	}
	
	public FireFoxFavoritesViewer(String user, String profile) {
		this.user = user;
		this.profile = profile;
	}
	
	/**
	 * Load bookmarked sites.
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public void loadSites() throws ClassNotFoundException, SQLException {
		List<Site> sites;
		Class.forName("org.sqlite.JDBC");
		Connection conn = DriverManager.getConnection(SQLITE_URL.replace(USER_PLACEHOLDER, user).replace(PROFILE_PLACEHOLDER, profile));
		conn.setReadOnly(true);
		Statement statement = conn.createStatement();
		ResultSet rs = statement.executeQuery(SQL);
		while (rs.next()) {
			Site site = new Site();
			String parentTitle = rs.getString(1);
			site.setTitle(rs.getString(3));
			site.setUrl(rs.getString(6));
			site.setParent(parentTitle);
			site.setPlaceId(rs.getInt(5));
			sites = siteMap.get(parentTitle);
			if (sites == null) {
				sites = new ArrayList<Site>();
			}
			sites.add(site);
			siteMap.put(parentTitle, sites);
		}
		siteMap.put("Live Bookmarks", subscriptions);
		rs.close();
		conn.close();
	}
	
	public void printBookmarks() {
		int sum = 0;
		for (String title : siteMap.keySet()) {
			System.out.println(title);
			List<Site> sites = siteMap.get(title);
			for (Site site : sites) {
				System.out.print("  |-" + site.getTitle() + " (" + site.getUrl() + ")");
				if (!title.equals("Live Bookmarks")) {
					sum++;
				}
				System.out.println();
			}
		}
		System.out.println("Total bookmarks: " + sum + ".");
	}
	
	public void getRandomList(int size) throws SQLException {
		List<Site> allSites = new ArrayList<Site>(subscriptions);
		for (List<Site> sites : siteMap.values()) {
			allSites.addAll(sites);
		}
		Collections.shuffle(allSites);
		System.out.println("Selected Bookmark:");
		for (int i = 0; i < size; i++) {
			Site site = allSites.get(i);
			System.out.println(site.getParent() + "-->" + site.getTitle() + " [" + site.getUrl() + "]");
		}
		System.out.println();
	}
	
	private static void showUsage() {
		System.out.println("Usage:");
		System.out.println("FireFoxFavoritesViewer <your computer account name> <your Firefox profile name>");
		System.out.println();
	}
	
	public static void main(String[] args) throws Exception {
		if (args.length != 2) {
			showUsage();
			System.exit(-1);
		}
		FireFoxFavoritesViewer viewer = new FireFoxFavoritesViewer(args[0], args[1]);
		viewer.loadSites();
		viewer.printBookmarks();
		System.out.println();
		viewer.getRandomList(1);
	}
	
	private static class Site {

		private String title;
		private String url;
		private String parent;
		private int placeId;

		/**
		 * Construct Site.
		 */
		public Site() {
			
		}

		/**
		 * Construct Site.
		 * @param title
		 * @param url
		 * @param parent
		 */
		public Site(String title, String url, String parent, int placeId) {
			this.title = title;
			this.url = url;
			this.parent = parent;
			this.placeId = placeId;
		}

		/*
		 * Accessor methods.
		 */
		
		public String getUrl() {
			return url;
		}

		public void setUrl(String url) {
			this.url = url;
		}

		public String getTitle() {
			return title;
		}

		public void setTitle(String title) {
			this.title = title;
		}

		public String getParent() {
			return parent;
		}

		public void setParent(String parent) {
			this.parent = parent;
		}
		
		@SuppressWarnings("unused")
		public int getPlaceId() {
			return placeId;
		}

		public void setPlaceId(int placeId) {
			this.placeId = placeId;
		}
	}
}
