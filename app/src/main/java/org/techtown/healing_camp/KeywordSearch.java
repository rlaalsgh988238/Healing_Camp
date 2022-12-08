package org.techtown.healing_camp;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLEncoder;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

public class KeywordSearch
{
    public static String url_string;
    public String http, numOfRows, pageNo, MobileOS, MobileApp, serviceKey, encodedKeyword;
    public static String[][] result = new String[10][10];//xml결과 저장

    public KeywordSearch() // 클래스 생성자에서는 바뀌지 않는 URL정보 입력해 줬어요
    {
        http="https://apis.data.go.kr/B551011/GoCamping/searchList?";
        numOfRows="numOfRows=3";
        pageNo="pageNo=1";
        MobileOS="MobileOS=AND";
        MobileApp="MobileApp=HealingCamp";
        serviceKey="serviceKey=xb5%2BBJffxQf2twAHBua7UPgM9nhdnmp1GOz79VG7t%2BcCO69MUTn5JYGznC0kZY0jSaB%2F0GIq%2Bueo4dob5rQCuA%3D%3D";
    }

    public String[][] searchStart(String keyword) // 키워드 검색 시작 , 인자로 넣을 키워드 넣어주면 백스레드를 통해 result배열 리턴
    {
        try
        {
            encodedKeyword = "keyword=" + (URLEncoder.encode(keyword, "UTF-8"));
        }
        catch (UnsupportedEncodingException e)
        {
            e.printStackTrace();
        }

        url_string = http + numOfRows + "&" + pageNo + "&" + MobileOS + "&" + MobileApp + "&" + serviceKey + "&" + encodedKeyword;
        System.out.println(url_string); //여기까지 url 조합

        BackgroundThread backTh = new BackgroundThread();
        backTh.start();
        try // 백스레드가 배열을 전부  받아올 때 까지 기다렸다가 result 리턴
        {
            backTh.join();
        } catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        return result;
    }
}

