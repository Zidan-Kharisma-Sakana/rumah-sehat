import 'package:flutter/material.dart';

class DetailAppointment extends StatefulWidget {
  const DetailAppointment({Key? key, required this.jsonData}) : super(key: key);
  final Map<String, dynamic> jsonData;
  @override
  _DetailAppointment createState() => _DetailAppointment();
}

class _DetailAppointment extends State<DetailAppointment>{
    void getData() async {
        final response = await http.get(Uri.parse(
            "https://pbp-uas-backend.herokuapp.com/get_students_subjects?sid=" +
                widget.jsonData['username']));}

        List<dynamic> responseList2 = json.decode(response2.body);
        List<Widget> listItem = [];

        listItem.add(Card(

        )

        )
}