import 'package:http/http.dart' as http;
import 'dart:convert';
import 'package:shared_preferences/shared_preferences.dart';

class Authentication {
  // late SharedPreferences local;
  final Future<SharedPreferences> _prefs = SharedPreferences.getInstance();

  final _BASEURL = "http://localhost:8081";

  String jwtToken = "";
  String username = "";
  String email = "";
  bool loggedIn = false;
  bool initialized = false;

  Future init() async {
    SharedPreferences local = await _prefs;
    jwtToken = local.getString("jwtToken") ?? '';
    username = local.getString("username") ?? '';
    email = local.getString("email") ?? '';
    if (jwtToken == '') {
      loggedIn = false;
    } else {
      loggedIn = true;
    }
  }

  Future<http.Response> login(String url, dynamic data) async {
    http.Response response = await http.post(Uri.parse(_BASEURL + url),
        headers: <String, String>{
          'Content-Type': 'application/json;charset=UTF-8',
        },
        body: jsonEncode(data));
    final Map parsed = json.decode(response.body);
    if (response.statusCode == 200) {
      jwtToken = parsed["jwtToken"];
      email = parsed["email"];
      username = parsed["name"];
      loggedIn = true;
      _loginUser(username, email, jwtToken);
    } else {
      loggedIn = false;
    }
    return response;
  }

  Future<http.Response> get(String url) async {
    http.Response response =
        await http.get(Uri.parse(url), headers: <String, String>{
      'Content-Type': 'application/json;charset=UTF-8',
      'Authorization': 'Bearer $jwtToken'
    });
    return response;
  }

  Future<http.Response> post(String url, dynamic data) async {
    http.Response response =
        await http.post(Uri.parse(url), body: data, headers: <String, String>{
      'Content-Type': 'application/json;charset=UTF-8',
      'Authorization': 'Bearer $jwtToken'
    });
    return response;
  }

  Future<void> logout(String url) async {
    jwtToken = "";
    username = "";
    email = "";
    loggedIn = false;
  }

  Future<void> _loginUser(
      String username, String email, String jwtToken) async {
    SharedPreferences local = await _prefs;
    local.setString('email', email);
    local.setString('username', username);
    local.setString('jwtToken', jwtToken);
  }
}
