package sqm.json;

public class Icon {
	public String prefix;
	public int[] sizes;
	public String name;
	
	public Icon() {}
	
	public String getUrl() {
		return prefix + "32"+name;
	}
	
	public String toString() {
		return "<img src='"
				+ prefix + "32"+name + "'/>";
		//return prefix + sizes.toString() + name;
	}
}
