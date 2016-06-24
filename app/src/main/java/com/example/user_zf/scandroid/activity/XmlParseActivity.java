package com.example.user_zf.scandroid.activity;

import android.app.Activity;
import android.content.res.XmlResourceParser;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.user_zf.scandroid.R;

public class XmlParseActivity extends Activity implements View.OnClickListener{

    TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LinearLayout container = new LinearLayout(this);
        container.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT , LinearLayout.LayoutParams.MATCH_PARENT));
        container.setOrientation(LinearLayout.VERTICAL);

        Button btn = new Button(this);
        btn.setText("解析my_books.xml内容");
        container.addView(btn, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT , LinearLayout.LayoutParams.WRAP_CONTENT));

        text = new TextView(this);
        container.addView(text, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT , LinearLayout.LayoutParams.WRAP_CONTENT));

        setContentView(container);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        //加载xml文件，用pull方式解析(基于事件解析方式)
        XmlResourceParser parser = XmlParseActivity.this.getResources().getXml(R.xml.my_books);
        try {
            StringBuilder sb = new StringBuilder();
            while (parser.getEventType() != XmlResourceParser.END_DOCUMENT) {//xml结束标签
                if(parser.getEventType() == XmlResourceParser.START_TAG){//开始标签
                    String tagName = parser.getName();
                    if(tagName.equals("book")) {
                        String price = parser.getAttributeValue(null, "price");
                        String publishTime = parser.getAttributeValue(null, "出版日期");
//                        String publishTime = parser.getAttributeValue(1);
                        String name = parser.nextText();

                        sb.append("书名："+name+"    ");
                        sb.append("价格："+price+"    ");
                        sb.append("出版时间"+publishTime+"    ");
                        sb.append("\n");
                    }
                }
                parser.next();//解析下一个事件
            }
            text.setText(sb.toString());

        }catch(Exception e){
            e.printStackTrace();
        }
    }

}
