package ru.lukianov.anton.moxyapplication;


import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import org.json.simple.JSONObject;

import ru.lukianov.anton.moxyapplication.model.JsonParseTest;
import ru.lukianov.anton.moxyapplication.presenter.MoxyAppPresenter;
import ru.lukianov.anton.moxyapplication.view.MoxyAppView;

public class MoxyAppActivity extends MvpAppCompatActivity implements MoxyAppView {

    @InjectPresenter
    MoxyAppPresenter mMoxyAppPresenter;

    LinearLayout llMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_moxy_app);
        llMain = (LinearLayout) findViewById(R.id.llMain);

    }


    @Override
    public void setViewArray(JSONObject jsonObject) {

        for (int s = 0; s < JsonParseTest.JsonParseView(jsonObject).size(); s++) {
            switch ((String)JsonParseTest.JsonParseView(jsonObject).get(s)){

                case "hz":
                    TextView textView = new TextView(this);
                    textView.setText(JsonParseTest.getJsonText( JsonParseTest.JsonParseData(jsonObject)));
                    textView.setTextSize(20);
                    textView.setGravity(Gravity.CENTER);
                    textView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Toast.makeText(getApplicationContext(),"hz",Toast.LENGTH_SHORT).show();
                        }
                    });
                    llMain.addView(textView);
                    break;
                case "picture":
                    ImageView imageView = new ImageView(this);
                    imageView.setImageBitmap(JsonParseTest.getJsonPicture( JsonParseTest.JsonParseData(jsonObject)));
                    imageView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Toast.makeText(getApplicationContext(),"picture",Toast.LENGTH_SHORT).show();
                        }
                    });
                    llMain.addView(imageView);
                    break;
                case "selector":
                    Spinner spinner = new Spinner(this);
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, JsonParseTest.getJsonSelector( JsonParseTest.JsonParseData(jsonObject)));
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinner.setAdapter(adapter);
                    spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            Toast.makeText(getApplicationContext(),"selector " +position,Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });
                    llMain.addView(spinner);
                    break;
            }

        }

    }
}
