import 'package:flutter/material.dart';
import 'package:http/http.dart' as http;
import 'dart:async';
import 'dart:convert';
import 'package:flutter/foundation.dart';

void main() {
  runApp(MyApp());
}

Future<List<Photo>> fetchPhoto(http.Client client) async{
  final response = await client.get(Uri.parse('https://jsonplaceholder.typicode.com/photos'));

  return compute(parsePhotos, response.body);
}

List<Photo> parsePhotos(String responseBody){
  final parsed = json.decode(responseBody).cast<Map<String, dynamic>>();
  return parsed.map<Photo>((json) => Photo.fromJson(json)).toList();
}

class Photo{
  final int albumId;
  final int id;
  final String title;
  final String url;
  final String thumbnailUrl;

  Photo({@required this.albumId, @required this.id, @required this.title, @required this.url, @required this.thumbnailUrl});
  factory Photo.fromJson(Map<String, dynamic> json){
    return Photo(
      albumId: json['albumId'] as int,
      id: json['id'] as int,
      title: json['title'] as String,
      url: json['url'] as String,
      thumbnailUrl: json['thumbnailUrl'] as String,
    );
  }
}

class MyApp extends StatelessWidget {
  // This widget is the root of your application.
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Week14_prac',
      theme: ThemeData(
        primarySwatch: Colors.blue,
      ),
      home: MyHomePage(title: '2018310478_HuhHanwool'),
    );
  }
}

class MyHomePage extends StatefulWidget {
  MyHomePage({Key key, this.title}) : super(key: key);
  final String title;

  @override
  _MyHomePageState createState() => _MyHomePageState();
}

class _MyHomePageState extends State<MyHomePage> {
  int _counter = 0;

  void _incrementCounter() {
    setState(() {
      _counter++;
    });
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text(widget.title),
      ),
      body: SingleChildScrollView(
          child: Column(
              children: <Widget>[
                TextButton(onPressed: _incrementCounter, child: Text("Http request button: " + '$_counter' + " clicked")),
                FutureBuilder<List<Photo>>(
                  future: fetchPhoto(http.Client()),
                  builder: (context, snapshot) {
                    if (snapshot.hasError) print(snapshot.error);
                    return snapshot.hasData ? PhotosList(photos: snapshot.data, count: _counter,) : Center(child: CircularProgressIndicator());
                  },
                ),
            ],
          ),
      ),
    );
  }
}

class PhotosList extends StatelessWidget {
  final List<Photo> photos;
  final int count;
  PhotosList({Key key, @required this.photos, @required this.count}) : super(key: key);

  @override
  Widget build(BuildContext context){
    return GridView.builder(
      scrollDirection: Axis.vertical,
      shrinkWrap: true,
      physics: NeverScrollableScrollPhysics(),
      gridDelegate: SliverGridDelegateWithFixedCrossAxisCount(
        crossAxisCount: 2,
      ),
      itemCount: count*count,
      itemBuilder: (context, index) {
        return Image.network(photos[index].thumbnailUrl);
      },
    );
  }
}