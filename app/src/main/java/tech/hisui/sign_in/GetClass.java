package tech.hisui.sign_in;

public class GetClass {

    private String et_Add_name;
    private int ImageId;

    public  GetClass(String et_Add_name, int imageId) {
        this.et_Add_name = et_Add_name;
        this.ImageId = imageId;
    }

    public String getName() {
        return et_Add_name;
    }

    public int getImageId() {
        return ImageId;
    }
}
