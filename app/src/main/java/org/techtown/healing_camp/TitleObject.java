package org.techtown.healing_camp;

public class TitleObject {
    static String Title,campingPlaceName;
    public static TitleObject titleObject = new TitleObject();
    private TitleObject(){
    }
    public static TitleObject getTitleObject(){
        return titleObject;
    }
    //메모장 이름 받기
    public static void setTitle(String title) {
        Title = title;
    }
    //캠핑장 이름 받아오기
    public static void setCampingPlaceName(String campingPlaceName) {
        TitleObject.campingPlaceName = campingPlaceName;
    }
    //메모장 이름 건네주기
    public static String getTitle() {
        return Title;
    }
    //캠핑장 이름 건내주기
    public static String getCampingPlaceName() {
        return campingPlaceName;
    }
}
