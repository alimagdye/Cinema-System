package Movie;

public enum enGenre {
    ACTION("Action"),
    ROMANTIC("Romantic"),
    HORROR("Horror"),
    COMEDY("Comedy");
    
    private final String genre;

    enGenre(String genre){
        this.genre = genre;
    }
    
    public String getGenre(){
        return genre;
    }
}
