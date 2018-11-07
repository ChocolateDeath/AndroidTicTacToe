package com.example.kevin.tictactoe;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.view.View.OnClickListener;
import android.widget.Toast;

public class tictactoe extends Activity implements OnClickListener {
    public final static String PLAYER1 = "PLAYER1NAME";
    public final static String PLAYER2 = "PLAYER2NAME";
    public static String P1 = "";
    public static String P2 = "";
    public static int MOVES = 0;
    String TURN = "TURN";
    String EmptyName ="";
    //set up for buttons to go in array
    Button buttonArray[] = new Button[9]; //put buttons into array to make easier to handle
    String instanceString[] = new String[9];
    String clickableString[] = new String[9];
    String buttonColor[] = new String[9];
    Button one, two, three, four, five, six, seven, eight, nine;

    //for saving instance state texts
    //---------------------------
    String a = "one";
    String b = "two";
    String c = "three";
    String d = "four";
    String e = "five";
    String f = "six";
    String g = "seven";
    String h = "eight";
    String i = "nine";
    //----------------------------
    //for saving instance state of clickable
    //----------------------------
    String b1 = "button1";
    String b2 = "button2";
    String b3 = "button3";
    String b4 = "button4";
    String b5 = "button5";
    String b6 = "button6";
    String b7 = "button7";
    String b8 = "button8";
    String b9 = "button9";




