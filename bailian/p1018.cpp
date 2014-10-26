#include <iostream>
#include <algorithm>
#include <cstring>
#include <climits>

#define MAX_DEV_NUM 10000

using namespace std;

class Device {
  public:
    int id;
    int bandwidth;
    int price;
};

bool cmp(const Device& a, const Device& b);

int main() {
  int t;
  cin >> t;

  for (int i = 0; i < t; i++) {
    int numOfDevs;
    cin >> numOfDevs;

    Device dev[MAX_DEV_NUM];
    int numOfOpts = 0;
    for (int j = 1; j <= numOfDevs; j++) {
      int b, p;
      int m;
      cin >> m;
      for (int cnt = 0; cnt < m; cnt++) {
        cin >> b >> p;

        dev[numOfOpts].id        = j;
        dev[numOfOpts].price     = p;
        dev[numOfOpts].bandwidth = b;

        numOfOpts++;
      }
    }

    sort(dev, dev + numOfOpts, cmp);

    int bandwidth, totalPrice;
    float result = 0.0, tmp;
    //memset(price, INT_MAX, sizeof(price));
    for (int i = 0; i <= numOfOpts - numOfDevs; i++) {
      int price[numOfDevs];
      memset(price, CHAR_MAX, sizeof(price));

      //bool isVisited[numOfDevs];
      //memset(isVisited, false, sizeof(isVisited));
      bandwidth = dev[i].bandwidth;
      price[dev[i].id - 1] = dev[i].price;
      //isVisited[dev[i].id - 1] = true;

      for (int j = i + 1; j <= numOfOpts - 1; j++) {

        if (dev[j].id == dev[i].id)
          continue;

        if (price[dev[j].id - 1] > dev[j].price) {
          price[dev[j].id - 1] = dev[j].price;
          //isVisited[dev[j].id - 1] = isVisited[dev[j].id - 1] || true;
        }
      }

      /*
      bool isAllVisited = true;
      for (int i = 0; i < numOfDevs; i++) {
        if (!isVisited[i]) {
          isAllVisited = false;
          break;
        }
      }

      if (!isAllVisited)
        tmp = 0.0;
      else {
        for (int i = 0; i < numOfDevs; i++)
          totalPrice += price[i];

        tmp = bandwidth * 1.0 / totalPrice;
      }
      */

      for (int i = 0; i < numOfDevs; i++)
        totalPrice += price[i];

      tmp = bandwidth * 1.0 / totalPrice;

      if (tmp > result) {
        for (int i = 0; i < numOfDevs; i++)
          cout << price[i] << " ";
        cout << endl;
        cout << bandwidth << endl;
        result = tmp;
      }
    }

    cout << result << endl;

    /*
    for (int i = 0; i < numOfOpts; i++)
      cout << dev[i].bandwidth << "\t" << dev[i].price << "\t" 
        << dev[i].id << endl;
    */

  }
}

bool cmp(const Device& a, const Device& b) {
  if (a.bandwidth != b.bandwidth)
    return a.bandwidth < b.bandwidth;
  else if (a.price != b.price)
    return a.price < b.price;
  else
    return a.id < b.id;
}
