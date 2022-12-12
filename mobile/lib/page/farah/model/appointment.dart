class Appointment {
  final String code;
  final String namaDokter;
  final String namaPasien;
  final String waktuAwal;
  final String status;
  final int idPrescription;

  Appointment(
      {required this.code,
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