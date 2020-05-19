package Model.Product.CommentAndScore;

public class Score {
    int score;
    String buyerUsername;

    public Score(int score, String buyerUsername) {
        this.score = score;
        this.buyerUsername = buyerUsername;
    }

    public int getScore() {
        return score;
    }

    public String getBuyerUsername() {
        return buyerUsername;
    }
}
