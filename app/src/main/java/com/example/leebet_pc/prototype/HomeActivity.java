package com.example.leebet_pc.prototype;

import android.animation.ArgbEvaluator;
import android.animation.ValueAnimator;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.nfc.*;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import java.util.*;
import android.os.Handler;

public class HomeActivity extends AppCompatActivity {

    NfcAdapter nfcAdapter;
    public static int ctr = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//// BETS'S CODE ///
        final Handler handler = new Handler();








        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_home);

        nfcAdapter = NfcAdapter.getDefaultAdapter(this);
        if(nfcAdapter!=null && nfcAdapter.isEnabled()){        }else{

            finish();
        }
    }

    LinearLayout colorCng;

    @Override
    protected void onNewIntent(Intent intent) {
        //// JSON Code //// WINNING SCREEN
        super.onNewIntent(intent);

        final TextView top = (TextView)findViewById(R.id.top);
        final TextView total = (TextView)findViewById(R.id.total);
        final TextView grats = (TextView)findViewById(R.id.grats);
        final TextView won = (TextView)findViewById(R.id.won);
        final TextView claim = (TextView)findViewById(R.id.claim);
        final TextView reward = (TextView)findViewById(R.id.reward);
        final TextView space1 = (TextView)findViewById(R.id.space1);
        final TextView space2 = (TextView)findViewById(R.id.space2);

        final ImageButton imgBtn11 = (ImageButton) findViewById(R.id.card11);
        final ImageButton imgBtn22 = (ImageButton) findViewById(R.id.card22);
        final ImageButton imgBtn33 = (ImageButton) findViewById(R.id.card33);
        final ImageButton imgBtn1 = (ImageButton) findViewById(R.id.card1);
        final ImageButton imgBtn2 = (ImageButton) findViewById(R.id.card2);
        final ImageButton imgBtn3 = (ImageButton) findViewById(R.id.card3);


        imgBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imgBtn2.setVisibility(View.GONE);
                imgBtn22.setVisibility(View.VISIBLE);
            }
        });

        imgBtn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imgBtn3.setVisibility(View.GONE);
                imgBtn33.setVisibility(View.VISIBLE);
            }
        });



        if(ctr == 0) {

            switchView();

        }

        else if(ctr==1) {
            final LinearLayout bal = (LinearLayout)findViewById(R.id.idle_screen);
            bal.setVisibility(View.GONE);
            final LinearLayout cards = (LinearLayout)findViewById(R.id.cards);
            cards.setVisibility(View.VISIBLE);
            imgBtn1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    imgBtn1.setVisibility(View.GONE);
                    imgBtn11.setVisibility(View.VISIBLE);

            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {

                    cards.setVisibility(View.GONE);
            imgBtn11.setVisibility(View.GONE);
                    imgBtn2.setVisibility(View.GONE);
                    imgBtn3.setVisibility(View.GONE);
            LinearLayout rl1 = (LinearLayout) findViewById(R.id.idle_screen);
            rl1.setVisibility(View.GONE);
            LinearLayout rl2 = (LinearLayout) findViewById(R.id.tapped_screen);
            rl2.setVisibility(View.GONE);

            LinearLayout rl3 = (LinearLayout)findViewById(R.id.container);
            rl3.setVisibility(View.VISIBLE);

            //Slide congratulation from left
            final Animation slideInLeft = AnimationUtils.loadAnimation(HomeActivity.this, android.R.anim.slide_in_left);
            slideInLeft.setDuration(1000);
            grats.setVisibility(View.VISIBLE);
            grats.startAnimation(slideInLeft);


            //Slide you won from right
            final Animation slideInRight = AnimationUtils.loadAnimation(HomeActivity.this, R.anim.slide_in_right);
            slideInRight.setDuration(1000);
            won.setVisibility(View.VISIBLE);
            won.startAnimation(slideInRight);

            //Start slide out
            final Animation slideOutRight = AnimationUtils.loadAnimation(HomeActivity.this, android.R.anim.slide_out_right);
            final Animation slideOutLeft = AnimationUtils.loadAnimation(HomeActivity.this, R.anim.slide_out_left);
            slideInRight.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {
                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    final Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            //slide out grats
                            slideOutRight.setDuration(1000);
                            grats.startAnimation(slideOutRight);


                            //slide out won
                            slideOutLeft.setDuration(1000);
                            won.startAnimation(slideOutLeft);
                        }
                    }, 1000);
                }

                @Override
                public void onAnimationRepeat(Animation animation) {
                }
            });

            //Remove grats, won, space1
            slideOutRight.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {
                }

                @Override
                public void onAnimationEnd(Animation animation) {


                    grats.setVisibility(View.GONE);
                    won.setVisibility(View.GONE);


                    //Tap to claim
                    claim.setVisibility(View.VISIBLE);
                    reward.setVisibility(View.VISIBLE);
                    final Animation fadeIn = AnimationUtils.loadAnimation(HomeActivity.this, android.R.anim.fade_in);
                    fadeIn.setDuration(1000);
                    claim.startAnimation(fadeIn);
                    reward.startAnimation(fadeIn);

                }

                @Override
                public void onAnimationRepeat(Animation animation) {
                }
            });
                }
            }, 2000);
                }
            });
        }else if(ctr==2) {


            //Total score animation
            final ValueAnimator animatorTotal = ValueAnimator.ofFloat(250, 270);
            animatorTotal.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    float currentValue = (Float) animation.getAnimatedValue();
                    total.setText("Balance: " + String.format("%.2f", currentValue));
                }
            });
            animatorTotal.setDuration(2000);

            //Top score animation
            final ValueAnimator animatorTop = ValueAnimator.ofFloat(20, 0);
            animatorTop.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    float currentValue = (Float) animation.getAnimatedValue();
                    top.setText("+ " + String.format("%.2f", currentValue));
                }
            });
            animatorTop.setDuration(2000);

            //Color animation
            colorCng = (LinearLayout) findViewById(R.id.container);
            final ValueAnimator va = new ValueAnimator();
            va.setIntValues(Color.parseColor("#FF223377"), Color.parseColor("#FF00B33C"));
            va.setEvaluator(new ArgbEvaluator());
            va.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                    colorCng.setBackgroundColor((Integer) valueAnimator.getAnimatedValue());
                }
            });
            va.setDuration(2000);

            //Fade out
            final Animation fadeOut = AnimationUtils.loadAnimation(HomeActivity.this, android.R.anim.fade_out);
            fadeOut.setDuration(1000);
            claim.startAnimation(fadeOut);
            reward.startAnimation(fadeOut);
            fadeOut.setAnimationListener(new Animation.AnimationListener(){
                @Override
                public void onAnimationStart(Animation animation) {}
                @Override
                public void onAnimationEnd(Animation animation) {
                    claim.setVisibility(View.GONE);
                    reward.setVisibility(View.GONE);
                    top.setVisibility(View.VISIBLE);
                    total.setVisibility(View.VISIBLE);

                    //Fade in
                    final Animation fadeIn = AnimationUtils.loadAnimation(HomeActivity.this, android.R.anim.fade_in);
                    fadeIn.setDuration(500);
                    top.setAnimation(fadeIn);
                    total.setAnimation(fadeIn);

                    fadeIn.setAnimationListener(new Animation.AnimationListener(){
                        @Override
                        public void onAnimationStart(Animation animation) {}
                        @Override
                        public void onAnimationEnd(Animation animation) {
                            animatorTotal.start();
                            animatorTop.start();
                            va.start();
                        }
                        @Override
                        public void onAnimationRepeat(Animation animation) {}
                    });
                }
                @Override
                public void onAnimationRepeat(Animation animation) {}
            });
        }
        ctr++;

    }

    public void reverseView(){
        LinearLayout rl1 = (LinearLayout) findViewById(R.id.idle_screen);
        rl1.setVisibility(View.VISIBLE);
        LinearLayout rl2 = (LinearLayout) findViewById(R.id.tapped_screen);
        rl2.setVisibility(View.GONE);

    }
    public void switchView(){
        LinearLayout rl1 = (LinearLayout) findViewById(R.id.idle_screen);
        rl1.setVisibility(View.GONE);
        LinearLayout rl2 = (LinearLayout) findViewById(R.id.tapped_screen);
        rl2.setVisibility(View.VISIBLE);
    }

    @Override
    protected void onResume() {

        Intent intent = new Intent(this, HomeActivity.class);
        intent.addFlags(Intent.FLAG_RECEIVER_REPLACE_PENDING);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,0,intent,0);
        IntentFilter[] intentFilter = new IntentFilter[]{};

        nfcAdapter.enableForegroundDispatch(this,pendingIntent,intentFilter,null);

        if(ctr == 0) {
            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    reverseView();
                }
            }, 5000);
        }

        super.onResume();
    }

    @Override
    protected void onPause() {
        nfcAdapter.disableForegroundDispatch(this);

        super.onPause();
    }
}
