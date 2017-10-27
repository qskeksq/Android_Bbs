package com.example.administrator.remotebbs.Remote;

import android.os.AsyncTask;
import android.util.Log;

import com.example.administrator.remotebbs.model.Data;
import com.example.administrator.remotebbs.model.Result;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Administrator on 2017-10-26.
 */

public class BbsRemote {

    /**
     * 1. GET
     * localhost:8090/bbs?type=all
     * localhost:8090/bbs?type=all&no=1
     * <p>
     * 2. POST
     * localhost:8090/bbs?type=all
     */
    public static String BASE_URL = "http://192.168.0.140:8090/bbs/";
    public static String TYPE_ALL = "?type=all";
    public static String TYPE_NO = "?type=no";
    public static String QUERY_NO = "&no=";

    public static void loadGet(final TaskInterface taskInterface, final String seq) {

        new AsyncTask<String, Void, String>() {
            @Override
            protected String doInBackground(String... seqs) {
                String result = "";
                if (seqs[0] == null || seqs[0].equals("")) {
                    // http://192.168.0.140:8090/bbs/?type=all
                    result = BbsRemote.sendGet(BASE_URL + TYPE_ALL);
                    Log.e("url 확인", BASE_URL + TYPE_ALL);
                } else {
                    // http://192.168.0.140:8090/bbs/?type=all&no=1
                    result = BbsRemote.sendGet(BASE_URL + TYPE_NO + QUERY_NO + seqs[0]);
                    Log.e("url 확인", BASE_URL + TYPE_NO + QUERY_NO + seqs[0]);
                }
                Log.e("결과 확인", result);
                return result;
            }

            @Override
            protected void onPostExecute(String jsonString) {
                Result datas = new Gson().fromJson(jsonString, Result.class);
                taskInterface.setData(datas.getData());
            }
        }.execute(seq);
    }

    public static void loadPost(final TaskInterface taskInterface, final Data data) {

        new AsyncTask<Data, Void, String>() {
            @Override
            protected String doInBackground(Data... json) {
                Gson gson = new Gson();
                String result = BbsRemote.sendPost(BASE_URL, gson.toJson(data));
                return result;
            }

            @Override
            protected void onPostExecute(String jsonString) {
                Result data = new Gson().fromJson(jsonString, Result.class);
                taskInterface.setResult(data);
            }
        }.execute(data);
    }


    public static String sendPost(String address, String postData) {
        String result = "";
        try {
            URL url = new URL(address);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");
            // Header 작성
            con.setRequestProperty("Content-Type", "application/json; charset=utf8");
            con.setRequestProperty("Authorization", "token=나중에 서버랑 통신할 때 쓸거임");

            con.setDoOutput(true);
            OutputStream os = con.getOutputStream();
            os.write(postData.getBytes());
            os.flush();
            os.close();

            // 통신이 성공인지 체크
            if (con.getResponseCode() == HttpURLConnection.HTTP_OK) {
                // 여기서 부터는 파일에서 데이터를 가져오는 것과 동일
                InputStreamReader isr = new InputStreamReader(con.getInputStream());
                BufferedReader br = new BufferedReader(isr);
                String temp = "";
                while ((temp = br.readLine()) != null) {
                    result += temp;
                }
                br.close();
                isr.close();
            } else {
                Log.e("ServerError", con.getResponseCode() + "");
            }
            con.disconnect();
        } catch (Exception e) {
            Log.e("Error", e.toString());
        }
        Log.e("결과", result);
        return result;
    }

    public static String sendGet(String address) {
        String result = "";
        try {
            URL url = new URL(address);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");

            // 통신이 성공인지 체크
            if (con.getResponseCode() == HttpURLConnection.HTTP_OK) {
                // 여기서 부터는 파일에서 데이터를 가져오는 것과 동일
                InputStreamReader isr = new InputStreamReader(con.getInputStream());
                BufferedReader br = new BufferedReader(isr);
                String temp = "";
                while ((temp = br.readLine()) != null) {
                    result += temp;
                }
                br.close();
                isr.close();
            } else {
                Log.e("ServerError", con.getResponseCode() + "");
            }
            con.disconnect();
        } catch (Exception e) {
            Log.e("Error", e.toString());
        }
        Log.e("결과", result);
        return result;
    }

}
