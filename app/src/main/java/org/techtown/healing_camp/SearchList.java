package org.techtown.healing_camp;

public class SearchList {
    String [] result;

    public SearchList(String[] result){
        this.result = result;
    }

    public String[] getSearchList(){
        return result;
    }
    //이름
    public String getName(){return result[0];}
    //요약설명
    public String getInfo(){return result[1];}
    //상세설명
    public String getDetailInfo(){return result[2];}
    //지리
    public String getHowToCome(){return result[3];}
    //도/지역
    public String getBigLocal(){return result[4];}
    //지역
    public String getLocal(){return result[5];}
    //전화번호
    public String getTel(){return result[6];}
    //홈페이지
    public String getHomePage(){return result[7];}
    //테마
    public String getTheme(){return  result[8];}
    //이미지
    public String getUrl(){return result[9];}
}
