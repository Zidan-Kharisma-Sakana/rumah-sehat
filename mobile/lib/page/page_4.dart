import 'package:flutter/material.dart';
import 'package:mobile/detail_appointment.dart';

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
          title: Text("List Appointment"),
          centerTitle: true,
        ),
        body: ListView(
          children: [
            Card(
              child: Column(
                mainAxisSize: MainAxisSize.min,
                children: [
                  ListTile(
                    title: Text("Appointment 1"),
                  ),
                  Row(
                    children: [
                      TextButton(
                        child: const Text('Detail Appointment'),
                        onPressed: () {
                          Navigator.push(
                              context,
                              MaterialPageRoute(
                                  builder: (context) => DetailAppointment(
                                      name: name,
                                      email: email,
                                      jwtToken: jwtToken,
                                      code: "APT-1")));
                        },
                      ),
                    ],
                  )
                ],
              ),
            ),
            Card(
              child: Column(
                mainAxisSize: MainAxisSize.min,
                children: [
                  ListTile(
                    title: Text("Appointment 1"),
                  ),
                  Row(
                    children: [
                      TextButton(
                        child: const Text('Detail Appointment'),
                        onPressed: () {
                          Navigator.push(
                              context,
                              MaterialPageRoute(
                                  builder: (context) => DetailAppointment(
                                      name: name,
                                      email: email,
                                      jwtToken: jwtToken,
                                      code: "APT-2")));
                        },
                      ),
                    ],
                  )
                ],
              ),
            )
          ],
        ),
      );
}
