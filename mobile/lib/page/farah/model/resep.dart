import 'package:mobile/page/farah/model/drug.dart';

class Resep {
  final String id;
  final String namaDokter;
  final String namaPasien;
  final String waktuAwal;
  final String confirmer;
  final String status;
  final List<DrugPrescription> drugs;

  Resep(
      {required this.id,
      required this.namaDokter,
      required this.namaPasien,
      required this.waktuAwal,
      required this.confirmer,
      required this.status,
      required this.drugs});

  factory Resep.fromJson(Map<String, dynamic> json) {
    final List<DrugPrescription> drugs = [];
    for (var i in json['list-drug']) {
      drugs.add(DrugPrescription.fromJson(i));
    }
    int id = json['code'];
    return Resep(
        id: "$id",
        namaDokter: json['nama-dokter'],
        namaPasien: json['nama-pasien'],
        waktuAwal: json['waktu-awal'],
        status: json['status'],
        confirmer: json['nama-confirmer'],
        drugs: drugs);
  }
}
