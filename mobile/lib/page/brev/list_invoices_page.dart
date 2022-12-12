import 'dart:collection';
import 'dart:convert';
import 'package:flutter/material.dart';
import 'package:http/http.dart' as http;
import 'package:interval_time_picker/interval_time_picker.dart';

Future<List<Invoice>> fetchInvoices(String jwtToken, String name) async {
  String uri = 'http://localhost:8081/api/invoice/get-all/' + name;
  final response = await http.get(
    Uri.parse(uri),
    headers: <String, String>{
      'Content-Type': 'application/json;charset=UTF-8',
      'Authorization': 'Bearer $jwtToken'
    },
  );

  if (response.statusCode != 200) {
    throw Exception('Failed to load invoices');
  }

  var data = jsonDecode(utf8.decode(response.bodyBytes));

  List<Invoice> listInvoices = [];
  for (var d in data) {
    if (d != null) {
      listInvoices.add(Invoice.fromJson(d));
    }
  }
  return listInvoices;
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

class ListInvoicesPage extends StatefulWidget {
  final String jwtToken;
  final String name;

  const ListInvoicesPage({
    Key? key,
    required this.jwtToken,
    required this.name,
  }) : super(key: key);

  @override
  _ListInvoicesPageState createState() => _ListInvoicesPageState();
}

class _ListInvoicesPageState extends State<ListInvoicesPage> {
  late String jwtToken;
  late String name;
  late Future<List<Invoice>> listInvoices;

  @override
  void initState() {
    super.initState();
    jwtToken = widget.jwtToken;
    name = widget.name;
    listInvoices = fetchInvoices(jwtToken, name);
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text("Tagihan Anda"),
      ),
      body: FutureBuilder(
        future: listInvoices,
        builder: (context, AsyncSnapshot<List<Invoice>> snapshot) {
          switch (snapshot.connectionState) {
            case ConnectionState.waiting:
              return const Center(
                child: CircularProgressIndicator(),
              );
            default:
              if (snapshot.hasError) {
                return Text('Error: ${snapshot.error}');
              } else {
                return ListView(
                    children: snapshot.data!.map(
                  (Invoice invoice) {
                    return Card(
                        child: Column(
                      children: [
                        Container(
                          child: Text("Nomor Tagihan : " + invoice.code),
                          padding: EdgeInsets.all(7.0),
                        ),
                        Container(
                          child: Text("Tanggal Terbuat : " +
                              translateDate(invoice.dateIssued)),
                          padding: EdgeInsets.all(7.0),
                        ),
                        Container(
                          child: Text("Status Pembayaran : " +
                              translateStatus(invoice.isPaid)),
                          padding: EdgeInsets.all(7.0),
                        ),
                        Container(
                          child: ElevatedButton(
                              onPressed: () {
                                Navigator.of(context).push(MaterialPageRoute(
                                  builder: (context) => DetailInvoicePage(
                                    code: invoice.code,
                                    jwtToken: jwtToken,
                                  ),
                                ));
                              },
                              child: Text("Detail")),
                        ),
                      ],
                    ));
                  },
                ).toList());
              }
          }
        },
      ),
    );
  }
}

String translateStatus(status) {
  if (status == true) {
    return "Sudah dibayar";
  } else {
    return "Belum dibayar";
  }
}

String translateDate(DateTime date) {
  final Map<String, String> months = HashMap();
  months['1'] = 'Januari';
  months['2'] = 'Februari';
  months['3'] = 'Maret';
  months['4'] = 'April';
  months['5'] = 'May';
  months['6'] = 'Juni';
  months['7'] = 'Juli';
  months['8'] = 'Agustus';
  months['9'] = 'September';
  months['10'] = 'Oktober';
  months['11'] = 'November';
  months['12'] = 'Desember';

  String day = date.day.toString();
  String month = months[date.month.toString()].toString();
  String year = date.year.toString();

  String output = day + " " + month + " " + year;
  return output;
}

Future<Invoice> fetchInvoice(String jwtToken, String code) async {
  String uri = 'http://localhost:8081/api/invoice/detail/' + code;
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

  if (data != null) {
    Invoice invoice = Invoice.fromJson(data);
    return invoice;
  }
  Invoice invoice = Invoice(
      code: "A",
      amount: 1,
      dateIssued: DateTime.now(),
      datePaid: DateTime.now(),
      isPaid: false);
  return invoice;
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

  @override
  void initState() {
    super.initState();
    jwtToken = widget.jwtToken;
    code = widget.code;
    invoice = fetchInvoice(jwtToken, code);
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
        appBar: AppBar(
          title: Text("Tagihan Anda"),
        ),
        body: Text("Test"));
  }
}
