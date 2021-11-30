import 'package:flutter/material.dart';
import 'package:game_review/app/screens/login_page.dart';

class MyApp extends StatelessWidget {
  const MyApp({Key? key}) : super(key: key);

  // This widget is the root of your application.
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Flutter Demo',
      /*
      initialRoute: '/',
      routes: {
        // When navigating to the "/" route, build the FirstScreen widget.
        '/': (context) => const LoginPage(),
        // When navigating to the "/second" route, build the SecondScreen widget.
        '/second': (context) => HomePage(),
      },
*/
      theme: ThemeData(
        colorScheme: const ColorScheme.dark().copyWith(
          primary: Colors.cyan.shade900 ,
          secondary: const Color(0XFF1F1B24),
          surface: Colors.cyan.shade900
        ),
      ),
      home:const LoginPage(),
      debugShowCheckedModeBanner: false,
    );
  }
}   
