package com.asteriatech.mobile.presentation.composables

import android.annotation.SuppressLint
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView

@SuppressLint("SetJavaScriptEnabled")
@Composable
fun VideoPlayerScreen(url: String, modifier: Modifier = Modifier) {
    AndroidView(
        factory = { context ->
            WebView(context).apply {
                settings.javaScriptEnabled = true
                settings.domStorageEnabled = true
                settings.setSupportZoom(true) // Enable zoom controls
                settings.builtInZoomControls = true // Show zoom controls
                settings.displayZoomControls = false // Hide default zoom controls
                webViewClient = WebViewClient()

                // Load the URL
                loadUrl(url)

                // Inject JavaScript to manipulate the DOM and scale the page
                evaluateJavascript(
                    """
                    (function() {
                        // Create meta viewport tag
                        var metaViewport = document.createElement('meta');
                        metaViewport.name = 'viewport';
                        metaViewport.content = 'width=device-width, initial-scale=0.5, maximum-scale=2.0, user-scalable=yes';
                        document.getElementsByTagName('head')[0].appendChild(metaViewport);

                        // Apply scaling transformation to the entire document
                        var scale = 0.5; // Adjust the scale factor as needed
                        document.body.style.transform = 'scale(' + scale + ')';
                        document.body.style.transformOrigin = '0 0'; // Set the origin of scaling to top-left corner
                        document.body.style.width = (100 / scale) + '%'; // Adjust the width to ensure scaling is correct
                        document.body.style.height = (100 / scale) + '%'; // Adjust the height to ensure scaling is correct
                        document.body.style.overflow = 'hidden'; // Prevent scrollbars
                    })();
                    """.trimIndent(), null
                )
            }
        },
        modifier = modifier.fillMaxSize()
    )
}

