package com.dipakkr.github.udacitylesson1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;



public class MainActivity extends AppCompatActivity {

    public static int quantity = 1 ;
    public static String billReceipt = "Null";
    public static  String topping = "Add WhippedCream : ";
    public static int Price = 0 ;

    CheckBox checkBox , checkBox2 ;
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
    /*...Method for button click on submit order..*/

    public void submitOrder(View view){

        Price = calculatePrice();
        editText = (EditText)findViewById(R.id.et_name);

        checkBox = (CheckBox)findViewById(R.id.checker);
        checkBox2 = (CheckBox)findViewById(R.id.Choco);
        String nameData = editText.getText().toString();

        boolean haswhippedcream = checkBox.isChecked();
        boolean hasChocolate  = checkBox2.isChecked();
        Log.v("MainActivity","HasWhippedCream : " + haswhippedcream);
        Log.v("MainActivity","HasChocolate : " + hasChocolate);

        if((checkBox).isChecked()){
            topping = "Add WhippedCream  : "+ haswhippedcream;
            Price = Price + 5*quantity ;
        }
        else
            topping = "Add WhippedCream : " + haswhippedcream ;

        if((checkBox2).isChecked()){
            topping = "Add Chocolate : " + hasChocolate ;
            Price += 10*quantity ;
        }
        else{
            topping = "Add Chololate : " + hasChocolate ;
        }

        displayQuantity(quantity);

        billReceipt = "Name : " + nameData  +
                "\nQuantity : "+quantity;
        billReceipt += "\n"+topping ;
        billReceipt += "\n"+topping;
        billReceipt += "\nPay Rs.  "  + Price;
        billReceipt += "\nThanks for Visiting ";

        displayMessage(billReceipt);



    }
    /**..calculate price of order
     *
     *@return total price
     * */

    private int calculatePrice(){
        int Price = quantity*20;
        return Price;
    }

    /*...Display the price of set quantity..*/

    public void displayQuantity(int number ){
        TextView quantityText =(TextView)findViewById(R.id.txt_quantity);
        quantityText.setText("" + number);
    }

    /*..Display the current price ..*/

    private void displayMessage(String message) {
        TextView priceTextView = (TextView) findViewById(R.id.txt_price);
        priceTextView.setText(message);
    }

     /*..increase quantity by 1..*/

    public void  increment(View view ){
        quantity =quantity +1;
        displayQuantity(quantity);

    }
       /*..decrease quantity by 1..*/

    public void  decrement(View view) {
        if (quantity > 1) {
            quantity = quantity - 1;
        } else {
            quantity = 0;
        }
        displayQuantity(quantity);
    }

    public void finalOrder(View view){
        String nameData = editText.getText().toString();
        String orderMeesage = "Hey \nYou have Received a order \n\n" +
                "Here is the Order detail" +
                "\nCustomer Name : " + nameData
                +"\nNo. of Coffee Cup : " + quantity +
                "\nAmount Due : " + Price ;

        Intent email = new Intent(Intent.ACTION_SEND);
        email.putExtra(Intent.EXTRA_EMAIL, new String[]{" raj.deeepak@gmail.com "});
        email.putExtra(Intent.EXTRA_SUBJECT, " Order for Coffee");
        email.putExtra(Intent.EXTRA_TEXT, orderMeesage);

        //need this to prompts email client only
        email.setType("message/rfc822");
        startActivity(Intent.createChooser(email,"Chose your client"));
        Toast.makeText(this, "Your order is being submitted", Toast.LENGTH_SHORT).show();
    }


}


