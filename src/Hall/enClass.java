package Hall;

public enum enClass{
	FIRST("First class"),
	SECOND("Second class"),
	THIRD("Third class");
	private final String description;
	enClass (String description) {
        this.description = description;
    }
	public String getDescription() {return description;}
}