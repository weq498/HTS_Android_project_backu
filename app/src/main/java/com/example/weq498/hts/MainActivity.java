package com.example.weq498.hts;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


public class MainActivity extends ActionBarActivity {
    private Button btn_sign, login_btn;
    private EditText userID, password;
    private static final int SEND_KEY = 2025;
    private static final int SEND_PASSWORD = 1252;
    private String[] array;
    private URL url;
    private String getKey = "http://127.0.0.1/Android2DB/key.php";
    private String httpLogin = "http://127.0.0.1/Android2DB/hmac_passwd.php";
    private String data;
    private Handler mhandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SEND_KEY:
                    Bundle b = msg.getData();
                    array = b.getStringArray("hashcode");
                    hmac h = new hmac();
                    String bufuser = userID.getText().toString();
                    String bufpas = password.getText().toString();
                    //Toast.makeText(getApplication(),array[0], Toast.LENGTH_SHORT).show();
                    /*if(bufuser.equals("")||bufuser.equals("")) {
                        Toast.makeText(getApplication(), "尚未填入帳號密碼", Toast.LENGTH_SHORT).show();

                    }else if(h.RFC1024(password.getText().toString(), array[1]).equals(array[0]))
                    {Toast.makeText(getApplication(),"登入成功", Toast.LENGTH_SHORT).show();}
                    else{Toast.makeText(getApplication(),"登入失敗，請檢查你的帳號密碼是否有誤",Toast.LENGTH_SHORT).show();}
                    */
                    break;
                case SEND_PASSWORD:
                    break;
                default:
                    Toast.makeText(getApplication(), "資料連接失敗", Toast.LENGTH_SHORT).show();
                    break;
            }
            super.handleMessage(msg);
        }
    };

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final FragmentManager fm = getFragmentManager();
        FragmentTransaction frt = fm.beginTransaction();
        //login
        login_btn = (Button) findViewById(R.id.login_btn);
        userID = (EditText) findViewById(R.id.userID);
        password = (EditText) findViewById(R.id.password);
        btn_sign = (Button) findViewById(R.id.btn_sign);

        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this,login_menu.class);
                startActivity(intent);
                finish();
                //select_userID(userID.getText().toString());
                //login(password.getText().toString());
            }
        });
        //signin
        btn_sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signon signin = new signon();
                fm
                        .beginTransaction()
                        .addToBackStack(null)
                        .replace(R.id.fra_layout, signin)
                        .commit();
            }
        });

    }

    public void login(String password) {
        try {
            url = new URL(httpLogin);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        try {
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("POST");
            urlConnection.setDoInput(true);
            urlConnection.setDoOutput(true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void select_userID(final String account) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String bufferStr = "", key = "";
                String hmacCode;
                try {
                    url = new URL(getKey);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }

                try {
                    HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                    urlConnection.setRequestMethod("POST");
                    urlConnection.setDoInput(true);
                    urlConnection.setDoOutput(true);
                    DataOutputStream dataout = new DataOutputStream(urlConnection.getOutputStream());
                    dataout.writeBytes("account=" + account);
                    dataout.flush();
                    dataout.close();
                    InputStreamReader in = new InputStreamReader(urlConnection.getInputStream());
                    BufferedReader buffer = new BufferedReader(in);
                    String inputline = null;
                    bufferStr = buffer.readLine();
                    key = buffer.readLine();
                    hmac n = new hmac();
                    Bundle bundle = new Bundle();
                    Message msg = Message.obtain(mhandler, SEND_KEY);
                    bundle.putStringArray("hashcode", new String[]{bufferStr, key});
                    msg.setData(bundle);
                    msg.sendToTarget();
                    dataout.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void finish() {
        if (getFragmentManager().getBackStackEntryCount() == 0) {
            super.finish();
        } else {
            getFragmentManager().popBackStack();
        }
    }
}
