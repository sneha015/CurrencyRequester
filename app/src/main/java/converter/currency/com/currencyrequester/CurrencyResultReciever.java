package converter.currency.com.currencyrequester;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;


/**
 * Created by lokisneha on 2/25/16.
 */
public class CurrencyResultReciever extends BroadcastReceiver {
    public CurrencyResultReciever(){
    }

    public static final String TAG = "CurrencyResultReceiver";
    public static final String RESULT = "currency.convert";
    public static final String CURRENCY_CONVERT_ACTION_REQ = "sneha.shridhar.custom.intent.currency.req";
    public static final String CURRENCY_CONVERT_ACTION_REPLY = "sneha.shridhar.custom.intent.currency.reply";
    public static final String CURRENCY_CONVERT_REJECT = "rejected";
    public static final String CURRENCY_CONVERT_RESULT = "result";

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d("Debug -->", ">> in onReceive ");
        String action = intent.getAction();
        if (intent.getAction().equals(CURRENCY_CONVERT_ACTION_REPLY)) {
            Bundle extras = intent.getExtras();
            Log.e(TAG, ""+extras.getString(CURRENCY_CONVERT_REJECT));

            Intent intent1 = new Intent(context, MainActivity.class);
            intent1.putExtras(extras);
            intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent1);
        }



    }
}



