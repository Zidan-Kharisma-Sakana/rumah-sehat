import 'dart:convert';

import 'package:flutter/material.dart';
import 'package:http/http.dart' as http;
import 'package:mobile/page/farah/model/resep.dart';


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

Widget buildDetail(String isi) {
  return Container(
    padding: const EdgeInsets.symmetric(horizontal: 60),
    child: Column(
        mainAxisAlignment: MainAxisAlignment.start,
        crossAxisAlignment: CrossAxisAlignment.center,
        children: [
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

class _DetailResepState extends State<DetailResep> {
  late String name;
  late String jwtToken;
  late String email;
  late String code;
  late Future<Resep> futureData;

  Future<Resep> fetchPrescription(String jwtToken, String id) async {
    final response = await http.get(
        Uri.parse('https://apap-059.cs.ui.ac.id/api/prescription/detail/$code'),
        headers: <String, String>{
          'Content-Type': 'application/json;charset=UTF-8',
          'Authorization': 'Bearer $jwtToken'
        });
    if (response.statusCode == 200) {
      return Resep.fromJson(jsonDecode(response.body));
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
    code = widget.id;
    futureData = fetchPrescription(jwtToken, code);
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
        appBar: AppBar(
          title: const Text("Rumah Sehat"),
        ),
        body: FutureBuilder<Resep>(
            future: futureData,
            builder: (context, AsyncSnapshot<Resep> snapshot) {
              List<Widget> listDrug = [];
              switch (snapshot.connectionState) {
                case ConnectionState.waiting:
                  return const Center(
                    child: CircularProgressIndicator(),
                  );
                default:
                  if (snapshot.hasError) {
                    return Text('Error: ${snapshot.error}');
                  } else {
                    for (var i in snapshot.data!.drugs) {
                      String nama = i.nama;
                      int qty = i.quantity;
                      print("Obat $nama $qty");
                      listDrug.add(buildDetail("$nama $qty"));
                    }
                    return ListView(
                        children: [
                              const SizedBox(
                                height: 20,
                              ),
                              buildField("Id", snapshot.data?.id ?? "-"),
                              buildField(
                                  "Status", snapshot.data?.status ?? "-"),
                              buildField("Nama Dokter",
                                  snapshot.data?.namaDokter ?? "-"),
                              buildField("Nama Pasien",
                                  snapshot.data?.namaPasien ?? "-"),
                              buildField("Created at",
                                  snapshot.data?.waktuAwal ?? "-"),
                              buildField("Daftar Obat: ", ""),
                            ] +
                            listDrug);
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
