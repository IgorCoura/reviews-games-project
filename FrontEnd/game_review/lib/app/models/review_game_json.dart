// To parse this JSON data, do
//
//     final reviewGameJson = reviewGameJsonFromJson(jsonString);

import 'dart:convert';

ReviewGameJson reviewGameJsonFromJson(String str) => ReviewGameJson.fromJson(json.decode(str));

class ReviewGameJson {
    ReviewGameJson({
        this.id,
        this.name,
        this.summary,
        this.developer,
        this.genre,
        this.score,
        this.img,
        this.release,
        this.consoles,
        this.reviews,
    });

    int? id;
    String? name;
    String? summary;
    String? developer;
    String? genre;
    int? score;
    String? img;
    String? release;
    String? consoles;
    List<Review>? reviews;

    factory ReviewGameJson.fromJson(Map<String, dynamic> json) => ReviewGameJson(
        id: json["id"],
        name: json["name"],
        summary: json["summary"],
        developer: json["developer"],
        genre: json["genre"],
        score: json["score"],
        img: json["img"],
        release: json["release"],
        consoles: json["consoles"],
        reviews: List<Review>.from(json["reviews"].map((x) => Review.fromJson(x))),
    );

}

class Review {
    Review({
        this.id,
        this.review,
        this.score,
        this.date,
        this.user,
    });

    int? id;
    String? review;
    int? score;
    String? date;
    User? user;

    factory Review.fromJson(Map<String, dynamic> json) => Review(
        id: json["id"],
        review: json["review"],
        score: json["score"],
        date: json["date"],
        user: User.fromJson(json["user"]),
    );

}

class User {
    User({
        this.id,
        this.name,
        this.email,
    });

    int? id;
    String? name;
    String? email;

    factory User.fromJson(Map<String, dynamic> json) => User(
        id: json["id"],
        name: json["name"],
        email: json["email"],
    );
}
