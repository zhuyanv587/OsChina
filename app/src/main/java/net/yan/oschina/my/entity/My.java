package net.yan.oschina.my.entity;

public class My {
    private int image;
    private String intro;
    private int bracket;



    public My(int image, String intro,int bracket) {
        this.image = image;
        this.intro = intro;
        this.bracket=bracket;
    }

    public int getImage() {
        return image;
    }

    public String getIntro() {
        return intro;
    }
    public int getBracket() {
        return bracket;
    }
}
