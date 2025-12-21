package com.informatique.tawsekmisr.ui.screens

import android.graphics.Bitmap
import android.webkit.SslErrorHandler
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.net.toUri
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.informatique.tawsekmisr.R
import com.informatique.tawsekmisr.ui.viewmodels.LoginWebViewModel
import com.informatique.tawsekmisr.ui.viewmodels.NavigationEvent

/**
 * Modern Jetpack Compose implementation of LoginWebView
 * Handles OAuth2 authentication via WebView with SSL error handling
 *
 * @param navController Navigation controller for screen navigation
 * @param reserveOrReservations Navigation parameter (0 = Reservations, 1 = ReservationWithQueue)
 * @param office Office data for reservation (optional)
 * @param onAuthComplete Callback when authentication is complete with auth code
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginWebViewScreen(
    navController: NavController,
    reserveOrReservations: Int = 0,
    office: Any? = null,
    onAuthComplete: (String) -> Unit = {},
    viewModel: LoginWebViewModel = hiltViewModel()
) {
    // State
    var webView by remember { mutableStateOf<WebView?>(null) }
    var isLoading by remember { mutableStateOf(true) }
    var showSslDialog by remember { mutableStateOf(false) }
    var sslErrorHandler by remember { mutableStateOf<SslErrorHandler?>(null) }

    // ViewModel states
    val authCode by viewModel.authCode.collectAsStateWithLifecycle()
    val navigationEvent by viewModel.navigationEvent.collectAsStateWithLifecycle()
    val isViewModelLoading by viewModel.isLoading.collectAsStateWithLifecycle()
    val error by viewModel.error.collectAsStateWithLifecycle()

    // OAuth2 URL
    val authUrl = remember {
        "https://sso.di.gov.eg/oauth2/authorize?response_type=code&scope=openid" +
                "&client_id=xMuE3zvKRXW4hPL0B2mzK5egX3Ya" +
                "&redirect_uri=https://ms.tawseek.gov.eg/CityStar/AOAS_RestServiceMobile_v1/api/ReservationMobile/getOrgUnitStatus"
    }

    // Handle navigation events
    LaunchedEffect(navigationEvent) {
        navigationEvent?.let { event ->
            when (event) {
                is NavigationEvent.ToReservations -> {
                    // Navigate to reservations screen
                    navController.popBackStack()
                    // Add navigation logic here based on your navigation graph
                }
                is NavigationEvent.ToReservationWithQueue -> {
                    // Navigate to reservation with queue screen
                    navController.popBackStack()
                    // Add navigation logic here based on your navigation graph
                }
            }
            viewModel.clearNavigationEvent()
        }
    }

    // Handle auth code completion
    LaunchedEffect(authCode) {
        authCode?.let { code ->
            onAuthComplete(code)
            viewModel.handleNavigation(reserveOrReservations, office)
        }
    }

    // Cleanup on dispose
    DisposableEffect(Unit) {
        onDispose {
            webView?.apply {
                clearHistory()
                clearFormData()
                clearCache(true)
                android.webkit.CookieManager.getInstance().removeAllCookies(null)
                destroy()
            }
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.login)) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = stringResource(R.string.back)
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary,
                    navigationIconContentColor = MaterialTheme.colorScheme.onPrimary
                )
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            Column(modifier = Modifier.fillMaxSize()) {
                // Loading indicator
                if (isLoading) {
                    LinearProgressIndicator(
                        modifier = Modifier.fillMaxWidth()
                    )
                }

                // WebView
                @Suppress("SetJavaScriptEnabled")
                AndroidView(
                    modifier = Modifier
                        .fillMaxSize()
                        .weight(1f),
                    factory = { context ->
                        WebView(context).apply {
                            webView = this

                            // WebView settings
                            settings.apply {
                                defaultTextEncodingName = "utf-8"
                                javaScriptEnabled = true
                                javaScriptCanOpenWindowsAutomatically = true
                                allowFileAccess = false
                                builtInZoomControls = true
                                displayZoomControls = false
                                domStorageEnabled = true
                                cacheMode = WebSettings.LOAD_CACHE_ELSE_NETWORK
                            }

                            // Custom WebViewClient
                            webViewClient = object : WebViewClient() {
                                @Deprecated("Deprecated in Java")
                                @Suppress("DEPRECATION")
                                override fun shouldOverrideUrlLoading(
                                    view: WebView?,
                                    url: String
                                ): Boolean {
                                    view?.loadUrl(url)
                                    return false
                                }

                                override fun onReceivedSslError(
                                    view: WebView?,
                                    handler: SslErrorHandler?,
                                    error: android.net.http.SslError?
                                ) {
                                    sslErrorHandler = handler
                                    showSslDialog = true
                                }

                                override fun onPageStarted(
                                    view: WebView?,
                                    url: String?,
                                    favicon: Bitmap?
                                ) {
                                    super.onPageStarted(view, url, favicon)
                                    isLoading = true

                                    // Check if URL contains auth code
                                    url?.let { currentUrl ->
                                        if (currentUrl.contains("code")) {
                                            val uri = currentUrl.toUri()
                                            val code = uri.getQueryParameter("code")

                                            if (!code.isNullOrEmpty()) {
                                                viewModel.saveAuthCode(code)
                                            }
                                        }
                                    }
                                }

                                override fun onPageFinished(view: WebView?, url: String?) {
                                    super.onPageFinished(view, url)
                                    isLoading = false
                                    view?.apply {
                                        clearHistory()
                                        clearFormData()
                                        clearCache(true)
                                    }
                                }
                            }

                            // Clear cookies and load URL
                            android.webkit.CookieManager.getInstance().removeAllCookies(null)
                            invalidate()
                            loadUrl(authUrl)
                        }
                    },
                    update = { _ ->
                        // Update if needed
                    }
                )
            }

            // Show loading overlay if ViewModel is processing
            if (isViewModelLoading) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }
        }
    }

    // SSL Error Dialog
    if (showSslDialog) {
        AlertDialog(
            onDismissRequest = {
                sslErrorHandler?.cancel()
                showSslDialog = false
                sslErrorHandler = null
            },
            title = {
                Text(stringResource(R.string.ssl_error_title))
            },
            text = {
                Text(stringResource(R.string.ssl_error_message))
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        sslErrorHandler?.proceed()
                        showSslDialog = false
                        sslErrorHandler = null
                    }
                ) {
                    Text(stringResource(R.string.ok))
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        sslErrorHandler?.cancel()
                        showSslDialog = false
                        sslErrorHandler = null
                    }
                ) {
                    Text(stringResource(R.string.close))
                }
            }
        )
    }

    // Error Dialog
    error?.let { errorMessage ->
        AlertDialog(
            onDismissRequest = { viewModel.clearError() },
            title = { Text(stringResource(R.string.error)) },
            text = { Text(errorMessage) },
            confirmButton = {
                TextButton(onClick = { viewModel.clearError() }) {
                    Text(stringResource(R.string.ok))
                }
            }
        )
    }
}
