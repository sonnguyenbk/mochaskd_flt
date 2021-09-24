package com.viettel.flutter.mochasdk_flt

import androidx.annotation.NonNull

import io.flutter.embedding.engine.plugins.FlutterPlugin
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugin.common.MethodChannel.MethodCallHandler
import io.flutter.plugin.common.MethodChannel.Result
import com.viettel.mochasdknew.common.MochaSDKManager
import com.viettel.mochasdknew.common.MochaSDKManager.Listener
import com.viettel.mochasdknew.model.Resource
/** MochasdkFltPlugin */
class MochasdkFltPlugin: FlutterPlugin, MethodCallHandler {
  /// The MethodChannel that will the communication between Flutter and native Android
  ///
  /// This local reference serves to register the plugin with the Flutter Engine and unregister it
  /// when the Flutter Engine is detached from the Activity
  private lateinit var channel : MethodChannel

  override fun onAttachedToEngine(@NonNull flutterPluginBinding: FlutterPlugin.FlutterPluginBinding) {
    channel = MethodChannel(flutterPluginBinding.binaryMessenger, "mochasdk_flt")
    channel.setMethodCallHandler(this)
  }

  override fun onMethodCall(@NonNull call: MethodCall, @NonNull result: Result) {
    when(call.method) {
      "getPlatformVersion" -> {
        result.success("Android ${android.os.Build.VERSION.RELEASE}")
      }
      "initUser" -> {
        initUser(call, result)
      }
      else -> {
        result.notImplemented()
      }
    }
  }

  override fun onDetachedFromEngine(@NonNull binding: FlutterPlugin.FlutterPluginBinding) {
    channel.setMethodCallHandler(null)
  }

  fun initUser(@NonNull call: MethodCall, @NonNull result: Result) {
    val userName: String? = call.argument<String>("userName")
    val phoneNumber: String? = call.argument<String>("phoneNumber")
    val appToken: String? = call.argument<String>("appToken")
    val countryCode: String? = call.argument<String>("countryCode")

    if (userName != null && phoneNumber != null && appToken != null && countryCode != null) {
      try {
        MochaSdkManager.initUser(userName, phoneNumber, appToken, countryCode)
        result.success("Init User Success")
      }catch (err: Exception) {
        result.error("DEBUG_MOCHA_SDK", "Error in initUser", null)
      }
    } else {
      result.error("DEBUG_MOCHA_SDK", "Null value in initUser", null)
    }
  }
}
