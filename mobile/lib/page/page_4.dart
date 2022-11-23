import 'package:flutter/material.dart';

class Page4 extends StatelessWidget {
  final String name;
  final String jwtToken;
  final String email;

  const Page4({
    Key? key,
    required this.name,
    required this.email,
    required this.jwtToken,
  }) : super(key: key);

  @override
  Widget build(BuildContext context) => Scaffold(
        appBar: AppBar(
          backgroundColor: Colors.blue,
          title: Text("Schedule"),
          centerTitle: true,
        ),
      );
}
