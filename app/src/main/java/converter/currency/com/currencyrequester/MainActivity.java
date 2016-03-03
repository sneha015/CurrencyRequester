package converter.currency.com.currencyrequester;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText mAmount;
    String mValue;
    Button mRequest;
    Button mClose;
    Spinner mSpinner;
    TextView result;
    static String amountStr;

    String[] mCurrencyLocales = new String[]{ "Indian Rupee", "British Pound", "Euro"};
    String currencyLocale = mCurrencyLocales[0];
    public static final String CURRENCY_AMOUNT = "currency_amount";
    public static final String CURRENCY_LOCALE = "currency_locale";
    public static final String CURRENCY_CONVERT_ACTION_REQ = "sneha.shridhar.custom.intent.currency.req";
    public static final String CURRENCY_CONVERT_ACTION_REPLY = "sneha.shridhar.custom.intent.currency.reply";
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mAmount = (EditText) findViewById(R.id.amount);
        amountStr = mAmount.getText().toString();
        mRequest = (Button) findViewById(R.id.request_button);
        mClose = (Button) findViewById(R.id.close_button);
        mSpinner = (Spinner) findViewById(R.id.spinner);
        mSpinner.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, mCurrencyLocales));
        result = (TextView) findViewById(R.id.result);

        mRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(CURRENCY_CONVERT_ACTION_REQ);
                Bundle b = new Bundle();
                b.putString(CURRENCY_LOCALE, currencyLocale);
                b.putFloat(CURRENCY_AMOUNT, Float.valueOf(mAmount.getText().toString()));
                intent.putExtras(b);
                sendBroadcast(intent);
            }
        });

        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                currencyLocale = mCurrencyLocales[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        mClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                moveTaskToBack(true);
                MainActivity.this.finish();
            }
        });

        //from Broadcast
        Bundle extras = getIntent().getExtras();
        if(extras != null){
            String response = extras.getString(CurrencyResultReciever.CURRENCY_CONVERT_REJECT);

            if( response != null){
                if(response.equals("reject")){
                    response = "Currency conversion request rejected!!";
                }else{
                    response = extras.getString("amout_sent") + " US dollars "+ "converted to "+ response + " (" + extras.getString("locale_sent") + " )";
                }
                result.setText(response);
            }
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        Float currencyResultValue;
        TextView currencyResult = (TextView) findViewById(R.id.result);
        Log.d("Debug -->", "in on resume window");

        //String action = intent.getAction();

        //Bundle bun = getIntent().getExtras();
        //if(bun!=null) {
        //    currencyResultValue = bun.getFloat(CurrencyResultReciever.RESULT);
         //   currencyResult.setText("Converted to " + currencyResultValue);
//
       // }
        mClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                moveTaskToBack(true);
                MainActivity.this.finish();
            }
        });
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
}