class BackgroundThread extends Thread // 파싱하기 위한 백스레드
{
    @Override
    public void run()
    {
        try
        {
            // TrustManger CA인증서 무시하게... 나중에는 수정하기
            TrustManager[] trustAllCerts = new TrustManager[]
                    {
                            new X509TrustManager()
                            {

                                public X509Certificate[] getAcceptedIssuers()
                                {
                                    return new X509Certificate[0];
                                }
                                public void checkClientTrusted(X509Certificate[] certs, String authType) {}
                                public void checkServerTrusted(X509Certificate[] certs, String authType) {}
                            }
                    };
            HostnameVerifier hv = new HostnameVerifier()
            {

                public boolean verify(String hostname, SSLSession session) { return true; }
            };
            try
            {

                SSLContext sc = SSLContext.getInstance("SSL");
                sc.init(null, trustAllCerts, new SecureRandom());
                HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
                HttpsURLConnection.setDefaultHostnameVerifier(hv);
            } catch (Exception e) {}

            URL url = new URL(KeywordSearch.url_string); // URL로 만들어주기
            XmlPullParserFactory xmlPullParserFactory = XmlPullParserFactory.newInstance();
            XmlPullParser parser=xmlPullParserFactory.newPullParser();

            InputStream IS = url.openStream(); // 오픈스트림으로 URL오픈
            InputStreamReader reader=new InputStreamReader(IS,"UTF-8");
            parser.setInput(reader);
            String tagName = ""; // xml 태그이름

            int i=-1;
            int j; //result 배열을 위한 변수
            int eventType = parser.getEventType(); //이벤트 타입(태그종류)를 저장, START_DOCUMENT

            while(eventType!=XmlPullParser.END_DOCUMENT)
            {
                switch (eventType)
                {
                    //태그가 시작
                    case XmlPullParser.START_TAG: // 시작태그
                        tagName=parser.getName();
                        break;
                    case XmlPullParser.END_TAG: // 닫는태그
                        tagName=parser.getName();
                        break;
                    case XmlPullParser.TEXT:
                        switch(tagName) // 여기서 받을 정보 수정, 파싱 이쁘게 하려고 조금 길어지지만
                        {               // 명령문을 케이스문 하나하나 다 넣어놨어요
                            case "":
                                break;
                            case "facltNm":// 캠핑장 이름,j=0
                            {
                                j = 0;
                                if(parser.getText().contains("\n"))
                                    break;
                                else
                                {
                                    i++;
                                    KeywordSearch.result[i][j]=parser.getText(); // result배열에 값 저장
                                    break;
                                }
                            }
                            case "lineIntro"://캠핑장 요약설명,j=1
                            {
                                j = 1;
                                if (parser.getText().contains("\n"))
                                    break;
                                else {
                                    KeywordSearch.result[i][j] = parser.getText(); // result배열에 값 저장
                                    break;
                                }
                            }
                            case "intro": //캠핑장 설명,j=2
                            {
                                j = 2;
                                if (parser.getText().contains("\n"))
                                    break;
                                else {
                                    KeywordSearch.result[i][j] = parser.getText(); // result배열에 값 저장
                                    break;
                                }
                            }
                            case "lctCl": //캠핑장 지리,j=3
                            {
                                j = 3;
                                if (parser.getText().contains("\n"))
                                    break;
                                else {
                                    KeywordSearch.result[i][j] = parser.getText(); // result배열에 값 저장
                                    break;
                                }
                            }
                            case "doNm": //도 지역 ,j=4
                            {
                                j = 4;
                                if (parser.getText().contains("\n"))
                                    break;
                                else {
                                    KeywordSearch.result[i][j] = parser.getText(); // result배열에 값 저장
                                    break;
                                }
                            }
                            case "sigunguNm": //시군구 ,j=5
                            {
                                j = 5;
                                if (parser.getText().contains("\n"))
                                    break;
                                else {
                                    KeywordSearch.result[i][j] = parser.getText(); // result배열에 값 저장
                                    break;
                                }
                            }
                            case "tel": //전화번호,j=6
                            {
                                j = 6;
                                if (parser.getText().contains("\n"))
                                    break;
                                else {
                                    KeywordSearch.result[i][j] = parser.getText(); // result배열에 값 저장
                                    break;
                                }
                            }
                            case "homepage": //홈페이지 ,j=7
                            {
                                j = 7;
                                if (parser.getText().contains("\n"))
                                    break;
                                else {
                                    KeywordSearch.result[i][j] = parser.getText(); // result배열에 값 저장
                                    break;
                                }
                            }
                            case "themaEnvrnCl": //테마 ,j=8
                            {
                                j = 8;
                                if (parser.getText().contains("\n"))
                                    break;
                                else {
                                    KeywordSearch.result[i][j] = parser.getText(); // result배열에 값 저장
                                    break;
                                }
                            }
                            case "firstImageUrl": //이미지,j=9
                            {
                                j = 9;
                                if (parser.getText().contains("\n"))
                                    break;
                                else {
                                    KeywordSearch.result[i][j] = parser.getText(); // result배열에 값 저장
                                    break;
                                }
                            }
                            default:
                            {
                                //아무것도 안해요
                            }
                        }
                        break;
                }
                eventType = parser.next();//다음으로 이동
            }
        }
        catch(XmlPullParserException xppe)
        {
            xppe.printStackTrace();
        }
        catch (IOException ioe)
        {
            ioe.printStackTrace();
        }
        /*
        // 이거는 시험삼아 출력하는거요
        for(int a=0; a<10;a++)
        {
            System.out.println("첫번째: "+KeywordSearch.result[0][a]+" i="+0+" j="+a);
        }

        for(int a=0; a<10;a++)
        {
            System.out.println("두번째: "+KeywordSearch.result[1][a]+" i="+1+" j="+a);
        }
        for(int a=0; a<10;a++)
        {
            System.out.println("세번째: "+KeywordSearch.result[2][a]+" i="+1+" j="+a);
        }
        */

    }
}
