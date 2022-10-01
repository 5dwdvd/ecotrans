package com.example.timtim

import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.budiyev.android.codescanner.AutoFocusMode
import com.budiyev.android.codescanner.CodeScanner
import com.budiyev.android.codescanner.DecodeCallback
import com.budiyev.android.codescanner.ErrorCallback
import com.budiyev.android.codescanner.R
import com.budiyev.android.codescanner.ScanMode
import com.example.timtim.databinding.QrCodeBinding

private const val CAMERA_REQUEST_CODE = 101

private lateinit var binding: QrCodeBinding
private lateinit var codeScanner: CodeScanner


class QRCodeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = QrCodeBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        setUpPermissions()
        codeScanner()
    }

    private fun codeScanner() {
        codeScanner = CodeScanner(this, binding.scannerView)

        codeScanner.apply {
            camera = CodeScanner.CAMERA_BACK
            formats = CodeScanner.ALL_FORMATS

            autoFocusMode = AutoFocusMode.SAFE
            scanMode = ScanMode.CONTINUOUS

            isAutoFocusEnabled = true;
            isFlashEnabled = false;


            decodeCallback = DecodeCallback {
                runOnUiThread {
                    val intent = Intent(this@QRCodeActivity, MemoryActivity::class.java)
                    val stuff = it.text.split(":")
                    intent.putExtra("EventName", stuff[0])
                    intent.putExtra("EventLocation", stuff[1])
                    intent.putExtra("EventDate", stuff[2])
                    intent.putExtra("FunFact", stuff[3])
                    startActivity(intent)
                }
            }

            errorCallback = ErrorCallback {
                runOnUiThread {
                    Log.e("Main", "Camera initialization error: ${it.message}")
                }
            }
        }

        binding.scannerView.setOnClickListener {
            codeScanner.startPreview()
        }
    }

    override fun onResume() {
        super.onResume()
        codeScanner.startPreview()
    }

    override fun onPause() {
        codeScanner.releaseResources()
        super.onPause()
    }

    private fun setUpPermissions() {
        val permission = ContextCompat.checkSelfPermission(
            this,
            android.Manifest.permission.CAMERA
        )

        if (permission != PackageManager.PERMISSION_GRANTED) {
            makeRequest()
        }
    }

    private fun makeRequest() {
        ActivityCompat.requestPermissions(
            this,
            arrayOf(android.Manifest.permission.CAMERA),
            CAMERA_REQUEST_CODE
        )
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            CAMERA_REQUEST_CODE -> {
                if (grantResults.isEmpty() || grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(
                        this,
                        "We need the camera permission",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }
}

