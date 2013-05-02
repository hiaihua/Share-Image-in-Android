package com.androidbegin.shareimage;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;


public class ShareImageActivity extends Activity {
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Share Image Tutorial");
        setContentView(R.layout.share_image);
        
        // Find the Button in share_image.xml
        Button SaveImageButton = (Button)findViewById(R.id.button);
        
        // Find the ImageView in share_image.xml
        ImageView ImagePreview = (ImageView)findViewById(R.id.image);

        // Attached the image into share_image.xml 
        ImagePreview.setImageResource(R.drawable.sample_wallpaper);
       
        // Listening to Button click
        SaveImageButton.setOnClickListener(new Button.OnClickListener(){

        	public void onClick(View arg0) {
        		// TODO Auto-generated method stub
        		
        		Bitmap bitmap;
        		OutputStream output;
        		Intent share;
        		
        		// Decoding the image
        		bitmap=BitmapFactory.decodeResource(getResources(),R.drawable.sample_wallpaper);
        	    
        		// Find the SD Card path
        		File filepath = Environment.getExternalStorageDirectory();
        		
        		// Create a new folder AndroidBegin in SD Card
        	    File dir = new File (filepath.getAbsolutePath() + "/AndroidBegin/");
    			dir.mkdirs();
    			
    			// Create a name for the saved image
        	    File file = new File(dir, "sample_wallpaper.png");
        	    
        	    try {
        	    	
        	    	// Start Share Intent
        	    	share = new Intent(Intent.ACTION_SEND);
        	    	
        	    	// Type of file to share
        			share.setType("image/jpeg");
        			
        			// Saves the file into SD Card
        	    	output = new FileOutputStream(file);
        	     
        	    	// Compress into png format image from 0% - 100%, using 100% for this tutorial
        	    	bitmap.compress(Bitmap.CompressFormat.PNG, 100, output);    	     
        	    	output.flush();
        	    	output.close();
        	    	
        	    	// Locate the image to Share
        	    	Uri uri = Uri.fromFile(file);
        	    	
        	    	// Captures the share image
        			share.putExtra(Intent.EXTRA_STREAM,uri);

        			// Start the share dialog
        			startActivity(Intent.createChooser(share, "Share Image"));
        	    }
        	    
        	    // Catch exceptions
        	    catch(Exception e){
        	    	// TODO Auto-generated catch block
        	    	e.printStackTrace();  
        	    }
        	}
        	});
        }

    // Not using options menu for this tutorial
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_share_image, menu);
        return true;
    }
}
