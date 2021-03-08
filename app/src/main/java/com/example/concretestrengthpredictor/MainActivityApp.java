package com.example.concretestrengthpredictor;

import android.content.res.AssetFileDescriptor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import org.tensorflow.lite.Interpreter;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

public class MainActivityApp extends AppCompatActivity {

    Interpreter interpreter;

    EditText etxt_cement;
    EditText etxt_blastFurnace;
    EditText etxt_flyAsh;
    EditText etxt_water;
    EditText etxt_superplasticizer;
    EditText etxt_coarseAggregate;
    EditText etxt_fineAggregate;
    EditText etxt_age;

    TextView txt_resultBox;

    Button predictButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_app);

        //Arrays for performing MinMaxScaler()
        float[] min = {102.000000f,0.000000f,0.000000f,121.750000f,0.000000f, 801.000000f,594.000000f,
                1.000000f,2.331808f};
        float[] max = {540.000000f,359.400000f,200.100000f,247.000000f,32.200000f,1145.000000f,992.600000f,
                365.000000f, 82.599225f};

        //Loading the tflite model.
        try
        {
            interpreter = new Interpreter(loadModelFile());
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        etxt_cement = findViewById(R.id.et_cement);
        etxt_blastFurnace = findViewById(R.id.et_BlastFurnace);
        etxt_flyAsh = findViewById(R.id.et_FlyAsh);
        etxt_water = findViewById(R.id.et_Water);
        etxt_superplasticizer = findViewById(R.id.et_Superplasticizer);
        etxt_coarseAggregate = findViewById(R.id.et_CoarseAggregate);
        etxt_fineAggregate = findViewById(R.id.et_FineAggregate);
        etxt_age = findViewById(R.id.et_Age);

        txt_resultBox = findViewById(R.id.txt_results);

        predictButton = findViewById(R.id.btn_predict);

        predictButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (etxt_cement.length() == 0) {
                    etxt_cement.requestFocus();
                    etxt_cement.setError("Please fill this field");
                } else if (etxt_blastFurnace.length() == 0) {
                    etxt_blastFurnace.requestFocus();
                    etxt_blastFurnace.setError("Please fill this field");
                } else if (etxt_flyAsh.length() == 0) {
                    etxt_flyAsh.requestFocus();
                    etxt_flyAsh.setError("Please fill this field");
                } else if (etxt_water.length() == 0) {
                    etxt_water.requestFocus();
                    etxt_water.setError("Please fill this field");
                } else if (etxt_superplasticizer.length() == 0) {
                    etxt_superplasticizer.requestFocus();
                    etxt_superplasticizer.setError("Please fill this field");
                } else if (etxt_coarseAggregate.length() == 0) {
                    etxt_coarseAggregate.requestFocus();
                    etxt_coarseAggregate.setError("Please fill this field");
                } else if (etxt_fineAggregate.length() == 0) {
                    etxt_fineAggregate.requestFocus();
                    etxt_fineAggregate.setError("Please fill this field");
                } else if (etxt_age.length() == 0) {
                    etxt_age.requestFocus();
                    etxt_age.setError("Please fill this field");
                }
                else
                {
                    float[][] concreteArray = new float[1][8];
                    concreteArray[0][0] = ((Float.parseFloat(etxt_cement.getText().toString()) - min[0]) / (max[0] - min[0]));
                    concreteArray[0][1] = ((Float.parseFloat(etxt_blastFurnace.getText().toString()) - min[1]) / (max[1] - min[1]));
                    concreteArray[0][2] = ((Float.parseFloat(etxt_flyAsh.getText().toString()) - min[2]) / (max[2] - min[2]));
                    concreteArray[0][3] = ((Float.parseFloat(etxt_water.getText().toString()) - min[3]) / (max[3] - min[3]));
                    concreteArray[0][4] = ((Float.parseFloat(etxt_superplasticizer.getText().toString()) - min[4]) / (max[4] - min[4]));
                    concreteArray[0][5] = ((Float.parseFloat(etxt_coarseAggregate.getText().toString()) - min[5]) / (max[5] - min[5]));
                    concreteArray[0][6] = ((Float.parseFloat(etxt_fineAggregate.getText().toString()) - min[6]) / (max[6] - min[6]));
                    concreteArray[0][7] = ((Float.parseFloat(etxt_age.getText().toString()) - min[7]) / (max[7] - min[7]));

                    float predictions = doInference(concreteArray);

                    predictions = Math.round(predictions);

                    txt_resultBox.setText("Estimated Concrete compressive strength is " + predictions + " MPa");
                }
            }
        });
    }

    public float doInference(float[][] input)
    {
        float[][] output = new float[1][1];

        interpreter.run(input,output);

        return output[0][0];
    }

    private MappedByteBuffer loadModelFile() throws IOException {
        AssetFileDescriptor assetFileDescriptor = this.getAssets().openFd("Concrete.tflite");
        FileInputStream fileInputStream = new FileInputStream(assetFileDescriptor.getFileDescriptor());
        FileChannel fileChannel = fileInputStream.getChannel();
        long startOffset = assetFileDescriptor.getStartOffset();
        long length = assetFileDescriptor.getLength();
        return fileChannel.map(FileChannel.MapMode.READ_ONLY, startOffset, length);
    }
}