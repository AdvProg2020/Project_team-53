package Model.Request;

public abstract class Request {
    private static int allRequestId=1;
    private int id;

    public static int getAllRequestId() {
        return allRequestId;
    }

    public static void setAllRequestId(int allRequestId) {
        Request.allRequestId = allRequestId;
    }

    public int getId() {
        return id;
    }

    public Request() {
        this.id = allRequestId;
        allRequestId++;
    }

    public abstract String acceptRequest();
    public abstract String show();

}
