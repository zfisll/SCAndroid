package com.example.user_zf.scandroid.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.example.user_zf.scandroid.R;
import com.example.user_zf.scandroid.utils.SDUtils;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class AsyncTaskActivity extends AppCompatActivity {

    TextView show;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_async_task);

        show = (TextView) findViewById(R.id.show);
    }

    public void download(View source) throws MalformedURLException{
        DownloadTask task = new DownloadTask(this);
        task.execute(new URL("https://qd.myapp.com/myapp/qqteam/AndroidQQ/mobileqq_android.apk"));
    }

    /**
     * 下载Task
     */
    class DownloadTask extends AsyncTask<URL, Integer, String>{

        Context context;
        ProgressDialog pdDown;
        int current = 0;
        Handler handler;


        public DownloadTask(Context context){
            this.context = context;
            handler = new Handler(){
                @Override
                public void handleMessage(Message msg) {
                    if(msg.what == 0x123){
                        pdDown.setMax(msg.arg1);
                    }
                }
            };
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pdDown = new ProgressDialog(context);
            pdDown.setTitle("下载任务");
            pdDown.setMessage("任务正在进行中，请稍后...");
            pdDown.setCancelable(true);
            pdDown.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            pdDown.setIndeterminate(false);
            pdDown.setMax(33554432);
            pdDown.setProgress(50);
            pdDown.show();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            pdDown.dismiss();
            show.setText("下载成功");
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
//            int progress = values[0];
//            show.setText("已经下载了"+progress+"%");
            int current = values[0];
            pdDown.setProgress(current);
        }

        @Override
        protected String doInBackground(URL... params) {
            try {
                URLConnection conn = params[0].openConnection();
                int count = conn.getContentLength();
                Message msg = new Message();
                msg.what = 0x123;
                msg.arg1 = count;
//                handler.sendMessage(msg);
                InputStream is = conn.getInputStream();
                OutputStream os = new FileOutputStream(SDUtils.getSdCardPath() + "/qq.apk");
                byte[] buffer = new byte[1024];
                int len = -1;
                while((len=is.read(buffer)) > 0){
                    current += len;
                    os.write(buffer, 0, len);
                    publishProgress(current);
                }
            }catch(Exception e){
                e.printStackTrace();
            }
            return null;
        }
    }
}
