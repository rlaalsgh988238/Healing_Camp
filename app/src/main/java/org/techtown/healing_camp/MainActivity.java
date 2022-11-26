package org.techtown.healing_camp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;


import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLEncoder;


import javax.net.ssl.*;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;


import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

public class MainActivity extends AppCompatActivity {
    public String url_string;
    String http, numOfRows,pageNo,MobileOS,MobileApp,serviceKey,keyword;
    String [][] result = new String[10][23];//xml결과 저장

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // Create a trust manager that does not validate certificate chains


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        http="https://apis.data.go.kr/B551011/GoCamping/searchList?";
        numOfRows="numOfRows=3";
        pageNo="pageNo=1";
        MobileOS="MobileOS=AND";
        MobileApp="MobileApp=HealingCamp";
        serviceKey="serviceKey=xb5%2BBJffxQf2twAHBua7UPgM9nhdnmp1GOz79VG7t%2BcCO69MUTn5JYGznC0kZY0jSaB%2F0GIq%2Bueo4dob5rQCuA%3D%3D";
        try {
            keyword= "keyword="+(URLEncoder.encode("야영장", "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        };
        url_string=http+numOfRows+"&"+pageNo+"&"+MobileOS+"&"+MobileApp+"&"+serviceKey+"&"+keyword;
        System.out.println(url_string); //여기까지 url 조합

        BackgroundThread Back = new BackgroundThread();
        Back.start();




    }

    class BackgroundThread extends Thread{ // 파싱하기 위한 백스레드
        @Override
        public void run(){


            try{

                // Create a trust manager that does not validate certificate chains
                TrustManager[] trustAllCerts = new TrustManager[] {
                        new X509TrustManager() {
                            public X509Certificate[] getAcceptedIssuers() {
                                return new X509Certificate[0];
                            }
                            public void checkClientTrusted(X509Certificate[] certs, String authType) {}
                            public void checkServerTrusted(X509Certificate[] certs, String authType) {}
                        }};
                // Ignore differences between given hostname and certificate hostname
                HostnameVerifier hv = new HostnameVerifier() {
                    public boolean verify(String hostname, SSLSession session) { return true; }
                };
                // Install the all-trusting trust manager
                try {
                    SSLContext sc = SSLContext.getInstance("SSL");
                    sc.init(null, trustAllCerts, new SecureRandom());
                    HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
                    HttpsURLConnection.setDefaultHostnameVerifier(hv);
                } catch (Exception e) {}


                URL url = new URL(url_string);
                XmlPullParserFactory xmlPullParserFactory = XmlPullParserFactory.newInstance();
                XmlPullParser parser=xmlPullParserFactory.newPullParser();
                //xmlpullparserFactory를 이용해서 instance를 생성하고
                // 그 인스턴스를 통해 XmlPullParser 객체인 parser를 생성함

                InputStream IS = url.openStream();
                InputStreamReader reader=new InputStreamReader(IS,"UTF-8");
                parser.setInput(reader);
                String tagName = ""; // xml 태그이름


                int i=0;
                int j=0;//result 배열을 위한 변수
                int eventType = parser.getEventType();//이벤트 타입(태그종류)를 저장, START_DOCUMENT

                while(eventType!=XmlPullParser.END_DOCUMENT){
                    switch (eventType) {
                        //태그가 시작
                        case XmlPullParser.START_TAG:
                            tagName=parser.getName();

                        case XmlPullParser.TEXT:
                            switch(tagName) {
                                case "":
                                    break;
                                case "facltNm":// 캠핑장 이름,j=0
                                case "facltDivNm"://캠핑장 환경,j=1
                                case "induty": //캠핑장 유형,j=2
                                case "lctCl": //캠핑장 유형,j=3
                                case "addr1": //주소,j=4
                                case "operPdCl": //운영기간,j=5
                                case "operDeCl": //운영일,j=6
                                case "sbrsCl": //캠핑장 시설 정보,j=7
                                case "posblFcltyCl": //주변 이용 가능시설,j=8
                                case "animalCmgCl": //반려동물,j=9
                                case "firstImageUrl": //캠핑장 이미지,j=10
                                {
                                    if(parser.getText()==null||parser.getText()==""){
                                        break;
                                    }else{
                                        result[i][j]=parser.getText(); // result배열에 값 저장

                                        j++;
                                        break;
                                    }

                                }
                                }
                            break;
                    }
                    if(j==22){
                        j=0;
                        i++;
                    }
                    eventType = parser.next();//다음으로 이동
                }
                for(int a=0; a<11;a++){
                    int jew=a*2;
                    System.out.println(result[0][jew]+" i="+0+" j="+jew/2);
                    System.out.println(result[1][jew]+" i="+1+" j="+jew/2);

                }
            }catch(XmlPullParserException xppe) {
                xppe.printStackTrace();
            }catch (IOException ioe){
                ioe.printStackTrace();
            }


        }
    }

}