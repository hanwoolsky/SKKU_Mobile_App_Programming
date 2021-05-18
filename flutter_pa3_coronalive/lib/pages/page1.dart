import 'package:flutter/material.dart';
import 'package:provider/provider.dart';

class Page1 extends StatelessWidget {
  final Map<String, String> arguments;
  Page1(this.arguments);

  String _imagepath = "assets/images/worldmap.png";

  @override
  Widget build(BuildContext context) {
    Page1CounterProvider counter = Provider.of<Page1CounterProvider>(context);

    void _start(){
      Navigator.pushNamed(
        context,
        '/menu.dart',
      );
    }

    return Scaffold(
      appBar: AppBar(
        title: Text("2018310478 HuhHanWool"),
      ),
      body: Center(
        child:Column(
            mainAxisAlignment: MainAxisAlignment.center,
            children: <Widget>[
              Text(arguments["user-msg1"], style: TextStyle(
                color: Colors.blueGrey,
                fontSize: 30,
              )),
              Text(arguments["user-msg2"], style: TextStyle(
                color: Colors.blue,
                fontSize: 20,
              )),
              SizedBox(
                width: 350,
                height: 300,
                child: Image.asset(_imagepath),
              ),
              ElevatedButton(onPressed: _start, child: Text("Start CORONA LIVE")),
            ]
        ),
      ),
    );
  }
}

class Page1CounterProvider with ChangeNotifier {
  int _counter;
  get counter => _counter;

  Page1CounterProvider(this._counter);
  void incrementCounter(){
    _counter++;
    notifyListeners();
  }
}