import 'dart:collection';
import 'dart:convert';
import 'package:flutter/material.dart' hide Element;
import 'package:http/http.dart' as http;

import '../brev/list_invoices_page.dart';

Future<http.Response> settle(String code, String jwtToken) async {
  http.Response response = await http.put(
      Uri.parse("localhost:8081/api/invoice/settle/$code"),
      headers: <String, String>{
        'Content-Type': 'application/json;charset=UTF-8',
        'Authorization': 'Bearer $jwtToken'
      });

  return response;
}

Future<Invoice> fetchInvoice(String jwtToken, String code) async {
  String uri = 'http://localhost:8081/api/invoice/detail/$code';
  final response = await http.get(
    Uri.parse(uri),
    headers: <String, String>{
      'Content-Type': 'application/json;charset=UTF-8',
      'Authorization': 'Bearer $jwtToken'
    },
  );

  if (response.statusCode != 200) {
    throw Exception('Failed to load invoice');
  }
  var data = jsonDecode(utf8.decode(response.bodyBytes));

  return Invoice.fromJson(data);
}

class Invoice {
  final String code;
  final int amount;
  final DateTime dateIssued;
  final DateTime datePaid;
  final bool isPaid;

  Invoice({
    required this.code,
    required this.amount,
    required this.dateIssued,
    required this.datePaid,
    required this.isPaid,
  });

  factory Invoice.fromJson(Map<String, dynamic> json) {
    return Invoice(
        code: json['code'],
        amount: json['amount'],
        dateIssued: DateTime.parse(json['dateIssued']),
        datePaid: DateTime.parse(json['datePaid']),
        isPaid: json['isPaid']);
  }
}

class DetailInvoicePage extends StatefulWidget {
  final String jwtToken;
  final String code;
  const DetailInvoicePage({
    Key? key,
    required this.jwtToken,
    required this.code,
  }) : super(key: key);

  @override
  _DetailInvoicePageState createState() => _DetailInvoicePageState();
}

class _DetailInvoicePageState extends State<DetailInvoicePage> {
  late String jwtToken;
  late String code;
  late Future<Invoice> invoice;
  late String _message;

  @override
  void initState() {
    super.initState();
    jwtToken = widget.jwtToken;
    code = widget.code;
    invoice = fetchInvoice(jwtToken, code);
    _message = "";
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text("Tagihan Anda"),
      ),
      body: FutureBuilder(
        future: invoice,
        builder: (context, AsyncSnapshot<Invoice> snapshot) {
          switch (snapshot.connectionState) {
            case ConnectionState.waiting:
              return const Center(
                child: CircularProgressIndicator(),
              );
            default:
              if (snapshot.hasError) {
                return Text('Error: ${snapshot.error}');
              } else {
                return Card(
                    child: Column(
                  children: [
                    Container(
                      padding: const EdgeInsets.all(7.0),
                      child: Text("Nomor Tagihan : ${snapshot.data!.code}"),
                    ),
                    Container(
                      padding: const EdgeInsets.all(7.0),
                      child: Text(
                          "Tanggal Terbuat : ${translateDate(snapshot.data!.dateIssued)}"),
                    ),
                    Container(
                      padding: const EdgeInsets.all(7.0),
                      child: Text(
                          "Status Pembayaran : ${translateStatus(snapshot.data!.isPaid)}"),
                    ),
                    ElevatedButton(
                        onPressed: () async {
                          final response = await settle(code, jwtToken);
                          print(jsonDecode(response.body));
                          if (response.statusCode == 200) {
                            setState(() {
                              _message = "Pembayaran berhasil";
                            });
                          } else {
                            setState(() {
                              _message = "Saldo tidak mencukupi";
                            });
                          }
                        },
                        child: const Text("Bayar")),
                  ],
                ));
              }
          }
        },
      ),
    );
  }
}
