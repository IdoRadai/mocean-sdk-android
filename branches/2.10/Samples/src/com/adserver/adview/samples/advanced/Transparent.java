package com.adserver.adview.samples.advanced;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.adserver.adview.MASTAdServerView;
import com.adserver.adview.samples.R;

public class Transparent extends Activity {
    /** Called when the activity is first created. */
	private Context context;
	private LinearLayout linearLayout;
	MASTAdServerView adServerView;
	
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        setContentView(R.layout.transparent);
        context = this;
        
        adServerView = (MASTAdServerView) findViewById(R.id.adServerView);
        Update();
        
        ((Button)findViewById(R.id.btnUpdate)).setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Update();
			}
		});
        
    }
    
    void Update()
    {
    	if (adServerView.getBackgroundColor()==0) 
    		adServerView.setBackgroundColor(0xFFFFFFFF);
    	else
    		adServerView.setBackgroundColor(0);
    	adServerView.update();
    }
}