import 'dart:convert';

import 'package:flutter/material.dart';
import 'package:http/http.dart' as http;
import 'package:mobile/widget/button_widget.dart';

class DetailResep extends StatefulWidget {
  final String name;
  final String jwtToken;
  final String email;
  final String id;

  const DetailResep(
      {Key? key,
      required this.name,
      required this.email,
      required this.jwtToken,
      required this.id})
      : super(key: key);

  @override
  _DetailResepState createState() => _DetailResepState();
}

class Resep {
  final String id;
  final String namaDokter;
  final String namaPasien;
  final String waktuAwal;
  final String status;
  final int idPrescription;
  final List<DrugPrescription> listPrescribed;

  Resep(
      {required this.id,
      required this.namaDokter,
      required this.namaPasien,
      required this.waktuAwal,
      required this.status,
      required this.idPrescription});

  factory Appointment.fromJson(Map<String, dynamic> json) {
    return Appointment(
        code: json['code'],
        namaDokter: json['nama-dokter'],
        namaPasien: json['nama-pasien'],
        waktuAwal: json['waktu-awal'],
        status: json['status'],
        idPrescription: json['id-prescription']);
  }
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
      children: [
        const Icon(Icons.mode_edit_outline, size: 28),
        const SizedBox(width: 16),
        const Text(
          "Detail Resep",
          style: TextStyle(fontSize: 22, color: Colors.white),
        ),
      ],
    );

class _DetailResepState extends State<DetailResep> {
  late String name;
  late String jwtToken;
  late String email;
  late String code;
  late Future<Resep> futureResep;

  Future<Resep> fetchAppointment(String jwtToken, String id) async {
    final response = await http.get(
        Uri.parse('http://localhost:8081/api/presciption/detail/$id'),
        headers: <String, String>{
          'Content-Type': 'application/json;charset=UTF-8',
          'Authorization': 'Bearer $jwtToken'
        });

    if (response.statusCode == 200) {
      // If the server did return a 200 OK response,
      // then parse the JSON.
      return Appointment.fromJson(jsonDecode(response.body));
    } else {
      // If the server did not return a 200 OK response,
      // then throw an exception.
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
                              onPressed: () {},
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
