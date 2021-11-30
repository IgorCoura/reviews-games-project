import 'package:flutter/material.dart';
import 'package:game_review/app/models/network_helper.dart';
import 'package:game_review/app/models/review_game_json.dart';


class ReviewPage2 extends StatefulWidget {
  const ReviewPage2({ Key? key }) : super(key: key);

  @override
  State<ReviewPage2> createState() => _ReviewPage2State();
}

class _ReviewPage2State extends State<ReviewPage2> {
  List<Review> reviews = [];
  var _firstPress = true;
  

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
        title: const Text("Reviews"),),
      body: ListView.builder(
        itemCount: reviews.length,
        itemBuilder: (context, itemIndex){
          return ListTile(
            title: Text(
              reviews[itemIndex].review!,
              style: TextStyle(
                fontSize: 24,
                color: Colors.cyan.shade100
              ),
            ),
            
            leading: const Icon(
              Icons.account_circle,
              size: 40.0,
              ),

            subtitle: Text(
              "${reviews[itemIndex].user!.name}",
              style: TextStyle(
                fontSize: 18,
                color: Colors.blueGrey.shade200,
              ),
            ),
          ); 
        },
      ),  
      floatingActionButton: FloatingActionButton.extended(
        onPressed: () async {
          if(_firstPress){
            _firstPress = false ;
          NetworkHelper helper = NetworkHelper(url:"http://localhost:8080/games/3");
          ReviewGameJson randomUsers = ReviewGameJson.fromJson(await helper.getData());
          //Colocar mais usuários
          randomUsers.reviews!.forEach((element) {
          reviews.add(
              Review(
              review: element.review!,
              user: element.user! , 
              )
            );
          }
        );
          setState(() {});
          }
        },
        label: const Text("Load All Reviews for this game"),
        icon: const Icon(Icons.sort,
        size: 40.0,),
        backgroundColor: Colors.cyan.shade100,
        ),     
      )
    );
  }
}
