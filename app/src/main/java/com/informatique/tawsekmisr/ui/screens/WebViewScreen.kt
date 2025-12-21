package com.informatique.tawsekmisr.ui.screens

import android.graphics.Bitmap
import android.webkit.WebResourceError
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.automirrored.rounded.ArrowForward
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material.icons.rounded.Refresh
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SegmentedButtonDefaults.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController
import com.informatique.tawsekmisr.R
import com.informatique.tawsekmisr.ui.components.localizedApp
import com.informatique.tawsekmisr.ui.theme.LocalExtraColors

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WebViewScreen(
    navController: NavController,
    url: String,
    title: String? = null
) {
    val extraColors = LocalExtraColors.current

    var webView by remember { mutableStateOf<WebView?>(null) }
    var isLoading by remember { mutableStateOf(true) }
    var loadingProgress by remember { mutableFloatStateOf(0f) }
    var canGoBack by remember { mutableStateOf(false) }
    var canGoForward by remember { mutableStateOf(false) }
    var pageTitle by remember { mutableStateOf(title ?: "") }
    var hasError by remember { mutableStateOf(false) }
    var currentUrl by remember { mutableStateOf(url) }

    // ✅ Permission request state for camera/audio
    var permissionCallback by remember { mutableStateOf<android.webkit.PermissionRequest?>(null) }

    // ✅ Permission launcher for camera and audio
    val permissionLauncher = androidx.activity.compose.rememberLauncherForActivityResult(
        androidx.activity.result.contract.ActivityResultContracts.RequestMultiplePermissions()
    ) { permissions ->
        val allGranted = permissions.values.all { it }
        if (allGranted) {
            permissionCallback?.grant(permissionCallback?.resources)
        } else {
            permissionCallback?.deny()
        }
        permissionCallback = null
    }

    // Get localized strings ONCE in composable context
    val loadingText = localizedApp(R.string.webview_loading)
    val errorTitle = localizedApp(R.string.webview_error_title)
    val errorMessage = localizedApp(R.string.webview_error_message)
    val retryText = localizedApp(R.string.webview_retry)

    // Set initial title if empty
    if (pageTitle.isEmpty()) {
        pageTitle = title ?: loadingText
    }

    // ✅ Helper function to update navigation state
    fun updateNavigationState(view: WebView?) {
        canGoBack = view?.canGoBack() ?: false
        canGoForward = view?.canGoForward() ?: false
    }

    // ✅ Properly clean up WebView when leaving screen
    DisposableEffect(Unit) {
        onDispose {
            webView?.apply {
                try {
                    stopLoading()
                    loadUrl("about:blank")
                    clearHistory()
                    clearCache(true)
                    removeAllViews()
                    destroy()
                } catch (_: Exception) {
                    // Ignore
                }
            }
            webView = null
        }
    }

    Scaffold(
        topBar = {
            Column {
                WebViewTopBar(
                    navController = navController,
                    title = pageTitle,
                    canGoBack = canGoBack,
                    canGoForward = canGoForward,
                    onBackClick = {
                        if (canGoBack) {
                            webView?.goBack()
                        }
                    },
                    onForwardClick = {
                        webView?.goForward()
                    },
                    onRefreshClick = {
                        webView?.reload()
                    }
                )

                // Loading Progress Bar under TopBar
                if (isLoading) {
                    LinearProgressIndicator(
                        progress = { loadingProgress / 100f },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(3.dp),
                        color = extraColors.textBlue,
                        trackColor = extraColors.textBlue.copy(alpha = 0.3f)
                    )
                } else {
                    // Empty space to prevent layout shift
                    Spacer(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(3.dp)
                    )
                }
            }
        }
    ) { paddingValues ->
        // WebView content
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(extraColors.background)
                .padding(paddingValues)
        ) {
            // WebView
            AndroidView(
                factory = { context ->
                    WebView(context).apply {
                        settings.apply {
                            // ✅ Enable JavaScript and all web features
                            javaScriptEnabled = true
                            javaScriptCanOpenWindowsAutomatically = true

                            // ✅ Enable storage
                            domStorageEnabled = true

                            // ✅ Enable viewport and zoom
                            loadWithOverviewMode = true
                            useWideViewPort = true
                            builtInZoomControls = false
                            setSupportZoom(true)

                            // ✅ Enable file and content access
                            allowFileAccess = true
                            allowContentAccess = true

                            // ✅ Enable mixed content (HTTP + HTTPS) - IMPORTANT for rern.gov.eg forms
                            mixedContentMode = android.webkit.WebSettings.MIXED_CONTENT_ALWAYS_ALLOW

                            // ✅ Enable caching for better performance and back/forward navigation
                            cacheMode = android.webkit.WebSettings.LOAD_DEFAULT

                            // ✅ Enable plugins and media
                            mediaPlaybackRequiresUserGesture = false

                            // ✅ Enable geolocation
                            setGeolocationEnabled(true)

                            // ✅ Disable safe browsing to prevent blocking mixed content
                            safeBrowsingEnabled = false

                            // ✅ Enable HTML5 features
                            loadsImagesAutomatically = true
                            blockNetworkImage = false
                            blockNetworkLoads = false
                        }

                        // ✅ Enable touch handling
                        isFocusable = true
                        isFocusableInTouchMode = true
                        isClickable = true

                        webViewClient = object : WebViewClient() {
                            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                                super.onPageStarted(view, url, favicon)
                                isLoading = true
                                hasError = false
                                loadingProgress = 0f
                                currentUrl = url ?: ""
                                // ✅ Update navigation state immediately when page starts
                                updateNavigationState(view)
                            }

                            override fun onPageFinished(view: WebView?, url: String?) {
                                super.onPageFinished(view, url)
                                isLoading = false
                                loadingProgress = 1f
                                // ✅ Update navigation state after page loads
                                updateNavigationState(view)
                                pageTitle = view?.title ?: title ?: ""
                                currentUrl = url ?: ""
                            }

                            override fun onReceivedError(
                                view: WebView?,
                                request: WebResourceRequest?,
                                error: WebResourceError?
                            ) {
                                super.onReceivedError(view, request, error)
                                // Only show error for main frame errors, ignore resource errors
                                if (request?.isForMainFrame == true) {
                                    hasError = true
                                    isLoading = false
                                }
                            }

                            // ✅ Allow all URL navigation - return false to let WebView handle it
                            override fun shouldOverrideUrlLoading(
                                view: WebView?,
                                request: WebResourceRequest?
                            ): Boolean {
                                // Return false to let WebView handle all URLs internally
                                // This ensures proper history tracking
                                return false
                            }

                            override fun doUpdateVisitedHistory(view: WebView?, url: String?, isReload: Boolean) {
                                super.doUpdateVisitedHistory(view, url, isReload)
                                // ✅ Update navigation state when history changes
                                updateNavigationState(view)
                            }
                        }

                        webChromeClient = object : android.webkit.WebChromeClient() {
                            override fun onProgressChanged(view: WebView?, newProgress: Int) {
                                super.onProgressChanged(view, newProgress)
                                loadingProgress = newProgress / 100f
                                isLoading = newProgress < 100
                                // ✅ Update navigation state during loading
                                if (newProgress > 30) {
                                    updateNavigationState(view)
                                }
                            }

                            override fun onReceivedTitle(view: WebView?, title: String?) {
                                super.onReceivedTitle(view, title)
                                if (!title.isNullOrBlank()) {
                                    pageTitle = title
                                }
                            }

                            // ✅ Enable geolocation permission
                            override fun onGeolocationPermissionsShowPrompt(
                                origin: String?,
                                callback: android.webkit.GeolocationPermissions.Callback?
                            ) {
                                callback?.invoke(origin, true, false)
                            }

                            // ✅ Handle console messages (for debugging)
                            override fun onConsoleMessage(consoleMessage: android.webkit.ConsoleMessage?): Boolean {
                                // Suppress mixed content warnings to reduce log spam
                                return true
                            }

                            // ✅ Request permissions for camera and audio
                            override fun onPermissionRequest(request: android.webkit.PermissionRequest?) {
                                super.onPermissionRequest(request)
                                permissionCallback = request
                                // Show permission dialog
                                permissionLauncher.launch(
                                    arrayOf(
                                        android.Manifest.permission.CAMERA,
                                        android.Manifest.permission.RECORD_AUDIO
                                    )
                                )
                            }
                        }

                        loadUrl(url)
                        webView = this
                    }
                },
                modifier = Modifier.fillMaxSize(),
                update = { view ->
                    // ✅ Update navigation state whenever the view updates
                    updateNavigationState(view)
                }
            )

            // Error State
            if (hasError) {
                Surface(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(extraColors.background),
                    color = Color.White
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(32.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Icon(
                            imageVector = Icons.Rounded.Refresh,
                            contentDescription = null,
                            tint = Color.Gray,
                            modifier = Modifier.size(72.dp)
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(
                            text = errorTitle,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = errorMessage,
                            fontSize = 14.sp,
                            color = Color.Gray,
                            textAlign = androidx.compose.ui.text.style.TextAlign.Center
                        )
                        Spacer(modifier = Modifier.height(24.dp))
                        Button(
                            onClick = {
                                webView?.reload()
                                hasError = false
                            },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color(0xFF6EB3A6)
                            ),
                            shape = CircleShape
                        ) {
                            Icon(
                                imageVector = Icons.Rounded.Refresh,
                                contentDescription = null,
                                modifier = Modifier.size(20.dp)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(retryText)
                        }
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WebViewTopBar(
    navController: NavController,
    title: String,
    canGoBack: Boolean,
    canGoForward: Boolean,
    onBackClick: () -> Unit,
    onForwardClick: () -> Unit,
    onRefreshClick: () -> Unit
) {

    val backContentDesc = localizedApp(R.string.webview_back)
    val refreshContentDesc = localizedApp(R.string.webview_refresh)
    val forwardContentDesc = localizedApp(R.string.webview_forward)

    TopAppBar(
        title = {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = title,
                maxLines = 1,
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 16.sp
                ),
                color = LocalExtraColors.current.textBlue,
                textAlign = TextAlign.Center
            )
        },
        navigationIcon = {
            IconButton(
                onClick = { navController.navigateUp() },
                modifier = Modifier
                    .padding(8.dp)
                    .clip(CircleShape)
                    .background(LocalExtraColors.current.iconLightBackground)
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = backContentDesc,
                    tint = LocalExtraColors.current.white
                )
            }
        },
        actions = {
            Row(
                modifier = Modifier
                    .clip(CircleShape)
                    .background(LocalExtraColors.current.iconLightBackground)
                    .padding(horizontal = 4.dp),
                horizontalArrangement = Arrangement.spacedBy(4.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Previous Button
                IconButton(
                    onClick = onBackClick,
                    enabled = canGoBack,
                    modifier = Modifier.size(40.dp)
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = backContentDesc,
                        tint = if (canGoBack) LocalExtraColors.current.white else Color.Gray
                    )
                }

                // Forward Button
                IconButton(
                    onClick = onForwardClick,
                    enabled = canGoForward,
                    modifier = Modifier.size(40.dp)
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                        contentDescription = forwardContentDesc,
                        tint = if (canGoForward) LocalExtraColors.current.white else Color.Gray
                    )
                }

                // Refresh Button
                IconButton(
                    onClick = onRefreshClick,
                    modifier = Modifier.size(40.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Refresh,
                        contentDescription = refreshContentDesc,
                        tint = LocalExtraColors.current.white
                    )
                }
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = LocalExtraColors.current.background,
        )
    )
}

