import 'dart:convert';

List<DoctorModel> DoctorModelFromJson(String str) => List<DoctorModel>.from(json.decode(str).map((x) => DoctorModel.fromJson(x)));

String DoctorModelToJson(List<DoctorModel> data) => json.encode(List<dynamic>.from(data.map((x) => x.toJson())));

class DoctorModel {
    DoctorModel({
        required this.name,
        required this.uuid,
    });

    String name;
    String uuid;

    factory DoctorModel.fromJson(Map<String, dynamic> json) => DoctorModel(
        name: json["name"],
        uuid: json["uuid"],
    );

    Map<String, dynamic> toJson() => {
        "name": name,
        "uuid": uuid,
    };
}