// ignore_for_file: unused_import, prefer_const_literals_to_create_immutables

import 'dart:convert';

import 'package:flutter/material.dart';
import 'package:http/http.dart' as http;
import 'package:mobile/widget/button_widget.dart';

Future<Profile> fetchProfile(String username) async {
  final response = await http
      .get(Uri.parse('http://localhost:8000/$username'));

  if (response.statusCode == 200) {
    // If the server did return a 200 OK response,
    // then parse the JSON.
    return Profile.fromJson(jsonDecode(response.body));
  } else {
    // If the server did not return a 200 OK response,
    // then throw an exception.
    throw Exception('Failed to load Profile');
  }
}

class Profile {
  final String username;
  final String email;
  final String bod;
  final String bio;

  Profile({
    required this.username,
    required this.email,
    required this.bod,
    required this.bio,
  });

  factory Profile.fromJson(Map<String, dynamic> json) {
    return Profile(
        username: json['username'],
        email: json['email'],
        bod: json['bod'],
        bio: json['bio']);
  }
}

class ProfilePage extends StatefulWidget {
  final String name;
  final String jwtToken;
  final String email;
  const ProfilePage({
    Key? key,
    required this.name,
    required this.email,
    required this.jwtToken,
  }) : super(key: key);
  @override
  _ProfilePageState createState() => _ProfilePageState();
}
const urlImage = 'https://64.media.tumblr.com/b2274200b6c46495f31c1e0a6678dc86/05ebb2b05dc70fb3-60/s640x960/a42b3a3ccd9fdf24d376508c7e223fb0db40de08.jpg';
class _ProfilePageState extends State<ProfilePage> {
  late String name;
  late String jwtToken;
  late String email;
  late Future<Profile> futureProfile;

  @override
  void initState() {
    // TODO: implement initState
    super.initState();
    name = widget.name;
    jwtToken = widget.jwtToken;
    email = widget.email;
    futureProfile = fetchProfile(name);
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
                  fontSize: 20,
                  color: Colors.blue,
                  fontWeight: FontWeight.w600),
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

  Widget buildContent() => Row(
        mainAxisSize: MainAxisSize.min,
        children: [
          const Icon(Icons.mode_edit_outline, size: 28),
          const SizedBox(width: 16),
          const Text(
            "Edit Profile",
            style: TextStyle(fontSize: 22, color: Colors.white),
          ),
        ],
      );
  @override
  Widget build(BuildContext context) {
    return Scaffold(
        appBar: AppBar(
          backgroundColor: Colors.blue,
          title: const Text("Profile"),
          centerTitle: true,
        ),
        body: FutureBuilder<Profile>(
            future: futureProfile,
            builder: (context, AsyncSnapshot<Profile> snapshot) {
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
                      children: [
                        Container(
                          padding: const EdgeInsets.symmetric(vertical: 50),
                          alignment: Alignment.center,
                          child: CircleAvatar(
                              backgroundImage: NetworkImage(urlImage),
                              radius: MediaQuery.of(context).size.width * 0.25),
                        ),
                        const SizedBox(
                          height: 20,
                        ),
                        buildField("Nama", name),
                        buildField("Email", email),
                        buildField("Birthday", snapshot.data!.bod),
                        buildField("Deskripsi diri", snapshot.data!.bio),
                        Container(
                          padding: const EdgeInsets.symmetric(horizontal:60),
                          child: ElevatedButton(
                            style: ElevatedButton.styleFrom(
                              minimumSize: const Size.fromHeight(50),
                            ),
                            child: buildContent(),
                            onPressed: () {},
                          ),
                        ),
                        const SizedBox(
                          height: 40,
                        )
                      ],
                    );
                  }
              }
            }));
  }
}
