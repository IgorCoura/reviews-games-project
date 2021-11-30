class GameJson {
  int? id;
  String? name;
  String? summary;
  String? developer;
  String? genre;
  int? score;
  String? img;
  String? release;
  String? consoles;

  GameJson(
      {this.id,
      this.name,
      this.summary,
      this.developer,
      this.genre,
      this.score,
      this.img,
      this.release,
      this.consoles});

  GameJson.fromJson(Map<String, dynamic> json) {
    id = json['id'];
    name = json['name'];
    summary = json['summary'];
    developer = json['developer'];
    genre = json['genre'];
    score = json['score'];
    img = json['img'];
    release = json['release'];
    consoles = json['consoles'];
  }
}
