package ug.co.sampletracker.app;

import android.content.ContentResolver;
import android.content.Context;
import android.content.res.Resources;
import android.net.Uri;
import android.support.annotation.AnyRes;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RatingBar;
import android.widget.TextView;

import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;

public class LayoutTester extends AppCompatActivity implements BaseSliderView.OnSliderClickListener, ViewPagerEx.OnPageChangeListener {


    private SliderLayout mDemoSlider;
    private TextView txvTipUsd1;
    private TextView txvTipUsd2;
    private TextView txvTipUsd5;
    private TextView btnCustomAmount;
    private RatingBar ratingBarMerchant;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard_tabs);

        //loadSlider();
        initialize();

    }

    private void initialize() {

        ratingBarMerchant = findViewById(R.id.ratingBarMerchant);
        txvTipUsd1 = (TextView)findViewById(R.id.txvTipUsd1);
        txvTipUsd2 = (TextView)findViewById(R.id.txvTipUsd2);
        txvTipUsd5 = (TextView)findViewById(R.id.txvTipUsd5);
        btnCustomAmount = (TextView)findViewById(R.id.btnCustomAmount);

        attachListeners();

    }

    private void attachListeners() {

        ratingBarMerchant.setOnRatingBarChangeListener(ratingBarChangeListener);
        btnCustomAmount.setOnClickListener(btn->showCustomAmountDialog());

    }

    private void showCustomAmountDialog() {


    }

    private void selectTip(int id){

        int[] tipIds = {R.id.txvTipUsd1,R.id.txvTipUsd2,R.id.txvTipUsd5};
        for (int i =0;i<tipIds.length;i++ ){

            if(id == tipIds[i]){

              //  catAll.setBackgroundResource(R.drawable.myshape);

            }else{

            }

        }

    }


    RatingBar.OnRatingBarChangeListener ratingBarChangeListener = new RatingBar.OnRatingBarChangeListener() {
        @Override
        public void onRatingChanged(RatingBar ratingBar, float rating, boolean isChangedByUser) {



        }
    };

    private void loadSlider() {

        /*mDemoSlider = (SliderLayout)findViewById(R.id.slider);

        for(String value : DataGenerator.getTestStrings()){

            TextSliderView textSliderView = new TextSliderView(this);
            // initialize a SliderLayout
            textSliderView
                    .description(value)
                    .image(R.drawable.help_bg)
                    .setScaleType(BaseSliderView.ScaleType.Fit)
                    .setOnSliderClickListener(this);

            //add your extra information
            textSliderView.bundle(new Bundle());
            textSliderView.getBundle()
                    .putString("extra",value);

            mDemoSlider.addSlider(textSliderView);
        }
        mDemoSlider.setPresetTransformer(SliderLayout.Transformer.Accordion);
        mDemoSlider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        mDemoSlider.setCustomAnimation(new DescriptionAnimation());
        mDemoSlider.setDuration(4000);
        mDemoSlider.addOnPageChangeListener(this);*/


    }

    @Override
    protected void onStop() {
        // To prevent a memory leak on rotation, make sure to call stopAutoCycle() on the slider before activity or fragment is destroyed
        mDemoSlider.stopAutoCycle();
        super.onStop();
    }


    private void setupBannerView(){

         /*
        List<BannerEntity> entities = new ArrayList<>();
        for (String value :
                DataGenerator.getStmtReceptionTypes()) {
            BannerEntity bannerEntity = new BannerEntity();
            bannerEntity.imageUrl = getUriToResource(LayoutTester.this,R.drawable.gradient_bg).getPath();
            bannerEntity.title = value;
            entities.add(bannerEntity);
        }

        mBannerView = (BannerView) findViewById(R.id.banner_view);
        mBannerView.setEntities(entities);
        mBannerView.setAutoScroll(3);
        mBannerView.setOnBannerClickListener(this); */

    }

    private void setUpCarouselPicker() {

      /*  CarouselPicker carouselPicker = findViewById(R.id.carousel);

        List<CarouselPicker.PickerItem> textItems = new ArrayList<>();

        for (String value : DataGenerator.getTestStrings()) {
            textItems.add(new CarouselPicker.TextItem(value, 12));
        }
        CarouselPicker.CarouselViewAdapter textAdapter = new CarouselPicker.CarouselViewAdapter(this, textItems, 0);
        textAdapter.setTextColor(getResources().getColor(R.color.white));
        carouselPicker.setAdapter(textAdapter);*/

    }

    public  Uri getUriToResource(@NonNull Context context,
                                             @AnyRes int resId)
            throws Resources.NotFoundException {
        /** Return a Resources instance for your application's package. */
        Resources res = context.getResources();
        /**
         * Creates a Uri which parses the given encoded URI string.
         * @param uriString an RFC 2396-compliant, encoded URI
         * @throws NullPointerException if uriString is null
         * @return Uri for this given uri string
         */
        Uri resUri = Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE +
                "://" + res.getResourcePackageName(resId)
                + '/' + res.getResourceTypeName(resId)
                + '/' + res.getResourceEntryName(resId));
        /** return uri */
        return resUri;
    }

    @Override
    public void onSliderClick(BaseSliderView slider) {

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
