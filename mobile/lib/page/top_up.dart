import 'package:flutter/material.dart';
import 'package:mobile/widget/button_widget.dart';

class TopUp extends StatelessWidget {
  static const routeName = 'top_up';
  final String name;
  final String jwtToken;
  final String email;

  const TopUp({
    Key? key,
    required this.name,
    required this.email,
    required this.jwtToken,
  }) : super(key: key);

  @override
  Widget build(BuildContext context) => Scaffold(
        appBar: AppBar(
          backgroundColor: Colors.blue,
          title: const Text("Top Up Saldo"),
          centerTitle: true,
        ),
        body: Builder(
          builder: (context) => Container(
            alignment: Alignment.center,
            padding: const EdgeInsets.symmetric(horizontal: 32),
            child: ButtonWidget(
              icon: Icons.attach_money,
              text: 'Konfirmasi',
              onClicked: () {
                
              },
            ),
          ),
        ),
      );
}
