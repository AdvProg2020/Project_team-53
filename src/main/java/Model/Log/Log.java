package Model.Log;

public abstract class Log {
    private static int allLogId = 1;
    String date;
    int price;
    String deliveryStatus;
    int logId;

//    public Log(String date, int logId, int price, String deliveryStatus) {
//        this.logId = logId;
//        this.date = date;
//        this.price = price;
//        this.deliveryStatus = deliveryStatus;
//    }
//
//    public void addLog(){
//        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
//        LocalDateTime now = LocalDateTime.now();
//        System.out.println(dtf.format(now));
//        SellLog sellLog = new SellLog(dtf.format(now), allLogId);
//    }
}
