import 'package:flutter/material.dart';
import 'package:testegridview/pessoa.dart';
import 'main.dart';

class HomeScreen extends StatelessWidget {
  HomeScreen({Key? key}) : super(key: key);

  final List<Pessoa> pessoas = [
    Pessoa(nome: "Maru", 
           url: "https://pbs.twimg.com/media/FEaCjohXIAQPVgl?format=jpg&name=small"),

  Pessoa(nome: "Karen",
         url: "https://pbs.twimg.com/media/FE1ITjQacAAcD7X?format=jpg&name=large",),
  
  Pessoa(nome: "Nano", 
         url: "https://pbs.twimg.com/media/FE1cXvTUcAMvkmI?format=jpg&name=360x360"),

  Pessoa(nome: "Komari", 
         url: "https://pbs.twimg.com/media/FE1LhE0XEAUDCap?format=jpg&name=small"),

  ];

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text('Kindacode.com'),
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
            color: Colors.blueGrey,
            elevation: 8,
            borderRadius: BorderRadius.circular(28),
            clipBehavior: Clip.antiAliasWithSaveLayer,
            
            child: InkWell(
              splashColor: Colors.black26,
              onTap: (){
                if(index == 0){
                          Navigator.of(context).push(MaterialPageRoute(
                              builder: (context) => GamePage1()));
                }else if(index == 1){
                          Navigator.of(context).push(MaterialPageRoute(
                              builder: (context) => GamePage2()));
                }else if(index == 2){
                          Navigator.of(context).push(MaterialPageRoute(
                              builder: (context) => GamePage3()));
                }else if(index == 3){
                          Navigator.of(context).push(MaterialPageRoute(
                              builder: (context) => GamePage4()));
               }else{
                 Navigator.of(context).push(MaterialPageRoute(
                              builder: (context) => GamePage1()));
               }

              },
              child: Column(
                mainAxisSize: MainAxisSize.min,
                children: [
                  Expanded(child:
                  Ink.image(
                    image: NetworkImage("${pessoas[index].url}"),
                    height: 200,
                    width: 200,
                    fit: BoxFit.cover,
                  ),
                ),
                  const SizedBox(height: 6),
                  Text(
                    "${pessoas[index].nome}",
                    style: TextStyle(fontSize: 16, color: Colors.white),
                  ),
                  const SizedBox(height: 3),
                ]
                
            ),
          ),
        ),      
      );
            }),
      ),
    );
  }
}

