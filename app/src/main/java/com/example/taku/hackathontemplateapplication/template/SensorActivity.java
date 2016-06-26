package com.example.taku.hackathontemplateapplication.template;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.taku.hackathontemplateapplication.R;

import java.util.List;

/**
 * センサー利用のテンプレート
 */
public class SensorActivity extends AppCompatActivity implements SensorEventListener {

    SensorManager sensorManager;

    float[] inR = new float[16];
    float[] outR = new float[16];
    float[] I = new float[16];

    float[] orientationValues = new float[3];
    float[] magneticValues = null;
    float[] accelerometerValues = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.template_sensor_orientation);

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);


    }

    @Override
    protected void onResume() {
        super.onResume();
        List<Sensor> sensors = sensorManager.getSensorList(Sensor.TYPE_ALL);
        for (Sensor sensor : sensors) {
            if (sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD) {
                sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL);
            }

            if (sensor.getType() == Sensor.TYPE_LINEAR_ACCELERATION) {
                sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL);
            }
            if (sensor.getType() == Sensor.TYPE_GYROSCOPE) {
                sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL);
            }
            if (sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
                sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL);
            }
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        switch (event.sensor.getType()) {
            case Sensor.TYPE_MAGNETIC_FIELD:
                magneticValues = event.values.clone();
                break;
            case Sensor.TYPE_LINEAR_ACCELERATION:
                accelerometerValues = event.values.clone();
                break;
            case Sensor.TYPE_ACCELEROMETER:
                accelerometerValues = event.values.clone();
                break;
            case Sensor.TYPE_GYROSCOPE:
                accelerometerValues = event.values.clone();
                break;
        }

        if (magneticValues != null && accelerometerValues != null) {
            SensorManager.getRotationMatrix(inR, I, accelerometerValues, magneticValues);
            // Activityの表示が縦固定の場合はこの設定
            SensorManager.remapCoordinateSystem(inR, SensorManager.AXIS_X, SensorManager.AXIS_Z, outR);
            SensorManager.getOrientation(outR, orientationValues);

            int yaw = radianToDegree(orientationValues[0]);
            int pitch = radianToDegree(orientationValues[0]);
            int roll = radianToDegree(orientationValues[0]);

            Log.d("Orientation", yaw + ", " + pitch + ", " + roll);
        }
    }

    int radianToDegree(float rad) {
        return (int) Math.floor(Math.toDegrees(rad));
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }
}
