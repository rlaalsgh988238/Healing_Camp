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
    public String getName(int position){
        return result[0];
    }
    //요약설명
    public String getInfo(int position){
        return result[1];
    }
    //지역
    public String getLocal(int position){
        return result[5];
    }
    //전화번호
    public String getTel(int position){
        return result[6];
    }
    //홈페이지
    public String getHomePage(int position){
        return result[7];
    }
}
