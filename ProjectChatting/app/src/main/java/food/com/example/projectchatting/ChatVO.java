package food.com.example.projectchatting;

public class ChatVO {

    private String content;
    private String uid;
    private String wdate;


    public String getUserName() {
        return uid;
    }

    public void setUserName(String userName) {
        this.uid = userName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }



    public String getWdate() {
        return wdate;
    }

    public void setWdate(String wdate) {
        this.wdate = wdate;
    }
}
