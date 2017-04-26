package com.sdaacademy.kasperek.andrzej.urlreader;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Currency;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
    private String urlText;
    @BindView(R.id.get)
    Button get;
    @BindView(R.id.urlEditText)
    EditText urlEditText;
    @BindView(R.id.show)
    TextView show;
    @BindView(R.id.fromEditText)
    EditText fromEditText;
    @BindView(R.id.toEditText)
    EditText toEditText;


    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

    }

    @OnClick
    public void onClick(View view) {

        urlText = "http://api.fixer.io/latest?symbols=USD,GBP";
        new MyAsync().execute(fromEditText.getText().toString().toUpperCase(), toEditText.getText().toString().toUpperCase());
    }

    private class MyAsync extends AsyncTask<String, Void, String> {

        private String readStream;

        @Override
        protected String doInBackground(String... params) {
            HttpURLConnection urlConnection = null;
            URL url = null;
            String urlAddress = "http://api.fixer.io/latest?symbols=" + params[0] + "," + params[1];
            try {
                url = new URL(urlAddress);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            try {
                urlConnection = (HttpURLConnection) url.openConnection();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                InputStream in = new BufferedInputStream(urlConnection.getInputStream());
                readStream = readStream(in);

            } catch (IOException e) {
                e.printStackTrace();
            } finally

            {
                urlConnection.disconnect();
            }

            return readStream;
        }

        private String readStream(InputStream in) throws IOException {
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            StringBuilder result = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                result.append(line);
            }
            return result.toString();
        }

        @Override
        protected void onPostExecute(String s) {
            ExchangeRate currency;
            String currencyJson = s;
            ObjectMapper objectMapper = new ObjectMapper();

            String result = currency.toString();

            show.setText(result);
        }
    }

}
