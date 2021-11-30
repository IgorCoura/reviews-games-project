import 'dart:convert';
import 'package:http/http.dart' as http;

class NetworkHelper {
  final String url;
  NetworkHelper({required this.url});

  Future getData() async {
    http.Response response = await http.get(Uri.parse(url));
    if (response.statusCode == 200) {
      //Resultado da requisição
      return jsonDecode(response.body);
    } else {
      print(response.statusCode);
      return null;
    }
  }
}