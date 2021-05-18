import 'package:flutter/material.dart';
import 'package:provider/provider.dart';
import 'dart:async';
import 'dart:convert';
import 'package:http/http.dart' as http;
import 'package:fl_chart/fl_chart.dart';

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

class Vaccine extends StatelessWidget {
  final Map<String, String> arguments;
  Vaccine(this.arguments);
  Future<Album> futureAlbum = fetchAlbum();

  @override
  Widget build(BuildContext context) {
    VaccineCounterProvider vaccine = Provider.of<VaccineCounterProvider>(context);
    void _menu(){
      Navigator.pushNamed(
        context,
        '/menu.dart',
      );
    }
    return Scaffold(
      body: Center(
        child:Column(
            mainAxisAlignment: MainAxisAlignment.center,
            children: <Widget>[
              Text("Vaccine Page"),
              Container(
                margin: EdgeInsets.only(top: 20.0),
                height: 110,
                width: 300,
                alignment: Alignment.center,
                decoration: BoxDecoration(
                    borderRadius: BorderRadius.all(Radius.circular(5.0)),
                    border: Border.all(
                      color: Colors.grey,
                      style: BorderStyle.solid,
                      width: 3,
                    )
                ),
                child: Column(
                  children: [
                    Row(
                        mainAxisAlignment: MainAxisAlignment.center,
                        children: [
                          Text("Total Vacc.", style: TextStyle(color: Colors.black, fontSize: 15)),
                          Text("Parsed latest date", style: TextStyle(color: Colors.black, fontSize: 15)),
                        ]
                    ),
                    FutureBuilder<Album>(
                      future: futureAlbum,
                      builder: (context, snapshot){
                        if (snapshot.hasData){
                          return Text( "${snapshot.data.rowNum} people           ${snapshot.data.subwayId}");
                        } else if (snapshot.hasError) {
                          return Text("${snapshot.error}");
                        }
                        return CircularProgressIndicator();
                      },
                    ),
                    Row(
                        mainAxisAlignment: MainAxisAlignment.center,
                        children: [
                          Text("Total fully Vacc.", style: TextStyle(color: Colors.black, fontSize: 15)),
                          Text("Daily Vacc.", style: TextStyle(color: Colors.black, fontSize: 15)),
                        ]
                    ),
                    FutureBuilder<Album>(
                      future: futureAlbum,
                      builder: (context, snapshot){
                        if (snapshot.hasData){
                          return Text(
                                  "${snapshot.data.trainLineNm} ${snapshot.data.arvlMsg2}");
                        } else if (snapshot.hasError) {
                          return Text("${snapshot.error}");
                        }
                        return CircularProgressIndicator();
                      },
                    ),
                  ],
                ),
              ),
              Container(
                margin: EdgeInsets.only(top: 20.0),
                height: 250,
                width: 300,
                alignment: Alignment.center,
                decoration: BoxDecoration(
                    borderRadius: BorderRadius.all(Radius.circular(5.0)),
                    border: Border.all(
                      color: Colors.grey,
                      style: BorderStyle.solid,
                      width: 3,
                    )
                ),
                child: Column(
                    children: [
                      Row(
                          mainAxisAlignment: MainAxisAlignment.center,
                          children: [
                            TextButton(onPressed: () => vaccine._changeGraph(0), child: Text("Graph1", style: TextStyle(color: Colors.blue, fontSize: 15),)),
                            TextButton(onPressed: () => vaccine._changeGraph(1), child: Text("Graph2", style: TextStyle(color: Colors.blue, fontSize: 15),)),
                            TextButton(onPressed: () => vaccine._changeGraph(2), child: Text("Graph3", style: TextStyle(color: Colors.blue, fontSize: 15),)),
                            TextButton(onPressed: () => vaccine._changeGraph(3), child: Text("Graph4", style: TextStyle(color: Colors.blue, fontSize: 15),)),
                          ]
                      ),
                      Consumer<VaccineCounterProvider> (
                        builder: (context, counter, child) => Text(
                          '${vaccine.graph}',
                          //style: Theme.of(context).textTheme.headline4,
                        ),
                      ),
                      /*consumer<VaccineCounterProvider>(
                        builder: (context, counter, child) => SizedBox(
                          width: 200,
                          height: 150,
                          child: Image.asset(vaccine.image),
                        ),
                      ),*/
                      SizedBox(
                          width: 250,
                          height: 140,
                          child: LineChart(
                            LineChartData(
                              lineTouchData: LineTouchData(enabled: false),
                              lineBarsData: [
                                LineChartBarData(
                                  spots: [
                                    FlSpot(0, 1),
                                    FlSpot(1, 2),
                                    FlSpot(2, 3),
                                    FlSpot(3, 4),
                                    FlSpot(4, 5),
                                    FlSpot(5, 6),
                                  ],
                                  isCurved: false,
                                barWidth: 2,
                                  colors: [
                                    Colors.blue,
                                  ],
                                  dotData: FlDotData(
                                    show: true,
                                  ),
                                ),
                              ],
                              minY: 0,
                              titlesData: FlTitlesData(
                                bottomTitles: SideTitles(
                                    showTitles: true,
                                    getTextStyles: (value) => const TextStyle(
                                        fontSize: 10, color: Colors.black, fontWeight: FontWeight.bold),
                                    getTitles: (value) {
                                      switch (value.toInt()) {
                                        case 0:
                                          return '5/2';
                                        case 1:
                                          return '5/3';
                                        case 2:
                                          return '5/4';
                                        case 3:
                                          return '5/5';
                                        case 4:
                                          return '5/6';
                                        case 5:
                                          return '5/7';
                                        case 6:
                                          return '5/8';
                                        default:
                                          return '';
                                      }
                                    }),
                                leftTitles: SideTitles(
                                  showTitles: true,
                                  getTitles: (value) {
                                    return '${value + 0.1}B';
                                  },
                                ),
                              ),
                              gridData: FlGridData(
                                show: true,
                                checkToShowHorizontalLine: (double value) {
                                  return value == 1 || value == 2 || value == 3 || value == 4 || value == 5 || value == 6 || value == 7;
                                  },
                              ),
                            ),
                          ),
                      ),
                  ]
                ),
              ),
              Container(
                margin: EdgeInsets.only(top: 20.0),
                height: 250,
                width: 300,
                alignment: Alignment.center,
                decoration: BoxDecoration(
                    borderRadius: BorderRadius.all(Radius.circular(5.0)),
                    border: Border.all(
                      color: Colors.grey,
                      style: BorderStyle.solid,
                      width: 3,
                    )
                ),
                child: Column(
                  children: [
                    Row(
                        mainAxisAlignment: MainAxisAlignment.center,
                        children: [
                          TextButton(onPressed: () => vaccine._changeGraph(4), child: Text("Table1", style: TextStyle(color: Colors.blue, fontSize: 15),)),
                          TextButton(onPressed: () => vaccine._changeGraph(5), child: Text("Table2", style: TextStyle(color: Colors.blue, fontSize: 15),)),
                        ]
                    ),
                    Consumer<VaccineCounterProvider> (
                      builder: (context, counter, child) => Text(
                        '${vaccine.table}',
                        //style: Theme.of(context).textTheme.headline4,
                      ),
                    ),
                    Consumer<VaccineCounterProvider>(
                      builder: (context, counter, child) => SizedBox(
                        width: 200,
                        height: 150,
                        child: Image.asset(vaccine.timage),
                      ),
                    ),
                  ],
                ),
              ),
            ]
        ),
      ),
      floatingActionButton: FloatingActionButton(
        onPressed: () => _menu(),
        child: Icon(Icons.list),
      ),
    );
  }
}

class VaccineCounterProvider with ChangeNotifier {
  int count = 0;
  String _imagepath = "assets/images/olaf1.png";
  String _timagepath = "assets/images/olaf1.png";
  String Graph = "Graph1";
  String Table = "Table1";
  get graph => Graph;
  get table => Table;
  get image => _imagepath;
  get timage => _timagepath;

  VaccineCounterProvider(this.count);
  void _changeGraph(int count) {
    switch (count) {
      case 0:
        _imagepath = "assets/images/olaf1.png";
        Graph = "Graph1";
        break;
      case 1:
        _imagepath = "assets/images/olaf2.png";
        Graph = "Graph2";
        break;
      case 2:
        _imagepath = "assets/images/olaf3.png";
        Graph = "Graph3";
        break;
      case 3:
        _imagepath = "assets/images/olaf4.png";
        Graph = "Graph4";
        break;
      case 4:
        _timagepath = "assets/images/olaf1.png";
        Table = "Table1";
        break;
      case 5:
        _timagepath = "assets/images/olaf2.png";
        Table = "Table2";
        break;
    }
    notifyListeners();
  }
}