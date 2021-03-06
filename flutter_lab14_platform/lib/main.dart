import 'dart:async';

import 'package:flutter/material.dart';
import 'package:flutter/services.dart';

void main() {
  runApp(MyApp());
}

const eventChannel = const EventChannel("com.example/Accel");

class AccelerometerEvent{
  final double x;
  final double y;
  final double z;
  AccelerometerEvent(this.x, this.y, this.z);
  @override
  String toString() => 'AccelerometerEvent(x: $x, y: $y, x: $z)';
}

Stream<AccelerometerEvent> _accelerometerEvents;

Stream<AccelerometerEvent> get accelerometerEventStream{
  Stream<AccelerometerEvent> accelerometerEvents = _accelerometerEvents;
  if(accelerometerEvents == null){
    accelerometerEvents = eventChannel.receiveBroadcastStream().map(
            (dynamic event) => AccelerometerEvent(event[0] as double, event[1] as double, event[2] as double)
    );
    _accelerometerEvents = accelerometerEvents;
  }
  return accelerometerEvents;
}


class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Flutter Demo',
      theme: ThemeData(
        primarySwatch: Colors.blue,
      ),
      home: MyHomePage(title: 'Flutter Demo Home Page'),
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
  String _batteryLevel = "default";
  List<String> _accelerometerValues;
  StreamSubscription<dynamic> _streamSubscription;

  @override
  void initState(){
    super.initState();
    _streamSubscription = accelerometerEventStream.listen((AccelerometerEvent event){
      setState((){
        _accelerometerValues = <String>[event.x.toStringAsFixed(1), event.y.toStringAsFixed(1), event.z.toStringAsFixed(1)];
      });
    });
  }

  @override
  void dispose(){
    super.dispose();
    _streamSubscription.cancel();
  }

  static const platform = const MethodChannel("com.example/Battery");
  Future<void> _getBatteryLevel() async {
    String batteryLevel;
    final int result = await platform.invokeMethod("getBatteryLevel");
    setState(() {
      _batteryLevel = 'Battery level is $result %.';
    });
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text(widget.title),
      ),
      body: Center(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: <Widget>[
            Text(
              'Rotation Vector: $_accelerometerValues',
            ),
            Text(
              '$_batteryLevel',
            ),
            ElevatedButton(onPressed: _getBatteryLevel, child: Text("get battery Level"))
          ],
        ),
      ),
    );
  }
}