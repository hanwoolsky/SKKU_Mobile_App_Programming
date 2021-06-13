import 'package:flutter/material.dart';
import 'package:provider/provider.dart';
import 'package:fl_chart/fl_chart.dart';
import 'dart:convert';
import 'package:flutter/foundation.dart';
import 'package:http/http.dart' as http;

Future<Album> fetchAlbum() async {
  final response = await http.get(Uri.https('covid.ourworldindata.org', 'data/owid-covid-data.json'));
  if (response.statusCode == 200) {
    return Album.fromJson(jsonDecode(response.body));
  } else {
    throw Exception('Failed to load album');
  }
}

class Album {
  final String date;
  final double totalCases;
  final double totalDeaths;
  final double newCases;
  Album({@required this.date, @required this.totalCases, @required this.totalDeaths, @required this.newCases});

  factory Album.fromJson(Map<String, dynamic> json) {
    var list = json['KOR']['data'];

    return Album(
      date: list[list.length-1]['date'],
      totalCases: list[list.length-1]['total_cases'],
      totalDeaths: list[list.length-1]['total_deaths'],
      newCases: list[list.length-1]['new_cases'],
    );
  }
}

class CaseDeath extends StatelessWidget {
  final Map<String, String> arguments;
  CaseDeath(this.arguments);
  Future<Album> futureAlbum = fetchAlbum();

  @override
  Widget build(BuildContext context) {
    CaseDeathCounterProvider casedeath = Provider.of<CaseDeathCounterProvider>(context);
    void _menu(){
      Navigator.pushNamed(
        context,
        '/menu.dart',
        arguments: {
          "user-msg1": arguments["user-msg1"],
        },
      );
    }
    return Scaffold(
      body: Center(
        child:Column(
            mainAxisAlignment: MainAxisAlignment.center,
            children: <Widget>[
              Text("Case/Death Page"),
              Container(
                margin: EdgeInsets.only(top: 30.0),
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
                          Text("Total Cases          ", style: TextStyle(color: Colors.black, fontSize: 15)),
                          Text("Parsed latest date", style: TextStyle(color: Colors.black, fontSize: 15)),
                        ]
                    ),
                    FutureBuilder<Album>(
                      future: futureAlbum,
                      builder: (context, snapshot){
                        if(snapshot.hasData){
                          return Text("0 people         ${snapshot.data.date}");
                        } else if(snapshot.hasError) {
                          return Text("${snapshot.error}");
                        }
                        return CircularProgressIndicator();
                      },
                    ),
                    Row(
                        mainAxisAlignment: MainAxisAlignment.center,
                        children: [
                          Text("\nTotal Deaths          ", style: TextStyle(color: Colors.black, fontSize: 15)),
                          Text("\nDaily Cases", style: TextStyle(color: Colors.black, fontSize: 15)),
                        ]
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
                          TextButton(onPressed: () => casedeath._changeGraph(0), child: Text("Graph1", style: TextStyle(color: Colors.blue, fontSize: 15),)),
                          TextButton(onPressed: () => casedeath._changeGraph(1), child: Text("Graph2", style: TextStyle(color: Colors.blue, fontSize: 15),)),
                          TextButton(onPressed: () => casedeath._changeGraph(2), child: Text("Graph3", style: TextStyle(color: Colors.blue, fontSize: 15),)),
                          TextButton(onPressed: () => casedeath._changeGraph(3), child: Text("Graph4", style: TextStyle(color: Colors.blue, fontSize: 15),)),
                        ]
                    ),
                    Consumer<CaseDeathCounterProvider> (
                      builder: (context, counter, child) => Text(
                        '${casedeath.graph}',
                      ),
                    ),
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
                                FlSpot(1, 8),
                                FlSpot(2, 2),
                                FlSpot(3, 4),
                                FlSpot(4, 8),
                                FlSpot(5, 1),
                              ],
                              isCurved: true,
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
                          TextButton(onPressed: () => casedeath._changeGraph(4), child: Text("Table1", style: TextStyle(color: Colors.blue, fontSize: 15),)),
                          TextButton(onPressed: () => casedeath._changeGraph(5), child: Text("Table2", style: TextStyle(color: Colors.blue, fontSize: 15),)),
                        ]
                    ),
                    Consumer<CaseDeathCounterProvider> (
                      builder: (context, counter, child) => Text(
                        '${casedeath.table}',
                        //style: Theme.of(context).textTheme.headline4,
                      ),
                    ),
                    Consumer<CaseDeathCounterProvider>(
                      builder: (context, counter, child) => SizedBox(
                        width: 200,
                        height: 150,
                        child: Image.asset(casedeath.timage),
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

class CaseDeathCounterProvider with ChangeNotifier {
  int count = 0;
  String _imagepath = "assets/images/olaf1.png";
  String _timagepath = "assets/images/olaf1.png";
  String Graph = "Graph1";
  String Table = "Table1";
  get graph => Graph;
  get table => Table;
  get image => _imagepath;
  get timage => _timagepath;

  CaseDeathCounterProvider(this.count);
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