import 'package:flutter/material.dart';
import 'package:game_review/app/models/pessoa.dart';
import 'package:game_review/app/screens/game_pages/game_page1.dart';
import 'game_pages/game_page2.dart';
import 'game_pages/game_page3.dart';
import 'game_pages/game_page4.dart';

class HomePage extends StatelessWidget {
   HomePage({Key? key}) : super(key: key);

  final List<Pessoa> pessoas = [
  Pessoa(nome: "God Of War", 
         url: "assets/images/God_of_war_capa_v1.png"),

  Pessoa(nome: "Hitman",
         url: "assets/images/hitman_capa_v1.png",),
  
  Pessoa(nome: "Minecraft", 
         url: "assets/images/minecraft_capa_v1.png"),

  Pessoa(nome: "Cyberpunk 2077", 
         url: "assets/images/cyberpunk_2077_capa_v1.png"),

  ];

  @override
  Widget build(BuildContext context) {
    return Container(
      decoration: const  BoxDecoration(
        gradient: LinearGradient(
          begin: Alignment.topLeft,
          end: Alignment.bottomRight,
          colors: [
            Color(0XFF0d324d),
            Color(0XFF000000)
          ],
        ),
      ), 

    child: Scaffold(
      backgroundColor: Colors.transparent,
      appBar: AppBar(
        centerTitle: true,
        title: const Text('Home Page - Projeto Game Review'),
      ),


      body: Padding(
        padding: const EdgeInsets.all(8.0),
        child: GridView.builder(
            gridDelegate: const SliverGridDelegateWithMaxCrossAxisExtent(
                maxCrossAxisExtent: 200,
                childAspectRatio: 2 / 3,
                crossAxisSpacing: 20,
                mainAxisSpacing: 20),
            itemCount: pessoas.length,
            itemBuilder: (BuildContext ctx, index) {

              return Container(
          alignment: Alignment.center,
          child: Material(
            color: Colors.cyan.shade900,
            elevation: 8,
            borderRadius: BorderRadius.circular(28),
            clipBehavior: Clip.antiAliasWithSaveLayer,
            
            child: InkWell(
              splashColor: Colors.black26,
              onTap: (){
                if(index == 0){
                          Navigator.of(context).push(MaterialPageRoute(
                              builder: (context) => const GamePage1()));
                }else if(index == 1){
                          Navigator.of(context).push(MaterialPageRoute(
                              builder: (context) => const GamePage2()));
                }else if(index == 2){
                          Navigator.of(context).push(MaterialPageRoute(
                              builder: (context) => const GamePage3()));
                }else if(index == 3){
                          Navigator.of(context).push(MaterialPageRoute(
                              builder: (context) => const GamePage4()));
               }else{
                 Navigator.of(context).push(MaterialPageRoute(
                              builder: (context) => const GamePage1()));
               }

              },
              child: Column(
                mainAxisSize: MainAxisSize.min,
                children: [
                  Expanded(child:
                  Ink.image(
                    image: AssetImage(
                      pessoas[index].url
                    ),
                    height: 200,
                    width: 200,
                    fit: BoxFit.cover,
                    ),
                  ),
                  const SizedBox(height: 6),
                  Text(
                    pessoas[index].nome,
                    style: const TextStyle(
                      fontSize: 16,
                      color: Colors.white
                      ),
                  ),
                  const SizedBox(height: 3),
                ]  
              ),
            ),
          ),      
        );
            }
          ),
       ),
      )
    );
  }
}

