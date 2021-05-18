import 'package:flutter/material.dart';
import 'package:provider/provider.dart';
import 'package:flutter_pa3_coronalive/pages/casedeath.dart';
import 'package:flutter_pa3_coronalive/pages/vaccine.dart';

class Menu extends StatelessWidget {
  final Map<String, String> arguments;
  Menu(this.arguments);

  @override
  Widget build(BuildContext context) {
    MenuCounterProvider menu = Provider.of<MenuCounterProvider>(context);
    CaseDeathCounterProvider casedeath = Provider.of<CaseDeathCounterProvider>(context);
    VaccineCounterProvider vaccine = Provider.of<VaccineCounterProvider>(context);

    void _case(){
      menu.incrementCounter(1);
      Navigator.pushNamed(
        context,
        '/casedeath.dart',
      );
    }

    void _vac(){
      menu.incrementCounter(2);
      Navigator.pushNamed(
        context,
        '/vaccine.dart',
      );
    }

    return Scaffold(
      appBar: AppBar(
        title: Text("Menu"),
      ),
      body: ListView(
          children: <Widget>[
            ListTile(
              leading: Icon(Icons.coronavirus_outlined),
              title: Text("Cases/Deaths"),
              onTap: _case,
            ),
            ListTile(
              leading: Icon(Icons.local_hospital),
              title: Text("Vaccine"),
              onTap: _vac,
            ),
            Container(
              height: 400,
            ),
            Row(
              mainAxisAlignment: MainAxisAlignment.center,
              children: [
                Text("Welcome! skku"),
              ]
            ),
            Row(
                mainAxisAlignment: MainAxisAlignment.center,
                children: [
                  Text("Previous: ${menu.previous}", style: TextStyle(color: Colors.blueGrey, fontSize: 15)),
                ]
            ),
          ]
      ),
    );
  }
}

class MenuCounterProvider with ChangeNotifier {
  String _prevpage = "Login Page";
  int count = 0;
  get previous => _prevpage;

  MenuCounterProvider(this.count);
  void incrementCounter(int count){
    if(count == 0) _prevpage = "Login Page";
    else if(count == 1) _prevpage = "Cases/Deaths Page";
    else if(count == 2) _prevpage = "Vaccine Page";
    notifyListeners();
  }
}