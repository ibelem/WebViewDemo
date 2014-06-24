package com.example.helloworld;

import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.*;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.os.Build;

public class MainActivity extends Activity {
	
	private Button button;
	private EditText editText;
	private WebView webview;
	final Activity activity = this;
	
	@SuppressLint("SetJavaScriptEnabled")
    private void initWebView(){ 
		webview=(WebView)this.findViewById(R.id.webView1); 
		WebSettings webSettings = webview.getSettings();
		webSettings.setJavaScriptEnabled(true);
		webview.setWebViewClient(new webViewClient()); 
		webview.loadUrl("about:blank"); 
    } 
    
    class webViewClient extends WebViewClient{ 
     @Override 
     public boolean shouldOverrideUrlLoading(WebView view, String url) { 
         view.loadUrl(url); 
         return true;  
     } 
    } 
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
    	this.requestWindowFeature(Window.FEATURE_NO_TITLE);
    	getWindow().setFlags(WindowManager.LayoutParams. FLAG_FULLSCREEN ,                
                WindowManager.LayoutParams. FLAG_FULLSCREEN);   
    	getWindow().requestFeature(Window.FEATURE_PROGRESS);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        button = (Button)findViewById(R.id.button1);   
        
        this.initWebView(); 
        
        webview.setWebChromeClient(new WebChromeClient() {
			public void onProgressChanged(WebView view, int progress) {
				activity.setProgress(progress * 100);
				if(progress == 100)
					activity.setTitle("Íê³É");
			}
		});
        
        button =(Button) findViewById(R.id.button1);
        editText = (EditText) findViewById(R.id.editText1);
        webview = (WebView) findViewById(R.id.webView1);
        
        button.setOnClickListener(new Button.OnClickListener()
        {
          @Override
          public void onClick(View v)
          {
        	  //editText.setText("http://m.baidu.com");
        	  webview.loadUrl(editText.getText().toString());
          }
        });
        
        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            return rootView;
        }
    }

}
