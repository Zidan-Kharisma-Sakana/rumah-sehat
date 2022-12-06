import 'package:flutter/material.dart';
import 'package:mobile/page/rayyan/model/doctorModel.dart';
import 'package:http/http.dart' as http;
import 'dart:convert';
import 'package:interval_time_picker/interval_time_picker.dart';

import 'listAppointments.dart';

Future<List<DoctorModel>> fetchDoctors(String jwtToken) async {
  var response = await http.get(
    Uri.parse('http://localhost:8081/api/appointment/doctor'),
    headers: {
      "Access-Control-Allow-Origin": "*",
      "Content-Type": "application/json",
      'Authorization': 'Bearer $jwtToken'
    },
  );

  if (response.statusCode != 200) {
    throw Exception('Failed to load doctors');
  }

  // melakukan decode response menjadi bentuk json
  var data = jsonDecode(utf8.decode(response.bodyBytes));

  // melakukan konversi data json menjadi object MyWatchlist
  List<DoctorModel> listDoctors = [];
  for (var d in data) {
    if (d != null) {
      listDoctors.add(DoctorModel.fromJson(d));
    }
  }
  return listDoctors;
}

class CreateAppointment extends StatefulWidget {
  final String name;
  final String jwtToken;
  final String email;
  const CreateAppointment({
    Key? key,
    required this.name,
    required this.email,
    required this.jwtToken,
  }) : super(key: key);

  @override
  // ignore: no_logic_in_create_state
  State<CreateAppointment> createState() => _CreateAppointment(jwtToken);
}

class _CreateAppointment extends State<CreateAppointment> {
  final _formKey = GlobalKey<FormState>();
  String? _uuid;
  DateTime? _date;
  TimeOfDay? _time;
  final String jwtToken;

  _CreateAppointment(this.jwtToken);

  // List<DoctorModel> doctors;

  @override
  Widget build(BuildContext context) => Scaffold(
        appBar: AppBar(
          backgroundColor: Colors.blue,
          title: const Text("Membuat Appointment"),
          centerTitle: true,
        ),
        body: FutureBuilder(
          future: fetchDoctors(jwtToken),
          builder: (context, AsyncSnapshot<List<DoctorModel>> snapshot) {
            if (snapshot.data == null) {
              return const Center(child: CircularProgressIndicator());
            }
            return Form(
              key: _formKey,
              child: SingleChildScrollView(
                child: Column(
                  children: [
                    Column(
                      mainAxisAlignment: MainAxisAlignment.center,
                      children: [
                        Center(
                          child: DropdownButtonFormField(
                            decoration: const InputDecoration(
                              labelText: "Pilih Dokter",
                            ),
                            value: _uuid,
                            items: snapshot.data!.map((DoctorModel doctor) {
                              return DropdownMenuItem<String>(
                                value: doctor.uuid,
                                child: Text(doctor.name),
                              );
                            }).toList(),
                            onChanged: (String? value) {
                              _uuid = value;
                            },
                            validator: (String? value) {
                              return value == null
                                  ? "Tidak Boleh Kosong!"
                                  : null;
                            },
                          ),
                        ),
                      ],
                    ),
                    Center(
                      child: Padding(
                          padding: const EdgeInsets.all(4),
                          child: Row(children: [
                            Row(children: [
                              const Text(
                                "Tanggal Kunjungan",
                                style: TextStyle(
                                    fontSize: 16,
                                    color: Colors.blue,
                                    fontWeight: FontWeight.w600),
                              ),
                              const SizedBox(
                                width: 10,
                              ),
                              Text(
                                ":  ${_date != null ? "${_date!.day}/${_date!.month}/${_date!.year}" : "-"}",
                                style: const TextStyle(
                                    fontSize: 16, fontWeight: FontWeight.w500),
                              )
                            ]),
                            const Spacer(),
                            ElevatedButton(
                              onPressed: () {
                                showDatePicker(
                                  context: context,
                                  initialDate: DateTime.now(),
                                  firstDate: DateTime.now(),
                                  lastDate: DateTime(2023),
                                ).then((value) {
                                  setState(() {
                                    _date = value;
                                  });
                                });
                              },
                              child: const Text("Pilih Tanggal"),
                            )
                          ])),
                    ),
                    Center(
                      child: Padding(
                          padding: const EdgeInsets.all(4),
                          child: Row(children: [
                            Row(children: [
                              const Text(
                                "Jam Kunjungan",
                                style: TextStyle(
                                    fontSize: 16,
                                    color: Colors.blue,
                                    fontWeight: FontWeight.w600),
                              ),
                              const SizedBox(
                                width: 10,
                              ),
                              Text(
                                ":  ${_time != null ? "${_time!.hour}:${_time!.minute.toString().padLeft(2, "0")}" : "-"}",
                                style: const TextStyle(
                                    fontSize: 16, fontWeight: FontWeight.w500),
                              )
                            ]),
                            const Spacer(),
                            ElevatedButton(
                              onPressed: () {
                                showIntervalTimePicker(
                                        context: context,
                                        initialTime:
                                            const TimeOfDay(hour: 8, minute: 0))
                                    .then((value) {
                                  setState(() {
                                    _time = value;
                                  });
                                });
                              },
                              child: const Text("Pilih Jam"),
                            )
                          ])),
                    ),
                    TextButton(
                      style: ButtonStyle(
                          backgroundColor:
                              MaterialStateProperty.all(Colors.blue)),
                      onPressed: () async {
                        if (_date == null || _uuid == null || _time == null) {
                          ScaffoldMessenger.of(context)
                              .showSnackBar(const SnackBar(
                            content: Text(
                                'Masukan nama dokter, tanggal, dan waktu kunjungan!'),
                          ));
                          return;
                        }
                        final response = await http.post(
                            Uri.parse(
                                "http://localhost:8081/api/appointment/add"),
                            headers: <String, String>{
                              'Content-Type': 'application/json;charset=UTF-8',
                              'Authorization': 'Bearer $jwtToken'
                            },
                            body: jsonEncode(<String, Object>{
                              'doctor_uuid': _uuid!,
                              'date': _date!.toIso8601String(),
                              'hour': _time!.hour,
                              'minute': _time!.minute
                            }));
                        print(response.body);
                        if (response.statusCode == 200) {
                          Navigator.pushReplacement(
                            context,
                            MaterialPageRoute(
                                builder: (context) => ListAppointments(
                                      jwtToken: jwtToken,
                                    )),
                          );
                        }
                        final Map parsed = json.decode(response.body);
                        ScaffoldMessenger.of(context).showSnackBar(SnackBar(
                          content: Text(parsed['message'] ?? "No Message"),
                        ));
                      },
                      child: const Text(
                        "Buat Appointment",
                        style: TextStyle(color: Colors.white),
                      ),
                    ),
                  ],
                ),
              ),
            );
          },
        ),
      );
}
