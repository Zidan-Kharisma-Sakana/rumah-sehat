// ignore_for_file: library_private_types_in_public_api

import 'dart:convert';

import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
// import 'package:';
import 'package:http/http.dart' as http;

class RegisterPage extends StatefulWidget {
  @override
  _RegisterPageState createState() => _RegisterPageState();
}

class _RegisterPageState extends State<RegisterPage> {
  final _formKey = GlobalKey<FormState>();
  final TextEditingController _pass = TextEditingController();
  final TextEditingController _confirmPass = TextEditingController();
  late String _username;
  late int _age;
  late String _name;
  late String _email;
  late String _password;
  late String _message;
  late bool _success;

  @override
  void initState() {
    super.initState();
    setState(() {
      _message = '';
      _success = false;
    });
  }

  Widget _buildUsername() {
    return TextFormField(
      decoration: InputDecoration(
        hintText: "Contoh: Shio-chan",
        labelText: "Username Pasien",
        icon: const Icon(Icons.class_rounded),
        border:
            OutlineInputBorder(borderRadius: BorderRadius.circular(5.0)),
      ),
      validator: (value) {
        if (value!.isEmpty) {
          return 'Username tidak boleh kosong';
        }
        return null;
      },
      onSaved: (value) {
        _username = value!;
      },
    );
  }

    Widget _buildAge() {
    return TextFormField(
      decoration: InputDecoration(
        labelText: "Usia Pasien",
        icon: const Icon(Icons.hourglass_bottom),
        border:
            OutlineInputBorder(borderRadius: BorderRadius.circular(5.0)),
      ),
      keyboardType: TextInputType.number,
      inputFormatters: <TextInputFormatter>[
    FilteringTextInputFormatter.digitsOnly
    ],
      validator: (value) {
        if (value!.isEmpty) {
          return 'Usia tidak boleh kosong';
        }
        return null;
      },
      onSaved: (value) {
        _age = int.parse(value!);
      },
    );
  }

  Widget _buildName() {
    return TextFormField(
      decoration: InputDecoration(
        hintText: "contoh: Shioriko Mifune",
        labelText: "Nama Lengkap Pasien",
        icon: const Icon(Icons.people),
        border:
            OutlineInputBorder(borderRadius: BorderRadius.circular(5.0)),
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

  Widget _buildEmail() {
    return TextFormField(
      decoration: InputDecoration(
        hintText: "Contoh: ShiorikoMifune@Nijigasaki.com",
        labelText: "Email",
        icon: const Icon(Icons.mail),
        border:
            OutlineInputBorder(borderRadius: BorderRadius.circular(5.0)),
      ),
      validator: (value) {
        if (value!.isEmpty) return 'Email is Required';
        if (!RegExp(
                r"[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?")
            .hasMatch(value)) {
          return 'Please enter a valid email Address';
        }
        return null;
      },
      onSaved: (value) {
        _email = value!;
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
        border:
            OutlineInputBorder(borderRadius: BorderRadius.circular(5.0)),
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

  Widget _buildConfirmPass() {
    return TextFormField(
      controller: _confirmPass,
      obscureText: true,
      decoration: InputDecoration(
        labelText: "Konfirmasi Password",
        icon: const Icon(Icons.security),
        border:
            OutlineInputBorder(borderRadius: BorderRadius.circular(5.0)),
      ),
      validator: (value) {
        if (value!.isEmpty) return 'Konfirmasi Password tidak boleh kosong';
        if (value != _pass.text) return 'Not Match';
        return null;
      },
    );
  }

  @override
  Widget build(BuildContext context) {
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
                        title: const Text('Perhatian, pengguna'),
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
                          "Registrasi Pasien Baru",
                          style: TextStyle(fontSize: 30),
                        ),
                      ),
                Padding(
                    padding: const EdgeInsets.all(8.0),
                    child: _buildUsername()),
                Padding(
                    padding: const EdgeInsets.all(8.0), child: _buildName()),
                Padding(
                    padding: const EdgeInsets.all(8.0), child: _buildEmail()),
                Padding(
                    padding: const EdgeInsets.all(8.0), child: _buildAge()),
                Padding(
                    padding: const EdgeInsets.all(8.0), child: _buildPass()),
                Padding(
                    padding: const EdgeInsets.all(8.0),
                    child: _buildConfirmPass()),
                ElevatedButton(
                  child: const Text(
                    "Submit",
                    style: TextStyle(color: Colors.white),
                  ),
                  onPressed: () async {
                    if (_formKey.currentState!.validate()) {
                      _formKey.currentState!.save();
                      final response = await http.post(
                          Uri.parse(
                              "https://apap-059.cs.ui.ac.id/api/auth/signup"),
                          headers: <String, String>{
                            'Content-Type': 'application/json;charset=UTF-8',
                          },
                          body: jsonEncode(<String, Object>{
                            'name': _name,
                            'email': _email,
                            'username': _username,
                            'password': _password,
                            'age': _age,
                          }));
                      if (response.statusCode == 200) {
                        setState(() {
                          final Map parsed = json.decode(response.body);
                          _success = true;
                          _message = "Berhasil Membuat Akun";
                        });
                      }else{
                      setState(() {
                        _message = "Tidak berhasil Membuat Akun, ada akun dengan username atau emai yang sama";
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
