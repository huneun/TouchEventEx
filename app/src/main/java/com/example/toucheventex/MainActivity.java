package com.example.toucheventex;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Switch;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    View view1, view2;
    // 다이얼로그를 만들때View 위젯으로 자바에 addView해서
    // main_activity가 아닌 또다른 xml 팝업창을 띄울수 있다
    TextView tv1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final GestureDetector detector;
        view1=(View)findViewById(R.id.view1);
        view2=(View)findViewById(R.id.view2);
        tv1=(TextView)findViewById(R.id.tv1);
        detector= new GestureDetector(this, new GestureDetector.OnGestureListener() {
            @Override
            public boolean onDown(MotionEvent e) {
                tv1.append("view2 화면을 눌렀다\n");
                return true;
            }

            @Override
            public void onShowPress(MotionEvent e) {
                tv1.append("view2 화면을 눌렀다 떼었구나!\n");
            }

            @Override //한손 탭
            public boolean onSingleTapUp(MotionEvent e) {
                tv1.append("view2한 손가락으로 눌렀다 떼었구나\n");
                return true;
            }

            @Override //일정한 속도로 누르고 움직일때
            public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
                tv1.append("view2에서 손가락을 누른 채 일정한 방향으로 움직였다. 떼었구나!\n");
                return true;
            }

            @Override //길게 누르고 있을때
            public void onLongPress(MotionEvent e) {
                tv1.append("view2에 너무 오래 누르고 있었군\n");
            }

            @Override //넘기듯 터치를 휘두를때
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                tv1.append("view2에 누른채 너무 빠르게 손가락을 움직이는군\n");
                return true;
            }
        });

                view1.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        float x = event.getX();
                        float y = event.getY();
                        switch (event.getAction()) {
                            case MotionEvent.ACTION_DOWN:
                                tv1.append("손가락 누름(x:" + x + ", y:" + y + ")\n");
                                break;
                            case MotionEvent.ACTION_MOVE:
                                tv1.append("손가락 움직임(x:" + x + ", y:" + y + ")\n");
                                break;
                            case MotionEvent.ACTION_UP:
                                tv1.append("손가락 뗌(x:" + x + ", y:" + y + ")\n");
                                break;
                        }
                        return true;
                    }
                });
                view2.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        detector.onTouchEvent(event);
                        return true;
                    }
                });
    }
}