import 'dart:convert';

import 'package:flutter/material.dart';
import 'package:mobile/page/rayyan/model/appointmentModel.dart';
import 'package:http/http.dart' as http;

import '../../detail_appointment.dart';

Future<List<AppointmentModel>> fetchAppointments(String jwtToken) async {
  var response = await http.get(
    Uri.parse('http://localhost:8081/api/appointment/all'),
    headers: {
      "Access-Control-Allow-Origin": "*",
      'Authorization': 'Bearer $jwtToken'
    },
  );

  if (response.statusCode != 200) {
    throw Exception('Failed to load appointments');
  }

  // melakukan decode response menjadi bentuk json
  var data = jsonDecode(utf8.decode(response.bodyBytes));

  // melakukan konversi data json menjadi object MyWatchlist
  List<AppointmentModel> listDoctors = [];
  for (var d in data) {
    if (d != null) {
      listDoctors.add(AppointmentModel.fromJson(d));
    }
  }
  return listDoctors;
}

class ListAppointments extends StatelessWidget {
  final String jwtToken;

  const ListAppointments({
    Key? key,
    required this.jwtToken,
  }) : super(key: key);

  @override
  Widget build(BuildContext context) => Scaffold(
        appBar: AppBar(
          backgroundColor: Colors.blue,
          title: const Text("List Appointment"),
          centerTitle: true,
        ),
        body: FutureBuilder(
          future: fetchAppointments(jwtToken),
          builder: (context, AsyncSnapshot<List<AppointmentModel>> snapshot){
            if(snapshot.data == null){
              return const Center(child: CircularProgressIndicator());
            }
            return ListView(
              children: snapshot.data!.map((AppointmentModel appointment){
                return Card(
              child: Column(
                mainAxisSize: MainAxisSize.min,
                children: [
                   ListTile(
                    title: Text(appointment.doctor_name),
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
                                      name: "",
                                      email: "",
                                      jwtToken: jwtToken,
                                      code: appointment.code)));
                        },
                      ),
                    ],
                  )
                ],
              ),
            );
          }).toList()
        );
          },
        )
      );

}
