package com.fe.graphicdetailslayout;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.fe.graphicdetails.widget.GraphicDetailsLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GraphicDetailsLayout gdLayout = (GraphicDetailsLayout) findViewById(R.id.gdlayout);
        gdLayout.addFragment(new Fragment[] {new SpFragment(), new DeFragment()}, getSupportFragmentManager());
    }
}
