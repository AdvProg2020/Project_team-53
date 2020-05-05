package Model.Request;

public abstract class Request {
    private static int numbers=0;
    private int id;

    public int getId() {
        return id;
    }

    public Request() {
        this.id = numbers;
        numbers++;
    }

    public abstract String acceptRequest();
    public abstract String show();
}
