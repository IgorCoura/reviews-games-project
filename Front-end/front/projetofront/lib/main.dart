import 'package:flutter/material.dart';
import 'package:testegridview/home_screen.dart';


void main() {
  runApp(MyApp());
}

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      // Hide the debug banner
      debugShowCheckedModeBanner: false,
      title: 'Kindacode.com',
      home: HomeScreen(),
    );
  }
}

class MyPage extends StatelessWidget {
  const MyPage({Key? key}) : super(key: key);


  @override
  Widget build(BuildContext context) =>
      Scaffold(
        appBar: AppBar(
          title: Text("Hello world"),
        ),

        body: 
        
        Container(
          alignment: Alignment.bottomLeft,
          child: Material(
            color: Colors.blueGrey,
            elevation: 8,
            borderRadius: BorderRadius.circular(28),
            clipBehavior: Clip.antiAliasWithSaveLayer,
            child: InkWell(
              splashColor: Colors.black26,
              onTap: (){
                          Navigator.of(context).push(MaterialPageRoute(
                              builder: (context) => GamePage1()));
                        },
              child: Column(
                mainAxisSize: MainAxisSize.min,
                children: [
                  Ink.image(
                    image: const NetworkImage("https://pbs.twimg.com/media/FEaCjohXIAQPVgl?format=jpg&name=small"),
                    height: 100,
                    width: 100,
                    fit: BoxFit.cover,
                  ),
                  const SizedBox(height: 3),
                  const Text(
                    'Maru',
                    style: TextStyle(fontSize: 12, color: Colors.white),
                  ),
                  const SizedBox(height: 3),
                ]
            ),  
          ),
        ),      
      ),       
    );  
  }





class GamePage1 extends StatelessWidget {
 GamePage1({ Key? key }) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text("Game Review"),
      ),
      body: ListView(
        children: [
          ListTile(
            title: const Text("AAAAAAAAAA"),
            leading: TextButton(
              child: Image.network("https://pbs.twimg.com/media/FEaCjohXIAQPVgl?format=jpg&name=small", fit: BoxFit.contain,),
              
              onPressed: () {
              Navigator.of(context).push(MaterialPageRoute(
                              builder: (context) => const MyPage()));
              }
            ),
            subtitle: const Text("aaaaaaaaaaaa"),
          ),
        ],
      ),
    );
  }
}


class GamePage2 extends StatelessWidget {
 GamePage2({ Key? key }) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text("Game Review"),
      ),
      body: ListView(
        children: [
          ListTile(
            title: const Text("bbbbbbbbbb"),
            leading: TextButton(
              child: Image.network("https://pbs.twimg.com/media/FEaCjohXIAQPVgl?format=jpg&name=small", fit: BoxFit.contain,),
              
              onPressed: () {
              Navigator.of(context).push(MaterialPageRoute(
                              builder: (context) => const MyPage()));
              }
            ),
            subtitle: const Text("BBBBBBBBBBBBB"),
          ),
        ],
      ),
    );
  }
}

class GamePage3 extends StatelessWidget {
 GamePage3({ Key? key }) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text("Game Review"),
      ),
      body: ListView(
        children: [
          ListTile(
            title: const Text("CCCCCCCCCCCCC"),
            leading: TextButton(
              child: Image.network("https://pbs.twimg.com/media/FEaCjohXIAQPVgl?format=jpg&name=small", fit: BoxFit.contain,),
              
              onPressed: () {
              Navigator.of(context).push(MaterialPageRoute(
                              builder: (context) => const MyPage()));
              }
            ),
            subtitle: const Text("cccccccccccc"),
          ),
        ],
      ),
    );
  }
}


class GamePage4 extends StatelessWidget {
 GamePage4({ Key? key }) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text("Game Review"),
      ),
      body: ListView(
        children: [
          ListTile(
            title: const Text("DDDDDDDDDDDDDD"),
            leading: TextButton(
              child: Image.network("https://pbs.twimg.com/media/FEaCjohXIAQPVgl?format=jpg&name=small", fit: BoxFit.contain,),
              
              onPressed: () {
              Navigator.of(context).push(MaterialPageRoute(
                              builder: (context) => const MyPage()));
              }
            ),
            subtitle: const Text("ddddddddddddddd"),
          ),
        ],
      ),
    );
  }
}