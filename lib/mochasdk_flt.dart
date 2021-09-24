
import 'dart:async';

import 'package:flutter/services.dart';

class MochasdkFlt {
  static const MethodChannel _channel = MethodChannel('mochasdk_flt');

  static Future<String?> get platformVersion async {
    final String? version = await _channel.invokeMethod('getPlatformVersion');
    return version;
  }

  static Future<dynamic> initUser({required String userName,required String phoneNumber,required String appToken, String countryCode = "vi"}) async {
    final String? result = await _channel.invokeMethod('initUser', {
      "userName": userName,
      "phoneNumber": phoneNumber,
      "appToken": appToken,
      "countryCode": countryCode,
    });
    print(result);
  }
}
