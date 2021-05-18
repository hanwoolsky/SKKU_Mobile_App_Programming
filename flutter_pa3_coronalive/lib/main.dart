import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_pa3_coronalive/pages/page1.dart';
import 'package:flutter_pa3_coronalive/pages/menu.dart';
import 'package:flutter_pa3_coronalive/pages/casedeath.dart';
import 'package:flutter_pa3_coronalive/pages/vaccine.dart';
import 'package:provider/provider.dart';


void main() {
  runApp(MyApp());
}

class MyApp extends StatelessWidget {
  // This widget is the root of your application.
  @override
  Widget build(BuildContext context) {
    return MultiProvider(
        providers: [
          ChangeNotifierProvider(create: (context) => Page1CounterProvider(0)),
          ChangeNotifierProvider(create: (context) => MenuCounterProvider(0)),
          ChangeNotifierProvider(create: (context) => CaseDeathCounterProvider(0)),
          ChangeNotifierProvider(create: (context) => VaccineCounterProvider(0)),
        ],
        child: MaterialApp(
            title: '2018310478 HuhHanWool',
            theme: ThemeData(
              primarySwatch: Colors.blue,
            ),
            initialRoute: '/',
            onGenerateRoute: (routerSettings) {
              switch(routerSettings.name){
                case '/':
                  return MaterialPageRoute(builder: (_) => MyHomePage(title: "2018310478 HuhHanWool"));
                case '/page1.dart':
                  return MaterialPageRoute(builder: (_) => Page1((routerSettings.arguments)));
                case '/menu.dart':
                  return MaterialPageRoute(builder: (_) => Menu((routerSettings.arguments)));
                case '/casedeath.dart':
                  return MaterialPageRoute(builder: (_) => CaseDeath((routerSettings.arguments)));
                case '/vaccine.dart':
                  return MaterialPageRoute(builder: (_) => Vaccine((routerSettings.arguments)));
                default:
                  return MaterialPageRoute(builder: (_) => MyHomePage(title: "Error Unknown Route!"));
              }
            }
        )
    );
  }
}

class MyHomePage extends StatelessWidget {
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
  Widget build(BuildContext context) {
    final Page1CounterProvider counter = Provider.of<Page1CounterProvider>(context);
    final MenuCounterProvider menu = Provider.of<MenuCounterProvider>(context);
    final CaseDeathCounterProvider casedeath = Provider.of<CaseDeathCounterProvider>(context);
    final VaccineCounterProvider vaccine = Provider.of<VaccineCounterProvider>(context);

    String _id = "skku";
    String _password = "1234";
    final idController = TextEditingController();
    final pwController = TextEditingController();

    void _login(){
      if(idController.text == _id && pwController.text == _password){
        Navigator.pushNamed(
          context,
          '/page1.dart',
          arguments: {
            "user-msg1": "CORONA LIVE",
            "user-msg2": "Login Success. Hello ${_id}!!",
          },
        );
      }
    }
    return Scaffold(
      appBar: AppBar(
        // Here we take the value from the MyHomePage object that was created by
        // the App.build method, and use it to set our appbar title.
        title: Text(title),
      ),
      body: Center(
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
            Text('CORONA LIVE', style: TextStyle(
              color: Colors.blueGrey,
              fontSize: 30,
            )),
            Text('Login Please...', style: TextStyle(
              color: Colors.grey,
              fontSize: 15,
            )),
            Container(
              margin: EdgeInsets.only(top: 50.0),
              height: 150,
              width: 250,
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
                      Text("ID: ", style: Theme.of(context).textTheme.headline6),
                      SizedBox(
                        width: 150,
                        child: TextField(
                          controller: idController,
                        ),
                      ),
                    ]
                  ),
                  Row(
                    mainAxisAlignment: MainAxisAlignment.center,
                    children: [
                      Text("PW: ", style: Theme.of(context).textTheme.headline6),
                      SizedBox(
                        width: 150,
                        child: TextField(
                          controller: pwController,
                        ),
                      ),
                    ]
                  ),
                  ElevatedButton(onPressed: _login, child: Text("Login")),
                ],
              ),
            ),
          ],
        ),
      ), // This trailing comma makes auto-formatting nicer for build methods.
    );
  }
}