    boolean XorO = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tictactoe);

        //set up strings for button texts in array getText restore
        instanceString[0]=a;
        instanceString[1]=b;
        instanceString[2]=c;
        instanceString[3]=d;
        instanceString[4]=e;
        instanceString[5]=f;
        instanceString[6]=g;
        instanceString[7]=h;
        instanceString[8]=i;
        //set up strings for isClickable restore
        clickableString[0]=b1;
        clickableString[1]=b2;
        clickableString[2]=b3;
        clickableString[3]=b4;
        clickableString[4]=b5;
        clickableString[5]=b6;
        clickableString[6]=b7;
        clickableString[7]=b8;
        clickableString[8]=b9;



        one = (Button) findViewById(R.id.button);
        two = (Button) findViewById(R.id.button2);
        three = (Button) findViewById(R.id.button3);
        four = (Button) findViewById(R.id.button4);
        five = (Button) findViewById(R.id.button5);
        six = (Button) findViewById(R.id.button6);
        seven = (Button) findViewById(R.id.button7);
        eight = (Button) findViewById(R.id.button8);
        nine = (Button) findViewById(R.id.button9);

        //recieve intent

        Intent intent = getIntent();

        String p1 = intent.getStringExtra(PLAYER1);
        String p2 = intent.getStringExtra(PLAYER2);

        //set player texts
        TextView setp1 = (TextView) findViewById(R.id.p1final);
        TextView setp2 = (TextView) findViewById(R.id.p2final);
        //set player tags
        TextView p1text = (TextView)findViewById(R.id.player1text);
        TextView p2text = (TextView)findViewById(R.id.player2text);
        //set colors for player tags
        p1text.setTextColor(Color.parseColor("#3556ac"));
        p2text.setTextColor(Color.parseColor("#993333"));


        //set to global variable for winner Toast
        tictactoe.P1=p1;
        tictactoe.P2=p2;


        setp1.setText(p1);
        setp2.setText(p2);
        //set up buttons for board in an array
        buttonArray[0] = one;
        buttonArray[1] = two;
        buttonArray[2] = three;
        buttonArray[3] = four;
        buttonArray[4] = five;
        buttonArray[5] = six;
        buttonArray[6] = seven;
        buttonArray[7] = eight;
        buttonArray[8] = nine;
        //set color for buttons
        for(int i=0;i<9;i++)
            buttonArray[i].setBackgroundColor(Color.parseColor("#5F5F5F"));
        //set color for text in buttons
        for(int i=0;i<9;i++)
            buttonArray[i].setTextColor(Color.parseColor("#FFFFFF"));


        //set listeners for buttons
        for (int temp = 0;temp < 9; temp++) {
            buttonArray[temp].setOnClickListener(this);


        }


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
        } else if (id == R.id.reset_game) {
            resetGame();//reset game when pressed
            return true;
        }

        return super.onOptionsItemSelected(item);
    }




    @Override
    public void onClick(View v) {
        //cast to view to button
        Button button = (Button) v;
        //Change from X to O or O to X on a button click.
        if (XorO) {//X's Turn
            button.setText("X");
            XorO = false;//change turn to O
        } else {  //O's Turn
            button.setText("O");
            XorO = true;//change turn to X
        }

        button.setClickable(false);//disable button after click
        button.setBackgroundColor(Color.parseColor("#B3B3B3"));//fade out button after click
        MOVES++;//increment moves
        if(MOVES>4)//lowest possible moves for a win
        winner();//check if anyone has won
    }




    public void winner() {
        String winner="";
        //If there are no names for the players, set a default
         if(P1.equals(EmptyName)){
            tictactoe.P1="Player 1";
        }
       if(P2.equals(EmptyName)){
            tictactoe.P2="Player 2";
        }
        //If top row matches and one of them is not clickable(disabled).
        //If one is not clickable then it is not a fresh game with a blank board
        //Rows for Board solver
        if(one.getText()==two.getText() &&one.getText()==three.getText()
                && two.isClickable()==false){
            if(one.getText().toString()=="X") {
                winner = P1 + " Wins!";//append name to winner string
                MOVES = 0;//reset moves for next game
            }
            else {
                winner = P2 + " Wins!";
                MOVES = 0;
            }

        }
        else if(four.getText()==five.getText()&&four.getText()==six.getText()
                &&five.isClickable()==false){
            if(four.getText().toString()=="X") {
                winner = P1 + " Wins!";
                MOVES = 0;
            }
            else {
                winner = P2 + " Wins!";
                MOVES = 0;
            }
        }
        else if(seven.getText()==eight.getText() && seven.getText()==nine.getText()
                &&eight.isClickable()==false){
            if(seven.getText().toString()=="X"){
                winner = P1 + " Wins!";
                MOVES = 0;
            }
            else{
                winner = P2 + "Wins!";
                MOVES = 0;
            }
        }
        //Rows for Board solver done

        //columns for Board solver
       else if(one.getText()==four.getText()&&one.getText()==seven.getText()
                &&four.isClickable()==false){
            if(one.getText().toString()=="X") {
                winner = P1 + " Wins!";
                MOVES = 0;
            }
            else{
                winner = P2 + " Wins!";
                MOVES = 0;
            }
        }
        else if(two.getText()==five.getText()&&two.getText()==eight.getText()
                &&five.isClickable()==false){
            if(two.getText()=="X"){
                winner = P1 + " Wins";
                MOVES = 0;
            }
            else{
                winner = P2 + " Wins!";
                MOVES = 0;
            }
        }
        else if(three.getText()==six.getText()&&three.getText()==nine.getText()
                &&six.isClickable()==false){
            if(three.getText()=="X"){
                winner = P1 + " Wins";
                MOVES = 0;
            }
            else{
                winner = P2 + " Wins!";
                MOVES = 0;

            }
        }
        //Columns for Board solver done
        //Diagonals
        else if(one.getText()==five.getText()&&one.getText()==nine.getText()
                &&five.isClickable()==false){
            if(one.getText()=="X"){
                winner = P1 + " Wins!";
                MOVES = 0;
            }
            else{
                winner = P2 + " Wins";
                MOVES = 0;
            }
        }
        else if(three.getText()==five.getText()&&three.getText()==seven.getText()
                &&five.isClickable()==false){
            if(one.getText()=="X"){
                winner = P1 + " Wins!";
                MOVES = 0;
            }
            else winner = P2 + " Wins";
        }
        else if(MOVES==9&&winner=="") {
            winner = "Draw!";
            MOVES =0;
        }

        if(winner!="") {
            for(int i=0;i<9;i++){
                buttonArray[i].setClickable(false);
                buttonArray[i].setBackgroundColor(Color.parseColor("#B3B3B3"));


            }

            Toast toast = Toast.makeText(getApplicationContext(), winner, Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER | Gravity.BOTTOM, 0, 0);//toast appears centered
            toast.show();//at bottom
            winner = "";//reset winner string
        }

    }




    public void onSaveInstanceState(Bundle savedInstanceState) {
        //Save whos turn it is
        savedInstanceState.putBoolean(TURN, XorO);

        //save states of every button- Text in buttons
        for(int i=0;i<9;i++){
            savedInstanceState.putString(instanceString[i],buttonArray[i].getText().toString());
        }

        //save clickable state
        for(int i=0;i<9;i++)
            savedInstanceState.putBoolean(clickableString[i], buttonArray[i].isClickable());

        //save the background color of every button
        for(int i=0;i<9;i++)
            savedInstanceState.putString(buttonColor[i], buttonArray[i].getBackground().toString());
        super.onSaveInstanceState(savedInstanceState);
    }




    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        //restore whos turn it is
        XorO = savedInstanceState.getBoolean(TURN);
        //restore value stored in bundle
        for(int i =0;i<9;i++)
            buttonArray[i].setText(savedInstanceState.getString(instanceString[i]));

        //restore clickable states and button colors
        for(int i=0;i<9;i++) {
            buttonArray[i].setClickable(savedInstanceState.getBoolean(clickableString[i]));
            if(buttonArray[i].isClickable()==false)
                buttonArray[i].setBackgroundColor(Color.parseColor("#B3B3B3"));
        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_tictactoe, menu);
        return true;
    }

    public void resetGame() {
        for (int temp = 0; temp < buttonArray.length; temp++) {
            buttonArray[temp].setText(getString(R.string.blankspace));
            buttonArray[temp].setClickable(true);
            MOVES = 0;
            XorO = true;
            buttonArray[temp].setBackgroundColor(Color.parseColor("#5F5F5F"));

        }
    }
}
