import 'dart:html';

import 'package:flutter/material.dart';
import 'package:mobile/widget/button_widget.dart';

class TopUp extends StatefulWidget {
  @override
  State<TopUp> createState() => _TopUpState();
}

class _TopUpState extends State<TopUp> {
  
  @override
  Widget build(BuildContext context) {
    return Scaffold(
        appBar: AppBar(title: Text("Top Up")),
        body: Form(
          child: ListView(padding: EdgeInsets.all(16.0), children: <Widget>[
            Text("Masukkan jumlah nominal"),
            TextFormField(
              decoration: InputDecoration(hintText: "contoh: 10000"),
            ),
            ElevatedButton(onPressed: () {
            }, child: Text("Top Up"))
          ]),
        ));
  }

}
