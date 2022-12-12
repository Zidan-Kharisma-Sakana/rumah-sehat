// ignore_for_file: library_private_types_in_public_api, unused_field

import 'dart:convert';

import 'package:flutter/material.dart';
// import 'package:';
import 'package:http/http.dart' as http;
import 'package:mobile/provider/auth.dart';
import 'package:provider/provider.dart';
import 'package:shared_preferences/shared_preferences.dart';

class LoginPage extends StatefulWidget {
  const LoginPage({super.key});

  @override
  _LoginPageState createState() => _LoginPageState();
}

class _LoginPageState extends State<LoginPage> {
  final _formKey = GlobalKey<FormState>();
  final TextEditingController _pass = TextEditingController();
  final Future<SharedPreferences> _prefs = SharedPreferences.getInstance();

  late String _name;
  late String _password;
  late Future<String> _email;
  late Future<String> _username;
  late Future<String> _jwtToken;
  late String _message;
  late bool _success;

  @override
  void initState() {
    super.initState();
    setState(() {
      _message = "";
      _success = false;
    });
  }


  Widget _buildUserName() {
    return TextFormField(
      decoration: InputDecoration(
        hintText: "contoh: Susilo Bambang",
        labelText: "Nama Lengkap",
        icon: const Icon(Icons.people),
        border: OutlineInputBorder(borderRadius: BorderRadius.circular(5.0)),
      ),
      validator: (value) {
        if (value!.isEmpty) {
          return 'Nama tidak boleh kosong';
        }
        return null;
      },
      onSaved: (value) {
        _name = value!;
      },
    );
  }

  Widget _buildPass() {
    return TextFormField(
      controller: _pass,
      obscureText: true,
      decoration: InputDecoration(
        labelText: "Password",
        icon: const Icon(Icons.security),
        border: OutlineInputBorder(borderRadius: BorderRadius.circular(5.0)),
      ),
      validator: (value) {
        if (value!.isEmpty) {
          return 'Password tidak boleh kosong';
        }
        return null;
      },
      onSaved: (value) {
        _password = value!;
      },
    );
  }

  @override
  Widget build(BuildContext context) {
    final request = context.watch<Authentication>();
    return Scaffold(
      appBar: AppBar(
        title: const Text("Rumah Sehat"),
      ),
      body: Form(
        key: _formKey,
        child: SingleChildScrollView(
          child: Container(
            padding: const EdgeInsets.all(20.0),
            child: Column(
              children: [
                _message != ''
                    ? AlertDialog(
                        title: const Text('Notification:'),
                        content: Text(_message),
                        actions: <Widget>[
                          TextButton(
                            onPressed: () {
                              if (_success) {
                                Navigator.pop(context, 'OK');
                              }
                            },
                            child: _success
                                ? const Text("OK")
                                : const SizedBox(
                                    height: 0,
                                  ),
                          ),
                        ],
                      )
                    : const Padding(
                        padding: EdgeInsets.all(8.0),
                        child: Text(
                          "Login",
                          style: TextStyle(fontSize: 30),
                        ),
                      ),
                Padding(
                    padding: const EdgeInsets.all(8.0),
                    child: _buildUserName()),
                Padding(
                    padding: const EdgeInsets.all(8.0), child: _buildPass()),
                ElevatedButton(
                  child: const Text(
                    "Submit",
                  ),
                  onPressed: () async {
                    if (_formKey.currentState!.validate()) {
                      _formKey.currentState!.save();
                      final response = await request.login("/api/auth/login", _name, _password);
                      print(jsonDecode(response.body));
                      if (response.statusCode == 200) {
                        setState(() {
                          _success = true;
                          _message = "Berhasil Masuk";
                        });
                      } else {
                        setState(() {
                          _message =
                              "Tidak berhasil Masuk, pastikan username dan password benar";
                        });
                      }
                    }
                  },
                ),
              ],
            ),
          ),
        ),
      ),
    );
  }
}
