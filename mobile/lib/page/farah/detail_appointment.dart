// ignore_for_file: library_private_types_in_public_api

import 'dart:convert';

import 'package:flutter/material.dart';
import 'package:http/http.dart' as http;
import 'package:mobile/page/farah/detail-resep.dart';

import 'model/appointment.dart';

class DetailAppointment extends StatefulWidget {
  final String name;
  final String jwtToken;
  final String email;
  final String code;

  const DetailAppointment(
      {Key? key,
      required this.name,
      required this.email,
      required this.jwtToken,
      required this.code})
      : super(key: key);

  @override
  _DetailAppointmentState createState() => _DetailAppointmentState();
}

Widget buildField(String judul, String isi) {
  return Container(
    padding: const EdgeInsets.symmetric(horizontal: 60),
    child: Column(
        mainAxisAlignment: MainAxisAlignment.start,
        crossAxisAlignment: CrossAxisAlignment.center,
        children: [
          Text(
            "$judul : ",
            style: const TextStyle(
                fontSize: 20, color: Colors.blue, fontWeight: FontWeight.w600),
          ),
          const SizedBox(
            height: 10,
          ),
          Text(isi, style: const TextStyle(fontSize: 20)),
          const SizedBox(
            height: 15,
          ),
        ]),
  );
}

Widget buildContent() => Row(
      mainAxisSize: MainAxisSize.min,
      children: const [
        Icon(Icons.mode_edit_outline, size: 28),
        SizedBox(width: 16),
        Text(
          "Detail Resep",
          style: TextStyle(fontSize: 22, color: Colors.white),
        ),
      ],
    );

class _DetailAppointmentState extends State<DetailAppointment> {
  late String name;
  late String jwtToken;
  late String email;
  late String code;
  late Future<Appointment> futureAppointment;

  Future<Appointment> fetchAppointment(String jwtToken, String code) async {
    final response = await http.get(
        Uri.parse('http://localhost:8081/api/appointment/detail/$code'),
        headers: <String, String>{
          'Content-Type': 'application/json;charset=UTF-8',
          'Authorization': 'Bearer $jwtToken'
        });

    if (response.statusCode == 200) {
      return Appointment.fromJson(jsonDecode(response.body));
    } else {
      throw Exception('Failed to load Profile');
    }
  }

  @override
  void initState() {
    super.initState();
    name = widget.name;
    jwtToken = widget.jwtToken;
    email = widget.email;
    code = widget.code;
    futureAppointment = fetchAppointment(jwtToken, code);
  }

  @override
  Widget build(BuildContext context) {
    // TODO: implement build
    return Scaffold(
        appBar: AppBar(
          title: const Text("Rumah Sehat"),
        ),
        body: FutureBuilder<Appointment>(
            future: futureAppointment,
            builder: (context, AsyncSnapshot<Appointment> snapshot) {
              switch (snapshot.connectionState) {
                case ConnectionState.waiting:
                  return const Center(
                    child: CircularProgressIndicator(),
                  );
                default:
                  int idPrescription = snapshot.data!.idPrescription;
                  if (snapshot.hasError) {
                    return Text('Error: ${snapshot.error}');
                  } else {
                    if (snapshot.data!.idPrescription == 0) {
                      return ListView(
                        children: [
                          const SizedBox(
                            height: 20,
                          ),
                          buildField("Kode", snapshot.data!.code),
                          buildField("Status", snapshot.data!.status),
                          buildField("Nama Dokter", snapshot.data!.namaDokter),
                          buildField("Nama Pasien", snapshot.data!.namaPasien),
                          buildField("Waktu Awal", snapshot.data!.waktuAwal),
                          const SizedBox(
                            height: 40,
                          )
                        ],
                      );
                    } else {
                      return ListView(
                        children: [
                          const SizedBox(
                            height: 20,
                          ),
                          buildField("Kode", snapshot.data!.code),
                          buildField("Status", snapshot.data!.status),
                          buildField("Nama Dokter", snapshot.data!.namaDokter),
                          buildField("Nama Pasien", snapshot.data!.namaPasien),
                          buildField("Waktu Awal", snapshot.data!.waktuAwal),
                          Container(
                            padding: const EdgeInsets.symmetric(horizontal: 60),
                            child: ElevatedButton(
                              style: ElevatedButton.styleFrom(
                                minimumSize: const Size.fromHeight(50),
                              ),
                              child: buildContent(),
                              onPressed: () {
                                Navigator.push(
                                    context,
                                    MaterialPageRoute(
                                        builder: (context) => DetailResep(
                                            name: name,
                                            email: email,
                                            jwtToken: jwtToken,
                                            id: "$idPrescription")));
                              },
                            ),
                          ),
                          const SizedBox(
                            height: 40,
                          )
                        ],
                      );
                    }
                  }
              }
            }));
  }
}

class ProfileDetailColumn extends StatelessWidget {
  const ProfileDetailColumn(
      {Key? key, required this.title, required this.value})
      : super(key: key);
  final String title;
  final String value;
  @override
  Widget build(BuildContext context) {
    return Container(
      child: Row(
        mainAxisAlignment: MainAxisAlignment.center,
        crossAxisAlignment: CrossAxisAlignment.center,
        children: [
          Column(
            crossAxisAlignment: CrossAxisAlignment.start,
            children: [
              Text(
                title,
                style: Theme.of(context)
                    .textTheme
                    .subtitle1!
                    .copyWith(color: Colors.black, fontSize: 12),
              ),

              Text(value, style: Theme.of(context).textTheme.caption),
              // ignore: prefer_const_constructors
              SizedBox(
                width: 92,
                child: Divider(
                  thickness: 1.0,
                ),
              )
            ],
          ),
        ],
      ),
    );
  }
}
