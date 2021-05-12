import 'dart:async';
import 'dart:convert';

import 'package:flutter/material.dart';
import 'package:http/http.dart' as http;

Future<Album> fetchAlbum() async {
  final response = await http.get(Uri.http('swopenapi.seoul.go.kr','api/subway/515a4d50756868773131386843626e52/json/realtimeStationArrival/0/5/성균관대'));
  if (response.statusCode == 200) {
    return Album.fromJson(jsonDecode(response.body));
  } else {
    throw Exception('Failed to load album');
  }
}

class Album {
  final int rowNum;
  final String subwayId;
  final String trainLineNm;
  final String subwayHeading;
  final String arvlMsg2;
  Album({@required this.rowNum, @required this.subwayId, @required this.trainLineNm, @required this.subwayHeading, @required this.arvlMsg2});

  factory Album.fromJson(Map<String, dynamic> json) {
    var list = json['realtimeArrivalList'];
    return Album(
      rowNum: list[0]['rowNum'],
      subwayId: list[0]['subwayId'],
      trainLineNm: list[0]['trainLineNm'],
      subwayHeading: list[0]['subwayHeading'],
      arvlMsg2: list[0]['arvlMsg2'],
    );
  }
}

void main() => runApp(MyApp());

class MyApp extends StatefulWidget {
  MyApp({Key key}) : super(key: key);
  @override
  _MyAppState createState() => _MyAppState();
}

class _MyAppState extends State<MyApp> {
  Future<Album> futureAlbum;
  int a = 0;
  @override
  void initState() {
    super.initState();
    futureAlbum = fetchAlbum();
  }

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
        title: 'Fetch Data Example',
        theme: ThemeData(
          primarySwatch: Colors.blue,
        ),
        home: Scaffold(
            appBar: AppBar(
              title: Text('Fetch Data Example'),
            ),
            body: Center(
              child: FutureBuilder<Album>(
                future: futureAlbum,
                builder: (context, snapshot){
                  if (snapshot.hasData){
                    return Text(
                        "rowNum : ${snapshot.data.rowNum}\n"
                        "subwayId : ${snapshot.data.subwayId}\n"
                        "trainLineNm : ${snapshot.data.trainLineNm}\n"
                        "subwayHeading : ${snapshot.data.subwayHeading}\n"
                        "arvlMsg2 : ${snapshot.data.arvlMsg2}");
                  } else if (snapshot.hasError) {
                    return Text("${snapshot.error}");
                  }
                  return CircularProgressIndicator();
                },
              ),
            ),
        ),
    );
  }
}
