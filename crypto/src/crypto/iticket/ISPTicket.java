package crypto.iticket;

import org.json.JSONArray;
import org.json.JSONException;

public class ISPTicket {

	private static JSONArray iTicket = new JSONArray();
	
	public static JSONArray getTicket() throws JSONException {
		JSONArray ticket = new JSONArray();
		JSONArray envolope = new JSONArray();
		
		ticket.put(0, "uid");
		ticket.put(1, "ip");
		ticket.put(2, "issuerID");
		ticket.put(3, "issuerCertURL");
		ticket.put(4, "timeOfSign");
		ticket.put(5, "notAfter");
		
		envolope.put(0, "md5");
		envolope.put(1, "md5-digest");
		
		iTicket.put(0, ticket);
		iTicket.put(1, envolope);

		return iTicket;
	}
	
	public static void main(String[] args) {
		
	}
}
