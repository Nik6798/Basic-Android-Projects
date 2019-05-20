/**
 * IMPORTANT: Make sure you are using the correct package name.
 * This example uses the package name:
 * package com.example.android.justjava
 * If you get an error when copying this code into Android studio, update it to match teh package name found
 * in the project's AndroidManifest.xml file.
 **/

package com.example.justjava;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {
    int x;
    String orderNum;
    String Name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view)
    {

        EditText et=(EditText)findViewById(R.id.name);
        Name= et.getText().toString();
        CheckBox ch1=(CheckBox) findViewById(R.id.whipped_cream);
        CheckBox ch2=(CheckBox) findViewById(R.id.chocolate);
        boolean haswipped=ch1.isChecked();
        boolean haschocolate=ch2.isChecked();

        orderNum="Name:"+Name+"\nAdd WhippedCream \u20B95: "+haswipped+"\nAdd Chocolate \u20B910: "+haschocolate+"\nQuantity:"+x+"\nTotal:\u20B9"+fun(haswipped,haschocolate)+"\nThanks for ordering";
        display(x);
        displayMessage(orderNum);
    }
    public int fun(boolean haswipped,boolean haschocolate)
    {
        if(haschocolate&&haswipped)
        {
            return (x*30+5*x+10*x);
        }

        else if(haschocolate)
        {
            return (x*30+10*x);
        }
        else if(haswipped)
        {
            return (x*30+5*x);
        }
        else
            return x*30;
    }

    public void displayMessage(String orderNum) {
        TextView priceTextView=(TextView)findViewById(R.id.price_text_view);
        priceTextView.setText(orderNum);
    }

    public void increment(View view)
    {
        if(x==100&&x<=100)
        {
            Toast.makeText(this, "You can't have more than 100 coffee", Toast.LENGTH_SHORT).show();
            return;
        }
        ++x;
        display(x);
    }

    public void decrement(View view)
    {
        if(x==1&&x<2||x==0)
        {Toast.makeText(this,"You can't have less than 1 coffee",Toast.LENGTH_SHORT).show();
            return;}
        --x;
        display(x);
    }
    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }
    /**
     * This method displays the given price on the screen.
     */
//    private void displayPrice(int number) {
//        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
//        priceTextView.setText(NumberFormat.getCurrencyInstance().format(number));
//    }
    public void Email(View view)
    {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("*/*");
        intent.putExtra(Intent.EXTRA_SUBJECT, "Coffee Order for "+Name);
        intent.putExtra(Intent.EXTRA_TEXT, orderNum);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
        //composeEmail("nikhilsaini94169@gmail.com","Coffee Order",orderNum);
    }
    public void composeEmail(String addresses, String subject, String attachment) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("*/*");
        intent.putExtra(Intent.EXTRA_EMAIL,addresses);
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, attachment);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    public void Reset(View view)
    {
        x=0;
        setContentView(R.layout.activity_main);
    }
}