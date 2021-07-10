package com.example.helloworld

import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView

class SensorActivity : AppCompatActivity() {

    protected var sensorMaxRangeText : TextView? = null
    protected var sensorProximityText : TextView? = null

    protected var sensorPower1Text : TextView? = null
    protected var sensorXText : TextView? = null
    protected var sensorYText : TextView? = null
    protected var sensorZText : TextView? = null

    protected var sensorPower2Text : TextView? = null
    protected var sensorAzimuthText : TextView? = null
    protected var sensorPitchText : TextView? = null
    protected var sensorRollText : TextView? = null

    protected var proximitySensorManager : SensorManager? = null
    protected var accelerometerSensorManager : SensorManager? = null
    protected var orientationSensorManager : SensorManager? = null

    protected var proximitySensorListener : SensorEventListener = object : SensorEventListener{
        override fun onSensorChanged(event: SensorEvent?) {
            val sensor = event?.sensor
            val values = event?.values
            sensorMaxRangeText?.text = "${sensor?.maximumRange}"
            sensorProximityText?.text = "${values?.get(0)}"
        }
        override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {}
    }

    protected var accelerometerSensorListener : SensorEventListener = object : SensorEventListener{
        override fun onSensorChanged(event: SensorEvent?) {
            val sensor = event?.sensor
            val values = event?.values
            sensorPower1Text?.text = "${sensor?.power}"
            sensorXText?.text = "${values?.get(0)}"
            sensorYText?.text = "${values?.get(1)}"
            sensorZText?.text = "${values?.get(2)}"
        }
        override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {}
    }

    protected var orientationSensorListener : SensorEventListener = object : SensorEventListener{
        override fun onSensorChanged(event: SensorEvent?) {
            val sensor = event?.sensor
            val values = event?.values
            sensorPower2Text?.text = "${sensor?.power}"
            sensorAzimuthText?.text = "${values?.get(0)}"
            sensorPitchText?.text = "${values?.get(1)}"
            sensorRollText?.text = "${values?.get(2)}"
        }
        override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {}
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sensor)

        sensorMaxRangeText = findViewById(R.id.sensorMaxRangeText)
        sensorProximityText = findViewById(R.id.sensorProximityText)

        sensorPower1Text = findViewById(R.id.sensorPower1Text)
        sensorXText = findViewById(R.id.sensorXText)
        sensorYText = findViewById(R.id.sensorYText)
        sensorZText = findViewById(R.id.sensorZText)

        sensorPower2Text = findViewById(R.id.sensorPower2Text)
        sensorAzimuthText = findViewById(R.id.sensorAzimuthText)
        sensorPitchText = findViewById(R.id.sensorPitchText)
        sensorRollText = findViewById(R.id.sensorRollText)

        proximitySensorManager = getSystemService(SENSOR_SERVICE) as SensorManager
        accelerometerSensorManager = getSystemService(SENSOR_SERVICE) as SensorManager
        orientationSensorManager = getSystemService(SENSOR_SERVICE) as SensorManager
    }

    override fun onPause() {
        super.onPause()

        proximitySensorManager?.unregisterListener(proximitySensorListener)
        accelerometerSensorManager?.unregisterListener(accelerometerSensorListener)
        orientationSensorManager?.unregisterListener(orientationSensorListener)
    }
    override fun onResume() {
        super.onResume()

        proximitySensorManager?.registerListener(
            proximitySensorListener,
            proximitySensorManager?.getDefaultSensor(Sensor.TYPE_PROXIMITY),
            SensorManager.SENSOR_DELAY_NORMAL
        )

        accelerometerSensorManager?.registerListener(
            accelerometerSensorListener,
            accelerometerSensorManager?.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
            SensorManager.SENSOR_DELAY_NORMAL
        )

        orientationSensorManager?.registerListener(
            orientationSensorListener,
            orientationSensorManager?.getDefaultSensor(Sensor.TYPE_ORIENTATION),
            SensorManager.SENSOR_DELAY_NORMAL
        )
    }
}