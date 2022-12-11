import 'package:flutter/material.dart';
import 'package:mobile/page/all.dart';
import 'package:mobile/page/farah/detail-resep.dart';
import 'package:shared_preferences/shared_preferences.dart';

class NavigationDrawerWidget extends StatefulWidget {
  const NavigationDrawerWidget({Key? key}) : super(key: key);
  @override
  NavigationDrawerWidgetState createState() => NavigationDrawerWidgetState();
}

class NavigationDrawerWidgetState extends State<NavigationDrawerWidget> {
  final padding = const EdgeInsets.symmetric(horizontal: 20);
  final Future<SharedPreferences> _prefs = SharedPreferences.getInstance();
  late Future<String> _email;
  late Future<String> _username;
  late Future<String> _jwtToken;

  @override
  void initState() {
    super.initState();
    _email = _prefs.then((SharedPreferences prefs) {
      return prefs.getString('email') ?? '';
    });
    _username = _prefs.then((SharedPreferences prefs) {
      return prefs.getString('username') ?? '';
    });
    _jwtToken = _prefs.then((SharedPreferences prefs) {
      return prefs.getString('jwtToken') ?? '';
    });
  }

  Future<void> _logout() async {
    final SharedPreferences prefs = await _prefs;
    setState(() {
      _email = prefs.setString('email', "").then((bool success) {
        return "";
      });
      _username = prefs.setString('username', "").then((bool success) {
        return "";
      });
      _jwtToken = prefs.setString('jwtToken', "").then((bool success) {
        return "";
      });
    });
  }

  @override
  Widget build(BuildContext context) {
    const urlImage = "https://static.vecteezy.com/system/resources/thumbnails/005/545/335/small/user-sign-icon-person-symbol-human-avatar-isolated-on-white-backogrund-vector.jpg";
    return SizedBox(
      width: MediaQuery.of(context).size.width *
          0.75, // 75% of screen will be occupied
      child: FutureBuilder<List<String>>(
        future: Future.wait([_username, _email, _jwtToken]),
        builder: (context, AsyncSnapshot<List<dynamic>> snapshot) {
          switch (snapshot.connectionState) {
            case ConnectionState.waiting:
              return const CircularProgressIndicator();
            default:
              if (snapshot.hasError) {
                return Text('Error: ${snapshot.error}');
              } else {
                bool isLoggedIn = snapshot.data?[0] != '';
                return Drawer(
                  child: Material(
                    color: const Color.fromRGBO(50, 75, 205, 1),
                    child: ListView(
                      children: <Widget>[
                        buildHeader(
                          isLoggedIn: isLoggedIn,
                          urlImage: urlImage,
                          name: snapshot.data?[0],
                          email: snapshot.data?[1],
                          onClicked: () =>
                              Navigator.of(context).push(MaterialPageRoute(
                            builder: (context) => ProfilePage(
                              name: snapshot.data?[0],
                              email: snapshot.data?[1],
                              jwtToken: snapshot.data?[2],
                            ),
                          )),
                        ),
                        Container(
                          padding: padding,
                          child: Column(
                            children: [
                              const SizedBox(height: 10),
                              buildMenuItem(
                                isLoggedIn: isLoggedIn,
                                text: 'Profile',
                                icon: Icons.person,
                                onClicked: () => selectedItem(
                                  context,
                                  0,
                                  snapshot.data?[0],
                                  snapshot.data?[1],
                                  snapshot.data?[2],
                                ),
                              ),
                              const SizedBox(height: 3),
                              buildMenuItem(
                                isLoggedIn: isLoggedIn,
                                text: 'Membuat Appointment',
                                icon: Icons.check_box,
                                onClicked: () => selectedItem(
                                  context,
                                  1,
                                  snapshot.data?[0],
                                  snapshot.data?[1],
                                  snapshot.data?[2],
                                ),
                              ),
                              const SizedBox(height: 3),
                              buildMenuItem(
                                isLoggedIn: isLoggedIn,
                                text: 'Melihat Appointment',
                                icon: Icons.note_add_rounded,
                                onClicked: () => selectedItem(
                                  context,
                                  2,
                                  snapshot.data?[0],
                                  snapshot.data?[1],
                                  snapshot.data?[2],
                                ),
                              ),
                              const SizedBox(height: 3),
                              buildMenuItem(
                                isLoggedIn: isLoggedIn,
                                text: 'Melihat Daftar Tagihan',
                                icon: Icons.schedule,
                                onClicked: () => selectedItem(
                                  context,
                                  3,
                                  snapshot.data?[0],
                                  snapshot.data?[1],
                                  snapshot.data?[2],
                                ),
                              ),
                              const SizedBox(height: 15),
                              const Divider(color: Colors.white70),
                              const SizedBox(height: 15),
                              buildMenuItem(
                                  isLoggedIn: isLoggedIn,
                                  text: 'Logout',
                                  icon: Icons.logout,
                                  onClicked: () {
                                    _logout();
                                    selectedItem(context, 7, snapshot.data?[0],
                                        snapshot.data?[1], snapshot.data?[2]);
                                  }),
                              buildMenuItem(
                                isLoggedIn: !isLoggedIn,
                                text: 'Login',
                                icon: Icons.login,
                                onClicked: () => selectedItem(
                                    context,
                                    7,
                                    snapshot.data?[0],
                                    snapshot.data?[1],
                                    snapshot.data?[2]),
                              ),
                              const SizedBox(height: 15),
                              buildMenuItem(
                                isLoggedIn: !isLoggedIn,
                                text: 'Register',
                                icon: Icons.app_registration_rounded,
                                onClicked: () => selectedItem(
                                    context,
                                    8,
                                    snapshot.data?[0],
                                    snapshot.data?[1],
                                    snapshot.data?[2]),
                              ),
                            ],
                          ),
                        ),
                      ],
                    ),
                  ),
                );
              }
          }
        },
      ),
    );
  }

