package Model;

public class Comment {
    String title;
    String context;
    String commenterUsername;
    boolean buyedThis;

    public Comment(String title, String context, String commenterUsername, boolean buyedThis) {
        this.title = title;
        this.context = context;
        this.commenterUsername = commenterUsername;
        this.buyedThis = buyedThis;
    }

    public String showComment(){
        return "Comment" +
                "   title=" + title + '\n' +
                "   context='" + context + '\n' +
                "   commenterUsername='" + commenterUsername + '\n' +
                "   buyedThis=" + buyedThis ;
    }
}
