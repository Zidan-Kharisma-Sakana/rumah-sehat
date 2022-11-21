import 'package:flutter/material.dart';

class Page5
 extends StatelessWidget {
  final String name;
  final String jwtToken;
  final String email;

  const Page5
  ({
    Key? key,
    required this.name,
    required this.email,
    required this.jwtToken,
  }) : super(key: key);

  @override
  Widget build(BuildContext context) => Scaffold(
        appBar: AppBar(
          backgroundColor: Colors.blue,
          title: Text("To Do"),
          centerTitle: true,
        ),
      );
}
