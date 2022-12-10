

class DrugPrescription {
  final int quantity;
  final String nama;

  DrugPrescription({required this.quantity, required this.nama});

  factory DrugPrescription.fromJson(Map<String, dynamic> json) {
    return DrugPrescription(
      quantity: json["quantity"],
      nama: json["nama"],
    );
  }
}