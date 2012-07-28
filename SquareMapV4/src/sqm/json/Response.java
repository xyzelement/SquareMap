package sqm.json;

public class Response {
	public Checkins checkins;
	public Response() {}
	public String toString() {
		return checkins.toString();
	}
}