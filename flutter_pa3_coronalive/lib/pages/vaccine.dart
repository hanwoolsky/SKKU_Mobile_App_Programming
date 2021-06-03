import 'package:flutter/material.dart';
import 'package:provider/provider.dart';
import 'dart:async';
import 'dart:convert';
import 'package:http/http.dart' as http;
import 'package:fl_chart/fl_chart.dart';
import 'package:flutter_pa3_coronalive/global.dart' as global;

Future<Album> fetchAlbum() async {
  final response = await http.get((Uri.http('raw.githubusercontent.com','owid/covid-19-data/master/public/data/vaccinations/vaccinations.json')));
  if (response.statusCode == 200) {
    int country = jsonDecode(response.body).length;
    int totalVacc = 0;
    //List<int> totalVaccList = [0, 0, 0, 0, 0, 0, 0];
    int totalFullyVacc = 0;
    //List<int> totalFullyVaccList = [0, 0, 0, 0, 0, 0, 0];
    int dailyVacc = 0;
    //List<int> dailyVaccList = [0, 0, 0, 0, 0, 0, 0];
    for(int i = 0; i < country; i++){
      int datalength = jsonDecode(response.body)[i]['data'].length;
      if(jsonDecode(response.body)[i]['data'][datalength-1]['total_vaccinations'] == null){
        if(jsonDecode(response.body)[i]['data'][datalength-1]['people_vaccinated'] != null) totalVacc += jsonDecode(response.body)[i]['data'][datalength-1]['people_vaccinated'];
        else if (jsonDecode(response.body)[i]['data'][datalength-1]['people_fully_vaccinated'] != null) totalVacc += jsonDecode(response.body)[i]['data'][datalength-1]['people_fully_vaccinated'];
        else totalVacc += 0;
      }
      else totalVacc += jsonDecode(response.body)[i]['data'][datalength-1]['total_vaccinations'];

      if(jsonDecode(response.body)[i]['data'][datalength-1]['people_fully_vaccinated'] == null) {
        if(datalength < 2 || jsonDecode(response.body)[i]['data'][datalength-2]['people_fully_vaccinated'] == null) totalFullyVacc += 0;
        else totalFullyVacc += jsonDecode(response.body)[i]['data'][datalength-2]['people_fully_vaccinated'];
      }
      else totalFullyVacc += jsonDecode(response.body)[i]['data'][datalength-1]['people_fully_vaccinated'];

      if(jsonDecode(response.body)[i]['data'][datalength-1]['daily_vaccinations'] == null) {
        if(datalength < 2 || jsonDecode(response.body)[i]['data'][datalength-2]['daily_vaccinations'] == null) dailyVacc += 0;
        else dailyVacc += jsonDecode(response.body)[i]['data'][datalength-2]['daily_vaccinations'];
      }
      else dailyVacc += jsonDecode(response.body)[i]['data'][datalength-1]['daily_vaccinations'];
    }
    global.totalVacc = totalVacc;
    global.totalFullyVacc = totalFullyVacc;
    global.dailyVacc = dailyVacc;
    return Album.fromJson(jsonDecode(response.body)[0]);
  } else {
    throw Exception('Failed to load album');
  }
}

class Album {
  final String date;
  final int totalVaccine;
  final int dailyVaccine;
  final double totalFullyVaccine;
  Album({@required this.date, @required this.totalVaccine, @required this.dailyVaccine, @required this.totalFullyVaccine});

  factory Album.fromJson(Map<String, dynamic> json) {
    var data = json['data'];
    return Album(
      date: data[data.length-1]['date'],
      totalVaccine: data[data.length-1]['total_vaccinations'],
      totalFullyVaccine: data[data.length-1]['people_fully_vaccinated_per_hundred'],
      dailyVaccine: data[data.length-1]['daily_vaccinations'],
    );
  }
}

