package tech.hisui.sign_in;

public class Attendancelist {
    private String mTime;
    private String mStudent;
    private String mPosition;


    public String getTime() {
        return mTime;
    }

    public void setTime(String time) {
        this.mTime = time;
    }

    public String getStu_id() {
        return mStudent;
    }

    public void setStu_id(String id) {
        this.mStudent = id;
    }

    public String getPosition() {
        return mPosition;
    }

    public void setPosition(String position) {
        this.mPosition = position;
    }
}