  Widget buildHeader({
    required bool isLoggedIn,
    required String urlImage,
    required String name,
    required String email,
    required VoidCallback onClicked,
  }) {
    if (!isLoggedIn) {
      return const SizedBox(
        height: 0,
      );
    }
    return InkWell(
      onTap: onClicked,
      child: Container(
        padding: padding.add(const EdgeInsets.only(top: 40, bottom: 20)),
        child: Row(
          children: [
            CircleAvatar(radius: 30, backgroundImage: NetworkImage(urlImage)),
            const SizedBox(width: 20),
            Flexible(
                child: Column(
              crossAxisAlignment: CrossAxisAlignment.start,
              children: [
                Text(
                  name,
                  style: const TextStyle(fontSize: 20, color: Colors.white),
                ),
                const SizedBox(height: 4),
                Text(
                  email,
                  style: const TextStyle(fontSize: 14, color: Colors.white),
                ),
              ],
            )),
            Container(
              padding: const EdgeInsets.only(left: 30, right: 10),
              child: const CircleAvatar(
                radius: 24,
                backgroundColor: Color.fromRGBO(30, 60, 168, 1),
                child: Icon(Icons.mode_edit_outline, color: Colors.white),
              ),
            ),
          ],
        ),
      ),
    );
  }

  Widget buildMenuItem({
    required bool isLoggedIn,
    required String text,
    required IconData icon,
    VoidCallback? onClicked,
  }) {
    const color = Colors.white;
    const hoverColor = Colors.white70;

    if (!isLoggedIn) {
      return const SizedBox(
        height: 0,
      );
    }
    return ListTile(
      leading: Icon(icon, color: color),
      title: Text(text, style: TextStyle(color: color)),
      hoverColor: hoverColor,
      onTap: onClicked,
    );
  }

  void selectedItem(BuildContext context, int index, String name, String email,
      String jwtToken) {
    Navigator.of(context).pop();

    switch (index) {
      case 0:
        Navigator.of(context).push(MaterialPageRoute(
          builder: (context) => ProfilePage(
            name: name,
            email: email,
            jwtToken: jwtToken,
          ),
        ));
        break;
      case 1:
        Navigator.of(context).push(MaterialPageRoute(
          builder: (context) => CreateAppointment(
            name: name,
            email: email,
            jwtToken: jwtToken,
          ),
        ));
        break;
      case 2:
        Navigator.of(context).push(MaterialPageRoute(
          builder: (context) => ListAppointments(
            jwtToken: jwtToken,
          ),
        ));
        break;
      case 3:
        Navigator.of(context).push(MaterialPageRoute(
          builder: (context) => ListInvoicesPage(
            name: name,
            jwtToken: jwtToken,
          ),
        ));
        break;
      case 5:
        Navigator.of(context).push(MaterialPageRoute(
          builder: (context) => DetailResep(
            name: name,
            email: email,
            jwtToken: jwtToken,
            id: "APT-XXX",
          ),
        ));
        break;
      case 7:
        Navigator.of(context).push(MaterialPageRoute(
          builder: (context) => const LoginPage(),
        ));
        break;
      case 8:
        Navigator.of(context).push(MaterialPageRoute(
          builder: (context) => RegisterPage(),
        ));
        break;
    }
  }
}
