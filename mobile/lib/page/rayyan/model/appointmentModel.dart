import 'dart:convert';

List<AppointmentModel> AppointmentModelFromJson(String str) =>
    List<AppointmentModel>.from(
        json.decode(str).map((x) => AppointmentModel.fromJson(x)));

String AppointmentModelToJson(List<AppointmentModel> data) =>
    json.encode(List<dynamic>.from(data.map((x) => x.toJson())));

class AppointmentModel {
  AppointmentModel({
    required this.doctor_name,
    required this.start,
    required this.isDone,
    required this.end,
    required this.code,
    required this.date,
  });

  String doctor_name;
  String start;
  String end;
  String date;
  String isDone;
  String code;

  factory AppointmentModel.fromJson(Map<String, dynamic> json) =>
      AppointmentModel(
        doctor_name: json["doctor_name"],
        start: json["start"],
        end: json["end"],
        date: json["date"],
        code: json['code'],
        isDone: json['isDone'],
      );

  Map<String, dynamic> toJson() => {};
