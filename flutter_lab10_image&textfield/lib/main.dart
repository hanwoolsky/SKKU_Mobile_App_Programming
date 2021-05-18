import 'package:flutter/material.dart';

void main() {
  runApp(MyApp());
}

class MyApp extends StatelessWidget {
  // This widget is the root of your application.
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Flutter Demo',
      theme: ThemeData(
        // This is the theme of your application.
        //
        // Try running your application with "flutter run". You'll see the
        // application has a blue toolbar. Then, without quitting the app, try
        // changing the primarySwatch below to Colors.green and then invoke
        // "hot reload" (press "r" in the console where you ran "flutter run",
        // or simply save your changes to "hot reload" in a Flutter IDE).
        // Notice that the counter didn't reset back to zero; the application
        // is not restarted.
        primarySwatch: Colors.blue,
      ),
      home: MyHomePage(title: '2018310478 HanwoolHuh'),
    );
  }
}

class MyHomePage extends StatefulWidget {
  MyHomePage({Key key, this.title}) : super(key: key);

  // This widget is the home page of your application. It is stateful, meaning
  // that it has a State object (defined below) that contains fields that affect
  // how it looks.

  // This class is the configuration for the state. It holds the values (in this
  // case the title) provided by the parent (in this case the App widget) and
  // used by the build method of the State. Fields in a Widget subclass are
  // always marked "final".

  final String title;

  @override
  _MyHomePageState createState() => _MyHomePageState();
}

class _MyHomePageState extends State<MyHomePage> {
  String _nameSave = "hanwool";
  final nameController = TextEditingController();

  String _emailSave = "hhw0925@naver.com";
  final emailController = TextEditingController();

  String _password = "qwer1234";
  final pwController = TextEditingController();

  int _count = 0;
  String _imagepath = "assets/images/olaf1.png";

  void _changeName(){
    setState(() {
      if(pwController.text == _password){
        _nameSave = nameController.text;
      }
    });
  }

  void _changeEmail(){
      setState(() {
        if(pwController.text == _password){
          _emailSave = emailController.text;
        }
      });
  }

  void _changeImage(){
    setState(() {
      if(pwController.text == _password) {
        _count = (_count + 1) % 5;
        switch (_count) {
          case 0:
            _imagepath = "assets/images/olaf1.png";
            break;
          case 1:
            _imagepath = "assets/images/olaf2.png";
            break;
          case 2:
            _imagepath = "assets/images/olaf3.png";
            break;
          case 3:
            _imagepath = "assets/images/olaf4.png";
            break;
          case 4:
            _imagepath = "assets/images/olaf5.png";
            break;
        }
      }
    });
  }

  @override
  Widget build(BuildContext context) {
    // This method is rerun every time setState is called, for instance as done
    // by the _incrementCounter method above.
    //
    // The Flutter framework has been optimized to make rerunning build methods
    // fast, so that you can just rebuild anything that needs updating rather
    // than having to individually change instances of widgets.
    return Scaffold(
      appBar: AppBar(
        // Here we take the value from the MyHomePage object that was created by
        // the App.build method, and use it to set our appbar title.
        title: Text(widget.title),
      ),
      body: Center(
        // Center is a layout widget. It takes a single child and positions it
        // in the middle of the parent.
        child: Column(
          // Column is also a layout widget. It takes a list of children and
          // arranges them vertically. By default, it sizes itself to fit its
          // children horizontally, and tries to be as tall as its parent.
          //
          // Invoke "debug painting" (press "p" in the console, choose the
          // "Toggle Debug Paint" action from the Flutter Inspector in Android
          // Studio, or the "Toggle Debug Paint" command in Visual Studio Code)
          // to see the wireframe for each widget.
          //
          // Column has various properties to control how it sizes itself and
          // how it positions its children. Here we use mainAxisAlignment to
          // center the children vertically; the main axis here is the vertical
          // axis because Columns are vertical (the cross axis would be
          // horizontal).
          mainAxisAlignment: MainAxisAlignment.center,
          children: <Widget>[
            Text(
              'Profile Image',
            ),
            Row(
                mainAxisAlignment: MainAxisAlignment.center,
                children: [
                  Text("Name : "),
                  Text(
                    '$_nameSave',
                    style: Theme.of(context).textTheme.headline6,
                  ),
                ]
            ),
            Row(
                mainAxisAlignment: MainAxisAlignment.center,
                children: [
                  Text("Email : "),
                  Text(
                    '$_emailSave',
                    style: Theme.of(context).textTheme.headline6,
                  ),
                ]
            ),
            SizedBox(
              width: 400,
              height: 150,
              child: Image.asset(_imagepath),
              ),
            Row(
                mainAxisAlignment: MainAxisAlignment.center,
                children: [
                  Text("Change Name: "),
                  SizedBox(
                    width: 150,
                    child: TextField(
                      controller: nameController,
                    ),
                  ),
                  ElevatedButton(onPressed: _changeName, child: Text("change Name")),
                ]
            ),
            Row(
                mainAxisAlignment: MainAxisAlignment.center,
                children: [
                  Text("Change Email: "),
                  SizedBox(
                    width: 150,
                    child: TextField(
                      controller: emailController,
                    ),
                  ),
                  ElevatedButton(onPressed: _changeEmail, child: Text("change Email")),
                ]
            ),
            TextButton(onPressed: _changeImage, child: Text("changeImage")),
            Row(
                mainAxisAlignment: MainAxisAlignment.center,
                children: [
                  Text("password: "),
                  SizedBox(
                    width: 150,
                    child: TextField(
                      controller: pwController,
                    ),
                  ),
                ]
            ),
          ]
        ),
      ),
      floatingActionButton: FloatingActionButton(
        tooltip: 'Increment',
        child: Icon(Icons.add),
      ), // This trailing comma makes auto-formatting nicer for build methods.
    );
  }
}