class Vaccine extends StatelessWidget {
  final Map<String, String> arguments;
  Vaccine(this.arguments);
  Future<Album> futureAlbum = fetchAlbum();
  String parseDate = "";
  @override
  Widget build(BuildContext context) {
    VaccineCounterProvider vaccine = Provider.of<VaccineCounterProvider>(context);
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
                          Text("Total Vacc.          ", style: TextStyle(color: Colors.black, fontSize: 15)),
                          Text("Parsed latest date", style: TextStyle(color: Colors.black, fontSize: 15)),
                        ]
                    ),
                    FutureBuilder<Album>(
                      future: futureAlbum,
                      builder: (context, snapshot){
                        if(snapshot.hasData){
                          parseDate = "${snapshot.data.date}";
                          return Text("${global.totalVacc} people         ${snapshot.data.date}");
                        } else if(snapshot.hasError) {
                          return Text("${snapshot.error}");
                        }
                        return CircularProgressIndicator();
                      },
                    ),
                    Row(
                        mainAxisAlignment: MainAxisAlignment.center,
                        children: [
                          Text("\nTotal fully Vacc.           ", style: TextStyle(color: Colors.black, fontSize: 15)),
                          Text("\nDaily Vacc.", style: TextStyle(color: Colors.black, fontSize: 15)),
                        ]
                    ),
                    FutureBuilder<Album>(
                      future: futureAlbum,
                      builder: (context, snapshot){
                        if(snapshot.hasData){
                          return Text("${global.totalFullyVacc} people    ${global.dailyVacc} people");
                        } else if(snapshot.hasError) {
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
                      Consumer<VaccineCounterProvider>(
                        builder: (context, counter, child) => SizedBox(
                          width: 250,
                          height: 140,
                          child: LineChart(
                            LineChartData(
                              lineTouchData: LineTouchData(enabled: false),
                              lineBarsData: [
                                LineChartBarData(
                                  spots: [
                                    FlSpot(0, vaccine.graphNum.toDouble()),
                                    FlSpot(1, vaccine.graphNum.toDouble()),
                                    FlSpot(2, vaccine.graphNum.toDouble()),
                                    FlSpot(3, vaccine.graphNum.toDouble()),
                                    FlSpot(4, vaccine.graphNum.toDouble()),
                                    FlSpot(5, vaccine.graphNum.toDouble()),
                                    FlSpot(6, global.totalVacc.toDouble()*0.000000001)
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
                                          return '05-24';
                                        case 1:
                                          return '05-25';
                                        case 2:
                                          return '05-26';
                                        case 3:
                                          return '05-27';
                                        case 4:
                                          return '05-28';
                                        case 5:
                                          return '05-29';
                                        case 6:
                                          return parseDate.substring(5, 10);
                                        default:
                                          return '';
                                      }
                                    }),
                                leftTitles: SideTitles(
                                  showTitles: true,
                                  getTitles: (value) {
                                    return '${value+0.5}B';
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
                          TextButton(onPressed: () => vaccine._changeGraph(4), child: Text("Country_name", style: TextStyle(color: Colors.blue, fontSize: 15),)),
                          TextButton(onPressed: () => vaccine._changeGraph(5), child: Text("Total_vacc", style: TextStyle(color: Colors.blue, fontSize: 15),)),
                        ]
                    ),/*
                    Consumer<VaccineCounterProvider> (
                      builder: (context, counter, child) => Text(
                        '${vaccine.table}',
                        //style: Theme.of(context).textTheme.headline4,
                      ),
                    ),*/
                    Consumer<VaccineCounterProvider>(
                      builder: (context, counter, child) => SizedBox(
                        width: 200,
                        height: 150,
                        child: SingleChildScrollView(
                            scrollDirection: Axis.vertical,
                            child: DataTable(
                              columns: [
                                DataColumn(label: Text('Country')),
                                DataColumn(label: Text('total')),
                                DataColumn(label: Text('fully')),
                                DataColumn(label: Text('daily')),
                              ],
                              rows: [
                                DataRow(
                                  cells: [
                                    DataCell(Text('A1')),
                                    DataCell(Text('B1')),
                                    DataCell(Text('C1')),
                                    DataCell(Text('D1')),
                                  ]
                                ),
                                DataRow(
                                  cells: [
                                    DataCell(Text('A2')),
                                    DataCell(Text('B2')),
                                    DataCell(Text('C2')),
                                    DataCell(Text('D2')),
                                  ]
                                ),
                              ],
                            )
                        )
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
  int graphNum = 0;
  String _timagepath = "assets/images/olaf1.png";
  String Graph = "Graph1";
  String Table = "Table1";
  get graph => Graph;
  get table => Table;
  get order => graphNum;
  get timage => _timagepath;

  VaccineCounterProvider(this.count);
  void _changeGraph(int count) {
    switch (count) {
      case 0:
        graphNum = 1;
        Graph = "Graph1";
        break;
      case 1:
        graphNum = 2;
        Graph = "Graph2";
        break;
      case 2:
        graphNum = 3;
        Graph = "Graph3";
        break;
      case 3:
        graphNum = 4;
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